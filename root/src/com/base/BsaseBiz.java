package com.base;

import org.apache.log4j.Logger;

import com.exception.BusinessException;

public abstract class BsaseBiz {
	protected Logger logger = Logger.getLogger(this.getClass());
	protected BusinessException throwBusinessException(String message, Throwable cause) {
		
		return new BusinessException(message);
	}
}
