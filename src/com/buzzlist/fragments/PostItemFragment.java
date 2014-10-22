package com.buzzlist.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.buzzlist.ItemInformationActivity;
import com.buzzlist.R;
import com.buzzlist.adapter.ItemAdapter;
import com.buzzlist.globals.JsonFields;
import com.buzzlist.globals.Routing;
import com.buzzlist.http.HttpManager;
import com.buzzlist.http.HttpManager.Request;
import com.buzzlist.models.BuzzListNameValuePair;
import com.buzzlist.models.Item;
import com.buzzlist.models.Tag;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class PostItemFragment extends Fragment {
	private ListView listView;
	private List<Item> items;
	private ItemAdapter adapter;
	private Button post, cancel, postTag, addImage;
	private EditText itemName, itemPrice, item_description, itemTag;
	private CheckBox negotiable;
	private ArrayList<String> tags;
	private ArrayList<Button> tagButtons;
	private LinearLayout populator;


	@SuppressWarnings("unchecked")
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.post_item_fragment, parent,
				false);
		tags = new ArrayList<String>();
		tagButtons = new ArrayList<Button>();
		
		populator = (LinearLayout) view.findViewById(R.id.post_item_tag_populator_layout);

		post = (Button) view.findViewById(R.id.post_item_post_button);
		cancel = (Button) view.findViewById(R.id.post_item_post_cancel);
		postTag = (Button) view.findViewById(R.id.post_item_tag_button);
		addImage = (Button) view.findViewById(R.id.add_image_button);

		itemName = (EditText) view
				.findViewById(R.id.post_item_item_name_edit_text);
		itemPrice = (EditText) view
				.findViewById(R.id.post_item_item_price_edit_text);
		item_description = (EditText) view
				.findViewById(R.id.post_item_item_description_edit_text);
		itemTag = (EditText) view.findViewById(R.id.post_item_tag_edit_text);

		negotiable = (CheckBox) view
				.findViewById(R.id.post_item_price_negotiable);

		post.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(itemName.getEditableText().toString().length() == 0
						|| itemPrice.getEditableText().toString().length()==0
						|| item_description.getEditableText().toString().length()==0){
					Toast.makeText(getActivity(), "One or more required fields are empty", Toast.LENGTH_SHORT).show();
				}
				else{
					
				}
			}
		});

		cancel.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		postTag.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(itemTag.getEditableText().toString().length() != 0){
					final Button myButton = new Button(getActivity().getApplicationContext());
					myButton.setText(itemTag.getEditableText().toString());
					
					tagButtons.add(myButton);
					tags.add(itemTag.getEditableText().toString());
					
					itemTag.setText("");
					
					myButton.setOnClickListener(new Button.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							Button this_button = (Button)v;
							tags.remove(this_button.getText());
							tagButtons.remove(this_button);
							this_button.setVisibility(View.GONE);
							Log.e("Tag List Size", "" + tags.size());
							Log.e("Tag Button Size", "" + tagButtons.size());
						}
					});
					
					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					populator.addView(myButton, lp);
				}

			}
		});
		addImage.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		
		

		//for (BuzzListNameValuePair pair : params) {
		//	getParams.add(pair);
		//}

		

		final String url = Routing.SERVER_URL + Routing.SEARCH;


		return view;
	}

	
}
