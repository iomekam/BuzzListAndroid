package com.buzzlist.fragments;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.buzzlist.HomeActivity;
import com.buzzlist.R;
import com.buzzlist.TabHostActivity;
import com.buzzlist.globals.Authentication;
import com.buzzlist.globals.JsonFields;
import com.buzzlist.globals.JsonPostFields;
import com.buzzlist.globals.Routing;
import com.buzzlist.globals.Utilities;
import com.buzzlist.http.HttpManager;
import com.buzzlist.http.HttpManager.Request;

public class LoginFragment extends Fragment
{
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) 
	{
		final View view = inflater.inflate(R.layout.fragment_login, parent, false);
		Button loginButton = (Button)view.findViewById(R.id.login_button);
		
		loginButton.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v) 
	        {
	        	final String url = Routing.SERVER_URL + Routing.LOGIN;
	        	
	        	if(Utilities.isOnline(getActivity()))
	        	{
	            	String username = ((EditText)view.findViewById(R.id.login_username)).getText().toString();
	            	String password = ((EditText)view.findViewById(R.id.login_password)).getText().toString();
	            	
	            	if(username.isEmpty() || password.isEmpty())
	            	{
	            		Toast.makeText(getActivity(), R.string.missing_fields, Toast.LENGTH_LONG).show();
	            	}
	            	else
	            	{
		            	List<NameValuePair> params = new LinkedList<NameValuePair>();
		            	params.add(new BasicNameValuePair(JsonPostFields.Login.USERNAME, username));
		            	params.add(new BasicNameValuePair(JsonPostFields.Login.PASSWORD, password));
		            	
		            	HttpTaskPost task = new HttpTaskPost();
		            	task.execute(url, params);
	            	}
	        	}
	        	else
	        	{
	        		Toast.makeText(getActivity(), getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
	        	}
	        }	
	    });
		
		return view;
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
					
					Intent intent = new Intent(getActivity(), HomeActivity.class);
					startActivity(intent);
				}
				else
				{
					Toast.makeText(getActivity(), getResources().getString(R.string.invalid_credentials), Toast.LENGTH_LONG).show();
				}
			} 
			catch (JSONException e) {}
		}
	}
}
