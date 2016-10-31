/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author shaun
 */
public class RegistrationService {
    
    public Map<String, String> registerUser(String username, String firstName, String lastName, String address, String dob, String password, String confirmPassword) {
        
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
            
            // check passwords match
            if (password.equals(confirmPassword)) {
                
                // attempt to create user
                // if create user
                    // success
                        // redirect to login
                    // fail
                        // add error
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
            Map.Entry pair = (Map.Entry)it.next();
            if (pair.getValue() == "") {
                valid = false;
                break;
            }
        }
        
        return valid;
    }
}
