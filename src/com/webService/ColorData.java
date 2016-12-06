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

public class ColorData {
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
	     System.out.println(jsonText);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    	} 
	    finally {
	    		is.close();
	    }
   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement preparedStatement=null;
		try
		{
			ResultSet rs=null;
			try{
				conn=DatabaseConnection.getConnection();
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
				if(rs.getLong((int) i)> 100070039){
			    JSONObject json = readJsonFromUrl("https://api.edmunds.com/api/vehicle/v2/styles/"+rs.getLong((int) i)+"/colors?fmt=json&api_key=7u59gmx28keddpaa8ybbn29h");
			    JSONArray array = new JSONArray(json.get("colors").toString());
			    for(int j=0; j<array.length();j++)
			    {
			    	JSONObject jsonObj=array.getJSONObject(j);
			    	String sql="insert into color_data(color_id, color_name, category, fabric_interior, style_id) values (?,?,?,?,?)";
			    	preparedStatement=conn.prepareStatement(sql);
			    	preparedStatement.setString(1, jsonObj.getString("id"));
			    	if(jsonObj.has("name"))
			    	{
			    		preparedStatement.setString(2, jsonObj.getString("name"));
			    	}
			    	else
			    	{
			    		preparedStatement.setString(2, " ");
			    	}
			    	if(jsonObj.has("category"))
			    	{
			    		preparedStatement.setString(3, jsonObj.getString("category"));
			    	}
			    	else
			    	{
			    		preparedStatement.setString(3, " ");
			    		
			    	}
			    	if(jsonObj.getString("category").equals("Interior")&& jsonObj.has("fabricTypes"))
			    	{
			    	JSONArray arrayFabric=new JSONArray(jsonObj.getJSONArray("fabricTypes").toString());
			    	for(int k=0; k<arrayFabric.length(); k++)
			    	{
			    		JSONObject jsonObjFabric=arrayFabric.getJSONObject(k);
			    	if(jsonObjFabric.has("value"))
			    	{
			    		preparedStatement.setString(4, "value");
			    	}
			    	else
			    	{
			    		preparedStatement.setString(4, " ");
			    	}
			    	}
			    	}
			    	else
			    	{
			    		preparedStatement.setString(4, "NULL");
			    	}
			    	preparedStatement.setInt(5, (int) rs.getLong((int) i));
			    	preparedStatement.executeUpdate();
			    	System.out.println("Inserted");
			    }
				}
			    i++;  
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}

}
