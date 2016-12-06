package com.webService;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.ObjectInputStream.GetField;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetRequest
{
//static String trail=null;
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

  public static void main(String[] args) throws IOException, JSONException {
    JSONObject json = readJsonFromUrl("https://api.edmunds.com/api/vehicle/v2/styles/200713322/colors?fmt=json&api_key=7u59gmx28keddpaa8ybbn29h");

    //System.out.println(json.get("models").toString());

    /*JSONArray array = new JSONArray(json.get("models").toString());

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
            
        }
        System.out.println("-----------------------");
        }*/
    }

  }