package com.buzzlist.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class Category 
{
	public Category(int categoryID, String name, Date createdAt, Date modifiedAt) {
		super();
		this.categoryID = categoryID;
		this.name = name;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	private int categoryID;
	private String name;
	private Date createdAt;
	private Date modifiedAt;
	
	public int getCategoryID() {
		return categoryID;
	}
	public String getName() {
		return name;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	
	public static Category decodeJSON(JSONObject obj) {
		try {		
			int categoryID = obj.getInt("category_id");
			String name = obj.getString("name");
			String createdDate = obj.getString("created_at");
			String modifiedDate = obj.getString("modified_at");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			
			return new Category(categoryID, name, dateFormat.parse(createdDate), dateFormat.parse(modifiedDate));
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
