package com.junj.imagerssreader;


public class Item {
	private CharSequence mTitle;
	private CharSequence mDescription;
	private String mImageURL;
	
	public Item() {
		mTitle = "";
		mDescription = "";
		mImageURL = "";
	}
	
	public CharSequence getDescription() {
		return mDescription;
	}
	
	public CharSequence getTitle() {
		return mTitle;
	}
	
	public String getImageURL() {
		return mImageURL;
	}
	
	public void setDescription(CharSequence description) {
		mDescription = description;
	}
	
	public void setTitle(CharSequence title) {
		mTitle = title;
	}
	
	public void setImageURL(String url) {
		mImageURL = url;
	}
}
