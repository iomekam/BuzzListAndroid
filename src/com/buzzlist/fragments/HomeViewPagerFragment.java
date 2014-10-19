package com.buzzlist.fragments;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.buzzlist.R;
import com.buzzlist.adapter.ItemAdapter;
import com.buzzlist.globals.JsonFields;
import com.buzzlist.globals.Routing;
import com.buzzlist.http.HttpManager;
import com.buzzlist.http.HttpManager.Request;
import com.buzzlist.models.Item;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.support.v4.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class HomeViewPagerFragment extends Fragment {
	
	 private List<Item> items;
	 private ItemAdapter adapter;
	 private ImageView bigtile, leftTile, rightTop, rightBot;
	 public static HomeViewPagerFragment newInstance(int index) {
		 HomeViewPagerFragment f = new HomeViewPagerFragment();

	        // Supply index input as an argument.
	        Bundle args = new Bundle();
	        args.putInt("index", index);
	        f.setArguments(args);

	        return f;
	    }
	 
	 public int getShownIndex() {
	        return getArguments().getInt("index", 0);
	   }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) 
	{
		final View view = inflater.inflate(R.layout.home_pager_view, parent, false);
		bigtile = (ImageView) view.findViewById(R.id.big_tile);
		leftTile = (ImageView) view.findViewById(R.id.big_tile);
		
		items = new ArrayList<Item>();
		final String url = Routing.SERVER_URL + Routing.ITEM;
		
		List<ImageView> lol = new ArrayList<ImageView>();
		lol.add(bigtile);
		
		HttpTaskGet task = new HttpTaskGet();
		task.execute(url);
		
		
		return view;
	}
	

	public static Bitmap getBitmapFromURL(String src) {
	    try {
	        URL url = new URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
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
						ImageLoader.getInstance().displayImage(item.getImagePath(), bigtile);
					}
					
					
					
				}
			} 
			catch (JSONException e) {}
		}
	}
	
}
