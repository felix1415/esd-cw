/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw;

/**
 *
 * @author shaun
 */
public enum Queries {

    INSERT_PAYMENT("INSERT INTO payments VALUES ('%s', '%s', %s , '%s')"),
    INSERT_USER("INSERT INTO users (id, password, status, is_admin) VALUES ('%s', '%s', '%s', %b)"),
    INSERT_MEMBER("INSERT INTO Members (id, name, address, dob, dor, status, balance, claims_remaining) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')"),
    UPDATE_MEMBER_STATUS("UPDATE Members WHERE id = %s SET status= '%s'"),
    UPDATE_USER_STATUS("UPDATE users WHERE id= %s SET status = '%s'");

    Queries(String sql) {
        this.sql = sql;
    }

    String sql;

    public String getStatement() {
        return sql;
    }

}
