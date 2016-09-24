package com.example.test;

import java.util.ArrayList;
import java.util.Calendar;
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
import android.text.format.DateFormat;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class Request_taxi extends ActionBarActivity {
	
	Spinner fav_group;
	EditText loc_to_edittext;
	EditText loc_from_edittext;
	EditText loc_purpose_edittext;
	EditText vehicle_type_edittext;
	ArrayList<HashMap<String, String>> fav_details;
	String[] fav_name_list;
	String user_id;
	TimePicker drop_time;
	DatePicker drop_date;
	Button send_fav;
	EditText information;
	
	int hour;
	int minute;
	int year;
	int month;
	int day;
	
	String loc_to_str;
	String loc_from_str;
	String loc_purpose_str;
	String vehicle_type_str;
	String drop_time_str;
	String drop_date_str;

	
	private static String url_request = "http://192.168.8.100/find_fav.php";
	private static String url_send = "http://192.168.8.100/send_data.php";
	//private static String url_request = "http://10.65.171.33/find_fav.php";
	//private static String url_send = "http://10.65.171.33/send_data.php";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request_taxi);
		
		Intent intent = getIntent();
		user_id = intent.getStringExtra("user_id");
		
		fav_group = (Spinner) findViewById(R.id.favorite_group);
		loc_to_edittext = (EditText) findViewById(R.id.location_to);
		loc_from_edittext = (EditText) findViewById(R.id.location_from);
		loc_purpose_edittext = (EditText) findViewById(R.id.location_purpose);
		vehicle_type_edittext = (EditText) findViewById(R.id.vehicle_type);
		drop_time = (TimePicker) findViewById(R.id.timePicker1);
		drop_date = (DatePicker) findViewById(R.id.datePicker1);
		send_fav = (Button) findViewById(R.id.send_fav);
		information = (EditText) findViewById(R.id.information);
		fav_details = new ArrayList<HashMap<String, String>>();
		drop_time.setIs24HourView(DateFormat.is24HourFormat(this));
		
		new fetch_fav().execute();
		
		fav_group.setOnItemSelectedListener(
	              new AdapterView.OnItemSelectedListener() {
	                  @Override
	                  public void onItemSelected(AdapterView<?> arg0, View arg1,
	                          int arg2, long arg3) {
	                	  
	                	  int position = fav_group.getSelectedItemPosition();
	                	  if(fav_name_list[position].equals("others")) {
	                		  loc_to_edittext.setText("");
	                		  loc_from_edittext.setText("");
	                		  loc_purpose_edittext.setText("");
	                		  vehicle_type_edittext.setText("");
	                	  }else {
	                		  loc_to_edittext.setText(fav_details.get(position).get("loc_to"));
	                		  loc_from_edittext.setText(fav_details.get(position).get("loc_from"));
	                		  loc_purpose_edittext.setText(fav_details.get(position).get("purpose"));
	                		  vehicle_type_edittext.setText(fav_details.get(position).get("vehicle_type"));
	                	  }
	                      // TODO Auto-generated method stub
	                  }
	                  @Override
	                  public void onNothingSelected(AdapterView<?> arg0) {
	                      // TODO Auto-generated method stub
	                  }
	              }
	          );
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		drop_time.setCurrentHour(hour);
		drop_time.setCurrentMinute(minute);
		drop_date.init(year, month, day, null);
		
		hour = drop_time.getCurrentHour();
		minute = drop_time.getCurrentMinute();
		day  = drop_date.getDayOfMonth();
        month= drop_date.getMonth() + 1;
        year = drop_date.getYear();
        //drop_date.init(year, month, day, null);
        
        send_fav.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Intent intent = new Intent (MainActivity.this, Request_taxi.class);
				//startActivity(intent);
				new send_data().execute();
				//login();
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.request_taxi, menu);
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
	
public class fetch_fav extends AsyncTask<String, String, String> {
	    String fav_name;
	    String loc_to;
	    String loc_from;
	    String purpose;
	    String vehicle_type;
	    
		@Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        
	    }
		
		protected String doInBackground(String... args) {
			
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("Userid", user_id));
		    
	        try {
	        	HttpParams httpParameters = new BasicHttpParams();
	        	HttpClient httpclient = new DefaultHttpClient(httpParameters);
				HttpPost httppost = new HttpPost(url_request);
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				
				String result = EntityUtils.toString(entity);
				
				// Create a JSON object from the request response
				JSONArray jsonArray = new JSONArray(result);
				int len = jsonArray.length();
				fav_name_list = new String[len+1];
				for (int i = 0; i < jsonArray.length(); i++) {
				    JSONObject detail = jsonArray.getJSONObject(i);
				    if(detail != null){
				    	fav_name = detail.getString("fav_name");
				    	loc_to = detail.getString("loc_to");
				    	loc_from = detail.getString("loc_from");
				    	vehicle_type = detail.getString("vehicle_type");
				    	purpose = detail.getString("purpose");
				    
				    	HashMap<String, String> one_detail = new HashMap<String, String>();
				    	one_detail.put("fav_name", fav_name);
				    	one_detail.put("loc_to", loc_to);
				    	one_detail.put("loc_from", loc_from);
				    	one_detail.put("vehicle_type", vehicle_type);
				    	one_detail.put("purpose", purpose);
				    
				    	fav_details.add(one_detail);
				    	fav_name_list[i] = fav_name;
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
			// Create an ArrayAdapter using the string array and a default spinner layout
			
			fav_name_list[(fav_name_list.length)-1] = "others";
			ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
					Request_taxi.this, android.R.layout.simple_spinner_item, fav_name_list);
			
			// Specify the layout to use when the list of choices appears
			spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			fav_group.setAdapter(spinnerArrayAdapter);
        }
		
		
	}


	public class send_data extends AsyncTask<String, String, String> {
		String success;
		String username;

	@Override
    protected void onPreExecute() {
        super.onPreExecute();
        loc_to_str = loc_to_edittext.getText().toString();
    	loc_from_str = loc_from_edittext.getText().toString();
    	loc_purpose_str = loc_purpose_edittext.getText().toString();
    	vehicle_type_str = vehicle_type_edittext.getText().toString();
    	drop_time_str = hour+":"+minute+":00";
    	drop_date_str = year+"-"+month+"-"+day;        
        
    }
	
	protected String doInBackground(String... args) {
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("Userid", user_id));
		nameValuePairs.add(new BasicNameValuePair("loc_to", loc_to_str));
		nameValuePairs.add(new BasicNameValuePair("loc_from", loc_from_str));
		nameValuePairs.add(new BasicNameValuePair("vehicle_type", vehicle_type_str));
		nameValuePairs.add(new BasicNameValuePair("purpose", loc_purpose_str));
		nameValuePairs.add(new BasicNameValuePair("drop_time", drop_time_str));
		nameValuePairs.add(new BasicNameValuePair("drop_date", drop_date_str));
        try {
        	HttpParams httpParameters = new BasicHttpParams();
        	HttpClient httpclient = new DefaultHttpClient(httpParameters);
			HttpPost httppost = new HttpPost(url_send);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));        
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			
			String result = EntityUtils.toString(entity);
			
			
			// Create a JSON object from the request response
			JSONObject jsonObject = new JSONObject(result);
			if(jsonObject != null) {
			    //username = jsonObject.getString("username");
			    success = jsonObject.getString("success");
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
		if (success.equals("1")) {
			Intent intent = new Intent (Request_taxi.this, MainActivity.class);
        	//intent.putExtra("user_id", user_id);
        	startActivity(intent);
		}
		//information.setText("user_id"+user_id+"loc_to_str:"+loc_to_str+"loc_from_str:"+loc_from_str+"vehicle_type_str:"+vehicle_type_str+"loc_purpose_str:"+loc_purpose_str+"success:"+ success +"username:"+ username +" drop_time_str:"+ drop_time_str + " drop_date_str:"+ drop_date_str +" Insert failed");
		
		
    }
	
	
}

}
