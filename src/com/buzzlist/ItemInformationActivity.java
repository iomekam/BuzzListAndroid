package com.buzzlist;

import com.buzzlist.fragments.ItemInformationFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class ItemInformationActivity extends FragmentActivity
{
	@Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        return true;
    }

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
