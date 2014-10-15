package com.buzzlist.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.buzzlist.R;
import com.buzzlist.models.Item;

public class ItemInformationFragment extends Fragment
{
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) 
	{
		final View view = inflater.inflate(R.layout.item_info, parent, false);
		
		TextView name = (TextView)view.findViewById(R.id.item_info_name);
		TextView price = (TextView)view.findViewById(R.id.item_info_price);
		EditText decrip = (EditText)view.findViewById(R.id.item_info_description);
		
		Bundle b = getArguments();
		Item item = (Item)b.get(getResources().getString(R.string.bundle_item_model));
		
		name.setText(item.getName());
	    decrip.setText(item.getDescription());
	    price.setText("" + item.getPrice());
		
		return view;
	}
}