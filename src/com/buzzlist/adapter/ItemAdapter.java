package com.buzzlist.adapter;

import java.util.List;
import java.util.Locale;

import com.buzzlist.R;
import com.buzzlist.models.Item;
import com.nostra13.universalimageloader.core.ImageLoader;

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
	LayoutInflater inflater;
    public ItemAdapter(Context context, int layoutResourceId, List<Item> data) {
        super(context, layoutResourceId, data);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = null;
        ViewHolder holder = null;
        
        if(convertView == null)
        {
        	convertView = inflater.inflate(R.layout.list_row_item, parent, false);
        	holder = new ViewHolder();
        	
        	holder.imageView = (ImageView)convertView.findViewById(R.id.image_ad);
        	holder.name = (TextView)convertView.findViewById(R.id.list_item_title);
        	holder.description = (TextView) convertView.findViewById(R.id.list_item_description);
        	holder.price = (TextView)convertView.findViewById(R.id.image_item_text);
        	holder.date = (TextView)convertView.findViewById(R.id.image_date_text);
        	
        	if(position%2 == 0)
            {
            	holder.description.setTextColor(Color.parseColor("#ffffff"));
            	convertView.setBackgroundColor(Color.parseColor("#125C90"));
            }
        	
        	convertView.setTag(holder);
        }
        else
        {
        	holder = (ViewHolder)convertView.getTag();
        }
            
        item = getItem(position);
   
        ImageLoader.getInstance().displayImage(item.getImagePath(), holder.imageView);
        holder.name.setText(item.getName().toUpperCase(Locale.getDefault()));
        holder.description.setText(item.getDescription());
        holder.price.setText("$" + item.getPrice());
        holder.date.setText(item.getCreatedAt().toString());

        return convertView;
    }
    
    private class ViewHolder
    {
    	ImageView imageView;
        TextView name;
        TextView description;
        TextView price;
        TextView date;
    }
}
