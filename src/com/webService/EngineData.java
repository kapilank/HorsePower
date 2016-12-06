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
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EngineData extends Test {

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
		     // System.out.println(jsonText);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		    	} 
		    finally {
		    		is.close();
		    }
	   }
		  
		  public static void main(String args[]) throws IOException, JSONException, SQLException
		    {
		    	Connection conn=null;
		    	PreparedStatement preparedStatement=null;
		    	try
		    	{
		    		//conn=DatabaseConnection.getConnection();
		    	/*for(int i=1990; i<=1999; i++)
		    	{*/
		    	//String url="https://api.edmunds.com/api/vehicle/v2/chevrolet/models?fmt=json&api_key=rvzphv5vyubm9n4f9tgb4dak";
		    	int stylearray[] = null;
		    	ResultSet rs=null;
		    	try
		    	{
		    		conn=DatabaseConnection.getConnection();
					//preparedStatement=(PreparedStatement) connect.getConnection().createStatement();
					String sql="select style_id from chevy";
					preparedStatement=conn.prepareStatement(sql);
					rs=preparedStatement.executeQuery(); 
					
		    	}
		    	catch(Exception ex)
		    	{
		    		ex.printStackTrace();
		    	}
		    	while(rs.next())
		    	{
		    		long i=1;
		    		if(rs.getLong((int) i)>200712163){
		    		JSONObject json = readJsonFromUrl("https://api.edmunds.com/api/vehicle/v2/styles/"+rs.getInt((int) i)+"/engines?fmt=json&api_key=rvzphv5vyubm9n4f9tgb4dak");
		    	JSONArray array = new JSONArray(json.get("engines").toString());
		    	for(int j=0; j<array.length(); j++){
	    	        JSONObject jsonObj  = array.getJSONObject(j);
	    	        //System.out.println("hi");
	    	        	String sql="insert into sys.engine(engine_id,availability,cylinder,size,configuration,fuelType,horsepower,torque,type,code,style_id) values(?,?,?,?,?,?,?,?,?,?,?)";
	    	        	preparedStatement=conn.prepareStatement(sql);
	    	        	if(jsonObj.has("id")){
	    	        	preparedStatement.setInt(1, jsonObj.getInt("id"));}
	    	        	else{
	    	        		preparedStatement.setInt(1, 0);
	    	        	}
	    	        	if(jsonObj.has("availability")){
	    	        	preparedStatement.setString(2, jsonObj.getString("availability"));}
	    	        	else
	    	        	{
	    	        		preparedStatement.setString(2, " ");
	    	        	}
	    	        	if(jsonObj.has("cylinder")){
	    	        	preparedStatement.setInt(3, jsonObj.getInt("cylinder"));}
	    	        	else
	    	        	{
		    	        	preparedStatement.setInt(3, 0);
	    	        	}
	    	        	if(jsonObj.has("size")){
	    	        	preparedStatement.setInt(4, jsonObj.getInt("size"));}
	    	        	else
	    	        	{
	    	        		preparedStatement.setInt(4, 0);
	    	        	}
	    	        	if(jsonObj.has("configuration")){
	    	        	preparedStatement.setString(5, jsonObj.getString("configuration"));}
	    	        	else
	    	        	{
	    	        		preparedStatement.setString(5, " ");
	    	        	}
	    	        	if(jsonObj.has("fuelType")){
	    	        	preparedStatement.setString(6, jsonObj.getString("fuelType"));
	    	        	}
	    	        	else
	    	        	{
	    	        		preparedStatement.setString(6, " ");
	    	        	}
	    	        	if(jsonObj.has("rpm")){
	    	        		if(jsonObj.getJSONObject("rpm").has("horsepower"))
	    	        		{
	    	        		if(jsonObj.getJSONObject("rpm").get("horsepower").equals(null))
	    	        		{
	    	        			preparedStatement.setInt(7, 0);
	    	        		}
	    	        		else
	    	        		{
	    	        	preparedStatement.setInt(7, (int) jsonObj.getJSONObject("rpm").get("horsepower"));
	    	        		}
	    	        		}
	    	        		else
	    	        		{
	    	        			preparedStatement.setInt(7, 0);
	    	        		}
	    	        		if(jsonObj.getJSONObject("rpm").has("torque"))
	    	        		{
	    	        		if(jsonObj.getJSONObject("rpm").get("torque").equals(null))
	    	        		{
	    	        			preparedStatement.setInt(8, 0);
	    	        		}
	    	        		else{
	    	        	preparedStatement.setInt(8, (int) jsonObj.getJSONObject("rpm").get("torque"));
	    	        		}}
	    	        		else
	    	        		{
	    	        			preparedStatement.setInt(8, 0);
	    	        		}
	    	        	}
	    	        	else{
	    	        		preparedStatement.setInt(7, 0);
	    	        		preparedStatement.setInt(8, 0);
	    	        		
	    	        	}
	    	        	if(jsonObj.has("type")){
	    	        	preparedStatement.setString(9, jsonObj.getString("type"));}
	    	        	else
	    	        	{
	    	        		preparedStatement.setString(9, " ");
	    	        	}
	    	        	if(jsonObj.has("code")){
	    	        	preparedStatement.setString(10, jsonObj.getString("code"));}
	    	        	else
	    	        	{
	    	        		preparedStatement.setString(10, " ");
	    	        	}
	    	        	preparedStatement.setInt(11, (int) rs.getLong((int) i));
	    	        	preparedStatement.executeUpdate();
	    	        	System.out.println("Inserted");
	    	        	
	    	        	}
		    		i++;	
	    	        Thread.sleep(100);
	    	        }
		    		i++;
		    	}
		    	
		    	}
		    	
		    	catch(Exception e)
		    	{
		    		e.printStackTrace();
		    	}
		    }
}
		  