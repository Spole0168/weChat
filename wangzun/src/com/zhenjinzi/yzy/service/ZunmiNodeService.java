package com.zhenjinzi.yzy.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.zhenjinzi.yzy.model.ZunmiNode;

public interface ZunmiNodeService {
	
	/**
	 * 增加或更新node
	 * @param node
	 * @return
	 */
	public boolean save(ZunmiNode node); 
	
	/**
	 * 查询node数据记录
	 * @return
	 */
	public List<ZunmiNode> findAll();
	
	/**
	 * 根据id查询node记录
	 * @param id
	 * @return
	 */
	public ZunmiNode findById(Integer id);
	
	/**
	 * 根据父栏目ID查询栏目记录
	 * @param parentId
	 * @return
	 */
	public List<ZunmiNode> searchByParentId(Integer parentId);
	
	public Map<ZunmiNode,Object> getNodeMap();
	
	/**
	 * 根据栏目名查询栏目记录
	 * @param nodeName
	 * @return
	 */
	public ZunmiNode searchByNodeName(String nodeName);
	
	/**
	 * 查询xml格式树型结构栏目记录(栏目管理)
	 * @return
	 */
	public String findXMLTreeNodeStr() throws IOException;
	
	/**
	 * 查询xml格式树型结构栏目记录(内容管理)
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public String findXMLTreeNodeStr2(String path)throws IOException;
	
	/**
	 * 查询xml格式树型结构栏目记录(生成栏目页)
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public String getChannelCreationList(String path)throws IOException;
	
	/**
	 * 查询xml格式树型结构栏目记录(生成内容页)
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public String getContentCreationList(String path) throws IOException;
	
	/**
	 * 持久化session数据到数据库
	 */
	public void flush();
}
