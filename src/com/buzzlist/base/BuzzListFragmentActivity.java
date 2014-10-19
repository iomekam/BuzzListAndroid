package com.buzzlist.base;

import com.buzzlist.R;

import android.support.v4.app.FragmentActivity;
import android.view.Menu;

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
}
