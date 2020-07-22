package madgui;

import java.io.IOException;
import java.util.Vector;

import org.lwjgl.opengl.GL33;

import widgets.Entity;

public class Scene {
	
	//########## Internal Data members ##########
	
	public int sceneIdentifier = -1;

	public Shader mShader = new Shader();
	public CameraOrtho2d mCamera = null;
//	public Vector<Texture> mTextureVector = new Vector<>();
	
//	text element holders
	public Vector<Quad> mTextVector = new Vector<>();
	VertexArray mTextVertexArray = new VertexArray();
	
//	UI element holders
	public Vector<Quad> mUIQuadVector = new Vector<>();
	VertexArray mUIVertexArray = new VertexArray();
	
//	Viewport element holders
	public Vector<Quad> mVQuadVector = new Vector<>();
	VertexArray mVVertexArray = new VertexArray();
	
	//########## External public functionality ##########
	public int getSceneIdentifier() {
		return sceneIdentifier;
	}

	public void setSceneIdentifier(int sceneIdentifier) {
		this.sceneIdentifier = sceneIdentifier;
	}
	
	public void add(Quad pQuad) {
		mVQuadVector.add(pQuad);
	}
	
	public void add(Entity pEntity) {
		pEntity.push(mUIQuadVector);
	}
	
	public void add(Text pText) {
		pText.push(mTextVector);
	}
	
	public void delete() {
		mShader.delete();
		mUIVertexArray.delete();
		mVVertexArray.delete();
		mTextVertexArray.delete();
	}
	
	public void initShader(String vs,String fs) throws IOException {
		mShader.load(vs, fs);
	}
	
	public void initVertexArray() {
		mUIVertexArray.init(mUIQuadVector);
		mVVertexArray.init(mVQuadVector);
		mTextVertexArray.init(mTextVector);
	}
	
	public void enableCamera() {
		mCamera = new CameraOrtho2d(Renderer.mWidth, Renderer.mHeight);
		mShader.setCamera(mCamera.getProjection());
	}
	
	public void render() {
		DrawViewPort();
		DrawUI();
		DrawText();
	}
	
	//########## Internal private functionality ##########
	
//	Drawing functions
	void DrawViewPort() {
		for(int i=0;i<Renderer.mTextureVector.size();i++) {
			Renderer.mTextureVector.get(i).setActive();
		}
		mShader.setCamera(mCamera.getProjection());
		mShader.setActive();
		mVVertexArray.setActive();
		GL33.glDrawElements(GL33.GL_TRIANGLES, mVVertexArray.getVertexCount(), GL33.GL_UNSIGNED_INT, 0);
	}
	
	void DrawUI() {
		for(int i=0;i<Renderer.mTextureVector.size();i++) {
			Renderer.mTextureVector.get(i).setActive();
		}
		mShader.disableCamera();
		mShader.setActive();
		mUIVertexArray.setActive();
		GL33.glDrawElements(GL33.GL_TRIANGLES, mUIVertexArray.getVertexCount(), GL33.GL_UNSIGNED_INT, 0);
	}
	
	void DrawText() {
		GL33.glEnable(GL33.GL_BLEND);
		GL33.glBlendFunc(GL33.GL_ONE, GL33.GL_ONE);
		for(int i=0;i<Renderer.mTextureVector.size();i++) {
			Renderer.mTextureVector.get(i).setActive();
		}
		mShader.setActive();
		mTextVertexArray.setActive();
		GL33.glDrawElements(GL33.GL_TRIANGLES, mTextVertexArray.getVertexCount(), GL33.GL_UNSIGNED_INT, 0);
		GL33.glDisable(GL33.GL_BLEND);
	}
	
	
}
