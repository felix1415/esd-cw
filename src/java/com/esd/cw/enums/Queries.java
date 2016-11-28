/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.enums;

/**
 *
 * @author shaun
 */
public enum Queries {

    //Users
    INSERT_USER("INSERT INTO users (id, password, status, is_admin) VALUES ('%s', '%s', '%s', %b)"),
    SELECT_USER_LAST_PAYMENT("SELECT date FROM payments WHERE mem_id ='%s' ORDER BY date DESC LIMIT 1"),
    UPDATE_USER_STATUS("UPDATE users SET status = '%s' WHERE id= '%s'"),
    SELECT_ALL_USERS("SELECT * FROM users"),
    SELECT_ALL_NON_ADMIN_USERS("SELECT * FROM users WHERE is_admin=0"),
    SELECT_USER_BY_ID("SELECT * FROM users WHERE id='%s'"),
    SELECT_USER_WITH_ID_LIKE("select * from users where id like('%%s%')"),
    
    //Payments
    INSERT_PAYMENT("INSERT INTO payments (mem_id, type_of_payment, amount, date) VALUES ('%s','%s',%s ,'%s')"),
    SELECT_PAYMENTS_FOR_USER("SELECT * FROM payments WHERE mem_id='%s'"),
    
    //Members
    INSERT_MEMBER("INSERT INTO Members (id, name, address, dob, dor, status, balance, claims_remaining) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')"),
    UPDATE_MEMBER_STATUS("UPDATE Members SET status = '%s' WHERE id = '%s'"),
    SELECT_USER_FIRST_PAYMENT("SELECT date FROM payments WHERE mem_id ='%s' ORDER BY date ASC LIMIT 1"),
    SELECT_MEMBER_REG_DATE("SELECT dor FROM Members WHERE id ='%s'"),
    DEDUCT_AMOUNT_FROM_ALL_MEMBERS_BALANCE("UPDATE Members set balance = balance - %s"),
    SELECT_ALL_MEMBERS("SELECT * FROM Members"),
    SELECT_MEMBER_BY_ID("SELECT * FROM Members WHERE id='%s'"),
    
    //Claims
    INSERT_CLAIM("INSERT INTO Claims (mem_id, date, rationale, status,amount) VALUES ('%s','%s','%s' ,'%s', %s)"),
    GET_CLAIMS_MADE_BY_A_MEMBER("SELECT * from Claims WHERE mem_id = '%s'"),
    GET_ALL_PENDING_CLAIMS("SELECT * from Claims WHERE status = 'PENDING'"),
    ACCEPT_CLAIM("UPDATE Claims SET STATUS = 'APPROVED' WHERE id = '%s'"),
    DECLINE_CLAIM("UPDATE Claims SET STATUS = 'DECLINED' WHERE id = '%s'"),
    TOTAL_AMOUNT_FOR_ALL_CLAIMS_MADE("SELECT SUM(amount) FROM Claims WHERE status = 'APPROVED' and  date > DATE_SUB(NOW(), INTERVAL 1 YEAR)");

    Queries(String sql) {
        this.sql = sql;
    }

    String sql;

    public String getSql() {
        return sql;
    }

}
