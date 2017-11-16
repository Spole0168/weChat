package com.zhenjinzi.yzy.template;
//package com.wangzun.zunmi.template;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Calendar;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class StaticPageServlet extends HttpServlet{
//	private Connection conn;
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException
//	{
//	doPost(request,response);
//	}
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException
//	{
//	PrintWriter out = response.getWriter();
//	HttpSession session = request.getSession();
//	//System.out.println("**********project start***********");
//	String id = request.getParameter("static");
//	//System.out.println("The id is "+id);
//	int pageno = new Integer(request.getParameter("pageno")).intValue();
//	boolean commitflg = false;
//	try
//	{
//
//	//System.out.println("*************ready data out*********");
//	//建立到数据库的链接
////	this.conn = DBConnectionDAO.getConnection();
////	conn.setAutoCommit(false);
////	//实例化ContentTreleMysql()类
////	ContentTreleMysql contenttrelemysql = new ContentTreleMysql(conn);
////	NewsClassMysql newsclassmysql = new NewsClassMysql(conn);
//
//	NewsBean news = new NewsBean();
////	news = contenttrelemysql.FindNew(id);
//	//System.out.println("**********The For Cycle is start*********");
//	/**
//	* 读取数据库存储的新闻记录
//	*/
//	String newsid = news.getNews_id();
//	//System.out.println("---------The newsid is "+newsid+"-----------------");
//	String title = news.getNews_title();//读取新闻标题
//	String newssubtitle = news.getNews_subtitle();//读取新闻副标题
//	String newsauthor = news.getNews_author();//读取新闻作者姓名
//	String newsaddtime = news.getNews_addtime();//新闻添加时间
//	String newssource = news.getNews_source();//新闻采集地点
//	String newscontent = news.getNews_content();//新闻内容
//	String classid = news.getNews_classid();//新闻所属栏目ID
//	String newstemppath = news.getNews_newstemppath();//新闻模板路径
//	String newstempname = news.getNews_newstemplet();//读取新闻模文件名
//	String newsvideoflg = news.getNews_videoflg();
//	//System.out.println("The video flg is "+newsvideoflg);
//	int newslevel = news.getNews_level();//取得该新闻的栏目深度
//
//	//判断是否是视频新闻
//	String videourl = "";
//	String videodisplay = "";
//	if(newsvideoflg.equals("1")==true)
//	{
//	//该新闻是视频新闻
//	videodisplay = "block";
//	videourl = news.getNews_videosavepath()+news.getNews_videoname();
//	//System.out.println("The video url is "+videourl);
//	}
//	else
//	{
//	videodisplay = "none";
//	videourl = "";
//	}
//
//	//控制新闻标题的长度
//	String newstitle = "";
//	if(title.length()>=12)
//	{
//	newstitle = title.substring(0,11)+"......";
//	}
//	else
//	{
//	newstitle = title;
//	}
//
//
//	//根据newslevel来判断生成的静态页引用样式的路径
//	String path = "";String pathurl = "";
//	for(int i = 1;i<=newslevel;i++)
//	{
//	path = path + "../";
//	}
//	pathurl = path+"../";
//
//	//根据newslevel来判断
//	String newsclassid = "";String pareid = "";
//	newsclassid = news.getNews_classid();
//	pareid = newsclassid.substring(0,3);
//	//System.out.println("The pareid is "+pareid);
//	String pareclasscname = newsclassmysql.FindNewsClassCnameByClassID(pareid);
//	//System.out.println("The classcnname is "+classcname);
//	String classcnname = newsclassmysql.FindNewsClassCnameByClassID(newsclassid);
//
//	//初始化NewsClassBean
//	NewsClassBean newsclass = new NewsClassBean();
//	newsclass = contenttrelemysql.FindClassId(classid);
//	//String newsclass_enname = newsclass.getNewsclass_enname();//查询栏目英文名称
//	String newsclass_savefilepath = newsclass.getNewsclass_savefilepath();//查询新闻栏目保存的路径
//	String filepath = request.getRealPath("/")+newstemppath+newstempname;
//
//	String templateC;
//	//调用时间函数
//	Calendar calendar = Calendar.getInstance();
//	String filename = String.valueOf(calendar.getTimeInMillis());
//	/**
//	* 下面的程序用来对静态页面生成并分页
//	* 判断新闻记录种的</p>数量来分页，每页30行字符 
//	* 用allpagenum来判断页面大小
//	* 当pagenum取余数为0时则用整页显示
//	* 当pagenum取余数不为0时则用pagenum+1页显示
//	*/
//
//	int line = 10 ;//每页显示10行
//	String arr[] = {};
//	//当分页标志即有</p>也有</br>时进行判断分页数
//	if(newscontent.split("</p>").length<=10&&newscontent.length()>=700&&newscontent.split("</br>").length>=10)
//	{
//	//用</br>来计算分页的页数
//	arr = newscontent.split("</br>");
//	}
//	else
//	if(newscontent.split("</br>").length<=10&&newscontent.length()>=700&&newscontent.split("</p>").length>=10)
//	{
//	//用</p>来计算分页的页数
//	arr = newscontent.split("</p>");
//	}
//	else
//	{
//	arr = newscontent.split("</p>");
//	}
//
//	//将数组内容按照</p>进行分割
//	int pagenum =arr.length/line;//需要多少页来显示数据
//	int length =arr.length;//数组长度
//	int other = arr.length%line;//最后一页所剩的行数
//	if(other!=0)
//	{
//	pagenum+=1;
//	}
//	/**
//	* 将静态页所在路径，静态页文件名称，静态页所属栏目ID存入数据库
//	*/
//	String staticfilename = filename+"_0.htm";//首页文件名
//	String staticfilepath = newsclass_savefilepath;//首页路径
//	String lp = filename+"_"+(pagenum-1)+".htm";//末页文件名
//	news.setNews_staticpagepath(staticfilepath);
//	news.setNews_staticfilename(staticfilename);
//	news.setNews_staticpagenum(pagenum);
//	contenttrelemysql.AddStaticPage(news);
//	/**
//	* 开始生成静态页面
//	*/
//	int q = 0;
//	String c;
//	String page ="";
//	int h = line;
//	for(int k=1;k<=pagenum;k++)
//	{
//	String a = "<a href="+filename+"_"+(k-1)+".htm>"+k+"</a>";
//	page =page+a;
//	}
//	for(int j=1;j<=pagenum;j++)
//	{
//	if((j==pagenum)&&(other!=0))
//	{
//	//最后一页
//	//取得剩余行数
//	for(int n=q;n<=length-1;n++)
//	{
//	//System.out.println("The Q is "+q);
//	//循环取出数据
//	content+=arr[n];
//	}
//	//替换模板页
//	FileInputStream fileinputstream = new FileInputStream(filepath);//读取模块文件 
//	int lenght = fileinputstream.available();
//	byte bytes[] = new byte[lenght]; 
//	fileinputstream.read(bytes); 
//	fileinputstream.close(); 
//	templateContent = new String(bytes);
//	templateContent = templateContent.replaceAll("###path###", path);
//	templateContent = templateContent.replaceAll("###pathurl###", pathurl);
//	templateContent = templateContent.replaceAll("###newsId###", newsid);
//	templateContent = templateContent.replaceAll("###pareclassid###", pareid);
//	templateContent = templateContent.replaceAll("###pareclasscnname###", pareclasscname);
//	templateContent = templateContent.replaceAll("###newsclassid###", newsclassid);
//	templateContent = templateContent.replaceAll("###classcnname###", classcnname);
//	templateContent = templateContent.replaceAll("###newstitle###", newstitle);
//	templateContent = templateContent.replaceAll("###newssubtitle###", newssubtitle);
//	templateContent = templateContent.replaceAll("###newsauthor###", newsauthor);
//	templateContent = templateContent.replaceAll("###newsaddtime###", newsaddtime);
//	templateContent = templateContent.replaceAll("###newssource###", newssource);
//	templateContent = templateContent.replaceAll("###videodisplay###", videodisplay);
//	templateContent = templateContent.replaceAll("###videourl###", videourl);
//	templateContent = templateContent.replaceAll("###newscontent###", content);
//	templateContent = templateContent.replaceAll("###first###", staticfilename);
//	templateContent = templateContent.replaceAll("###number###", page);
//	templateContent = templateContent.replaceAll("###last###", lp);
//	String fileame_m = filename +"_"+(j-1)+".htm";
//	//System.out.println("The fileame is "+fileame);
//	String file = request.getRealPath("/")+newsclass_savefilepath+"/"+fileame_m;//生成html文件并存储绝对路径
//	//System.out.println("The file is "+file);
//	FileOutputStream fileoutputstream = new FileOutputStream(file);//建立文件输出流
//	byte tag_bytes[] = templateContent.getBytes(); 
//	fileoutputstream.write(tag_bytes); 
//	fileoutputstream.close();
//	//System.out.println("This is the ++++"+j+"++++"+content);
//	}
//	else
//	{
//
//	//System.out.println("h is "+h);
//	for(int m=q;;)
//	{
//	content+=arr[m];
//	m++;
//	q=m;
//
//	if(q>=h)
//	{
//	//替换模板页
//	FileInputStream fileinputstream = new FileInputStream(filepath);//读取模块文件 
//	int lenght = fileinputstream.available();
//	byte bytes[] = new byte[lenght]; 
//	fileinputstream.read(bytes); 
//	fileinputstream.close(); 
//	templateContent = new String(bytes); 
//	templateContent = templateContent.replaceAll("###path###", path);
//	templateContent = templateContent.replaceAll("###pathurl###", pathurl);
//	templateContent = templateContent.replaceAll("###newsId###", newsid);
//	templateContent = templateContent.replaceAll("###pareclassid###", pareid);
//	templateContent = templateContent.replaceAll("###pareclasscnname###", pareclasscname);
//	templateContent = templateContent.replaceAll("###newsclassid###", newsclassid);
//	templateContent = templateContent.replaceAll("###classcnname###", classcnname);
//	templateContent = templateContent.replaceAll("###newstitle###", newstitle);
//	templateContent = templateContent.replaceAll("###newssubtitle###", newssubtitle);
//	templateContent = templateContent.replaceAll("###newsauthor###", newsauthor);
//	templateContent = templateContent.replaceAll("###newsaddtime###", newsaddtime);
//	templateContent = templateContent.replaceAll("###newssource###", newssource);
//	templateContent = templateContent.replaceAll("###videodisplay###", videodisplay);
//	templateContent = templateContent.replaceAll("###videourl###", videourl);
//	templateContent = templateContent.replaceAll("###newscontent###", content);
//	templateContent = templateContent.replaceAll("###first###", staticfilename);
//	templateContent = templateContent.replaceAll("###number###", page);
//	templateContent = templateContent.replaceAll("###last###", lp);
//	String fileame_m = filename +"_"+(j-1)+".htm";
//	String file = request.getRealPath("/")+newsclass_savefilepath+"/"+fileame_m;//生成html文件并存储绝对路径
//	FileOutputStream fileoutputstream = new FileOutputStream(file);//建立文件输出流
//	byte tag_bytes[] = templateContent.getBytes(); 
//	fileoutputstream.write(tag_bytes); 
//	fileoutputstream.close();
//	c;
//	h=line+h;
//	break;
//	}
//
//	}
//	}
//	}
//	NewsMysql newsmysql = new NewsMysql(conn);
//	PageBean pagebean = new PageBean();
//	pagebean = newsmysql.FindNewsByPage(pageno, classid);
//	conn.commit();
//	session.setAttribute(SessionABC.NEWSPAGEINFO, pagebean);
//	response.sendRedirect("admin/admin_newsmang.jsp");
//	}
//	catch (Exception e)
//	{
//	out.print(e.getMessage());
//	}
//	finally
//	{
//	if (conn != null) 
//	{
//	try {
//	if (commitflg)
//	conn.rollback();
//	conn.close();
//	} 
//	catch (SQLException sqe) 
//	{
//	sqe.printStackTrace();
//	session.setAttribute("SessionABC.ERRORMSG", "数据库连接关闭时发生错误");
//	response.sendRedirect("admin/error.jsp");
//	}
//	}
//	}
//	}
//	}
//}
