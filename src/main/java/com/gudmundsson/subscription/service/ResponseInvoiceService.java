package com.gudmundsson.subscription.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gudmundsson.subscription.core.Company;
import com.gudmundsson.subscription.core.Invoice;
import com.gudmundsson.subscription.core.ItemService;
import com.gudmundsson.subscription.core.Subscription;
import com.gudmundsson.subscription.dto.CustomerDto;
import com.gudmundsson.subscription.dto.InvoiceDto2;
import com.gudmundsson.subscription.dto.ItemServiceDto;
import com.gudmundsson.subscription.dto.ResponseInvoiceDto;
import com.gudmundsson.subscription.dto.core.Data;
import com.gudmundsson.subscription.util.exception.RepositoryException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ResponseInvoiceService {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ItemServiceService itemServiceService;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private SubscriptionService subscriptionService;

	public ResponseInvoiceDto getInvoiceDetailsA(Optional<Long> invoiceId, String sessionLogId)
			throws RepositoryException {

		ResponseInvoiceDto responseObject = new ResponseInvoiceDto();
		responseObject.setData(new Data());

//		Empezamos a settear el invoice con los datos que se require
		Invoice invoice = invoiceService.getInvoiceById(invoiceId);
		InvoiceDto2 invoiceDto2 = new InvoiceDto2();
		invoiceDto2.setBillingPeriod(invoice.getBillingPeriod());
		invoiceDto2.setIssueDate(invoice.getIssueDate());
		invoiceDto2.setSubscriptionId(invoice.getSubscription().getSubscriptionId());

//		Calcular el costo total de los servicios
		List<Subscription> subscriptions = subscriptionService.getSubscriptionsByInvoiceId(invoiceId);
		double totalCost = calculateTotalCost(subscriptions);
		invoice.setTotalAmount(totalCost);
		invoiceService.update(invoice);

		invoiceDto2.setTotalAmount(totalCost);

//		aca debo setear el monto total
		responseObject.getData().setInvoice(invoiceDto2);

//		Este "customer" es un Dto por si caso....
		CustomerDto customer = new CustomerDto();
		customer.setName(customerService.getCustomerByInvoiceId(invoiceId).getName());
		customer.setEmail(customerService.getCustomerByInvoiceId(invoiceId).getEmail());
		responseObject.getData().setCustomer(customer);

		List<Company> companies = companyService.getCompaniesByInvoiceId(invoiceId);

		for (Company company : companies) {
			List<ItemService> itemServicesxCompany = itemServiceService
					.getItemServicesByCompanyId(Optional.of(company.getCompanyId()));

			List<ItemServiceDto> itemServices = new ArrayList<>();

			for (ItemService itemService : itemServicesxCompany) {
				ItemServiceDto itemServiceDto = new ItemServiceDto();
				itemServiceDto.setName(itemService.getName());
				itemServiceDto.setCostHour(itemService.getCostHour());
				itemServiceDto.setDescription(itemService.getDescription());
				itemServiceDto.setCompanyId(itemService.getCompany().getCompanyId());
				itemServices.add(itemServiceDto);
			}
			company.setItemServiceDtos(itemServices);
		}

		responseObject.getData().setCompanies(companies);

		return responseObject;
	}

	public Double calculateTotalCost(List<Subscription> subscriptions) {

		double totalCost = 0.0;

		for (Subscription subscription : subscriptions) {
			if (subscription.getState()) {
				ItemService item = itemServiceService
						.getItemServiceBySubscriptionId(Optional.of(subscription.getSubscriptionId()));
				double costPerHour = item.getCostHour();
				double hoursUsed = subscription.getHoursUsed();
				totalCost += costPerHour * hoursUsed;
			}
		}
		return totalCost;
	}

	public ResponseInvoiceDto getInvoiceDetailsB(Optional<Long> customerId, Optional<Long> companyId,
			Optional<String> billingPeriod, String sessionLogId) throws RepositoryException {

		ResponseInvoiceDto responseObject = new ResponseInvoiceDto();
		responseObject.setData(new Data());

		Invoice invoice = invoiceService.getInvoiceByCustomerIdCompanyIdBillingPeriod(customerId, companyId,
				billingPeriod);
		InvoiceDto2 invoiceDto2 = new InvoiceDto2();
		invoiceDto2.setBillingPeriod(invoice.getBillingPeriod());
		invoiceDto2.setIssueDate(invoice.getIssueDate());
		invoiceDto2.setSubscriptionId(invoice.getSubscription().getSubscriptionId());

		List<Subscription> subscriptions = subscriptionService
				.getSubscriptionsByInvoiceId(Optional.of(invoice.getInvoiceId()));
		double totalCost = calculateTotalCost(subscriptions);
		invoice.setTotalAmount(totalCost);
		invoiceService.update(invoice);

		invoiceDto2.setTotalAmount(totalCost);

		responseObject.getData().setInvoice(invoiceDto2);

		CustomerDto customer = new CustomerDto();
		customer.setName(customerService.getCustomerByInvoiceId(Optional.of(invoice.getInvoiceId())).getName());
		customer.setEmail(customerService.getCustomerByInvoiceId(Optional.of(invoice.getInvoiceId())).getEmail());
		responseObject.getData().setCustomer(customer);

		List<Company> companies = companyService.getCompaniesByInvoiceId(Optional.of(invoice.getInvoiceId()));

		for (Company company : companies) {
			List<ItemService> itemServicesxCompany = itemServiceService
					.getItemServicesByCompanyId(Optional.of(company.getCompanyId()));

			List<ItemServiceDto> itemServiceDtos = new ArrayList<>();

			for (ItemService itemService : itemServicesxCompany) {
				ItemServiceDto itemServiceDto = new ItemServiceDto();
				itemServiceDto.setName(itemService.getName());
				itemServiceDto.setCostHour(itemService.getCostHour());
				itemServiceDto.setDescription(itemService.getDescription());
				itemServiceDto.setCompanyId(itemService.getCompany().getCompanyId());
				itemServiceDtos.add(itemServiceDto);
			}
			company.setItemServiceDtos(itemServiceDtos);
		}

		responseObject.getData().setCompanies(companies);

		return responseObject;

	}
	
	public void export(Optional<Long> invoiceId, HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		ResponseInvoiceDto responseA = getInvoiceDetailsA(invoiceId, null);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String dateString = dateFormat.format(responseA.getData().getInvoice().getIssueDate());
		
		long subscriptionId = responseA.getData().getInvoice().getSubscriptionId();
		String myStringSubscription = Long.toString(subscriptionId);
		
		Double total = responseA.getData().getInvoice().getTotalAmount();
		String totalAmount = Double.toString(total);
		
		List<Company> companies = responseA.getData().getCompanies();
		
		document.open();		
//		Conf del titulo
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);
		
		Paragraph paragraph = new Paragraph("Factura");
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
		fontParagraph.setSize(12);
		
		Paragraph paragraph1 = new Paragraph("Perido de Facturacion: ");
		paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
		Paragraph paragraph2 = new Paragraph(responseA.getData().getInvoice().getBillingPeriod());
		paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
		
		Paragraph paragraph3 = new Paragraph("Fecha de Emision: ");
		paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
		Paragraph paragraph4 = new Paragraph(dateString);
		paragraph4.setAlignment(Paragraph.ALIGN_LEFT);
		
		Paragraph paragraph5 = new Paragraph("N° de Suscripcion: ");
		paragraph5.setAlignment(Paragraph.ALIGN_LEFT);
		Paragraph paragraph6 = new Paragraph(myStringSubscription);
		paragraph6.setAlignment(Paragraph.ALIGN_LEFT);
		
		Paragraph paragraph7 = new Paragraph("TOTAL : ");
		paragraph5.setAlignment(Paragraph.ALIGN_LEFT);
		Paragraph paragraph8 = new Paragraph(totalAmount);
		paragraph6.setAlignment(Paragraph.ALIGN_LEFT);
		
		Paragraph paragraph9 = new Paragraph("Cliente: ");
		paragraph9.setAlignment(Paragraph.ALIGN_LEFT);
		Paragraph paragraph10 = new Paragraph(responseA.getData().getCustomer().getName());
		paragraph10.setAlignment(Paragraph.ALIGN_LEFT);
		
		Paragraph paragraph11 = new Paragraph("Email: ");
		paragraph11.setAlignment(Paragraph.ALIGN_LEFT);
		Paragraph paragraph12 = new Paragraph(responseA.getData().getCustomer().getEmail());
		paragraph12.setAlignment(Paragraph.ALIGN_LEFT);
		
		Paragraph paragraph13 = new Paragraph("Cod. Compania: ");
		paragraph13.setAlignment(Paragraph.ALIGN_LEFT);
		
		document.add(paragraph);
		document.add(paragraph1);
		document.add(paragraph2);
		document.add(paragraph3);
		document.add(paragraph4);
		document.add(paragraph5);
		document.add(paragraph6);
		document.add(paragraph7);
		document.add(paragraph8);
		document.add(paragraph9);
		document.add(paragraph10);
		document.add(paragraph11);
		document.add(paragraph12);
		document.add(paragraph13);
		
		for (Company company : companies) {
			Paragraph paragraph14 = new Paragraph(Long.toString(company.getCompanyId()));
			paragraph14.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(paragraph14);
			
			Paragraph paragraph15 = new Paragraph("Nombre de la Compania:");
			paragraph15.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(paragraph15);
			
			Paragraph paragraph16 = new Paragraph(company.getName());
			paragraph16.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(paragraph16);
			
			Paragraph paragraph17 = new Paragraph("Direccion de la Compañina:");
			paragraph17.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(paragraph17);
			
			Paragraph paragraph18 = new Paragraph(company.getAddress());
			paragraph18.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(paragraph18);
			
			List<ItemService> itemServicesxCompany = itemServiceService
					.getItemServicesByCompanyId(Optional.of(company.getCompanyId()));
				
			List<ItemServiceDto> itemServiceDtos = new ArrayList<>();

			for (ItemService itemService : itemServicesxCompany) {
				ItemServiceDto itemServiceDto = new ItemServiceDto();
				itemServiceDto.setName(itemService.getName());
				itemServiceDto.setCostHour(itemService.getCostHour());
				itemServiceDto.setDescription(itemService.getDescription());
				itemServiceDto.setCompanyId(itemService.getCompany().getCompanyId());
				itemServiceDtos.add(itemServiceDto);
			}
			company.setItemServiceDtos(itemServiceDtos);
			
		}
				
					
		

			
		
		document.close();
		
	}

}
