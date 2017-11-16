package com.zhenjinzi.yzy.action.outeract;

import java.sql.Timestamp;
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

/***
 * 维修记录 整理 
 * @author ly
 *
 */
public class RepairlogouterAction extends BaseAction{

	@Resource
	private RepairlogService repairlogService;
	@Resource
	private UserinfoService userinfoService;
	
	private String machine_id;
	//repairdate（当天时间），
	private String fuwuyuan_id;
	private String tds_in;
	private String tds_out;
	private String scale;//水垢，
	private String texture;//口感，
	private String smell;//异味，
	private String[] whats;//维修内容
	
	@Override
	public String execute() throws Exception {/**返回到测试页面*/
		return "input";
	}
	
	public String save(){
//		private String text5;// varchar(300)              20160313统一用来做 逻辑删除   1 代表正常   2代表删除
//	    private YzyUserinfo waterMachine;//	  machineid	 int(11)unsigned,
		String retMsg="";
		
		Search search = new Search();
		if(machine_id!=null ){
			search.addFilterEqual("machineid", machine_id);
		}
		SearchResult<YzyUserinfo> searchResult = userinfoService.searchAndCount(search);
		if(searchResult==null){
			  retMsg="饮水机id为"+machine_id+"，无此饮水机！";
		      Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\""+retMsg+"\"}");
		      return null;
		}
		List<YzyUserinfo> list = searchResult.getResult();
		if(list==null || list.size() == 0){
			  retMsg="饮水机id为"+machine_id+"，无此饮水机！";
			  Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\""+retMsg+"\"}");
			  return null;
		}else{
			YzyUserinfo user=list.get(0);
			YzyRepairlog repairlog=new YzyRepairlog();
			repairlog.setWaterMachine(user);
			repairlog.setServiceuserid(fuwuyuan_id);//服务人员工号
			repairlog.setWatertds(tds_in);
			repairlog.setOutputtds(tds_out);
			
			user.setInputtds(tds_in);
			user.setOutputtds(tds_out);
			
			repairlog.setScaleflag(scale);
			repairlog.setRepairtime(new Timestamp(System.currentTimeMillis()));
			repairlog.setText1(texture);
			repairlog.setText2(smell);
			
//			private String[] whats;//维修内容
			StringBuffer sb= new StringBuffer();
			if(whats!=null &&whats.length>0){
				for(int i=0;i<whats.length;i++){
					sb.append((i+1)).append(whats[i]).append("：");
				}
				repairlog.setServicepoint(sb.substring(0, sb.length()-1));
			}
			repairlog.setText5("1");
			repairlog.setText4(machine_id);//2016-03-28
			repairlogService.save(repairlog);
			userinfoService.save(user);/**更新 user 用户表中的 tds_in和tds_out值*/
		}
		 Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"维修记录，维护成功！\"}");
		return null;
	}

	public String getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}
	public String getFuwuyuan_id() {
		return fuwuyuan_id;
	}
	public void setFuwuyuan_id(String fuwuyuan_id) {
		this.fuwuyuan_id = fuwuyuan_id;
	}
	public String getTds_in() {
		return tds_in;
	}
	public void setTds_in(String tds_in) {
		this.tds_in = tds_in;
	}

	public String getTds_out() {
		return tds_out;
	}

	public void setTds_out(String tds_out) {
		this.tds_out = tds_out;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public String getSmell() {
		return smell;
	}

	public void setSmell(String smell) {
		this.smell = smell;
	}

	public String[] getWhats() {
		return whats;
	}

	public void setWhats(String[] whats) {
		this.whats = whats;
	}
	
	
}
