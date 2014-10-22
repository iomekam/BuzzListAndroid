package com.buzzlist;

import com.buzzlist.base.BuzzListFragmentActivity;
import com.buzzlist.fragments.BrowseItemsFragment;
import com.buzzlist.fragments.PostItemFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.os.Bundle;

public class PostItemActivity extends BuzzListFragmentActivity {   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_item); 
        
        PostItemFragment frag = new PostItemFragment();
    	frag.setArguments(getIntent().getExtras());
        
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, frag)
                    .commit();
        }
        
        
    }
}