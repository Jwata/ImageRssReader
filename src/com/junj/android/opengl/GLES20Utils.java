// package com.junj.imagerssreader;
package com.junj.android.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLException;
import android.opengl.GLUtils;
import android.util.Log;

public class GLES20Utils {
	
	private static final int FIRST_INDEX = 0;
	
	private static final int DEFAULT_OFFSET = 0;
	
	private static final int FLOAT_SIZE_BYTES = 4;

	private static final String TAG = "GLES20Utils";

	public static final int INVALID = 0;
	
	// construct
	public GLES20Utils() {}
	
	public static FloatBuffer createBuffer(float[] array) {
		final FloatBuffer buffer = ByteBuffer.allocateDirect(array.length * FLOAT_SIZE_BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer();
		buffer.put(array).position(FIRST_INDEX);
		return buffer;
	}

	public static void checkGlError(final String op) throws GLException{
		int error;
		while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
			String msg = op + ": glError " + error; 
			Log.e(TAG, msg);
			throw new GLException(error, msg);
		}
	}

	public static int createProgram(final String vertexSource, final String fragmentSource) throws GLException {
		final int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
		if (vertexShader == INVALID) {
			return INVALID;
		}
		
		final int pixelShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
		if (pixelShader == INVALID) {
			return INVALID;
		}
		
		int program = GLES20.glCreateProgram();
		if (program == INVALID) {
			return INVALID;
		}
		
		GLES20.glAttachShader(program, vertexShader);
		checkGlError("glAttachShader");
		
		GLES20.glAttachShader(program, pixelShader);
		checkGlError("glAttachShader");
		
		GLES20.glLinkProgram(program);
		
		final int[] linkStatus = new int[1];
		GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, DEFAULT_OFFSET);

		if (linkStatus[FIRST_INDEX] != GLES20.GL_TRUE) {
			Log.e(TAG, "Could not link program: ");
			Log.e(TAG, GLES20.glGetProgramInfoLog(program));
			GLES20.glDeleteProgram(program);
			return INVALID;
		}
		
		return program;
	}

	private static int loadShader(final int shaderType, final String source) {
		int shader = GLES20.glCreateShader(shaderType);
		if (shader == INVALID) {
			return INVALID;
		}

		GLES20.glShaderSource(shader, source);
		GLES20.glCompileShader(shader);

		final int[] compiled = new int[1];
		GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, DEFAULT_OFFSET);
		if (compiled[FIRST_INDEX] == INVALID) {
			Log.e(TAG, "Could not compile shader " + shaderType + ":");
			Log.e(TAG, GLES20.glGetShaderInfoLog(shader));
			GLES20.glDeleteShader(shader);
			return INVALID;
		}

		return shader;
	}

	public static int loadTexture(final Bitmap bitmap) {
		return loadTexture(bitmap, GLES20.GL_NEAREST, GLES20.GL_LINEAR);
	}
	
	public static int loadTexture(final Bitmap bitmap, final int min, final int mag) {
		final int[] textures = new int[1];
		GLES20.glGenTextures(1, textures, DEFAULT_OFFSET);

		final int texture = textures[FIRST_INDEX];
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture);
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

		// テクスチャを拡大/縮小する方法を設定します。
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, min);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, mag);

		return texture;
	}
	
}
