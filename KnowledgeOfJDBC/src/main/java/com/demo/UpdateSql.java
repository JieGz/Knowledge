package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author jieguangzhi
 * @date 2021-11-17
 */
public class UpdateSql {

    public static void main(String[] args) {
        String URL = "jdbc:mysql://47.74.243.72:6311/shopline_binlog_collect?useUnicode=true&characterEncoding=utf8";
        String USER = "hiido_test_o";
        String PASSWORD = "ICdWsMZPxNvkXxHSiPjrFqh6";
        String sql = "update hive_compact_mysql_meta set merge_interval = 600 where id=1";
        PreparedStatement ptmt = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);// 关闭事务
            ptmt = connection.prepareStatement(sql);
            //批量执行
            ptmt.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOG.error("sendRecordMonitorToMysql SQLException load data failed!\n" + e);
        } catch (Exception e) {
            throw new RuntimeException("sendRecordMonitorToMysql failed \n" + e);
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("sendRecordMonitorToMysql close connection failed,as the reason is:", e);
            }

        }
}
