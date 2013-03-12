package com.junj.imagerssreader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.R.integer;

public class GLES20Utils {
	
	private static final int FIRST_INDEX = 0;
	
	private static final int DEFAULT_OFFSET = 0;
	
	private static final int FLOAT_SIZE_BYTES = 4;
	
	// construct
	public GLES20Utils() {}
	
	public static FloatBuffer createBuffer(float[] array) {
		final FloatBuffer buffer = ByteBuffer.allocateDirect(array.length * FLOAT_SIZE_BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer();
		buffer.put(array).position(FIRST_INDEX);
		return buffer;
	}

}
