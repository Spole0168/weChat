package com.zhenjinzi.yzy.action.outeract;

import java.util.List;

import javax.annotation.Resource;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Sort;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyServiceApply;
import com.zhenjinzi.yzy.model.YzyUserinfo;
import com.zhenjinzi.yzy.service.UserinfoService;
//import com.zhenjinzi.yzy.service.YinzyWatermechineService;

/**
 * TODO
 * 提供对饮水机信息和用户信息的添加操作
 * @author ly
 *
 */
public class UserwatermechineAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	@Resource
	private UserinfoService userinfoService;
	
//	@Resource
//	private YinzyWatermechineService watermechineService;
	
	
	
	
	public String addMechine(){
		
		
		Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"修改成功!\",\"callbackType\":\"closeCurrent\"}");
		return null;
	}
	
	
	private Integer getIdByUserInfoId(){
		Search search = new Search();
		Sort sort=new Sort();
		sort.setProperty("id");
		sort.setDesc(true);
		search.addSort(sort);
		SearchResult<YzyUserinfo> searchResult = userinfoService.searchAndCount(search);
		if(searchResult == null){
			return new Integer(1);
		}
		List<YzyUserinfo> list = searchResult.getResult();
		if(list == null ||list.size()==0){
			return new Integer(1);
		}
		return list.get(0).getId()+1;
	}
	
}
