package com.junj.imagerssreader;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;

public final class SimpleRenderer implements GLSurfaceView.Renderer {
	private Context mContext;

	public SimpleRenderer(final Context context) {
		mContext = context;
	}
	
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub

	}

}
