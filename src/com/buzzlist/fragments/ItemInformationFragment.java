package com.buzzlist.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buzzlist.R;
import com.buzzlist.models.Item;
import com.buzzlist.models.Tag;

public class ItemInformationFragment extends Fragment
{
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) 
	{
		final View view = inflater.inflate(R.layout.posted_item_info, parent, false);
		
		//ImageView image = (ImageView)view.findViewById(R.id.item_info_image);
		
		TextView name = (TextView)view.findViewById(R.id.item_title);
		TextView price = (TextView)view.findViewById(R.id.item_price);
		TextView date = (TextView)view.findViewById(R.id.date_posted);
		TextView decrip = (TextView)view.findViewById(R.id.description_content);
		TextView tagList = (TextView)view.findViewById(R.id.tags_list);
		
		String tags = "";
		
		Bundle b = getArguments();
		Item item = (Item)b.get(getResources().getString(R.string.bundle_item_model));
		
		for(Tag tag: item.getTagList())
		{
			tags = tags + " " + tag.getName();
		}
		
		name.setText(item.getName());
	    decrip.setText(item.getDescription());
	    price.setText("" + item.getPrice());
	    date.setText(item.getCreatedAt().toString());
	    tagList.setText(tags);
		
		return view;
	}
}
