package com.buzzlist;

import com.buzzlist.base.BuzzListFragmentActivity;
import com.buzzlist.fragments.ItemInformationFragment;
import android.os.Bundle;

public class ItemInformationActivity extends BuzzListFragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_information); 
        
        if (savedInstanceState == null) {
			ItemInformationFragment frag = new ItemInformationFragment();
			frag.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, frag)
                    .commit();
        }
    }
}
