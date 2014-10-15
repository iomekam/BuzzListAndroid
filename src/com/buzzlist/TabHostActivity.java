package com.buzzlist;

import java.util.ArrayList;
import java.util.List;

import com.buzzlist.fragments.BrowseItemsFragment;
import com.buzzlist.fragments.LoginFragment;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class TabHostActivity extends FragmentActivity implements TabListener {
	
	    private ActionBar actionBar;
	    List<Fragment> fragList;
	    
	    private final String TAB_ONE = "Browse";
	    private final String TAB_TWO = "TAB 2";
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) 
	    {
	    	getActionBar().setDisplayShowHomeEnabled(false);              
	        getActionBar().setDisplayShowTitleEnabled(false);

	        return true;
	    }
	    
	    // Tab titles
	    private String[] tabs = { TAB_ONE, TAB_TWO };
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_tab_host);
	        actionBar = getActionBar();
	        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);     
	        
	        /** The tabs in the tab host. Add in order of tab **/
	        fragList = new ArrayList<Fragment>();
	        fragList.add(new BrowseItemsFragment());
	        fragList.add(new LoginFragment());
	 
	        // Adding Tabs
	        for (String tab_name : tabs) {
	            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
	        }
	    }

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction trans) {
			Fragment tabFragment = null;
			
			tabFragment = fragList.get(tab.getPosition());
			trans.replace(android.R.id.content, tabFragment);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

}
