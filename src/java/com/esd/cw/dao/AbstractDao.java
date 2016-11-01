/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.dao;

import com.esd.cw.util.PropertiesUtil;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract DAO class, for all over DAOs to extend. 
 * Contains methods to execute queries and get statements.
 * @author shaun
 */
public abstract class AbstractDao {
        
    private Connection con;
    private Statement state;
    private ResultSet rs;
    
    private static final String URL_KEY = "database-url";
    private static final String PORT_KEY = "database-port";
    private static final String USERNAME_KEY = "database-username";
    private static final String PASSWORD_KEY = "database-password";
    private static final String DATABASE_NAME = "database-name";

    public AbstractDao() {
        try {
            String url = PropertiesUtil.getPropertyAsString(URL_KEY);
            String username = PropertiesUtil.getPropertyAsString(USERNAME_KEY);
            String password = PropertiesUtil.getPropertyAsString(PASSWORD_KEY);
            String databaseName = PropertiesUtil.getPropertyAsString(DATABASE_NAME);
            
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setServerName(url);
            dataSource.setDatabaseName(databaseName); // TODO shouldn't hardcode this. Should set as a context attribute? (or whichever is relevant)

            con = dataSource.getConnection();

        } catch (Exception e) {
            System.err.println("Error: " + e);

        }
    }
    
    public ArrayList doQuery(String query) throws SQLException {
        
        state = con.createStatement();
        rs = state.executeQuery(query);
        
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList <HashMap> result = new ArrayList<>();
        
        while (rs.next()) {
            
            HashMap row = new HashMap(columns);
            
            for (int i = 1; i <= columns; i++) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            
            result.add(row);
        }
        rs.close();
        state.close();
        con.close();
        
        return result;
    }
    
    public String doQueryReturningTwoColumns(String query) throws SQLException {
       StringBuilder sb = new StringBuilder();
       state = con.createStatement();
       rs = state.executeQuery(query);
       while (rs.next()) {
           sb.append(rs.getString(1) + "," + rs.getString(2));
       }
       rs.close();
       state.close();
       con.close();
       return sb.toString();
   }
    
    /**
     * Return a comma separated string containing the results of your query.
     * The result is aggregated for how many columns you specify. 
     * @param query : String query to execute
     * @param columns : Amount of columns to combine and return 
     * @return : comma separated string results.
     * @throws SQLException 
     */
    public String doQueryReturningXColumns(String query,int columns) throws SQLException {
       StringBuilder sb = new StringBuilder();
       state = con.createStatement();
       rs = state.executeQuery(query);
       while (rs.next()) {
           for (int i = 0; i < columns; i++) {
               sb.append(rs.getString(i) + ",");
           }
           
       }
       rs.close();
       state.close();
       con.close();
       return sb.toString();
   }
    
    public Statement getStatement(){
        Statement statement = null;
        try {
            statement = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDao.class.getName()).log(Level.SEVERE, "Failed to get statement", ex);
        }
        return statement;
    }
    
}
