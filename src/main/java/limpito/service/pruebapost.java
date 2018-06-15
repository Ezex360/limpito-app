/*
package org.activiti.designer.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import org.apache.tomcat.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class pruebapost {
 
	// http://localhost:8080/RESTfulExample/json/product/post
	public static void main(String[] args) {
 
	  try {
 
		URL url = new URL("http://192.168.56.101:8080/activiti-rest/service/runtime/process-instances");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("charset", "UTF-8");
        String userCredentials = "kermit:kermit";
        String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
        conn.setRequestProperty ("Authorization", basicAuth);
        
		String input = "{ \"processDefinitionId\":\"myProcessPrimerImpl:1:502504\"} ";
 
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();
		System.out.println("\n Codigo respuesta: "+ conn.getResponseCode());
		
		if (conn.getResponseCode() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}
       
		
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
 
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}
 
		conn.disconnect();
 
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	 }
 
	}
 
}
*/