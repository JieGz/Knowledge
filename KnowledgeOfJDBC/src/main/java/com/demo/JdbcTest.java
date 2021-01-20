package com.demo;

import java.sql.*;

/**
 * 使用JDBC规范来操作数据据
 *
 * @author jieguangzhi
 * @date 2021-01-13 09:59
 */
public class JdbcTest {

    public static void main(String[] args) {
        demo();
    }

    /**
     * JDBC api的方法使用
     */
    public static void demo() {
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取Connection对象
            Connection connection = DriverManager.getConnection("jdbc:mysql://14.215.104.99:6316/hiido_dashboard?useUnicode=true&characterEncoding=utf-8&useSSL=false", "hiido_dev_test", "KgYuZqfW4m");
            //通过Connection对象获取Statement对象
            Statement statement = connection.createStatement();
            //通过statement执行sql语句,并返回一个ResultSet对象
            //ResultSet对象代表查询操作的结果集
            String sql = "SELECT * FROM d_card_info LIMIT 3";
            ResultSet resultSet = statement.executeQuery(sql);
            //获取结果集的元数据信息
            ResultSetMetaData metaData = resultSet.getMetaData();
            //我们可以通过ResultSetMetaData对象获取结果集中所有的字段名称,字段数量,字段数据类型等信息
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String value = resultSet.getString(columnName);
                    System.out.println(columnName + ":" + value);
                    //列标签
                    String columnLabel = metaData.getColumnLabel(i);
                    System.out.println(columnLabel);
                }
                System.out.println("-----");
            }

            //关闭连接
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
