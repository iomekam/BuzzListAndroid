package com.buzzlist;

import com.buzzlist.fragments.BrowseItemsFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class BrowseItemsActivity extends FragmentActivity {
		   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
    	getActionBar().setDisplayShowHomeEnabled(false);              
        getActionBar().setDisplayShowTitleEnabled(false);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_items); 
        
        DisplayImageOptions options = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.generic_item)
        .showImageForEmptyUri(R.drawable.generic_item)
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .build();
        
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        	.defaultDisplayImageOptions(options)
        	.build();
        
        ImageLoader.getInstance().init(config);
        
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new BrowseItemsFragment())
                    .commit();
        }
    }
}
