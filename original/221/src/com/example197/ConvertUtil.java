package com.example197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class ConvertUtil {
public static double[] getLocationInfo(String address){
	HttpClient client= new DefaultHttpClient();
	HttpGet httpGet=new HttpGet("http://maps.google.com/maps/api/geocode/json?address="+address+"ka&sensor=false");
	StringBuffer sb =new StringBuffer();
	HttpResponse response;
	try {
		response = client.execute(httpGet);
		HttpEntity entity =response.getEntity();
InputStream in=entity.getContent();
BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
String line=null;
while ((line = reader.readLine()) != null) {
 sb.append(line);
 }
in.close();
reader.close();
JSONObject json = new JSONObject(sb.toString());
	JSONObject location = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
	double x=location.getDouble("lng");
	double y = location.getDouble("lat");
	return new double[]{x,y};
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
}
