/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.services;

import com.esd.cw.util.PropertiesUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

/**
 *
 * @author shaun
 */
public class AddressLookupService {
    
    public String doLookup(String postcode) throws MalformedURLException, IOException{
        
        StringBuilder stringBuilder = new StringBuilder();
        String username = PropertiesUtil.getPropertyAsString("address-lookup-api-username");
        String password = PropertiesUtil.getPropertyAsString("address-lookup-api-password");
        String urlString = PropertiesUtil.getPropertyAsString("address-lookup-api-url") + postcode;
        
     
        
        URL url = new URL (urlString);
        String encodingString = username + ":" + password;
        String encoding = Base64.getEncoder().encodeToString(encodingString.getBytes());

            URLConnection connection =  url.openConnection();
           
            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
            InputStream content = connection.getInputStream();
            BufferedReader in   = 
                new BufferedReader (new InputStreamReader (content));
            String line;
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
            
          
        
        return stringBuilder.toString().replace(" , , , ,", "");
    }
    
}
