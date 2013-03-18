// package com.junj.imagerssreader;
package com.junj.android.example.gles20.ui;


import com.junj.android.example.gles20.gl.SimpleRenderer;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
// import android.app.ListActivity;
// import android.content.Intent;
import android.view.Menu;
// import android.view.MenuItem;
// import android.view.View;
// import android.widget.ListView;

// import java.util.ArrayList;

public class RssReaderActivity extends Activity {
// public class RssReaderActivity extends ListActivity {
	public static final int MENU_ITEM_RELOAD = Menu.FIRST;
	public static final String RSS_FEED_URL = "https://picasaweb.google.com/data/feed/base/featured?alt=rss"; 
//	private ArrayList<Item> mItems;
//	private RssListAdapter mAdapter;
	
	private GLSurfaceView mGLSurfaceView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		setContentView(R.layout.main);
		
		mItems = new ArrayList<Item>();
		mAdapter = new RssListAdapter(this, mItems);
		
		RssParserTask task = new RssParserTask(this, mAdapter);
		task.execute(RSS_FEED_URL);
		*/

		mGLSurfaceView = new GLSurfaceView(this);
		mGLSurfaceView.setEGLContextClientVersion(2);
		mGLSurfaceView.setRenderer(new SimpleRenderer(getApplicationContext()));
		setContentView(mGLSurfaceView);
	}

	/*
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Item item = mItems.get(position);
		Intent intent = new Intent(this, ItemDetailActivity.class);
		intent.putExtra("TITLE", item.getTitle());
		intent.putExtra("DESCRIPTION", item.getDescription());
		intent.putExtra("IMAGEURL", item.getImageURL());
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		
		menu.add(0, MENU_ITEM_RELOAD, 0, "Reload");

		return result;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ITEM_RELOAD:
		    mItems = new ArrayList<Item>();
		    mAdapter = new RssListAdapter(this, mItems);
		    
		    RssParserTask task = new RssParserTask(this, mAdapter);
		    task.execute(RSS_FEED_URL);
		    return true;
		}
		return super.onOptionsItemSelected(item);
	}
	*/
	
	@Override
	public void onResume() {
		super.onResume();
		mGLSurfaceView.onResume();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mGLSurfaceView.onPause();
	}

}
