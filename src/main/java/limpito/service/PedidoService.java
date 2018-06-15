package limpito.service;

import limpito.model.Pedido;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
 
//import org.apache.tomcat.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class PedidoService {

   public String getHTML(String urlToRead, String usuario, String clave,String method,String body) throws IOException {
      URL url;
      HttpURLConnection conn;
      BufferedReader rd;
      String line;
      String result = "";
      String input = "";
      try {
         //Parametros de la conexion
         url = new URL(urlToRead);
         conn = (HttpURLConnection) url.openConnection();
         conn.setDoOutput(true);
         conn.setDoInput(true);
         conn.setRequestMethod(method);
         conn.setRequestProperty("charset", "UTF-8");
         if(method.equals("POST")){
            conn.setRequestProperty("Content-Type", "application/json");
            input = body;
            //conn.setRequestProperty("Accept", "application/json");
         }
         String userCredentials = usuario+":"+clave;
         String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
         conn.setRequestProperty ("Authorization", basicAuth);
  
         if(method.equals("GET")){
            InputStream _inputstream;
            
            try {
             _inputstream = conn.getInputStream();
            
            } catch (IOException e) {
                //e.printStackTrace();
                return "401";
            }
            

            rd = new BufferedReader(new InputStreamReader(_inputstream));
            while ((line = rd.readLine()) != null) {
               result += line;
            } 
            rd.close();
         }
         else if(method.equals("POST")){
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            //System.out.println("\n Codigo respuesta: "+ conn.getResponseCode());
            
            if (conn.getResponseCode() != 200) {
               throw new RuntimeException("Failed : HTTP error code : "
                  + conn.getResponseCode());
            }
             
            
            BufferedReader br = new BufferedReader(new InputStreamReader(
                  (conn.getInputStream())));
       
            String output;
            while ((output = br.readLine()) != null) {
               result += output;
            }
       
            conn.disconnect();

         }else
            System.out.println("Method not recognized");

      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return result;
   }

   public static List<Pedido> lista_pedidos() throws IOException, ParseException,java.text.ParseException{
      PedidoService _http = new PedidoService();
      String body = "{ \"processDefinitionKey\":\"limpito-process\"} ";
      String _res = _http.getHTML("http://localhost:8080/activiti-rest/service/query/tasks","kermit","kermit","POST",body); 
      //System.out.println(_res);
     
      JSONParser parser = new JSONParser();
      Object obj = parser.parse(_res);
      JSONObject jsonObject = (JSONObject) obj;
         
      JSONArray data = (JSONArray) jsonObject.get("data");
      Iterator i = data.iterator();

      System.out.println("---------INICIANDO CARGA-----------");
      
      List<Pedido> pedidos = new ArrayList<Pedido>();
      Date today = new Date(2018,06,14);
      pedidos.add(new Pedido(1,"uno","eze",today,true));
      while (i.hasNext()) {
         System.out.println(" GIME TE AMO. JOA");
         JSONObject innerObj = (JSONObject) i.next();
         Integer id = Integer.parseInt(innerObj.get("id").toString());
         String name = innerObj.get("name").toString();
         String assignee = innerObj.get("assignee").toString();
         SimpleDateFormat date_parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String date_toParse = innerObj.get("createTime").toString().substring(0,innerObj.get("createTime").toString().indexOf("."));
         date_toParse = date_toParse.replace('T',' ');
         Date createTime = date_parser.parse(date_toParse);
         Boolean suspended = Boolean.parseBoolean(innerObj.get("suspended").toString());
         Pedido new_pedido = new Pedido(id,name,assignee,createTime,suspended);
         //System.out.println(new_pedido.toString());
         pedidos.add(new_pedido);

      }

      System.out.println("---------CARGA FINALIZADA-----------");

      return pedidos;
     
   }
}