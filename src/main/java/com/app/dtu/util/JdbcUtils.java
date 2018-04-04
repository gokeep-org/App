package com.app.dtu.util;

import com.app.dtu.config.DtuConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * Created by xuning on 2017/10/23.
 * DB-UTIL
 * 使用前需要导入对应的数据库驱动包
 */
public class JdbcUtils {
    //数据库用户名
    private static String USERNAME = DtuConfig.cacheUsername;
    //数据库密码
    private static String PASSWORD = DtuConfig.cachePassword;
    //驱动信息
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //数据库地址
    private static String URL = "jdbc:mysql://" + DtuConfig.cacheHost + ":" + DtuConfig.cachePort + "/" + DtuConfig.cacheDbName;
    private static Connection connection;
    private static PreparedStatement pstmt;
    private static ResultSet resultSet;
    private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);
    private static String dbType = "mysql";

    static {
        try {
            if (dbType.equals("mysql")) {
                Class.forName(DRIVER);
                System.out.println("db connection success");
            } else {
                USERNAME = "deepdata";
                PASSWORD = "oracle2014";
                URL = "jdbc:oracle:thin:@119.254.86.83:1521:deepdata";
                Class.forName("oracle.jdbc.driver.OracleDriver");
            }
        } catch (Exception e) {
            logger.error("db connection fail !");
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            logger.error("get db connection fail");
            e.printStackTrace();
        }
    }

    public JdbcUtils() {

    }

    /**
     * 获得数据库的连接
     *
     * @return
     */
    public Connection getConnection() {
        return connection;
    }


    /**
     * 查询单条记录
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static Map<String, Object> findSimpleResult(String sql, List<Object> params) throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();//返回查询结果
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col_len = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 0; i < col_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
        }
        return map;
    }


    public static boolean update(String sql, List<String> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        int res = -1;
        try {
            res = pstmt.executeUpdate();
        } catch (Throwable e) {
            logger.error("update data is exception");
        }
        return res >= 0;

    }


    /**
     * 查询多条记录
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }

        return list;
    }

    /**
     * 通过反射机制查询单条记录
     *
     * @param sql
     * @param params
     * @param cls
     * @return
     * @throws Exception
     */
    public static <T> T findSimpleRefResult(String sql, List<Object> params,
                                            Class<T> cls) throws Exception {
        T resultObject = null;
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            //通过反射机制创建一个实例
            resultObject = cls.newInstance();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                Field field = cls.getDeclaredField(cols_name);
                field.setAccessible(true); //打开javabean的访问权限
                field.set(resultObject, cols_value);
            }
        }
        return resultObject;
    }

    /**
     * 通过反射机制查询多条记录
     *
     * @param sql
     * @param params
     * @param cls
     * @return
     * @throws Exception
     */
    public static <T> List<T> findMoreRefResult(String sql, List<Object> params,
                                                Class<T> cls) throws Exception {
        List<T> list = new ArrayList<T>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            //通过反射机制创建一个实例
            T resultObject = cls.newInstance();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                Field field = cls.getDeclaredField(cols_name);
                field.setAccessible(true); //打开javabean的访问权限
                field.set(resultObject, cols_value);
            }
            list.add(resultObject);
        }
        return list;
    }

    /**
     * 释放数据库连接
     */
    public static void close() {
        if (!Objects.equals(null, resultSet)) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("db result close fail");
            }
        }

        if (!Objects.equals(null, connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("db connection close fail");
            }
        }
    }

//    /**
//     * 导入新增数据分类和产品ID的映射
//     *
//     * @param args
//     */
//    public static void main(String[] args) throws SQLException {
//        Map<String, String> map = new HashMap<>();
//        map.put("402880f35f6c66e3015f6c68d6e80000", "1");
//        map.put("402880f35f6c66e3015f6c68d8600001", "3");
//        map.put("402880f35f6c66e3015f6c68d9b80002", "3");
//        map.put("402880f35f6c66e3015f6c68db2c0003", "3");
//        map.put("402880f35f6c66e3015f6c68dcb00004", "3");
//        map.put("402880f35f6c66e3015f6c68de320005", "3");
//        map.put("402880f35f6c66e3015f6c68dfae0006", "3");
//        map.put("402880f35f6c66e3015f6c68e1390007", "3");
//        map.put("402880f35f6c66e3015f6c68e2d50008", "3");
//        map.put("402880f35f6c66e3015f6c68e4550009", "2");
//        map.put("402880f35f6c66e3015f6c68e5d9000a", "3");
//        map.put("402880f35f6c66e3015f6c68e754000b", "3");
//        map.put("402880f35f6c66e3015f6c68e8dc000c", "3");
//        map.put("402880f35f6c66e3015f6c68ea56000d", "3");
//        map.put("402880f35f6c66e3015f6c68ebdc000e", "3");
//        map.put("402880f35f6c66e3015f6c68ed63000f", "3");
//        map.put("402880f35f6c66e3015f6c68eeda0010", "3");
//        map.put("402880f35f6c66e3015f6c68f0500011", "3");
//        map.put("402880f35f6c66e3015f6c68f1f30012", "7");
//        map.put("402880f35f6c66e3015f6c68f3ab0013", "2");
//        map.put("402880f35f6c66e3015f6c68f5340014", "3");
//        map.put("402880f35f6c66e3015f6c68f6b80015", "3");
//        map.put("402880f35f6c66e3015f6c68f83f0016", "3");
//        map.entrySet().stream().forEach(m -> {
//            String sql = "insert into KS_BULK_ITEM_CATEGORY_MAPPING(ID, ADDED, CATEGORY_ID, PRODUCT_ID, UPDATED) values(?, ?, ?, ?, ?)";
//            List<String> params = new ArrayList<>();
//            params.add(UUID.randomUUID().toString().replaceAll("-", ""));
//            params.add(String.valueOf(new Date().getTime()));
//            params.add(m.getValue());
//            params.add(m.getKey());
//            params.add(String.valueOf(new Date().getTime()));
//
//            try {
//                boolean res = JdbcUtils.update(sql, params);
//            } catch (SQLException e) {
//                logger.error("更新数据异常");
//            }
//        });
//        JdbcUtils.close();
//    }

}
