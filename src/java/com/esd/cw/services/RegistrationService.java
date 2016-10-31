/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import java.util.HashMap;

/**
 *
 * @author shaun
 */
public class RegistrationService {
    
    public HashMap<String, String> registerUser(String username, String firstName, String lastName, String address, String dob, String password) {
        
        // response hashmap
        HashMap<String, String> registerResponse = new HashMap();
        
        registerResponse.put("success", "false");
        registerResponse.put("message", "Bad username");
        
        return registerResponse;
    }
}
