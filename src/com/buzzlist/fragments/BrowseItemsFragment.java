package com.buzzlist.fragments;

import java.util.ArrayList;
import java.util.List;

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
import com.buzzlist.models.Item;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BrowseItemsFragment extends Fragment {

	private ListView listView;
	private List<Item> items;
	private ItemAdapter adapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) 
	{
		final View view = inflater.inflate(R.layout.fragment_browse_items, parent, false);
		
		items = new ArrayList<Item>();
        listView = (ListView)view.findViewById(R.id.browse_list_view);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Item model = (Item) listView.getItemAtPosition(position);
				
				Intent intent = new Intent(getActivity(), ItemInformationActivity.class);
				intent.putExtra(getResources().getString(R.string.bundle_item_model), model);
				startActivity(intent);
			}
        });
		
		final String url = Routing.SERVER_URL + Routing.ITEM;
		
		HttpTaskGet task = new HttpTaskGet();
		task.execute(url);
		
		return view;
	}
	
	private class HttpTaskGet extends AsyncTask<String, String, String>
	{
		@Override
		protected String doInBackground(String... params) {
			return HttpManager.getContent((String)params[0], Request.GET);
		}	
		
		@Override
		protected void onPostExecute(String result) 
		{
			try {
				JSONObject obj = new JSONObject(result);
				boolean error = obj.getBoolean(JsonFields.ERROR);
				
				JSONArray arr = obj.getJSONArray(JsonFields.Item.ITEMS_ARRAY);
				
				if(!error)
				{	
					for(int count = 0; count < arr.length(); count++)
					{
						Item item = Item.decodeJSON(arr.getJSONObject(count));
						items.add(item);
					}
					
					adapter = new ItemAdapter(getActivity(), R.layout.list_row_item, items);
					listView.setAdapter(adapter);
				}
			} 
			catch (JSONException e) {}
		}
	}
}
