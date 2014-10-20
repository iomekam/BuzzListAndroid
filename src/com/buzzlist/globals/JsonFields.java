package com.buzzlist.globals;

public class JsonFields 
{
	public static final String ERROR = "error";
	
	public class Login
	{
		public static final String API_KEY = "api_key";
	}
	
	public class Item
	{
		public static final String ITEMS_ARRAY = "items";
	}
	
	public class Search
	{
		public static final String SEARCH = "search";
		public static final String CATEGORY = "category_id";
		public static final String TITLE = "by_title";
		public static final String TAG = "by_tag";
		public static final String IMAGE = "has_image";
		public static final String BEST_OFFER = "best_offer";
		public static final String SORT = "sort";
		
		public class SortType
		{
			public static final int PRICE_UP = 2;
			public static final int PRICE_DOWN = 3;
			public static final int RECENT = 1;
		}
		
		
	}
}
