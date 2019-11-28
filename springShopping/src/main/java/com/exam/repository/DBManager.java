package com.exam.repository;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	
	public static Connection getConnection() throws Exception {
		Connection conn = null;		
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/oracledb");
		conn = ds.getConnection();
		return conn;
	} // getConnection
	
	// SELECT
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // SELECT close method
	
	// INSERT, UPDATE, DELETE
	public static void close(Connection conn, Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // INSERT, UPDATE, DELETE close method
	
} // DBManager class
