package com.buzzlist.adapter;

import java.util.List;

import com.buzzlist.R;
import com.buzzlist.models.Item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<Item>
{
    public ItemAdapter(Context context, int layoutResourceId, List<Item> data) {
        super(context, layoutResourceId, data);
    }

    @SuppressLint("DefaultLocale")
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Item item = null;
        
        if(row == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.list_row_item, parent, false);
            
            item = getItem(position);
            
            ImageView imageView = (ImageView)row.findViewById(R.id.image_ad);
            TextView name = (TextView)row.findViewById(R.id.list_item_title);
            TextView description = (TextView) row.findViewById(R.id.list_item_description);
            TextView price = (TextView)row.findViewById(R.id.image_item_text);
            TextView date = (TextView)row.findViewById(R.id.image_date_text);

            name.setText(item.getName().toUpperCase());
            description.setText(item.getDescription());
            price.setText("$" + item.getPrice());
            date.setText(item.getCreated());
            
            if(position%2 == 0){
            	description.setTextColor(Color.parseColor("#ffffff"));
            	row.setBackgroundColor(Color.parseColor("#125C90"));
            }
        }

        return row;
    }
}
