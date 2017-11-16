package com.zhenjinzi.yzy.action.manager;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;

import org.springframework.stereotype.Controller;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.sun.imageio.plugins.common.InputStreamAdapter;
import com.zhenjinzi.util.DomainUtils;
import com.zhenjinzi.util.FileUtil;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.ZunmiDomain;
import com.zhenjinzi.yzy.model.ZunmiUser;
import com.zhenjinzi.yzy.service.ZunmiDomainService;
import com.zhenjinzi.yzy.service.ZunmiUserService;

@Controller
public class UserDomainAction extends BaseAction {

	/**
	 * 暂时没用上，方法已转移到com.wangzun.zunmi.action.domain.AddDomainAction.java上面。
	 */
	private static final long serialVersionUID = 1L;
	
	
}
