package com.buzzlist.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item implements Serializable
{
	public Item(String name, double price, int itemID, int userID,
			String description, String imagePath,
			int categoryID, Date createdAt, Date modifiedAt, List<Tag> tagList) {
		super();
		this.name = name;
		this.price = price;
		this.itemID = itemID;
		this.userID = userID;
		this.description = description;
		this.imagePath = imagePath;
		this.categoryID = categoryID;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}


	private static final long serialVersionUID = 7805152687630550497L;

	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date created) {
		this.createdAt = created;
	}


	public Date getModified() {
		return modifiedAt;
	}


	public void setModified(Date modified) {
		this.modifiedAt = modified;
	}


	private String name;
	private double price;
	private int itemID;
	private int userID;
	private String description;
	private String imagePath;
	private int categoryID;
	private Date createdAt;
	private Date modifiedAt;
	
	List<Tag> tagList;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getItemID() {
		return itemID;
	}


	public void setItemID(int itemID) {
		this.itemID = itemID;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getCategoryID() {
		return categoryID;
	}


	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}


	public Date getModifiedAt() {
		return modifiedAt;
	}


	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public List<Tag> getTagList() {
		return tagList;
	}


	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
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
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			
			List<Tag> tagList = new LinkedList<Tag>();
			
			JSONArray arr = obj.getJSONArray("tags");
			
			for(int count = 0; count < arr.length(); count++)
			{
				Tag tag = Tag.decodeJSON(arr.getJSONObject(count));
				tagList.add(tag);
			}
			
			return new Item(name, price, itemID, userID, description, imagePath, categoryID,
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
