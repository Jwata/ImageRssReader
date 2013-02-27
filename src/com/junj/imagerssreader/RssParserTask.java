package com.junj.imagerssreader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Xml;

public class RssParserTask extends AsyncTask<String, Integer, RssListAdapter> {
	private RssReaderActivity mActivity; 
	private RssListAdapter mAdapter; 
	private ProgressDialog mProgressDialog;

	// Construct
	public RssParserTask(RssReaderActivity activity, RssListAdapter adapter) {
		mActivity = activity;
		mAdapter = adapter;
	}

	@Override
	protected void onPreExecute() {
		// display progress bar
		mProgressDialog = new ProgressDialog(mActivity);
		mProgressDialog.setMessage("Now Loading...");
		mProgressDialog.show();
	}
	
	@Override
	protected RssListAdapter doInBackground(String... params) {
		RssListAdapter result = null;
		
		try {
			// HTTP access and get InputStream
			URL url = new URL(params[0]);
			InputStream is = url.openConnection().getInputStream();
			result = parseXml(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	protected void onPostExecute(RssListAdapter result) {
		mProgressDialog.dismiss();
		mActivity.setListAdapter(result);
	}
	
	public RssListAdapter parseXml(InputStream is) throws IOException,XmlPullParserException {
		XmlPullParser parser = Xml.newPullParser();
		int i = 0;
		int itemNum = 10;
//debug
System.out.println("parse start!");
		
		try {
			
			parser.setInput(is, null);
			int eventType = parser.getEventType();
			Item currentItem = null;
			
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tag = null;
				switch (eventType) {
				case XmlPullParser.START_TAG:
					tag = parser.getName();
					if (tag.equals("item")) {
						currentItem = new Item();
					} else if (currentItem != null) {
						if (tag.equals("title")) {
							currentItem.setTitle(parser.nextText());
						} else if (tag.equals("description")) {
							currentItem.setDescription(parser.nextText());
						} else if (tag.equals("thumbnail")) {
							currentItem.setImageURL(parser.getAttributeValue(null, "url"));	
						}
					}
					break;
				case XmlPullParser.END_TAG:
					tag = parser.getName();
					if (tag.equals("item")) {
						mAdapter.add(currentItem);
						i++;
					}
					break;
				}
				
				if (i >= itemNum) {
					break;
				}
				
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mAdapter;
	}
}
