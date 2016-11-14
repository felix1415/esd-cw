/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.dao;

import com.esd.cw.enums.Queries;
import com.esd.cw.model.Claim;
import com.esd.cw.model.Payment;
import com.esd.cw.util.PropertiesUtil;
import com.mysql.jdbc.util.PropertiesDocGenerator;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shaun
 */
public class ClaimDao {
    
    public ClaimDao() {
       
    }
    
    public List<Claim> findClaimsForMember(String memberId) {

        // define a list of users
        List<Claim> allClaims = new ArrayList<>();

        // define a hash map to store the result in
        ArrayList<HashMap> result = new ArrayList();

        try {
            result = DbBean.getInstance().select(String.format(Queries.GET_CLAIMS_MADE_BY_A_MEMBER.getSql(),memberId));
        } catch (SQLException e) {
            System.out.println("ERROR: ClaimDao().findClaimsForMember() - " + e.toString());
        }

        for (HashMap r : result) {
            allClaims.add(
                    new Claim(
                            (float) r.get("amount"),
                            (String) r.get("rationale"),
                            (int) r.get("mem_id")
                    )
            );
        }

        // return all the users
        return allClaims;
    }
}
