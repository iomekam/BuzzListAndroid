package com.buzzlist.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Item implements Serializable
{
	private static final long serialVersionUID = 7805152687630550497L;

	public Item(String name, double price, int itemID, int userID,
			String description, String imagePath, String created, String modified) {
		this.name = name;
		this.price = price;
		this.itemID = itemID;
		this.userID = userID;
		this.description = description;
		this.imagePath = imagePath;
		this.created = created;
		this.modified = modified;
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	public String getModified() {
		return modified;
	}


	public void setModified(String modified) {
		this.modified = modified;
	}


	private String name;
	private double price;
	private int itemID;
	private int userID;
	private String description;
	private String imagePath;
	private String created;
	private String modified;



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


	public static Item decodeJSON(JSONObject obj) {
		try {		
			int itemID = obj.getInt("item_id");
			int userID = obj.getInt("user_id");
			String name = obj.getString("name");
			double price = obj.getDouble("price");
			String description = obj.getString("description");
			String imagePath = obj.getString("image_path");
			String createdDate = obj.getString("created_at");
			String modifiedDate = obj.getString("modified_at");
			
			return new Item(name, price, itemID, userID, description, imagePath,createdDate, modifiedDate );
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


}
