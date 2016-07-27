package com.DBCP.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import java.sql.PreparedStatement;

/**
 * 测试DBCP连接池
 * @author Administrator
 *
 */
public class TestDbcp {
	private static DataSource dataSource;

	static {
		InputStream in = TestDbcp.class.getResourceAsStream("/DBCP.properties");  //加上'/'表示在Classpath根下查找
		Properties prop = new Properties();
		try {
			prop.load(in);
			dataSource = BasicDataSourceFactory.createDataSource(prop);   //由DBCP连接池提供的数据库源
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/*public static void main(String[] args) throws SQLException {
		Connection conn = TestDbcp.getConnection();
		String sql = "SELECT * FROM customer";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt(1) + ":" + rs.getString("firstname")
					+ "," + rs.getString("lastname"));
		}
		conn.close();
	}*/
}
