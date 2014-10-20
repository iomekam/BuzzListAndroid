package com.buzzlist.base;

import java.util.ArrayList;
import com.buzzlist.BrowseItemsActivity;
import com.buzzlist.R;
import com.buzzlist.globals.JsonFields;
import com.buzzlist.models.BuzzListNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;

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

	    View popupView = getLayoutInflater().inflate(R.layout.new_pop_up, null);
	    
	    final View layout = getLayoutInflater().inflate(R.layout.popup_fade,
	            (ViewGroup) findViewById(R.id.popup_fade));
	    
	    final PopupWindow fadePopup = new PopupWindow(layout, 
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	    fadePopup.setBackgroundDrawable(new ColorDrawable());

	    PopupWindow popupWindow = new PopupWindow(popupView, 
	                           LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

	    // If the PopupWindow should be focusable
	    popupWindow.setFocusable(true);
	    
	    // If you need the PopupWindow to dismiss when when touched outside 
	    popupWindow.setBackgroundDrawable(new ColorDrawable());
	    
	    // Using location, the PopupWindow will be displayed right under anchorView
	    popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
	    
	    View view = getLayoutInflater().inflate(R.layout.new_pop_up, null);
	    
	    final CheckBox obo = (CheckBox) view.findViewById(R.id.checkBox3);
	    final CheckBox bTag = (CheckBox) view.findViewById(R.id.checkBox1);
	    final CheckBox hImage = (CheckBox) view.findViewById(R.id.checkBox2);

	    Button searchGo = (Button) popupView.findViewById(R.id.go_button);
	    Button incPrice = (Button) view.findViewById(R.id.go_button);
	    Button decPrice = (Button) view.findViewById(R.id.go_button);
	    Button recent = (Button) view.findViewById(R.id.go_button);
	    
	    final EditText search = (EditText) popupView.findViewById(R.id.editText1);
	    
	    final Activity activity = this;

		searchGo.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) 
			{			
				Log.e("ff", search.getText().toString());
				ArrayList<BuzzListNameValuePair> params = new ArrayList<BuzzListNameValuePair>();
            	params.add(new BuzzListNameValuePair(JsonFields.Search.SEARCH, search.getText().toString()));
            	params.add(new BuzzListNameValuePair(JsonFields.Search.CATEGORY, "" + 0));
            	params.add(new BuzzListNameValuePair(JsonFields.Search.IMAGE, "1")); //hImage.isSelected() ? "1":"0"));
            	params.add(new BuzzListNameValuePair(JsonFields.Search.BEST_OFFER, "1"));//obo.isSelected() ? "1":"0"));
            	params.add(new BuzzListNameValuePair(JsonFields.Search.TAG, "1"));//bTag.isSelected() ? "1":"0"));
            	params.add(new BuzzListNameValuePair(JsonFields.Search.TITLE, "1"));
            	params.add(new BuzzListNameValuePair(JsonFields.Search.SORT, "" + JsonFields.Search.SortType.RECENT));
            	
            	Intent intent = new Intent(activity, BrowseItemsActivity.class);
				intent.putExtra(getResources().getString(R.string.browse_items_params), params);
				startActivity(intent);
			}
		});
		incPrice.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		decPrice.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		recent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}
}
