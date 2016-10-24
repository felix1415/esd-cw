/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author shaun
 */
public class DbBean {
    
  private Connection con;
  private Statement state;
  private ResultSet rs;
  
  public DbBean(String url, String username, String password) throws SQLException, InstantiationException{
      try {
      // You will need to explicitly load this driver in a web app
      MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setServerName(url);
        dataSource.setDatabaseName("esd");
      
      con = dataSource.getConnection();
      
    } catch (SQLException e) {
      System.err.println("Error: " + e);

    }
  }

  public String doQuery(String query) throws SQLException{
      StringBuilder sb = new StringBuilder();
      state = con.createStatement();
      rs = state.executeQuery(query);
      while (rs.next()) {
        sb.append(rs.getString(1) + " " + rs.getString(2));
        sb.append("\n<br>");
        System.out.println(rs.getString(1) + " " + rs.getString(2));
      }
      rs.close();
      state.close();
      con.close();
      return sb.toString();
  }
  
}

