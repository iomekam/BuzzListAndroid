package com.buzzlist.base;

import com.buzzlist.R;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class BuzzListFragmentActivity extends FragmentActivity 
{
	@Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
    	// Inflate the menu; this adds items to the action bar if it is present.
    	getActionBar().setDisplayShowHomeEnabled(false);
    	getActionBar().setDisplayShowTitleEnabled(false);
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
