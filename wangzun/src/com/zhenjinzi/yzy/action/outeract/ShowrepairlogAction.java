package com.zhenjinzi.yzy.action.outeract;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fire.modules.web.struts2.Struts2Utils;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.model.YzyAppoitmentcheck;
import com.zhenjinzi.yzy.model.YzyRepairlog;
import com.zhenjinzi.yzy.model.YzyUserinfo;
import com.zhenjinzi.yzy.service.UserinfoService;

/***
 * 展示 维修记录页面
 * @author ly
 *
 */
public class ShowrepairlogAction extends BaseAction{

	@Resource
	private UserinfoService userinfoService;
	
	private String machine_id;
	@Override
	public String execute() throws Exception {/**返回到测试页面*/
		return "input";
	}
	public String getrepairlog(){
		Search search = new Search();
		if(machine_id!=null ){
			search.addFilterEqual("machineid", machine_id);
		}
		
		SearchResult<YzyUserinfo> searchResult = userinfoService.searchAndCount(search);
		String retMsg=null;
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
			YzyUserinfo user= list.get(0);
			Set<YzyRepairlog> repairlogs=user.getYzyRepairlogs();
			if(repairlogs==null && repairlogs.size()==0){
				  retMsg="饮水机id为"+machine_id+"，无维修记录！";
				  Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\""+retMsg+"\"}");
				  return null;
			}else{
				List<Map<String,String>>  mm=getMapByBean(repairlogs);
				JSONArray json = new JSONArray(mm);
				String str= json.toString();
				System.out.println(str);
				Struts2Utils.renderJson(str);
			}
		}
		return null;
	}
	private  List<Map<String, String>> getMapByBean(
			Set<YzyRepairlog> list) {
		List<Map<String,String>> retList=new ArrayList<Map<String,String>>();
		if(list!=null && list.size()>0){
			for (YzyRepairlog temp : list) {
				if(temp.getText5()!=null&&temp.getText5().equals("1")){
					Map<String,String> map=new HashMap<String, String>();
					map.put("id", temp.getId()+"");//维修时间
					map.put("repairdate", temp.getRepairtime()+"");//维修时间
					map.put("repaircontent", temp.getServicepoint());//维修内容
					map.put("tds_in",  temp.getWatertds());//tds_in
					map.put("tds_out",  temp.getOutputtds());//tds_out
					map.put("scale",  temp.getScaleflag());//scale
					map.put("texture",  temp.getText1());//texture
					map.put("smell",  temp.getText2());//smell
					retList.add(map);
				}
			}
		}
		return retList;
	}
	
	public String getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}
	
}
