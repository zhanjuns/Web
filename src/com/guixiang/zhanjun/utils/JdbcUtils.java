package com.guixiang.zhanjun.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    //因为创建订单需要使用同一个连接用来回滚事务，所以这里使用ThreadLocal
    private static ThreadLocal<Connection> coons = new ThreadLocal<>();

    static {


        try {
            Properties properties = new Properties();
            //读取jdbc.properties配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

//            System.out.println(dataSource.getConnection());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        System.out.println("测试");
//    }


    /**
     * 获取数据库连接池的连接
     *
     * @return 如果返回null，即获取连接失败
     */
    public static Connection getConnection() {
       /* Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;*/
        Connection conn = coons.get();
        if (conn == null) {
            try {
                //为空，从数据库连接池获取链接
                conn = dataSource.getConnection();
                //保存到ThreadLocal中，供后面的jdbc使用
                coons.set(conn);
                //设置手动管理事务
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }


    /**
     * 提交事务，并且关闭释放连接
     */
    public static void commitAndClose(){
        Connection connection = coons.get();
        if(connection != null){
            try {
                //提交事务
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        //~!!!!一定要执行remove，负责会出错，（因为tomcat底层也是使用线程池技术）
        coons.remove();
    }

    /**
     * 回滚事务，并且关闭连接
     */
    public static void rollbackAndClose(){
        Connection connection = coons.get();
        if(connection != null){
            try {
                //回滚事务
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        //~!!!!一定要执行remove，负责会出错，（因为tomcat底层也是使用线程池技术）
        coons.remove();
    }


    /**
     * 关闭连接，放回数据库连接池
     *
     * @param conn
     */
/*    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
}
