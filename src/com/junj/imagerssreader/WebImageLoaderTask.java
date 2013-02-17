package com.junj.imagerssreader;

import java.io.InputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
// import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

public class WebImageLoaderTask extends AsyncTask<String, Void, Bitmap> {
	private ImageView mImageView;
	private String mUrl;
	
	public WebImageLoaderTask(ImageView iv) {
		mImageView = iv;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		try {
			mUrl = params[0];
			mImageView.setTag(mUrl);
			URL imageURL = new URL(mUrl);
			InputStream is = imageURL.openStream();
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			is.close();
			
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		if (mImageView.getTag() == mUrl) {
			mImageView.setImageBitmap(result);
		}
	}
	/*
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
	*/
}
