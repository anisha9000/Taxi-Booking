package com.example.test;


import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

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
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

	EditText id;
	EditText password_edittext;
	Button login_button;
	EditText information;
	
	String username;
	String managerid;
	String charge_code;
	String plot;
	String level;
	String address;
	
	private static String url_login = "http://192.168.8.100/login.php";
	//private static String url_login = "http://10.65.171.33/login.php";
	//private static String TAG_SUCCESS = "success";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		id = (EditText) findViewById(R.id.id);
		password_edittext = (EditText) findViewById(R.id.password);
		information = (EditText) findViewById(R.id.information);
		login_button = (Button) findViewById(R.id.login);
		
		login_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Intent intent = new Intent (MainActivity.this, Request_taxi.class);
				//startActivity(intent);
				new login().execute();
				//login();
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	public class login extends AsyncTask<String, String, String> {
		
		String user_id;
		String password;

		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        
	    }
		
		protected String doInBackground(String... args) {
			user_id = id.getText().toString();
			password = password_edittext.getText().toString();
			
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("Userid", user_id));
			nameValuePairs.add(new BasicNameValuePair("Password", password));
		    
	        try {
	        	HttpParams httpParameters = new BasicHttpParams();
	        	HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost(url_login);
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				
				String result = EntityUtils.toString(entity);
				
				
				// Create a JSON object from the request response
				JSONObject jsonObject = new JSONObject(result);
				
				username = jsonObject.getString("username");
				managerid = jsonObject.getString("managerid");
				charge_code = jsonObject.getString("charge_code");
				plot = jsonObject.getString("plot");
				level = jsonObject.getString("level");
				address = jsonObject.getString("address");
				

		    } 
		    catch (Exception e) {
	        	Log.e("Buffer Error", "Error converting result " + e.toString());
		        e.printStackTrace();
	        }
	        
			return null;
			
			}
		
		protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
			if (level.equals("e2")) {
				Intent intent = new Intent (MainActivity.this, Request_taxi.class);
	        	intent.putExtra("user_id", user_id);
	        	startActivity(intent);
			} else if(level.equals("e5")) {
				Intent intent = new Intent (MainActivity.this, Manager.class);
	        	//intent.putExtra("user_id", user_id);
	        	startActivity(intent);
			} else {
				information.setText("user_id:"+ user_id +" Password:"+ password +" Level: " + level + " Login failed");
				//information.setText("Login failed");
			}
           
        }
		
		
	}
	
}

