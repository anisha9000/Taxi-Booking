package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Information extends ActionBarActivity {
	
	EditText information;
	Button b1;
	int choice;
	ArrayList<HashMap<String, String>> user_details;
	LinearLayout linear;
	int count;
	
	private static String user_info_request = "http://192.168.8.100/find_user.php";
	private static String time_info_request = "http://192.168.8.100/find_time.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		
		Intent intent = getIntent();
		choice = intent.getIntExtra("choice", 1);
		information = (EditText) findViewById(R.id.information);
		b1 = (Button) findViewById(R.id.button1);
		linear = (LinearLayout) findViewById(R.id.linearlayout);
		user_details = new ArrayList<HashMap<String, String>>();
		
		if(choice == 1) {
			information.setHint("Enter userid");
		} else if (choice == 2) {
			information.setHint("Enter time");
		}
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(choice == 1)
				{
					new fetch_user().execute();
				} else if (choice == 2) {
					new fetch_time().execute();
				}
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.information, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public class fetch_user extends AsyncTask<String, String, String> {
	    String drop_time;
	    String loc_to;
	    String loc_from;
	    String purpose;
	    String drop_date;
	    
		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        count = 0;
	        
	    }
		
		protected String doInBackground(String... args) {
			
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("Userid", information.getText().toString()));
		    
	        try {
	        	HttpParams httpParameters = new BasicHttpParams();
	        	HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost(user_info_request);
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				
				String result = EntityUtils.toString(entity);
				
				// Create a JSON object from the request response
				JSONArray jsonArray = new JSONArray(result);
				int len = jsonArray.length();
				for (int i = 0; i < jsonArray.length(); i++) {
				    JSONObject detail = jsonArray.getJSONObject(i);
				    if(detail != null){
				    	loc_to = detail.getString("loc_to");
				    	loc_from = detail.getString("loc_from");
				    	purpose = detail.getString("purpose");
				    	drop_time = detail.getString("drop_time");
				    	drop_date = detail.getString("drop_date");
				    	
				    
				    	HashMap<String, String> one_detail = new HashMap<String, String>();
				    	
				    	one_detail.put("loc_to", loc_to);
				    	one_detail.put("loc_from", loc_from);
				    	one_detail.put("drop_date", drop_date);
				    	one_detail.put("drop_time", drop_time);
				    	one_detail.put("purpose", purpose);
				    
				    	user_details.add(one_detail);
				    	count++;
				    }
				}
		    } 
		    catch (Exception e) {
	        	Log.e("Buffer Error", "Error converting result " + e.toString());
		        e.printStackTrace();
	        }
	        
			return null;
			
			}
		
		protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
			//int i = user_details.size();
			
            for(int j=0;j<count;j++) {
				// Create TextView
	            TextView product = new TextView(Information.this);
	            product.setText((j+1)+"\nLocation_to: " +user_details.get(j).get("loc_to")+ "\nLocation_from: "+user_details.get(j).get("loc_from") + "\nDrop_time: "+user_details.get(j).get("drop_time")+ "\nDrop_date: "+user_details.get(j).get("drop_date")+ "\nPurpose: "+user_details.get(j).get("purpose"));
	            linear.addView(product);
	             
			}
			
        }
		
		
	}
	
	
	public class fetch_time extends AsyncTask<String, String, String> {
	    String drop_time;
	    String loc_to;
	    String loc_from;
	    String purpose;
	    String drop_date;
	    String id;
	    String name;
	    
		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        count = 0;
	        
	    }
		
		protected String doInBackground(String... args) {
			
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("Time", information.getText().toString()));
		    
	        try {
	        	HttpParams httpParameters = new BasicHttpParams();
	        	HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost(time_info_request);
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				
				String result = EntityUtils.toString(entity);
				
				// Create a JSON object from the request response
				JSONArray jsonArray = new JSONArray(result);
				int len = jsonArray.length();
				for (int i = 0; i < jsonArray.length(); i++) {
				    JSONObject detail = jsonArray.getJSONObject(i);
				    if(detail != null){
				    	loc_to = detail.getString("loc_to");
				    	loc_from = detail.getString("loc_from");
				    	purpose = detail.getString("purpose");
				    	drop_time = detail.getString("drop_time");
				    	drop_date = detail.getString("drop_date");
				    	id = detail.getString("id");
				    	name = detail.getString("name");
				    	
				    
				    	HashMap<String, String> one_detail = new HashMap<String, String>();
				    	
				    	one_detail.put("loc_to", loc_to);
				    	one_detail.put("loc_from", loc_from);
				    	one_detail.put("drop_date", drop_date);
				    	one_detail.put("drop_time", drop_time);
				    	one_detail.put("purpose", purpose);
				    	one_detail.put("id", id);
				    	one_detail.put("name", name);
				    
				    	user_details.add(one_detail);
				    	count++;
				    }
				}
		    } 
		    catch (Exception e) {
	        	Log.e("Buffer Error", "Error converting result " + e.toString());
		        e.printStackTrace();
	        }
	        
			return null;
			
			}
		
		protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
			//int i = user_details.size();
			
            for(int j=0;j<count;j++) {
				// Create TextView
	            TextView product = new TextView(Information.this);
	            product.setText((j+1)+"\nid: " +user_details.get(j).get("id")+"\nname: " +user_details.get(j).get("name")+ "\nLocation_to: " +user_details.get(j).get("loc_to")+ "\nLocation_from: "+user_details.get(j).get("loc_from") + "\nDrop_time: "+user_details.get(j).get("drop_time")+ "\nDrop_date: "+user_details.get(j).get("drop_date")+ "\nPurpose: "+user_details.get(j).get("purpose"));
	            linear.addView(product);
	             
			}
			
        }
		
		
	}
}
