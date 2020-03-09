package com.jin.expertsystem.expertsystem.base.code.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/4/18
 */
@Component
@Slf4j
public class ConnectionUtil {

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    /**
     *  获得数据库中的表名
     */
    public List<String> getTableNameByCon () {
        List<String> tables = new ArrayList<>();
        Connection con = null;
        try {
            con = getConnection();
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getTables(null, null, null,
                    new String[] { "TABLE" });
            while (rs.next()) {
                tables.add(rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
            try {
                con.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return tables;
    }
    /**
     * 通过jdbc 获取 connection
     * @return Connection
     */
    public Connection getConnection() {
        Connection con = null;
        try {
            //创建驱动器
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return con;
    }


}
