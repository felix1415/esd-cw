/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.dao;

import com.esd.cw.enums.Queries;
import com.esd.cw.model.Claim;
import com.esd.cw.model.User;
import com.esd.cw.util.PropertiesUtil;
import com.esd.cw.util.Util;
import com.mysql.jdbc.util.PropertiesDocGenerator;
import com.sun.org.apache.xerces.internal.impl.dv.xs.StringDV;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shaun
 */
public class ClaimDao {

    public ClaimDao() {

    }

    public long getMembershipDate(String memId) throws SQLException, ParseException {

        // define a hash map to store the result in
        ArrayList<HashMap> result = new ArrayList();
        long dateInMs = 0;
        try {
            result = DbBean.getInstance().select(String.format(Queries.SELECT_USER_LAST_PAYMENT.getStatement(), memId));
        } catch (SQLException e) {

        }

        if (result.size() > 0) {
            Date date = Util.getDateFromString(result.get(0).get("date").toString());

            dateInMs = date.getTime();
        }
        return dateInMs;
    }

    public void makeClaim(Claim claim) throws SQLException {
        String claimDate = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(claim.getClaimDate());

        String query = String.format(Queries.INSERT_CLAIM.getStatement(), claim.getMemberId(), claimDate, claim.getRationale(), claim.getStatus(), claim.getAmount());

        DbBean.getInstance().runQuery(query);

    }
}
