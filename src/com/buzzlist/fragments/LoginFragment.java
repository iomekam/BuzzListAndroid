package com.buzzlist.fragments;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.buzzlist.HomeActivity;
import com.buzzlist.BrowseItemsActivity;
import com.buzzlist.R;
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
	            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dev.m.gatech.edu/login?url=buzzlist://loggedin&sessionTransfer=window"));
	            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(myIntent);
	        }	
	    });
		
		return view;
	}
}
