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
import android.widget.TextView;

public class HomeViewPagerFragment extends Fragment {

	private List<Item> items;
	private ItemAdapter adapter;
	private ImageView bigTile, leftTile, rightTop, rightBot;
	private TextView bigTilePrice, leftTilePrice, rightTopPrice, rightBotPrice;
	private int pageNum = 0;
	private List<ImageView> tileImageViews;
	private List<TextView> tilePrices;
	
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
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.home_pager_view, parent,
				false);
		bigTile = (ImageView) view.findViewById(R.id.big_tile);
		leftTile = (ImageView) view.findViewById(R.id.medium_left_tile);
		rightTop = (ImageView) view.findViewById(R.id.small_top_tile);
		rightBot = (ImageView) view.findViewById(R.id.small_bottom_tile);

		bigTilePrice = (TextView) view.findViewById(R.id.big_tile_price);
		leftTilePrice = (TextView) view
				.findViewById(R.id.medium_left_tile_price);
		rightTopPrice = (TextView) view.findViewById(R.id.small_top_tile_price);
		rightBotPrice = (TextView) view
				.findViewById(R.id.small_bottom_tile_price);

		items = new ArrayList<Item>();
		final String url = Routing.SERVER_URL + Routing.ITEM;

		tileImageViews = new ArrayList<ImageView>();
		tilePrices = new ArrayList<TextView>();
		tileImageViews.add(bigTile);
		tilePrices.add(bigTilePrice);
		tileImageViews.add(leftTile);
		tilePrices.add(leftTilePrice);
		tileImageViews.add(rightTop);
		tilePrices.add(rightTopPrice);
		tileImageViews.add(rightBot);
		tilePrices.add(rightBotPrice);

		HttpTaskGet task = new HttpTaskGet();
		task.execute(url);

		return view;
	}

	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
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

	private class HttpTaskGet extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... params) {
			return HttpManager.getContent((String) params[0], Request.GET);
		}

		@Override
		protected void onPostExecute(String result) {
			try {
				JSONObject obj = new JSONObject(result);
				boolean error = obj.getBoolean(JsonFields.ERROR);

				JSONArray arr = obj.getJSONArray(JsonFields.Item.ITEMS_ARRAY);

				if (!error) {
					for (int count = 0; count < arr.length(); count++) {
						Item item = Item.decodeJSON(arr.getJSONObject(count));
						items.add(item);
					}
					int index = getShownIndex();
					Log.e("Page Number", ""+ index);
					
					int startIndex = index*4; 
					Log.e("Start index", ""+ startIndex);
					
					for(int i = 0; i < tilePrices.size(); i++){
						
						if(startIndex >= items.size()){
							int newIndex = startIndex%items.size();
							Log.e("Modded index", "PAth: "+ items.get(newIndex).getImagePath() + "  Price: " +items.get(newIndex).getPrice() );
							Log.e("Item Info", ""+ newIndex);
							if(items.get(newIndex).getImagePath().length() !=0){
								ImageLoader.getInstance().displayImage(items.get(newIndex).getImagePath(),
										tileImageViews.get(i));
								tilePrices.get(i).setText("$" + items.get(newIndex).getPrice());
							}
							else{
								i--;
							}
						}
						else{
							Log.e("Unmodified index", ""+ startIndex);
							Log.e("Modded index", "PAth: "+ items.get(startIndex).getImagePath() + "  Price: " +items.get(startIndex).getPrice() );
							if(items.get(startIndex).getImagePath().length() !=0){
							ImageLoader.getInstance().displayImage(items.get(startIndex).getImagePath(),
									tileImageViews.get(i));
							tilePrices.get(i).setText("$" + items.get(startIndex).getPrice());
							}
							else{
								i--;
							}
						}
						startIndex++;
					}
					
				}
			} catch (JSONException e) {
			}
		}
	}

}
