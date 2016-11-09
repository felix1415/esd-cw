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
 * DbBean
 *
 * @author shaun
 */
public class DbBean {
    
    private static DbBean instance;
    private static Connection con;
    private static MysqlDataSource dataSource;
    private Statement state;
    private ResultSet rs;
    private static String url;
    private static String username;
    private static String password;
    private static String databaseName;

    /**
     * A private Constructor prevents any other class from
     * instantiating.
     */
    private DbBean() {
        
    }
    
    public static void setParameters(String url, String username, String password, String databaseName) {
        try {
            getInstance().url = url;
            getInstance().username = username;
            getInstance().password = password;
            getInstance().databaseName = databaseName;
            dataSource = new MysqlDataSource();
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setServerName(url);
            dataSource.setDatabaseName(databaseName);
            con = dataSource.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error setting up database connection");
        }
    }

    /**
     * The Static initializer constructs the instance at class
     * loading time; this is to simulate a more involved
     * construction process (it it were really simple, you'd just
     * use an initializer)
     */
    static {
        instance = new DbBean();
    }

    /** Static 'instance' method */
    public static DbBean getInstance() {
        return instance;
    }

    // other methods protected by singleton-ness would be here...
    /** A simple demo method */
    public String demoMethod() {
        return "demo";
    }

//    public DbBean(String url, String username, String password,String databaseName) {
//        try {
//            
//            MysqlDataSource dataSource = new MysqlDataSource();
//            dataSource.setUser(username);
//            dataSource.setPassword(password);
//            dataSource.setServerName(url);
//            dataSource.setDatabaseName(databaseName); 
//
//            con = dataSource.getConnection();
//
//        } catch (Exception e) {
//            System.err.println("Error creating database connection: " + e);
//
//        }
//    }

    public ArrayList select(String query) throws SQLException {

        state = instance.getConnection().createStatement();
        
        rs = state.executeQuery(query);

        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList<HashMap> result = new ArrayList<>();

        while (rs.next()) {

            HashMap row = new HashMap(columns);

            for (int i = 1; i <= columns; i++) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }

            result.add(row);
        }
        rs.close();
        state.close();

        return result;
    }

    public String doQueryReturningTwoColumns(String query) throws SQLException {
        StringBuilder sb = new StringBuilder();
        state = instance.getConnection().createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            sb.append(rs.getString(1) + "," + rs.getString(2));
        }
        rs.close();
        state.close();
        return sb.toString();
    }

    /**
     * Return a comma separated string containing the results of your query. The
     * result is aggregated for how many columns you specify.
     *
     * @param query : String query to execute
     * @param columns : Amount of columns to combine and return
     * @return : comma separated string results.
     * @throws SQLException
     */
    public String doQueryReturningXColumns(String query, int columns) throws SQLException {
        StringBuilder sb = new StringBuilder();
        state = instance.getConnection().createStatement();
        rs = state.executeQuery(query);
        while (rs.next()) {
            for (int i = 0; i < columns; i++) {
                sb.append(rs.getString(i) + ",");
            }
        }
        rs.close();
        state.close();
        return sb.toString();
    }

    public Statement getStatement() {
        Statement statement = null;
        try {
            statement = instance.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, "Failed to get statement", ex);
        }
        return statement;
    }

    public void runQuery(String query) throws SQLException {
        // TODO - BUG
        // There is a bit of a bug here. As we initialise the connection in the constructor,
        // if you attempt to make 2 or more queries to the DB, it will error because the conenction
        // will be closed.
        // We can either reinstance *Dao object every time we need to make a connection.
        // or create a get connection function which is called at the start or every query function
        // see below functions
        
        con = instance.getConnection();
        state = con.createStatement();
        state.execute(query);
        state.close();
    }
    
    private Connection getConnection(){
        return instance.con;
    }

    public void shutDown() {
        try {
            getConnection().close();
        } catch (SQLException ex) {
            System.out.println("Failed to close the database connection");
        }
    }
    
  

}
