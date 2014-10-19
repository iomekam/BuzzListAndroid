package com.buzzlist;

import com.buzzlist.base.BuzzListFragmentActivity;
import com.buzzlist.fragments.BrowseItemsFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.os.Bundle;

public class BrowseItemsActivity extends BuzzListFragmentActivity {   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_items); 
        
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new BrowseItemsFragment())
                    .commit();
        }
    }
}
