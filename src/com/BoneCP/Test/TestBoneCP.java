package com.BoneCP.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.prop.util.PropertiesUtil;

import java.sql.PreparedStatement;

/**
 * 使用BoneCP的方式创建数据源并对数据库连接进行管理
 * @author Administrator
 *
 */
public class TestBoneCP {

	public static void main(String[] args) {
		BoneCP connectionPool = null;  
        Connection connection = null;  
        PropertiesUtil prop = new PropertiesUtil("BoneCP");
        try {  
            //加载JDBC驱动  
            Class.forName(prop.getMyProperty("DriverClass"));  
        } catch (Exception e) {  
            e.printStackTrace();  
            return;  
        }  
          
        try {  
            //设置连接池配置信息  
            BoneCPConfig config = new BoneCPConfig();  
            //数据库的JDBC URL  
            config.setJdbcUrl(prop.getMyProperty("URL"));   
            //数据库用户名  
            config.setUsername(prop.getMyProperty("User"));   
            //数据库用户密码  
            config.setPassword(prop.getMyProperty("Password"));  
            //数据库连接池的最小连接数  
            config.setMinConnectionsPerPartition(5);  
            //数据库连接池的最大连接数  
            config.setMaxConnectionsPerPartition(10);  
            //  
            config.setPartitionCount(1);  
            //设置数据库连接池  
            connectionPool = new BoneCP(config);  
            //从数据库连接池获取一个数据库连接  
            connection = connectionPool.getConnection(); // fetch a connection  
              
            if (connection != null){  
                System.out.println("Connection successful!");  
                String sql = "SELECT * FROM customer";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();   
                while(rs.next()){  
                    System.out.println(rs.getInt(1)+":"+rs.getString("firstname")+","+rs.getString("lastname"));   
                }  
            }  
            //关闭数据库连接池  
            connectionPool.shutdown();   
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
            if (connection != null) {  
                try {  
                    connection.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  

}
