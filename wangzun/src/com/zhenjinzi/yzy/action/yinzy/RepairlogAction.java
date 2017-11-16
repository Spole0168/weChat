package com.zhenjinzi.yzy.action.yinzy;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyRepairlog;
import com.zhenjinzi.yzy.model.YzyUserinfo;
import com.zhenjinzi.yzy.service.RepairlogService;
import com.zhenjinzi.yzy.service.UserinfoService;

/**
 * 维修记录查看
 * @author ly
 *
 */
public class RepairlogAction extends BaseAction{

	@Resource
	private RepairlogService repairlogService;
	
	private List<YzyRepairlog> list;
	
	private YzyRepairlog rlog;
	
	private Integer serviceId;
	
	private Timestamp repairtime;
	
	@Override
	public String execute() throws Exception {
		SearchResult<YzyRepairlog> searchResult = repairlogService.findEnable(firstResult(),maxResult());
		if(searchResult == null){
			return "list";
		}
		list = searchResult.getResult();
		totalCount = searchResult.getTotalCount();
		return "list";
	}
	
	public String del(){
		YzyRepairlog log=null;
		if(serviceId!=null){
			log = repairlogService.findById(serviceId);
			if(log!=null){
				log.setText5("2");
				repairlogService.save(log);
				Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"删除成功!\",\"navTabId\":\"repairlog-list\"}");
				return null;
			}
		}
		Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"删除失败！\",\"callbackType\":\"closeCurrent\",\"navTabId\":\"repairlog-list\"}");
		return null;
	}
	
	public String search(){
		Search search = new Search();
//		if(community_name != null && community_name.length()>0){
//			search.addFilterLike("communityName", "%"+community_name+"%");
//		}
//		if(applyeName!= null && applyeName.length()>0){
//			search.addFilterLike("applyeName", "%"+applyeName+"%");
////			search.addFilterEqual("status", admin.getStatus());
//		}
		if(repairtime!=null ){
			long repair = repairtime.getTime();
			long onedays = 24*60*60*1000;
			Timestamp repairtimeafter= new Timestamp(repair+onedays);
			Timestamp repairtimebefore= new Timestamp(repair-onedays);
			search.addFilterGreaterOrEqual("repairtime", repairtimebefore);
			search.addFilterLessOrEqual("repairtime", repairtimeafter);
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
//			search.addFilterEqual("repairtime", repairtime);
		}
		search.addFilterEqual("text5", "1");
		search.setFirstResult((pageNum-1)*numPerPage);
		search.setMaxResults(pageNum*numPerPage);
//		search.
		SearchResult<YzyRepairlog> searchResult = repairlogService.searchAndCount(search);
		list = searchResult.getResult();
		totalCount = searchResult.getTotalCount();
		
		return "list";
	}
	
	public String show(){
		if(serviceId!=null&&serviceId>0){rlog = repairlogService.findById(serviceId);}
		return "show";
	}
	public List<YzyRepairlog> getList() {
		return list;
	}
	public void setList(List<YzyRepairlog> list) {
		this.list = list;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Timestamp getRepairtime() {
		return repairtime;
	}
	public void setRepairtime(Timestamp repairtime) {
		this.repairtime = repairtime;
	}
	public YzyRepairlog getRlog() {
		return rlog;
	}
	public void setRlog(YzyRepairlog rlog) {
		this.rlog = rlog;
	}
	
}
