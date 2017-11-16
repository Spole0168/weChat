package com.zhenjinzi.yzy.service;

import com.zhenjinzi.yzy.model.ZunmiUserLog;

public interface ZunmiUserLogService {
	public ZunmiUserLog getLastLoginLog(Integer userid);
	public boolean save(ZunmiUserLog userLog);
}
