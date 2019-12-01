package com.exam.repository;

import java.sql.*;
import java.util.*;

public class GraphDao {
	private static final GraphDao instance = new GraphDao();
	
	public static GraphDao getInstance() {
		return instance;
	}

	private GraphDao() {
		
	}
	
	public List<Map<String, Object>> getAddressOfMember() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			conn = DBManager.getConnection();
			sql = "SELECT SUBSTR(address, 1, 2) AS address, COUNT(*) AS cnt ";
			sql += "FROM member ";
			sql += "WHERE address LIKE '__%' ";
			sql += "GROUP BY SUBSTR(address, 1, 2) ";
			sql += "ORDER BY SUBSTR(address, 1, 2) ASC ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("address", rs.getString("address"));
				map.put("cnt", rs.getInt("cnt"));
				list.add(map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	} // getAddressOfMember


	public List<Map<String, Object>> getTotalCountOfBoards() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			conn = DBManager.getConnection();
			sql = "SELECT TABLE_NAME, TO_NUMBER( ";
			sql += "dbms_xmlgen.getxmltype('SELECT COUNT(*) c FROM ' || table_name).Extract('//text()')) num_rows2 ";
			sql += "FROM USER_TABLES ";
			sql += "WHERE TABLE_NAME LIKE 'MERCHANDISE' ";
			sql += "OR TABLE_NAME LIKE 'BOARD' ";
			sql += "OR TABLE_NAME LIKE 'FBOARD' ";
			sql += "OR TABLE_NAME LIKE 'ASELL' ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("TABLE_NAME", rs.getString(("TABLE_NAME")));
				map.put("num_rows2", rs.getInt("num_rows2"));
				list.add(map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	} // getTotalCountOfBoards
	
	
} // class EmpDao
