package com.buzzlist.base;

import com.buzzlist.R;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class BuzzListFragmentActivity extends ActionBarActivity 
{
	@Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
    	// Inflate the menu; this adds items to the action bar if it is present.
		
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
        getMenuInflater().inflate(R.menu.task_menu, menu);
        
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) 
	    {
	    case R.id.action_add:
	    	Log.e("menu click", "add");
	        return true;
	    case R.id.action_profile:
	    	Log.e("menu click", "profile");
	        return true;
	    case R.id.action_search:
	    	Log.e("menu click", "search");
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
}
