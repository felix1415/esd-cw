/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import com.esd.cw.dao.DbBean;
import com.esd.cw.dao.MemberDao;
import com.esd.cw.dao.UserDao;
import com.esd.cw.model.Member;
import com.esd.cw.model.User;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shaun
 */
public class RegistrationService {
    
    UserDao userDao = new UserDao();
    MemberDao memberDao = new MemberDao();

    public Map<String, String> registerUser(String username, String firstName, String lastName, String address, String dob, String password, String confirmPassword) {
        

        try {
            String result = DbBean.getInstance().doQueryReturningTwoColumns("SELECT * FROM users");
            System.out.println(result);
        } catch (SQLException e) {

        }
        // response hashmap
        Map<String, String> registerResponse = new HashMap<>();

        // store the data sent by user so it can be displayed again in the form / used to create a user
        registerResponse.put("username", username);
        registerResponse.put("firstName", firstName);
        registerResponse.put("lastName", lastName);
        registerResponse.put("address", address);
        registerResponse.put("dob", dob);
        registerResponse.put("password", password);
        registerResponse.put("confirmPassword", confirmPassword);

        /* Run validations against the user inputs */
        if (validateUserInput(registerResponse)) {

            // check if user does not already exist
            if (userDao.userExists(username)) {

                // no point to continue. set response and return.
                registerResponse.put("success", "false");
                registerResponse.put("message", "There is already a user with this username.");

            } else // check passwords match
             if (password.equals(confirmPassword)) {

                    DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date dateOfBirth;
                    try {
                        dateOfBirth = dateFormatter.parse(dob);
                        userDao.registerUser(
                                new User(username, password, "UNPAID", false),
                                new Member(username, firstName + " " + lastName, address, dateOfBirth, new Date(), "UNPAID", 0, 2)
                        );
                        registerResponse.put("success", "true");
                        registerResponse.put("message", "Success!");
                    } catch (ParseException ex) {
                        Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {

                    // passwords dont match. return with error.
                    registerResponse.put("success", "false");
                    registerResponse.put("message", "Passwords do not match");
                }

        } else {

            // no point to continue. set response and return.
            registerResponse.put("success", "false");
            registerResponse.put("message", "Please fill in all fields");
        }

        return registerResponse;
    }

    public boolean validateUserInput(Map registerResponse) {

        boolean valid = true;

        Iterator it = registerResponse.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getValue() == "") {
                valid = false;
                break;
            }
        }

        return valid;
    }
}
