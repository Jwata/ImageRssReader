package com.junj.imagerssreader;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;

public final class SimpleRenderer implements GLSurfaceView.Renderer {
	private final Context mContext;
	
	// 頂点データ
	private static final float VERTEXS[] = {
		-1.0f,  1.0f, 0.0f,	// 左上
		-1.0f, -1.0f, 0.0f,	// 左下
		 1.0f,  1.0f, 0.0f,	// 右上
		 1.0f, -1.0f, 0.0f	// 右下
	};

	// テクスチャデータ
	private static final float TEXCOORDS[] = {
		0.0f, 0.0f,	// 左上
		0.0f, 1.0f,	// 左下
		1.0f, 0.0f,	// 右上
		1.0f, 1.0f	// 右下
	};
	
	// 頂点バッファ
	private final FloatBuffer mVertexBuffer = GLES20Utils.createBuffer(VERTEXS);
	
	// テクスチャバッファ
	private final FloatBuffer mTexcoordBuffer = GLES20Utils.createBuffer(TEXCOORDS);

	// Construct
	public SimpleRenderer(final Context context) {
		mContext = context;
	}
	
	// vertex shader code
	private static final String VERTEX_SHADER =
			"attribute vec4 position;" +
			"attribute vec2 texcoord;" +
			"varying vec2 texcoordVarying;" +
			"void main() {" +
				"gl_Position = position;" +
				"texcoordVarying = texcoord;" +
			"}";

	// fragment shader code
	private static final String FRAGMENT_SHADER =
			"precision mediump float;" +
			"varying vec2 texcoordVarying;" +
			"uniform sampler2D texture;" +
			"void main() {" +
				"gl_FragColor = texture2D(texture, texcoordVarying);" +
			"}";
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// OpenGL ES 2.0 を使用するので、パラメータで渡された GL10 インターフェースを無視して、代わりに GLES20 クラスの静的メソッドを使用します。

		// XXX - このサンプルではテクスチャの簡単な描画だけなので深さ関連の有効/無効や指定は一切していません。

		// 背景色を指定して背景を描画します。
		GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

		// 背景とのブレンド方法を設定します。
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);
		GLES20.glEnable(GLES20.GL_BLEND);
		GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);	// 単純なアルファブレンド

		// テクスチャの指定
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureId);
		GLES20.glUniform1i(mTexture, 0);
		GLES20.glVertexAttribPointer(mTexcoord, 2, GLES20.GL_FLOAT, false, 0, mTexcoordBuffer);
		GLES20.glVertexAttribPointer(mPosition, 3, GLES20.GL_FLOAT, false, 0, mVertexBuffer);
		GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

		GLES20.glDisable(GLES20.GL_BLEND);
		GLES20.glDisable(GLES20.GL_TEXTURE_2D);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// OpenGL ES 2.0 を使用するので、パラメータで渡された GL10 インターフェースを無視して、代わりに GLES20 クラスの静的メソッドを使用します。

		// ビューポートを設定します。
		GLES20.glViewport(0, 0, width, height);
		GLES20Utils.checkGlError("glViewport");
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// OpenGL ES 2.0 を使用するので、パラメータで渡された GL10 インターフェースを無視して、代わりに GLES20 クラスの静的メソッドを使用します。

		// プログラムを生成して使用可能にします。
		mProgram = GLES20Utils.createProgram(VERTEX_SHADER, FRAGMENT_SHADER);
		if (mProgram == 0) {
			throw new IllegalStateException();
		}
		GLES20Utils.glUseProgram(mProgram);
		GLES20Utils.checkGlError("glUseProgram");

		// シェーダで使用する変数のハンドルを取得し使用可能にします。
		mPosition = GLES20.glGetAttribLocation(mProgram, "position");
		GLES20Utils.checkGlError("glGetAttribLocation position");
		if (mPosition == -1) {
			throw new IllegalStateException("Could not get attrib location for position");
		}
		GLES20.glEnableVertexAttribArray(mPosition);

		mTexcoord = GLES20.glGetAttribLocation(mProgram, "texcoord");
		GLES20Utils.checkGlError("glGetAttribLocation texcoord");
		if (mPosition == -1) {
			throw new IllegalStateException("Could not get attrib location for texcoord");
		}
		GLES20.glEnableVertexAttribArray(mTexcoord);

		mTexture = GLES20.glGetUniformLocation(mProgram, "texture");
		GLES20Utils.checkGlError("glGetUniformLocation texture");
		if (mTexture == -1) {
			throw new IllegalStateException("Could not get uniform location for texture");
		}

		// テクスチャを作成します。(サーフェスが作成される度にこれを行う必要があります)
		final Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.sample);
		mTextureId = GLES20Utils.loadTexture(bitmap);
		bitmap.recycle();
	}

}
