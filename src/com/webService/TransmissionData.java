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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TransmissionData {
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
public static void main(String args[])
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
		if(rs.getLong((int) i)>200711667){
		JSONObject json = readJsonFromUrl("https://api.edmunds.com/api/vehicle/v2/styles/"+rs.getInt((int) i)+"/transmissions?fmt=json&api_key=cwmwhqsn8qgcqh3gg3yy49rm");
		JSONArray array = new JSONArray(json.get("transmissions").toString());
		for(int j=0; j< array.length(); j++)
		{
			JSONObject jsonTrans=array.getJSONObject(j);
			String sql= "insert into transmission(transmission_id, name, equipmentType, availability, automaticType, transmissionType, numberofSpeeds, style_id) values (?,?,?,?,?,?,?,?)";
			preparedStatement=conn.prepareStatement(sql);
			if(jsonTrans.has("id"))
			{
				preparedStatement.setInt(1,jsonTrans.getInt( "id") );
			}
			if(jsonTrans.has("name"))
			{
				preparedStatement.setString(2, jsonTrans.getString("name"));
			}
			else
			{
				preparedStatement.setString(2, " ");
			}
			if(jsonTrans.has("equipmentType"))
			{
				preparedStatement.setString(3, jsonTrans.getString("equipmentType"));
			}
			else
			{
				preparedStatement.setString(3, " ");
			}
			if(jsonTrans.has("availability"))
			{
				preparedStatement.setString(4, jsonTrans.getString("availability"));
			}
			else
			{
				preparedStatement.setString(4, " ");
			}
			if(jsonTrans.has("automaticType"))
			{
				preparedStatement.setString(5, jsonTrans.getString("automaticType"));
			}
			else
			{
				preparedStatement.setString(5, " ");
			}
			if(jsonTrans.has("transmissionType"))
			{
				preparedStatement.setString(6, jsonTrans.getString("transmissionType"));
			}
			else
			{
				preparedStatement.setString(6, " ");
			}
			if(jsonTrans.has("numberOfSpeeds"))
			{
				preparedStatement.setString(7, jsonTrans.getString("numberOfSpeeds"));
			}
			else
			{
				preparedStatement.setString(7, " ");
			}
			preparedStatement.setInt(8, (int) rs.getLong((int) i));
			preparedStatement.executeUpdate();
			System.out.println("inserted");
		}
		i++;
		Thread.sleep(100);	
	}
		}
	
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}

}
}
