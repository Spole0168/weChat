package com.zhenjinzi.yzy.action.template;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.yzy.action.BaseAction;
import com.zhenjinzi.yzy.exception.BuildHtmlException;
import com.zhenjinzi.yzy.model.ZunmiContent;
import com.zhenjinzi.yzy.model.ZunmiNode;
import com.zhenjinzi.yzy.service.TemplateService;
import com.zhenjinzi.yzy.service.ZunmiContentService;
import com.zhenjinzi.yzy.service.ZunmiNodeService;

@Controller
public class CreateHtmlAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private ZunmiContentService contentService;
	@Resource
	private TemplateService templateService;
	@Resource
	private ZunmiNodeService nodeService;
	private ZunmiContent content;
	private ZunmiNode node;
	private Integer contentId;
	private Integer nodeId;

	public void createIndex() {
		try {
			templateService.createIndex(httpSession.getServletContext()
					.getRealPath("/"), request.getContextPath());
			Struts2Utils
					.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"navTabId\":\"contentBox\"}");
		} catch (BuildHtmlException e) {
			Struts2Utils
					.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败\"}");
			logger.info(e.getMessage());
		}
	}

	public void createNewsChannel() {
		try {
			templateService.createChannel(httpSession.getServletContext()
					.getRealPath("/"), "channel.ftl", nodeId, request
					.getContextPath());
			Struts2Utils
					.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\",\"navTabId\":\"contentBox\"}");
		} catch (BuildHtmlException e) {
			Struts2Utils
					.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败\"}");
			logger.info(e.getMessage());
		}
	}

	public String nodelist() {
		String channelCreationList;
		try {
			channelCreationList = nodeService.getChannelCreationList(request
					.getContextPath());
			request.setAttribute("channelCreationList", channelCreationList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "chlist";
	}

	public void createContentList() {
		try {
			templateService.createContentList(httpSession.getServletContext()
					.getRealPath("/"), "contentlist.ftl", nodeId, request
					.getContextPath());
			Struts2Utils
					.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\"}");
		} catch (BuildHtmlException e) {
			Struts2Utils
					.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败\"}");
			logger.info(e.getMessage());
		}
	}

	public void createContentByNodeId() {
		try {
			Struts2Utils.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\"}");
			final String path = httpSession.getServletContext().getRealPath("/");
			List<ZunmiContent> contentList = contentService.searchContentByNodeId(nodeId);
			final String contextPath = request.getContextPath();
			int cnt = 0;
			for (final ZunmiContent content : contentList) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							logger.debug(e.getMessage());
						}
						if (content == null) {
							Thread.currentThread().interrupt();
						}
						templateService.createContent(path, content.getId(),contextPath);
					}
				}).start();
				++cnt;
				if (cnt == 50) {
					cnt = 0;
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			Struts2Utils.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败\"}");
			logger.error(e.getMessage());
		}
	}

	public String nodelist2() {
		String contentCreationList;
		try {
			contentCreationList = nodeService.getContentCreationList(request
					.getContextPath());
			request.setAttribute("contentCreationList", contentCreationList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ch2list";
	}

	public void createSelectedContent() {
		String ids[] = request.getParameterValues("ids");
		try {

			String path = httpSession.getServletContext().getRealPath("/");
			for (int i = 0; i < ids.length; i++) {
				ZunmiContent content = contentService.findById(Integer
						.parseInt(ids[i]));
				templateService.createContent(path, content.getId(),
						request.getContextPath());
			}
			Struts2Utils
					.renderJson("{\"statusCode\":\"200\",\"message\":\"操作成功\"}");
		} catch (BuildHtmlException e) {
			Struts2Utils
					.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败\"}");
			logger.info(e.getMessage());
		} catch (Exception e) {
			Struts2Utils
					.renderJson("{\"statusCode\":\"300\",\"message\":\"操作失败\"}");
			logger.error(e.getMessage());
		}
	}

	// jsp生成静态页
	public String fuckNews() {
		// 获取应用路径
		String path = httpSession.getServletContext().getRealPath("/");

		content = contentService.findById(content.getId());
		Integer contentId = content.getId();
		String title = content.getTitle();
		String newsContent = content.getContent();
		node = content.getZunmiNode();

		// 控制标题长度
		String contentTitle;
		if (title.length() > 12) {
			contentTitle = title.substring(0, 11) + "......";
		} else {
			contentTitle = title;
		}

		// 读取模版路径
		String filePath = path + "WEB-INF/templates/" + "content.ftl";

		// 调用时间函数
		Calendar calendar = Calendar.getInstance();
		String filename = String.valueOf(calendar.getTimeInMillis());

		int line = 10;// 每页显示10行
		String arr[] = {};
		// 当分页标志即有</p>也有</br>时进行判断分页数
		if (newsContent.split("</p>").length <= 10
				&& newsContent.length() >= 700
				&& newsContent.split("</br>").length >= 10) {
			// 用</br>来计算分页的页数
			arr = newsContent.split("</br>");
		} else if (newsContent.split("</br>").length <= 10
				&& newsContent.length() >= 700
				&& newsContent.split("</p>").length >= 10) {
			// 用</p>来计算分页的页数
			arr = newsContent.split("</p>");
		} else {
			arr = newsContent.split("</p>");
		}

		// 将数组内容按照</p>进行分割
		int pagenum = arr.length / line;// 需要多少页来显示数据
		int length = arr.length;// 数组长度
		int other = arr.length % line;// 最后一页所剩的行数
		if (other != 0) {
			pagenum += 1;
		}

		/**
		 * 将静态页所在路径，静态页文件名称，静态页所属栏目ID存入数据库
		 */
		String staticfilename = filename + "_0.html";// 首页文件名
		String staticfilepath = "";// 首页路径
		String lp = filename + "_" + (pagenum - 1) + ".html";// 末页文件名

		/**
		 * 开始生成静态页面
		 */
		int q = 0;
		String page = "";
		int h = line;
		for (int k = 1; k <= pagenum; k++) {
			String a = "<a href=" + filename + "_" + (k - 1) + ".html>" + k
					+ "</a>";
			page = page + a;
		}

		String content = "";
		try {
			for (int j = 1; j <= pagenum; j++) {
				if ((j == pagenum) && (other != 0)) {
					// 最后一页
					// 取得剩余行数
					for (int n = q; n <= length - 1; n++) {
						// 循环取出数据
						content += arr[n];
					}
					// 替换模板页
					FileInputStream fileinputstream = new FileInputStream(
							filePath);// 读取模块文件
					int lenght = fileinputstream.available();
					byte bytes[] = new byte[lenght];
					fileinputstream.read(bytes);
					fileinputstream.close();
					String templateContent = new String(bytes);
					templateContent = templateContent.replaceAll(
							"###contentId###", contentId.toString());
					templateContent = templateContent.replaceAll(
							"###contentTitle###", contentTitle);
					templateContent = templateContent.replaceAll(
							"###contentContent###", content);
					templateContent = templateContent.replaceAll("###first###",
							staticfilename);
					templateContent = templateContent.replaceAll(
							"###number###", page);
					templateContent = templateContent.replaceAll("###last###",
							lp);
					String fileame_m = filename + "_" + (j - 1) + ".html";
					String file = path + "news/" + node.getId() + "/"
							+ fileame_m;// 生成html文件并存储绝对路径
					FileOutputStream fileoutputstream = new FileOutputStream(
							file);// 建立文件输出流
					byte tag_bytes[] = templateContent.getBytes("utf8");
					fileoutputstream.write(tag_bytes);
					fileoutputstream.close();
				} else {
					for (int m = q;;) {
						content += arr[m];
						m++;
						q = m;
						if (q >= h) {
							// 替换模板页
							FileInputStream fileinputstream = new FileInputStream(
									filePath);// 读取模块文件
							int lenght = fileinputstream.available();
							byte bytes[] = new byte[lenght];
							fileinputstream.read(bytes);
							fileinputstream.close();
							String templateContent = new String(bytes);
							templateContent = templateContent.replaceAll(
									"###contentId###", contentId.toString());
							templateContent = templateContent.replaceAll(
									"###contentTitle###", title);
							templateContent = templateContent.replaceAll(
									"###contentContent###", content);
							templateContent = templateContent.replaceAll(
									"###first###", staticfilename);
							templateContent = templateContent.replaceAll(
									"###number###", page);
							templateContent = templateContent.replaceAll(
									"###last###", lp);
							String fileame_m = filename + "_" + (j - 1)
									+ ".html";
							String file = path + "news/" + node.getId() + "/"
									+ fileame_m;// 生成html文件并存储绝对路径
							FileOutputStream fileoutputstream = new FileOutputStream(
									file);// 建立文件输出流
							byte tag_bytes[] = templateContent.getBytes("utf8");
							fileoutputstream.write(tag_bytes);
							fileoutputstream.close();
							h = line + h;
							break;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return "";
	}

	public ZunmiContent getContent() {
		return content;
	}

	public void setContent(ZunmiContent content) {
		this.content = content;
	}

	public ZunmiNode getNode() {
		return node;
	}

	public void setNode(ZunmiNode node) {
		this.node = node;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

}
