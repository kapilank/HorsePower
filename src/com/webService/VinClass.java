package com.webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class VinClass {
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
		    } finally {
		      is.close();
		    }
		  }
	public static void main(String args[])
	{
		try
		{
		String vin;
		System.out.println("Please enter the vin number : ");
		Scanner in=new Scanner(System.in);
		vin=in.nextLine();
		JSONObject json = readJsonFromUrl("https://api.edmunds.com/api/vehicle/v2/squishvins/"+vin+"/?fmt=json&api_key=rvzphv5vyubm9n4f9tgb4dak");
   	// JSONArray array = new JSONArray(json.get("models").toString());
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
