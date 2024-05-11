package com.gudmundsson.subscription.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gudmundsson.subscription.core.User;
import com.gudmundsson.subscription.util.exception.RepositoryException;

@Mapper
public interface MUserMapper {

	public User getUserById(@Param("recordId") String recordId) throws RepositoryException;
	
	public void saveRecord(@Param("user") User user);
}
