package org.liufeng.course.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.liufeng.course.message.resp.TextMessage;
//import org.liufeng.course.pojo.Game;
//import org.liufeng.course.pojo.GameRound;

/**
 * Mysql数据库操作类
 * 
 * @author liufeng
 * @date 2013-11-21
 */
public class MySQLUtil {
	private static  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 获取Mysql数据库连接
	 * 
	 * @return Connection
	 */
	public static  Connection getConn(HttpServletRequest request) {
		Connection conn = null;

		// 从request请求头中取出IP、端口、用户名和密码
//		String host = request.getHeader("BAE_ENV_ADDR_SQL_IP");
//		String port = request.getHeader("BAE_ENV_ADDR_SQL_PORT");
//		String username = request.getHeader("BAE_ENV_AK");
//		String password = request.getHeader("BAE_ENV_SK");
		
		String host = "localhost";
		String port ="3306";
		String username = "root";//G3uZLnPa6uG81Rc6tfO8bTc8 
		String password = "654321";//
		// 数据库名称
		String dbName = "zunmi";
		// JDBC URL
		String url = String.format("jdbc:mysql://%s:%s/%s", host, port, dbName);
//		url=url+"?useUnicode=true&characterEncoding=UTF8";
//        System.out.println("url:=="+url);
		try {
			// 加载MySQL驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取数据库连接
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 释放JDBC资源
	 * 
	 * @param conn 数据库连接
	 * @param ps
	 * @param rs 记录集
	 */
	public static  void releaseResources(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (null != rs)
				rs.close();
			if (null != ps)
				ps.close();
			if (null != conn)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static  void releaseResources(Connection conn, Statement stmt,PreparedStatement ps, ResultSet rs) {
		try {
			if(null!=stmt){
				stmt.close();
			}
			if (null != rs)
				rs.close();
			if (null != ps)
				ps.close();
			if (null != conn)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * 
 * @return
 */
	public static int savePrintLog(HttpServletRequest request,TextMessage tm,String schoolName,int num,String signature){
		int printLog=-1;
		String sql = "insert into printlog(open_id, create_time, schoolname, printnum, flag,signature) values(?, ?, ?, ?, ?,?)";
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			System.out.println(schoolName);
		Date date=new Date(tm.getCreateTime());
		String createTime=sdf.format(date);
		conn = mysqlUtil.getConn(request);
		// 保存游戏
		ps = conn.prepareStatement(sql);
		ps.setString(1, tm.getToUserName());
		ps.setString(2, createTime);
		ps.setString(3, schoolName);
		ps.setString(4, num+"");
		ps.setString(5, "0");//未打印
		ps.setString(6, signature);//未打印
		ps.executeUpdate();
		// 获取游戏的id
		sql = "select printid from printlog where open_id=? and create_time=? order by printid desc limit 0,1";
		ps = conn.prepareStatement(sql);
		ps.setString(1, tm.getToUserName());
		ps.setString(2, createTime);
		rs = ps.executeQuery();
		if (rs.next()) {
			printLog = rs.getInt("printid");
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		// 释放资源
		mysqlUtil.releaseResources(conn, ps, rs);
	}
		return printLog;
	}

public static String  query(HttpServletRequest request, TextMessage tm) {
	String  tempStr=null;
	String sql = "";
	MySQLUtil mysqlUtil = new MySQLUtil();
	Connection conn = null;
//	PreparedStatement ps = null;
	Statement s=null;
	ResultSet rs = null;
    	try{
           SimpleDateFormat sdfdd=new SimpleDateFormat("yyyy-MM-dd");
    		Date date=new Date(tm.getCreateTime());
    		conn = mysqlUtil.getConn(request);
    		String createTime=sdfdd.format(date);
    		s=conn.createStatement();
    		sql = "select printnum,create_time from printlog where open_id='"+tm.getToUserName()+"' and create_time like '"+createTime+"%'";
//    		ps = conn.prepareStatement(sql);
//    		ps.setString(1, tm.getFromUserName());
//    		ps.setString(2, createTime);
//    		rs = ps.executeQuery();
    		rs=s.executeQuery(sql);
    		if (rs.next()) {
    			String num = rs.getString("printnum");
    			String create_time=rs.getString("create_time");
    			tempStr="你在   "+create_time+"，申请打印了"+num+",一天只能打印一次哦,亲，明天再申请吧，天天都有机会哦";
    		}
    		System.out.println(sql+ " "+tempStr);
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}finally {
    		try {
    			if (null != rs)
    				rs.close();
    			if (null != s)
    				s.close();
    			if (null != conn)
    				conn.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	return tempStr;
}
	
	
	
	
	
	/**
	 * 保存游戏信息
	 * 
	 * @param request 请求对象
	 * @param game 游戏对象
	 * @return gameId
	 */
//	public static int saveGame(HttpServletRequest request, Game game) {
//		int gameId = -1;
//		String sql = "insert into game(open_id, game_answer, create_time, game_status, finish_time) values(?, ?, ?, ?, ?)";
//
//		MySQLUtil mysqlUtil = new MySQLUtil();
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = mysqlUtil.getConn(request);
//			// 保存游戏
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, game.getOpenId());
//			ps.setString(2, game.getGameAnswer());
//			ps.setString(3, game.getCreateTime());
//			ps.setInt(4, game.getGameStatus());
//			ps.setString(5, game.getFinishTime());
//			ps.executeUpdate();
//			// 获取游戏的id
//			sql = "select game_id from game where open_id=? and game_answer=? order by game_id desc limit 0,1";
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, game.getOpenId());
//			ps.setString(2, game.getGameAnswer());
//			rs = ps.executeQuery();
//			if (rs.next()) {
//				gameId = rs.getInt("game_id");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			mysqlUtil.releaseResources(conn, ps, rs);
//		}
//		return gameId;
//	}

	/**
	 * 获取用户最近一次创建的游戏 <br>
	 * 
	 * @param request 请求对象
	 * @param openId 用户的OpendID
	 * @return
	 */
//	public static Game getLastGame(HttpServletRequest request, String openId) {
//		Game game = null;
//		String sql = "select * from game where open_id=? order by game_id desc limit 0,1";
//
//		MySQLUtil mysqlUtil = new MySQLUtil();
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = mysqlUtil.getConn(request);
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, openId);
//			rs = ps.executeQuery();
//			if (rs.next()) {
//				game = new Game();
//				game.setGameId(rs.getInt("game_id"));
//				game.setOpenId(rs.getString("open_id"));
//				game.setGameAnswer(rs.getString("game_answer"));
//				game.setCreateTime(rs.getString("create_time"));
//				game.setGameStatus(rs.getInt("game_status"));
//				game.setFinishTime(rs.getString("finish_time"));
//			}
//		} catch (SQLException e) {
//			game = null;
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			mysqlUtil.releaseResources(conn, ps, rs);
//		}
//		return game;
//	}

	/**
	 * 根据游戏id修改游戏状态和完成时间
	 * 
	 * @param request 请求对象
	 * @param gameId 游戏id
	 * @param gameStatus 游戏状态（0:游戏中 1:成功 2:失败 3:取消）
	 * @param finishTime 游戏完成时间
	 */
//	public static void updateGame(HttpServletRequest request, int gameId, int gameStatus, String finishTime) {
//		String sql = "update game set game_status=?, finish_time=? where game_id=?";
//		MySQLUtil mysqlUtil = new MySQLUtil();
//		Connection conn = null;
//		PreparedStatement ps = null;
//		try {
//			conn = mysqlUtil.getConn(request);
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, gameStatus);
//			ps.setString(2, finishTime);
//			ps.setInt(3, gameId);
//			ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			mysqlUtil.releaseResources(conn, ps, null);
//		}
//	}

	/**
	 * 保存游戏的回合信息
	 * 
	 * @param request 请求对象
	 * @param gameRound 游戏回合对象
//	 */
//	public static void saveGameRound(HttpServletRequest request, GameRound gameRound) {
//		String sql = "insert into game_round(game_id, open_id, guess_number, guess_time, guess_result) values (?, ?, ?, ?, ?)";
//		MySQLUtil mysqlUtil = new MySQLUtil();
//		Connection conn = null;
//		PreparedStatement ps = null;
//		try {
//			conn = mysqlUtil.getConn(request);
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, gameRound.getGameId());
//			ps.setString(2, gameRound.getOpenId());
//			ps.setString(3, gameRound.getGuessNumber());
//			ps.setString(4, gameRound.getGuessTime());
//			ps.setString(5, gameRound.getGuessResult());
//			ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			mysqlUtil.releaseResources(conn, ps, null);
//		}
//	}

	/**
	 * 根据游戏id获取游戏的全部回合<br>
	 * 
	 * @param request 请求对象
	 * @param gameId 游戏id
	 * @return
	 */
//	public static List<GameRound> findAllRoundByGameId(HttpServletRequest request, int gameId) {
//		List<GameRound> roundList = new ArrayList<GameRound>();
//		// 根据id升序排序
//		String sql = "select * from game_round where game_id=? order by id asc";
//		MySQLUtil mysqlUtil = new MySQLUtil();
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = mysqlUtil.getConn(request);
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, gameId);
//			rs = ps.executeQuery();
//			GameRound round = null;
//			while (rs.next()) {
//				round = new GameRound();
//				round.setGameId(rs.getInt("game_id"));
//				round.setOpenId(rs.getString("open_id"));
//				round.setGuessNumber(rs.getString("guess_number"));
//				round.setGuessTime(rs.getString("guess_time"));
//				round.setGuessResult(rs.getString("guess_result"));
//				roundList.add(round);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			mysqlUtil.releaseResources(conn, ps, rs);
//		}
//		return roundList;
//	}

	/**
	 * 获取用户的战绩
	 * 
	 * @param request 请求对象
	 * @param openId 用户的OpenID
	 * @return HashMap<Integer, Integer>
	 */
//	public static HashMap<Integer, Integer> getScoreByOpenId(HttpServletRequest request, String openId) {
//		HashMap<Integer, Integer> scoreMap = new HashMap<Integer, Integer>();
//		// 根据id升序排序
//		String sql = "select game_status,count(*) from game where open_id=? group by game_status order by game_status asc";
//		MySQLUtil mysqlUtil = new MySQLUtil();
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = mysqlUtil.getConn(request);
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, openId);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				scoreMap.put(rs.getInt(1), rs.getInt(2));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			// 释放资源
//			mysqlUtil.releaseResources(conn, ps, rs);
//		}
//		return scoreMap;
//	}

    public static String getOpenidByCode(String code){
    	String openid="";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select openid from yzy_openidcode where opencode=?";
		try {
			conn = getConn(null);
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs=ps.executeQuery();
			if(rs.next()){
				openid = rs.getString("openid").trim();
			}
			//yzy_openidcode 存放的都是临时数据，可以删除小于 今天的内容的删除掉，以保证
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			releaseResources(conn, ps, rs);
		}
		return openid;
    }
    
    public static String getUrlpathByFlag(String flag){
    	String urlpath="";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select urlpath from yzy_redict where flag=?";
		try {
			conn = getConn(null);
			ps = conn.prepareStatement(sql);
			ps.setString(1, flag);
			rs=ps.executeQuery();
			if(rs.next()){
				urlpath = rs.getString("urlpath").trim();
			}
			//yzy_openidcode 存放的都是临时数据，可以删除小于 今天的内容的删除掉，以保证
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			releaseResources(conn, ps, rs);
		}
		return urlpath;
    }
}
