package com.esd.cw.dao;

import com.esd.cw.enums.Queries;
import com.esd.cw.model.Claim;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClaimDao {

    public ClaimDao() {

    }

    public List<Claim> getAllPendingClaims() throws SQLException {
        List<Claim> pendingClaims = new ArrayList<>();
        ArrayList<HashMap> result = new ArrayList<>();

        result = DbBean.getInstance().select(Queries.GET_ALL_PENDING_CLAIMS.getSql());

        for (HashMap r : result) {
            pendingClaims.add(
                    new Claim(
                            (int) r.get("id"),
                            (float) r.get("amount"),
                            (String) r.get("rationale"),
                            (String) r.get("mem_id"),
                            (Date) r.get("date"),
                            (String) r.get("status")
                    )
            );
        }

        return pendingClaims;
    }

    public void makeClaim(Claim claim) throws SQLException {
        String claimDate = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(claim.getClaimDate());
        String query = String.format(Queries.INSERT_CLAIM.getSql(), claim.getMemberId(), claimDate, claim.getRationale(), claim.getStatus(), claim.getAmount());
        DbBean.getInstance().runQuery(query);
    }

    public List<Claim> getClaimsForMember(String memberId) throws SQLException, SQLException, SQLException {

        List<Claim> allClaims = new ArrayList<>();
        ArrayList<HashMap> result = new ArrayList();
        try {
            result = DbBean.getInstance().select(String.format(Queries.GET_CLAIMS_MADE_BY_A_MEMBER.getSql(), memberId));
        } catch (SQLException e) {
            System.out.println("ERROR: ClaimDa0().findClaimsForUser() - " + e.toString());
        }
        for (HashMap r : result) {
            allClaims.add(
                    new Claim(
                            (int) r.get("id"),
                            (float) r.get("amount"),
                            (String) r.get("rationale"),
                            (String) r.get("mem_id"),
                            (Date) r.get("date"),
                            (String) r.get("status")
                    )
            );
        }
        return allClaims;
    }

    public String getTotalOfAllClaimsInPastYear() throws SQLException {
        return DbBean.getInstance().doQueryReturningXColumns(Queries.TOTAL_AMOUNT_FOR_ALL_CLAIMS_MADE.getSql(), 1);
    }

    public boolean updateClaim(boolean accept, String claimId) {
        boolean result = false;
        try {
            if (accept) {
                DbBean.getInstance().runQuery(String.format(Queries.ACCEPT_CLAIM.getSql(), claimId));
            } else {
                DbBean.getInstance().runQuery(String.format(Queries.DECLINE_CLAIM.getSql(), claimId));
            }
            result = true;
        } catch (Exception e) {
            System.out.println("Failed to accpet claim with id = " + claimId);
            e.printStackTrace();
        }
        return result;
    }

}
