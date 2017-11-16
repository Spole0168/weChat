package com.base;

import org.apache.log4j.Logger;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	protected SqlSessionTemplate sqlSession;
	
	protected String nameSpace;
	
	
}
