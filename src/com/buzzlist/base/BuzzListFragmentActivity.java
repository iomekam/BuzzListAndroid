package com.buzzlist.base;

import com.buzzlist.BrowseItemsActivity;
import com.buzzlist.R;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

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
	    	PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.action_search)); 
	    	//popupMenu.inflate(R.menu.task_menu);
	    	//popupMenu.show();
	    	
	    	showPopup(findViewById(R.id.action_search));
	    	
	        return true;
	    case R.id.action_profile:
	    	showPopup(findViewById(R.id.action_search));
	        return true;
	    case R.id.action_search:
	    	showPopup(findViewById(R.id.action_search));
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	public void showPopup(View anchorView) {

	    View popupView = getLayoutInflater().inflate(R.layout.popup_search, null);
	    
	    LayoutInflater  inflator =  (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    final View layout = inflator.inflate(R.layout.popup_fade,
	            (ViewGroup) findViewById(R.id.popup_fade));
	    
	    final PopupWindow fadePopup = new PopupWindow(layout, 
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	    fadePopup.setBackgroundDrawable(new ColorDrawable());
	    fadePopup.showAsDropDown(anchorView);
	    fadePopup.showAtLocation(anchorView, Gravity.NO_GRAVITY, 0, 0);

	    PopupWindow popupWindow = new PopupWindow(popupView, 
	                           LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


	    // If the PopupWindow should be focusable
	    popupWindow.setFocusable(true);
	    
	    // If you need the PopupWindow to dismiss when when touched outside 
	    popupWindow.setBackgroundDrawable(new ColorDrawable());
	    popupWindow.setOnDismissListener(new OnDismissListener()
	    {
			@Override
			public void onDismiss() {
				fadePopup.dismiss();
				
			}
	    });
	    
	    // Using location, the PopupWindow will be displayed right under anchorView
	    popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);

	}
}
