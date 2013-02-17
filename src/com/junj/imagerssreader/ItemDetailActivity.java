package com.junj.imagerssreader;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailActivity extends Activity {
	private TextView mTitle;
	private TextView mDescription;
	private ImageView mImage;
	
	@Override
	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.item_detail);
		
		Intent intent = getIntent();
		
		String title = intent.getStringExtra("TITLE");
		String description = intent.getStringExtra("DESCRIPTION");
		//String imageUrlString = intent.getStringExtra("IMAGEURL");
		String imageUrlString = "http://www.universe-s.com/img/news/2004/0520_01.jpg";		
		
		mTitle = (TextView) findViewById(R.id.item_detail_title);
		mDescription = (TextView) findViewById(R.id.item_detail_descr);
		
		mImage = (ImageView) findViewById(R.id.item_detail_image);
		Bitmap bitmap = this.getImageBitmapByUrlString(imageUrlString);
		
		mTitle.setText(title);
		mDescription.setText(description);
		mImage.setImageBitmap(bitmap);
		// mImage.setImageDrawable(drawable);
	}
	private Bitmap getImageBitmapByUrlString(String imageUrlString) {
		Bitmap bitmap = null;
		try {
			URL imageURL = new URL(imageUrlString);
			InputStream is = imageURL.openStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bitmap;
	}
	
	private Drawable getImageDrawableByUrlString(String imageUrlString) {
		Drawable drawable = null;
		try {
			URL imageURL = new URL(imageUrlString);
			InputStream is = imageURL.openStream();
			drawable = Drawable.createFromStream(is, "");
			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(drawable);
		return drawable;
	}
}
