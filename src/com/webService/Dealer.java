package com.webService;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dealer {
	  private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1)
		    {
		    	//System.out.println("--***----");
		    	//System.out.println(sb);
		      sb.append((char) cp);
		      //System.out.println(sb);
		    }
		   // trail=sb.toString();
		    return sb.toString();
		  }

		  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream is = new URL(url).openStream();
		    try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		    } finally {
		      is.close();
		    }
		  }
 
		    public static void main(String args[]) throws IOException, JSONException, SQLException
		    {
		    	Connection conn=null;
		    	PreparedStatement preparedStatement=null;
		    	try
		    	{
		    		conn=DatabaseConnection.getConnection();
		    	/*for(int i=1990; i<=1999; i++)
		    	{*/
		    	//String url="https://api.edmunds.com/api/vehicle/v2/chevrolet/models?fmt=json&api_key=rvzphv5vyubm9n4f9tgb4dak";
		    	JSONObject json = readJsonFromUrl("http://api.edmunds.com/v1/api/drrrepository/getdrrbyzipcodeandmake?zipcode={zipcode}&make=chevrolet&fmt=json&api_key=rvzphv5vyubm9n4f9tgb4dak");
		    	 JSONArray array = new JSONArray(json.get("models").toString());
		    	 for(int i=0; i<array.length(); i++){
		    	        JSONObject jsonObj  = array.getJSONObject(i);
		    	        System.out.println("----------------------");
		    	        System.out.println("ID: "+jsonObj.getString("id"));
		    	        System.out.println("Name: "+jsonObj.getString("name"));
		    	        JSONArray arrayforyears = new JSONArray(jsonObj.getJSONArray("years").toString());
		    	        for(int j=0;j<arrayforyears.length();j++){
		    	        	JSONObject jsonObjyears  = arrayforyears.getJSONObject(j);
		    	            System.out.println("*****************************");
		    	            System.out.println("Year Id: "+jsonObjyears.get("id"));
		    	            System.out.println("Year name: "+jsonObjyears.get("year"));
		    	            System.out.println("****************************");
		    	            JSONArray arrayforstyle = new JSONArray(jsonObjyears.getJSONArray("styles").toString());
		    	            for(int k=0; k<arrayforstyle.length(); k++){
		    	            	JSONObject jsonObjstyle=arrayforstyle.getJSONObject(k);
		    	            	System.out.println("**************************");
		    	            	System.out.println("Styles: "+jsonObjstyle.get("name"));
		    	            	System.out.println("Name:" +jsonObj.getString("name"));
		    	            	String sql="insert into chevy(car_id, car_name, car_style, production_year) values (?,?,?,?) ";
		    	            	preparedStatement=conn.prepareStatement(sql);
		    	            	preparedStatement.setString(1, jsonObj.getString("id"));
		    	            	preparedStatement.setString(2, jsonObj.getString("name"));
		    	            	preparedStatement.setString(3, jsonObjstyle.getString("name").trim());
		    	            	preparedStatement.setInt(4, jsonObjyears.getInt("year"));
		    	            	preparedStatement.executeUpdate();
		    	     
		    	            }
		    	        }
		    	        System.out.println("-----------------------");
		    	        }
		    	
		    	}
		    	catch(IOException e)
		    	{
		    		e.printStackTrace();
		    	}
		//JSONObject json=new JSONObject(s1);
				//System.out.println(json.get("id"));
				//Map<String, String> map=ParseJSON(json,map);
				
		    
		}

			private static Map<String, String> ParseJSON(JSONObject json, Map<String, String> map) {
				// TODO Auto-generated method stub
				
				return null;
			}
	}
