package com.buzzlist.base;

import java.util.ArrayList;

import com.buzzlist.BrowseItemsActivity;
import com.buzzlist.PostItemActivity;
import com.buzzlist.R;
import com.buzzlist.globals.JsonFields;
import com.buzzlist.models.BuzzListNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ToggleButton;

public class BuzzListFragmentActivity extends ActionBarActivity {
	private int currButton = JsonFields.Search.SortType.RECENT;
	private Button searchGo, incPrice, decPrice, recent;
	private CheckBox obo, bTag, hImage, bTitle;
	private ToggleButton tBTitle, tBTag, tBImage, tBObo;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getMenuInflater().inflate(R.menu.task_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_add:
			Intent intent = new Intent(this, PostItemActivity.class);
			startActivity(intent);

			return true;
		case R.id.action_profile:
			showPopup(findViewById(R.id.action_search));
			return true;
		case R.id.action_search:
			showPopup(findViewById(R.id.action_search));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void showPopup(View anchorView) {

		View popupView = getLayoutInflater().inflate(R.layout.new_pop_up, null);

		final View layout = getLayoutInflater().inflate(R.layout.popup_fade,
				(ViewGroup) findViewById(R.id.popup_fade));

		final PopupWindow fadePopup = new PopupWindow(layout,
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		fadePopup.setBackgroundDrawable(new ColorDrawable());

		PopupWindow popupWindow = new PopupWindow(popupView,
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		// If the PopupWindow should be focusable
		popupWindow.setFocusable(true);

		// If you need the PopupWindow to dismiss when when touched outside
		popupWindow.setBackgroundDrawable(new ColorDrawable());

		// Using location, the PopupWindow will be displayed right under
		// anchorView
		popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);

		View view = getLayoutInflater().inflate(R.layout.new_pop_up, null);
		


		obo = (CheckBox) popupView.findViewById(R.id.negotiable_check);
		bTag = (CheckBox) popupView.findViewById(R.id.tags_check);
		hImage = (CheckBox) popupView.findViewById(R.id.image_check);
		bTitle = (CheckBox) popupView.findViewById(R.id.title_check);
		
		tBTitle = (ToggleButton) popupView.findViewById(R.id.toggle_title);
		tBTag = (ToggleButton) popupView.findViewById(R.id.toggle_tags);
		tBImage =(ToggleButton) popupView.findViewById(R.id.toggle_image);
		tBObo=(ToggleButton) popupView.findViewById(R.id.toggle_negotiable);

		searchGo = (Button) popupView.findViewById(R.id.go_button);
		incPrice = (Button) popupView.findViewById(R.id.inc_price);
		decPrice = (Button) popupView.findViewById(R.id.dec_price);
		recent = (Button) popupView.findViewById(R.id.recent);

		final EditText search = (EditText) popupView
				.findViewById(R.id.editText1);

		final Activity activity = this;

		searchGo.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("ff", search.getText().toString());
				ArrayList<BuzzListNameValuePair> params = new ArrayList<BuzzListNameValuePair>();
				params.add(new BuzzListNameValuePair(JsonFields.Search.SEARCH,
						search.getText().toString()));
				params.add(new BuzzListNameValuePair(
						JsonFields.Search.CATEGORY, "" + 0));
				
				if(obo.isChecked() == true){
					params.add(new BuzzListNameValuePair(
							JsonFields.Search.BEST_OFFER, tBObo.isChecked() ? "1":"0"));				}
				else{
					params.add(new BuzzListNameValuePair(
							JsonFields.Search.BEST_OFFER, "-1"));				}
				if(hImage.isChecked() == true){
					params.add(new BuzzListNameValuePair(JsonFields.Search.IMAGE,tBImage.isChecked() ? "1":"0")); // hImage.isSelected() ? "1":"0"));
				}
				else{
					params.add(new BuzzListNameValuePair(JsonFields.Search.IMAGE,"-1")); // hImage.isSelected() ? "1":"0"));
				}
				if(bTag.isChecked() == true){
					params.add(new BuzzListNameValuePair(JsonFields.Search.TAG, tBTag.isChecked() ? "1":"0"));// bTag.isSelected()
				}
				else{
					params.add(new BuzzListNameValuePair(JsonFields.Search.TAG,"-1"));// bTag.isSelected()
				}
				if(bTitle.isChecked() == true){
					params.add(new BuzzListNameValuePair(JsonFields.Search.TITLE,
							tBTitle.isChecked() ? "1":"0"));				}
				else{
					params.add(new BuzzListNameValuePair(JsonFields.Search.TITLE,
							"-1"));				}																	// ?
			
				params.add(new BuzzListNameValuePair(JsonFields.Search.SORT, ""
						+ currButton));

				Intent intent = new Intent(activity, BrowseItemsActivity.class);
				intent.putExtra(
						getResources().getString(R.string.browse_items_params),
						params);
				
				startActivity(intent);
			}
		});
		incPrice.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("Button Pressed", "" + JsonFields.Search.SortType.PRICE_UP);
				if (currButton != JsonFields.Search.SortType.PRICE_UP) {
					Log.e("Button Pressed", "" + 0);
					if (currButton == JsonFields.Search.SortType.RECENT) {
						incPrice.setBackgroundColor(Color.parseColor("#8FD8D8"));
						recent.setBackgroundColor(Color.parseColor("#ffffff"));
					} else if (currButton == JsonFields.Search.SortType.PRICE_DOWN) {
						incPrice.setBackgroundColor(Color.parseColor("#8FD8D8"));
						decPrice.setBackgroundColor(Color.parseColor("#ffffff"));
					}
					currButton = JsonFields.Search.SortType.PRICE_UP;
				}

			}
		});
		decPrice.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("Button Pressed", "" + 2);
				if (currButton != JsonFields.Search.SortType.PRICE_DOWN) {
					Log.e("Button Pressed", "" + 0);
					if (currButton == JsonFields.Search.SortType.RECENT) {
						decPrice.setBackgroundColor(Color.parseColor("#8FD8D8"));
						recent.setBackgroundColor(Color.parseColor("#ffffff"));
					} else if (currButton == JsonFields.Search.SortType.PRICE_UP) {
						decPrice.setBackgroundColor(Color.parseColor("#8FD8D8"));
						incPrice.setBackgroundColor(Color.parseColor("#ffffff"));
					}
					currButton = JsonFields.Search.SortType.PRICE_DOWN;
				}

			}
		});
		recent.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("Button Pressed", "" + JsonFields.Search.SortType.RECENT);
				if (currButton != JsonFields.Search.SortType.RECENT) {
					Log.e("Button Pressed", "" + 1);
					if (currButton == JsonFields.Search.SortType.PRICE_UP) {
						recent.setBackgroundColor(Color.parseColor("#8FD8D8"));
						incPrice.setBackgroundColor(Color.parseColor("#ffffff"));
					} else if (currButton == JsonFields.Search.SortType.PRICE_DOWN) {
						recent.setBackgroundColor(Color.parseColor("#8FD8D8"));
						decPrice.setBackgroundColor(Color.parseColor("#ffffff"));
					}
					currButton = JsonFields.Search.SortType.RECENT;
				}

			}
		});
	}
}
