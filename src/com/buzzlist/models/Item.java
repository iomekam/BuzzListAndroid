package com.buzzlist.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Item implements Serializable
{
	private static final long serialVersionUID = 7805152687630550497L;

	public Item(String name, double price, int itemID, int userID,
			String description, String imagePath) {
		this.name = name;
		this.price = price;
		this.itemID = itemID;
		this.userID = userID;
		this.description = description;
		this.imagePath = imagePath;
	}


	private String name;
	private double price;
	private int itemID;
	private int userID;
	private String description;
	private String imagePath;
	

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
			
			return new Item(name, price, itemID, userID, description, imagePath);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


}
