package com.buzzlist.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class Tag 
{

	public Tag(int tagID, int itemID, String name, Date createdAt,
			Date modifiedAt) {
		this.tagID = tagID;
		this.itemID = itemID;
		this.name = name;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	private int tagID;
	private int itemID;
	private String name;
	private Date createdAt;
	private Date modifiedAt;
	
	public int getTagID() {
		return tagID;
	}
	public void setTagID(int tagID) {
		this.tagID = tagID;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	public static Tag decodeJSON(JSONObject obj) {
		try {		
			int tagID = obj.getInt("tag_id");
			int itemID = obj.getInt("item_id");
			String name = obj.getString("name");
			String createdDate = obj.getString("created_at");
			String modifiedDate = obj.getString("modified_at");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			
			return new Tag(tagID, itemID, name, dateFormat.parse(createdDate), dateFormat.parse(modifiedDate));
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
