package com.esd.cw.dao;

import com.esd.cw.util.PropertiesUtil;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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
     * A private Constructor prevents any other class from instantiating.
     */
    private DbBean() {

    }

    /**
     * Method to set database parameters before singleton is instantiated.
     * Called by context initialised in listener class.
     *
     * @param url
     * @param username
     * @param password
     * @param databaseName
     */
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
     * The Static initialiser constructs the instance at class loading time
     */
    static {
        instance = new DbBean();
    }

    /**
     * Static instance method
     *
     * @return : the only instance we have is returned
     */
    public static DbBean getInstance() {
        return instance;
    }

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
                if(i>0){
                  sb.append(rs.getString(i + 1) + ",");  
                }else{
                  sb.append(rs.getString(i + 1));
                }
            }
        }
        rs.close();
        state.close();
        return sb.toString();
    }

    public Date doQueryReturningXDate(String query) throws SQLException {
        StringBuilder sb = new StringBuilder();
        state = instance.getConnection().createStatement();
        rs = state.executeQuery(query);
        Date date = rs.getDate("date");
        rs.close();
        state.close();
        return date;
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
        con = instance.getConnection();
        state = con.createStatement();
        state.execute(query);
        state.close();
    }

    private Connection getConnection() {
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
