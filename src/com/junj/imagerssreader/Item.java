package com.junj.imagerssreader;

public class Item {
	private CharSequence mTitle;
	private CharSequence mDescription;
	
	public Item() {
		mTitle = "";
		mDescription = "";
	}
	
	public CharSequence getDescription() {
		return mDescription;
	}
	
	public CharSequence getTitle() {
		return mTitle;
	}
	
	public void setDescription(CharSequence description) {
		mDescription = description;
	}
	
	public void setTitle(CharSequence title) {
		mTitle = title;
	}
}
