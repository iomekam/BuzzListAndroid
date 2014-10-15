package com.buzzlist.adapter;

import java.util.ArrayList;
import java.util.List;

import com.buzzlist.R;
import com.buzzlist.models.Item;

import android.app.Activity;
import android.content.Context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Item item = null;
        
        if(row == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.row_item, parent, false);
            
            item = getItem(position);
            
            ImageView imageView = (ImageView)row.findViewById(R.id.itemImage);
            TextView name = (TextView)row.findViewById(R.id.itemName);
            TextView price = (TextView)row.findViewById(R.id.itemPrice);
            
            name.setText(item.getName());
            price.setText("" + item.getPrice());
        }

        return row;
    }
}
