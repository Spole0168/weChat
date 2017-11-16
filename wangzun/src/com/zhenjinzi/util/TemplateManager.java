package com.zhenjinzi.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.zhenjinzi.yzy.exception.BuildHtmlException;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateManager {
	public static Logger logger = Logger.getLogger(TemplateManager.class
			.getName());

	private static TemplateManager templateManager = null;
	private Configuration cfg = null;

	public TemplateManager() {
		// 设置freemarker的参数
		cfg = new Configuration();
		// setDirectoryForTemplateLoading("/template");
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		cfg.setDefaultEncoding("utf-8");
	}

	/**
	 * 取得模板处理的实例
	 * 
	 * @return templateManager 模板处理实例
	 */
	public synchronized static TemplateManager getInstance() {
		if (templateManager == null) {
			templateManager = new TemplateManager();
		}
		return templateManager;
	}

	/**
	 * 生成静态文件<br/>
	 * [概要]<br/>
	 * <p>
	 * 读取模板文件生成静态文件
	 * </p>
	 * 
	 * [详细]<br/>
	 * <ol>
	 * <li>取得模板文件</li>
	 * <li>设置生成文件路径</li>
	 * <li>载入objectMap中的内容生成文件</li>
	 * </ol>
	 * 
	 * @param templateFolder
	 *            模板相对于classpath的路径
	 * @param templateFileName
	 *            模版名称
	 * @param htmlFolder
	 *            要生成的静态文件的目录
	 * @param htmlFileName
	 *            要生成的文件名
	 * @param objectMap
	 *            模板中对象集合
	 * @return boolean 是否生成成功
	 */
	public void buildFile(String templateFolder, String templateFileName,
			String htmlFolder, String htmlFileName, Map objectMap)
			throws BuildHtmlException {
		this.buildFile(templateFolder, templateFileName,
				StringUtils.applyRelativePath(htmlFolder, htmlFileName),
				objectMap);
	}

	/**
	 * 生成静态文件<br/>
	 * [概要]<br/>
	 * <p>
	 * 读取模板文件生成静态文件
	 * </p>
	 * 
	 * [详细]<br/>
	 * <ol>
	 * <li>取得模板文件</li>
	 * <li>设置生成文件路径</li>
	 * <li>载入objectMap中的内容生成文件</li>
	 * </ol>
	 * 
	 * @param templateFolder
	 *            模板相对于classpath的路径
	 * @param templateFileName
	 *            模版名称
	 * @param htmlFilePath
	 *            要生成的静态文件的路径
	 * @param objectMap
	 *            模板中对象集合
	 * @return boolean 是否生成成功
	 */
	public void buildFile(String templateFolder, String templateFileName,
			String htmlFilePath, Map objectMap) throws BuildHtmlException {
		Writer out = null;
		try {
			// configuration.setClassForTemplateLoading(this.getClass(),
			// templateFolder);
			cfg.setDirectoryForTemplateLoading(new File(templateFolder));
			Template template = cfg.getTemplate(templateFileName);
			template.setEncoding("UTF-8");
			// 创建生成文件目录
			File htmlFile = new File(htmlFilePath);
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "UTF-8"));
			template.process(objectMap, out);
			out.flush();
		} catch (TemplateException ex) {
			logger.error("Build Error" + templateFileName, ex);
			throw new BuildHtmlException(ex.getMessage());
		} catch (IOException e) {
			logger.error("Build Error" + templateFileName, e);
			throw new BuildHtmlException(e.getMessage());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 生成多个文件
	 * 
	 * @param templateFolder
	 *            模板目录
	 * @param templateMap
	 *            模板和文件对应的映射
	 * @param objectMap
	 *            对象Map
	 */
	public void buildFiles(String templateFolder, Map templateMap, Map objectMap) {
		Set set = templateMap.entrySet();
		Iterator iterator = set.iterator();
		Map.Entry entry = null;
		String templateFileName = null;
		String filePath = null;
		while (iterator.hasNext()) {
			entry = (Map.Entry) iterator.next();
			templateFileName = (String) entry.getKey();
			filePath = (String) entry.getValue();
			this.buildFile(templateFolder, templateFileName, filePath,
					objectMap);
		}
	}

}
