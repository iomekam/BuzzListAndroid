package com.buzzlist;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.buzzlist.globals.Authentication;
import com.buzzlist.globals.JsonFields;
import com.buzzlist.globals.JsonPostFields;
import com.buzzlist.globals.Routing;
import com.buzzlist.http.HttpManager;
import com.buzzlist.http.HttpManager.Request;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class AuthActivity extends Activity {
	
	HttpClient client;
	ProgressDialog progressDialog;
	private final String apiLocation = "http://dev.m.gatech.edu/developer/iomekam3/widget/buzzlist/content/api/username";
	private Activity activity = this;
	
	String sessionName;
	String sessionId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		
		Intent intent = getIntent();

	    // To get the data use
	    Uri data = intent.getData();
	    sessionName = data.getQueryParameter("sessionName");
	    sessionId = data.getQueryParameter("sessionId");
	    
		new FetchTask().execute();

		// TODO Auto-generated method stub
	}

	public class FetchTask extends
			AsyncTask<String, Integer, String> {
		int progress_status;
		
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
			progressDialog = new ProgressDialog(AuthActivity.this);
			progressDialog.setCancelable(true);
			progressDialog.setMessage("Loading...");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMax(100);
			progressDialog.show();
			progress_status = 0;
		}

		@Override
		protected String doInBackground(String... params) {
			client = new DefaultHttpClient();
			
			while (progress_status < 100) {
				progress_status += 1;
				publishProgress(progress_status);
			}
			try {
				URI api = new URI(apiLocation);
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				request.setURI(api);		
				request.setHeader("Cookie", sessionName+"="+sessionId);
				
				HttpResponse response = client.execute(request);
				HttpEntity entity = response.getEntity();
				String str = EntityUtils.toString(entity);
				try 
				{
					JSONObject obj = new JSONObject(str);
					return obj.getString("username");

				} catch (JSONException e) 
				{
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "";

		}

		@Override
		protected void onPostExecute(final String result) {

			super.onPostExecute(result);
			Message message = new Message();
			Handler handler = new Handler()
			{
				@Override
				public void handleMessage(Message msg) {

					super.handleMessage(msg);
					progressDialog.dismiss();
					
					final String url = Routing.SERVER_URL + Routing.LOGIN;
					
					List<NameValuePair> params = new ArrayList<NameValuePair>();
	            	params.add(new BasicNameValuePair(JsonPostFields.Login.USERNAME, result));
	            	params.add(new BasicNameValuePair(JsonPostFields.Login.PASSWORD, ""));
					
					HttpTaskPost task = new HttpTaskPost();
					task.execute(url, params);	
				}
			};
			
			handler.sendMessage(message);

		}

		@Override
		protected void onCancelled() {

			super.onCancelled();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			super.onProgressUpdate(values);
			progressDialog.setProgress(values[0]);
		}
	}
	
	private class HttpTaskPost extends AsyncTask<Object, String, String>
	{
		@SuppressWarnings("unchecked")
		@Override
		protected String doInBackground(Object... params) {
			return HttpManager.getContent((String)params[0], Request.POST, (List<NameValuePair>)params[1]);
		}	
		
		@Override
		protected void onPostExecute(String result) 
		{
			try {
				JSONObject obj = new JSONObject(result);
				
				boolean error = obj.getBoolean(JsonFields.ERROR);
				
				if(!error)
				{
					Authentication.apiKey = obj.getString(JsonFields.Login.API_KEY);
					
					Intent intent = new Intent(activity, HomeActivity.class);
					startActivity(intent);
					
					progressDialog.dismiss();
				}
				else
				{
					Toast.makeText(activity, getResources().getString(R.string.invalid_credentials), Toast.LENGTH_LONG).show();
				}
			} 
			catch (JSONException e) {}
		}
	}

}
