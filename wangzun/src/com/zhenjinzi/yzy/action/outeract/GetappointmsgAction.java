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
import com.zhenjinzi.yzy.service.AppoitmentcheckService;
import com.zhenjinzi.yzy.service.UserinfoService;

/***
 * 预约检测记录   未调试
 * @author ly
 *
 */
public class GetappointmsgAction extends BaseAction{
	
	@Resource
	private AppoitmentcheckService appoitmentcheckService; 
	@Resource
	private UserinfoService userinfoService;
	private String machine_id;
	
	@Override
	public String execute() throws Exception {/**返回到测试页面*/
		return "input";
	}
	/**返回查询信息，支持模糊查询*/
	public String getappointmsg(){
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
//			List<Map<String,String>>  mm=getMapByBean(list);
//			JSONObject json = new JSONObject(list);
			YzyUserinfo info = list.get(0);//machineid name tel creat_date
			Set<YzyAppoitmentcheck> yzyAppoitmentchecks=info.getYzyAppoitmentchecks();
			if(yzyAppoitmentchecks==null || yzyAppoitmentchecks.size()==0){
				  retMsg="饮水机id为"+machine_id+"，预约检测信息！";
				  Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\""+retMsg+"\"}");
				  return null;
			}
			List<Map<String,String>>  mm=getMapByBean(yzyAppoitmentchecks);
			JSONArray json = new JSONArray(mm);
//			YzyAppoitmentcheck  checkInfo=new YzyAppoitmentcheck();
//			for (YzyAppoitmentcheck temp : yzyAppoitmentchecks) {
//				checkInfo.setText1(temp.getText1());//name
//				checkInfo.setText2(temp.getText2());// tel
//				checkInfo.setText3(temp.getText3());   //预约时间
//			}
			String machineid="";
			String creat_date="";
			if(info!=null){
				machineid=info.getMachineid();
				creat_date =info.getCreate_Date()+"";
			}
			 Struts2Utils.renderJson("{\"statusCode\":\"200\",\"machineid\":\""+machineid+"\",\"messages\":"+json.toString()+"}");
		}
		return null;
	}
	
	private  List<Map<String, String>> getMapByBean(
			Set<YzyAppoitmentcheck> list) {
		List<Map<String,String>> retList=new ArrayList<Map<String,String>>();
		if(list!=null && list.size()>0){
			for (YzyAppoitmentcheck temp : list) {
				if(temp.getText5()!=null&&temp.getText5().equals("1")){//
					Map<String,String> map=new HashMap<String, String>();
					map.put("id", temp.getId()+"");//ID 标识唯一性
					map.put("name", temp.getText1()+"");//维修时间
					map.put("telphone", temp.getText2());//维修内容
					map.put("appointdate", temp.getText3()+"");//预约时间时间
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
	
	public static void main(String[] args) {
//		List<YzyAppoitmentcheck> list=new ArrayList<YzyAppoitmentcheck>();
//		YzyAppoitmentcheck dd= new YzyAppoitmentcheck();
//		YzyUserinfo user=new YzyUserinfo();
//		user.setMachineid("aaa");
//		dd.setMechine(user);
//		dd.setText1("abc");
//		list.add(dd);
//		YzyAppoitmentcheck dd1= new YzyAppoitmentcheck();
//		YzyUserinfo user1=new YzyUserinfo();
//		user1.setMachineid("aaa");
//		dd1.setMechine(user);
//		dd1.setText1("abc");
//		list.add(dd1);
//		YzyAppoitmentcheck dd2= new YzyAppoitmentcheck();
//		YzyUserinfo user2=new YzyUserinfo();
//		user2.setMachineid("aaa");
//		dd2.setMechine(user);
//		dd2.setText1("abc");
//		list.add(dd2);
//		List<Map<String,String>>  mm=getMapByBean(list);
//		JSONArray array =new JSONArray(mm);
//		JSONObject json = new JSONObject(list);
//		System.out.println(json.toString());
//		System.out.println(array);		
	}
	private  List<Map<String, String>> getMapByBean(
			List<YzyAppoitmentcheck> list) {
		List<Map<String,String>> retList=new ArrayList<Map<String,String>>();
		if(list!=null && list.size()>0){
			for (YzyAppoitmentcheck temp : list) {
				Map<String,String> map=new HashMap<String, String>();
				YzyUserinfo user=temp.getUserInfo();
				if(user!=null){
				    map.put("mechine_id", user.getMachineid());//饮水机id
				}else{
					 map.put("mechine_id", "");//饮水机id
				}
				map.put("openid", temp.getOpenid());//openid
				map.put("applyDate", temp.getCreate_Date()+"");//申请时间
				map.put("name", temp.getText1());//姓名
				map.put("tel", temp.getText2());//申请时间
				map.put("appointTime", temp.getText3());//预约时间
				retList.add(map);
			}
		}
		return retList;
	}
}
