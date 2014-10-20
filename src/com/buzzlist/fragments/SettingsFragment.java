package com.buzzlist.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.buzzlist.R;

public class SettingsFragment extends Fragment {
	private CheckBox obo, hImage, bTag;
	private EditText search;
	private Button searchGo, incPrice, decPrice, recent;
	private int currButton = 0;

	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.new_pop_up, parent, false);
		obo = (CheckBox) view.findViewById(R.id.checkBox3);
		bTag = (CheckBox) view.findViewById(R.id.checkBox1);
		hImage = (CheckBox) view.findViewById(R.id.checkBox2);

		searchGo = (Button) view.findViewById(R.id.go_button);
		incPrice = (Button) view.findViewById(R.id.go_button);
		decPrice = (Button) view.findViewById(R.id.go_button);
		recent = (Button) view.findViewById(R.id.go_button);

		searchGo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		recent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(currButton!=0){
					if(currButton == 1){
						recent.setBackgroundColor(Color.parseColor("#8FD8D8"));
						incPrice.setBackgroundColor(Color.parseColor("#ffffff"));
					}
					else if(currButton == 2){
						recent.setBackgroundColor(Color.parseColor("#8FD8D8"));
						decPrice.setBackgroundColor(Color.parseColor("#ffffff"));
					}
				}

			}
		});
		incPrice.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(currButton!=1){
					if(currButton == 0){
						incPrice.setBackgroundColor(Color.parseColor("#8FD8D8"));
						recent.setBackgroundColor(Color.parseColor("#ffffff"));
					}
					else if(currButton == 2){
						incPrice.setBackgroundColor(Color.parseColor("#8FD8D8"));
						decPrice.setBackgroundColor(Color.parseColor("#ffffff"));
					}
				}

			}
		});
		decPrice.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(currButton!=2){
					if(currButton == 0){
						decPrice.setBackgroundColor(Color.parseColor("#8FD8D8"));
						recent.setBackgroundColor(Color.parseColor("#ffffff"));
					}
					else if(currButton == 1){
						decPrice.setBackgroundColor(Color.parseColor("#8FD8D8"));
						incPrice.setBackgroundColor(Color.parseColor("#ffffff"));
					}
				}

			}
		});
		

		search = (EditText) view.findViewById(R.id.editText1);
		obo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub

				if (buttonView.isChecked()) {

				} else {

				}

			}
		});
		bTag.setChecked(true);
		bTag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub

				if (buttonView.isChecked()) {

				} else {

				}

			}
		});
		hImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub

				if (buttonView.isChecked()) {

				} else {

				}

			}
		});

		return view;
	}

}
