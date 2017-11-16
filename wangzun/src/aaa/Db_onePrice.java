package aaa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Db_onePrice {
	private static final String  user="root";
	private static final String password="123456";
	private static final String driver="com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/test";
	
	static {
			//Class.forName(driver);
	}
	
	public static boolean getInsert(Ykj ykj) {
		try {
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url, user, password);
			String sql="insert into ykj(domainname,meaning,price) values(?,?,?)";
			PreparedStatement state=conn.prepareStatement(sql);
			state.setString(1, ykj.getDomainname());
			state.setString(2, ykj.getMeaning());
			state.setString(3, ykj.getPrice());
			int i=state.executeUpdate();
			System.out.println(i);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	public static void main(String[] args) {
		Ykj ykj=new Ykj();
		ykj.setDomainname("ww.com");
		ykj.setMeaning("sss");
		ykj.setPrice("45");
		Db_onePrice.getInsert(ykj);
	}

}
