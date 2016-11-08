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
    SELECT("");

    Queries(String sql) {
        this.sql = sql;
    }

    String sql;

    public String getStatement() {
        return sql;
    }

}
