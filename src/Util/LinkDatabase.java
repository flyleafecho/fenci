package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LinkDatabase {
	private Connection conn;
	private Statement stmt;
	public LinkDatabase(){
		conn=null; stmt=null;
	}
	
	public boolean connect(){
		try{
			Class.forName(Const.DBDriver);
			conn = (Connection) DriverManager.getConnection(Const.DBConnectStr,Const.DBUser,Const.DBPassword);
			stmt = (Statement) conn.createStatement();
			return true;
		}catch(SQLException e){
			System.err.println("Error when trying to get connection...");
			e.printStackTrace();
		}catch(ClassNotFoundException ce){
			System.err.println("Error when trying to load class...");
			ce.printStackTrace();
		}
		return false;
	}
	
	public int executeSQL(String sql){
		int flag = 0;
		if(conn==null||stmt==null){
			System.err.println("No connection or statement...");
		}else{
			try{
				flag = stmt.executeUpdate(sql);
			}catch(SQLException e){
				System.err.println("Error when executing update SQL...");
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public ResultSet executeQuerySQL(String sql){
		ResultSet rs = null;
		if(conn==null||stmt==null){
		}else{
			try{
				rs = stmt.executeQuery(sql);
			}catch(SQLException e){
				System.err.println("Error when executing query SQL...");
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public void close(){
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException e){
				try{
					conn.close();
				}catch(SQLException ce){
					System.err.println("Error when trying to close connection...");
					ce.printStackTrace();
				}
			}
		}
	}
}
