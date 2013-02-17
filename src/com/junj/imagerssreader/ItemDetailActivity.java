package com.junj.imagerssreader;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
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
		String imageUrlString = intent.getStringExtra("IMAGEURL");
		System.out.println(imageUrlString);
		
		Drawable drawable = this.getImageWithURL(imageUrlString);
		
		mTitle = (TextView) findViewById(R.id.item_detail_title);
		mDescription = (TextView) findViewById(R.id.item_detail_descr);
		mImage = (ImageView) findViewById(R.id.item_detail_image);
		
		mTitle.setText(title);
		mDescription.setText(description);
		mImage.setImageDrawable(drawable);
	}
	
	private Drawable getImageWithURL(String imageUrlString) {
		URL imageURL;
		try {
			imageURL = new URL(imageUrlString);
			InputStream is = imageURL.openStream();
			Drawable d = Drawable.createFromStream(is, "webimg");
			is.close();
			return d;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
