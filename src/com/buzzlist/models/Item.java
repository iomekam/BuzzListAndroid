package com.buzzlist.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item implements Serializable
{
	public Item(String name, double price, int itemID, int userID,
			String description, String imagePath, boolean hasImage, boolean hasBestOffer,
			int categoryID, Date createdAt, Date modifiedAt, List<Tag> tagList) {
		super();
		this.name = name;
		this.price = price;
		this.itemID = itemID;
		this.userID = userID;
		this.description = description;
		this.imagePath = imagePath;
		this.hasImage = hasImage;
		this.hasBestOffer = hasBestOffer;
		this.categoryID = categoryID;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		
		this.tagList = tagList;
	}


	private static final long serialVersionUID = 7805152687630550497L;
	private String name;
	private double price;
	private int itemID;
	private int userID;
	private String description;
	private String imagePath;
	private int categoryID;
	private Date createdAt;
	private Date modifiedAt;	
	private List<Tag> tagList;
	private boolean hasImage;
	private boolean hasBestOffer;

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getItemID() {
		return itemID;
	}

	public int getUserID() {
		return userID;
	}

	public String getDescription() {
		return description;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getModified() {
		return modifiedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Tag> getTagList() {
		return tagList;
	}
	
	public boolean isHasImage() {
		return hasImage;
	}

	public boolean isHasBestOffer() {
		return hasBestOffer;
	}

	public static Item decodeJSON(JSONObject obj) {
		try {		
			int itemID = obj.getInt("item_id");
			int userID = obj.getInt("user_id");
			String name = obj.getString("name");
			double price = obj.getDouble("price");
			String description = obj.getString("description");
			String imagePath = obj.getString("image_path");
			int categoryID = obj.getInt("category_id");
			String createdDate = obj.getString("created_at");
			String modifiedDate = obj.getString("modified_at");
			boolean hasImage = obj.getBoolean("has_image");
			boolean hasBestOffer = obj.getBoolean("accepts_best_offer");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			
			List<Tag> tagList = new ArrayList<Tag>();
			
			JSONArray arr = obj.getJSONArray("tags");
			
			for(int count = 0; count < arr.length(); count++)
			{
				Tag tag = Tag.decodeJSON(arr.getJSONObject(count));
				tagList.add(tag);
			}
			
			return new Item(name, price, itemID, userID, description, imagePath, hasImage, hasBestOffer, categoryID,
					dateFormat.parse(createdDate), dateFormat.parse(modifiedDate), tagList );
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


}
