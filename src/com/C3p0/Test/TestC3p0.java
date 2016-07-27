package com.C3p0.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.PreparedStatement;

/**
 * C3p0数据库工具类
 * @author Administrator
 *
 */
public class TestC3p0 {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();  //由c3p0连接池提供的数据源

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*public static void main(String[] args) {
		Connection conn = TestC3p0.getConnection();
		String sql = "SELECT * FROM customer";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + ":" + rs.getString("firstname")
						+ "," + rs.getString("lastname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}*/
}
