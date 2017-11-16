package aaa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.ibm.icu.util.Output;

public class Oneprice {
	
	public  static void  main(String[] args) throws FileNotFoundException{
	String s2="<tr><td><a href=\"http://www.";//domainname
	String s3="\" target=\"_blank\" title=\"尝试访问此域名\">";//domainname
	String s4="</a></td><td>";//含义
	String s5="</td><td>￥";
	String s6="</td><td>未售</td></tr> ";
	
	InputStream io=new FileInputStream(new File("src/域名列表1.txt"));
	BufferedReader br=new BufferedReader(new InputStreamReader(io));
	ArrayList<String> list=new ArrayList<String>();
	String str=null;
	try {
		while((str=br.readLine())!=null){
			list.add(str);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	Collections.sort(list);
	for (String string : list) {
//		System.out.println(string);
//		for (String string2 : strs) {
//			System.out.println(string2);
//		}
		
	}
	String qianse="qianse",shense="shense";
	ArrayList<String> objStrs=new ArrayList<String>();
	for(int i=0;i<list.size();i++){
		String obj=null;
		String[] strs=list.get(i).split("	");
		ArrayList<String> sd=new ArrayList<String>();
		for (int j=0;j<strs.length;j++) {
			if((!strs[j].equals("	"))||(!strs[j].equals(" "))||(!strs[j].equals(""))){
				sd.add(strs[j]);
			}
		}
//		Iterator<String> it=sd.iterator();
//		while(it.hasNext()){
//			String its=it.next();
//			if(its.equals("")){
//				it.remove();
//			}
//		}
//		for (String string : sd) {
//			System.out.println(string);
//		}
//		if(sd.size()==6){
//			System.out.println("0:"+sd.get(0)+"   ;1:"+sd.get(1)+"      5:"+sd.get(5)+"ssssssssssssi:"+i);
//		}else if(sd.size()==3){
//			System.out.println(" *******************************************************333333");
//			System.out.println("0:"+sd.get(0)+"   ;1:"+sd.get(1)+"      5:"+sd.get(2)+"ssssssssssssi:"+i);
//		}
//		System.out.println(sd.size());
		    Ykj ykj=new Ykj();
			if(sd.size()==6){
//				obj=s2+sd.get(0)+s3+sd.get(0)+s4+sd.get(1)+s5+sd.get(5)+s6;
				ykj.setDomainname(sd.get(0));
				ykj.setMeaning(sd.get(1));
				ykj.setPrice(sd.get(5));
			}else if(sd.size()==3){
//				obj=s2+sd.get(0)+s3+sd.get(0)+s4+sd.get(1)+s5+sd.get(2)+s6;
				ykj.setDomainname(sd.get(0));
				ykj.setMeaning(sd.get(1));
				ykj.setPrice(sd.get(2));
			}
			//objStrs.add(obj);
			Db_onePrice.getInsert(ykj);
	}
//	for (int k=400;k<792;k++) {
//		System.out.println(objStrs.get(k));
//	}
//	OutputStream os=new FileOutputStream(new File(""));
//	os.w
	
//	String s="360qp.cn	360qp				160";
//	String[] strs=s.split("	");
//	System.out.println(s.indexOf(""));
//	for (String string : strs) {
//		
//		System.out.println(string);
//	}
	
	
	
}


}
