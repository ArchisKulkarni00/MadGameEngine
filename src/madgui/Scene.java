package madgui;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_C;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;
import java.io.IOException;
import java.util.Vector;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.opengl.GL33;

import widgets.Entity;

public class Scene {
	
	//########## Internal Data members ##########
	
	private int sceneIdentifier = -1;

	private Shader mShader = new Shader();
	private Shader mDynShader = new Shader();
	private CameraOrtho2d mCamera = null;
	private Vector3f cameraPosition = new Vector3f(0,0,0);
	
	GLFWScrollCallback scrollCallback = null;
	
//	public Vector<Texture> mTextureVector = new Vector<>();
	
//	text element holders
	public Vector<Quad> mTextVector = new Vector<>();
	VertexArray mTextVertexArray = new VertexArray();
	
//	UI element holders
	public Vector<Quad> mUIQuadVector = new Vector<>();
	VertexArray mUIVertexArray = new VertexArray();
	
//	Viewport element holders (static)
	public Vector<Quad> mVQuadVector = new Vector<>();
	VertexArray mVVertexArray = new VertexArray();
	
//	viewport element holders (dynamic)
	public Vector<QuadDynamic> mDynamicVector = new Vector<>();
	VertexArrayDyn mDynVertexArray = new VertexArrayDyn();
	
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
	
	public void add(QuadDynamic pQuadDynamic) {
		mDynamicVector.add(pQuadDynamic);
	}
	
	public void delete() {
		mDynShader.delete();
		mShader.delete();
		mUIVertexArray.delete();
		mVVertexArray.delete();
		mTextVertexArray.delete();
		mDynVertexArray.delete();
		mVQuadVector.clear();
		mTextVector.clear();
		mUIQuadVector.clear();
		mDynamicVector.clear();
	}
	
	public void initShader(String vs,String fs) throws IOException {
		mShader.load(vs, fs);
	}
	
	public void initDynShader(String vs,String fs) throws IOException {
		mDynShader.load(vs, fs);
	}
	
	public void initVertexArray() {
		if (mUIQuadVector.size()>0) {
			mUIVertexArray.init(mUIQuadVector);
		}
		
		if (mVQuadVector.size()>0) {
			mVVertexArray.init(mVQuadVector);
		}
		
		if (mTextVector.size()>0) {
			mTextVertexArray.init(mTextVector);
		}
		
		if (mDynamicVector.size()>0) {
			mDynVertexArray.init();
		}
		
		
		
	}
	
	public void enableCamera() {
		mCamera = new CameraOrtho2d(Renderer.mWidth, Renderer.mHeight);
//		mShader.setCamera(mCamera.getProjection());
		setScrollCallback();
	}
	
	public void updateCamera() {
		cameraPosition.x=0.0f;
		cameraPosition.y=0.0f;
		cameraPosition.z=0.0f;
		if (glfwGetKey(Renderer.mWindow, GLFW_KEY_S)==1) {
			cameraPosition.add(0, 0.05f, 0);
		}
		
		if (glfwGetKey(Renderer.mWindow, GLFW_KEY_W)==1) {
			cameraPosition.add(0, -0.05f, 0);
		}
		
		if (glfwGetKey(Renderer.mWindow, GLFW_KEY_D)==1) {
			cameraPosition.add(-0.05f, 0, 0);
		}
		
		if (glfwGetKey(Renderer.mWindow, GLFW_KEY_A)==1) {
			cameraPosition.add(0.05f, 0, 0);
		}
		
		mCamera.setTransaltion(cameraPosition);
	}
	
	public void render() {
		if (mUIQuadVector.size()>0) {
			DrawUI();
		}
		if (mVQuadVector.size()>0) {
			DrawViewPort();
		}
		if (mTextVector.size()>0) {
			DrawText();
		}
		
//		disableShader();
		
		if (mDynamicVector.size()>0) {
			DrawDyn();
		}
		
//		disableShader();
		
	}
	
	//########## Internal private functionality ##########
	
	private void disableShader() {
		GL33.glUseProgram(0);
	}
	
//	Drawing functions
	private void DrawViewPort() {
		for(int i=0;i<Renderer.mTextureVector.size();i++) {
			Renderer.mTextureVector.get(i).setActive();
		}
		mShader.setActive();
		mShader.setCamera(mCamera.getProjection());
		mVVertexArray.setActive();
		GL33.glDrawElements(GL33.GL_TRIANGLES, mVVertexArray.getVertexCount(), GL33.GL_UNSIGNED_INT, 0);
	}
	
	private void DrawUI() {
		for(int i=0;i<Renderer.mTextureVector.size();i++) {
			Renderer.mTextureVector.get(i).setActive();
		}
		mShader.disableCamera();
		mShader.setActive();
		mUIVertexArray.setActive();
		GL33.glDrawElements(GL33.GL_TRIANGLES, mUIVertexArray.getVertexCount(), GL33.GL_UNSIGNED_INT, 0);
	}
	
	private void DrawText() {
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
	
	private void DrawDyn() {
		for(int j=0;j<mDynamicVector.size();j++) {
//			for(int i=0;i<Renderer.mTextureVector.size();i++) {
//				Renderer.mTextureVector.get(i).setActive();
//			}
			mDynShader.setActive();
			mDynShader.setCamera(mCamera.getProjection());
			mDynShader.setQuadTransformation(mDynamicVector.get(j).getModelMatrix());
			mDynVertexArray.setActive();
			GL33.glDrawElements(GL33.GL_TRIANGLES, mDynVertexArray.getVertexCount(), GL33.GL_UNSIGNED_INT, 0);
		}
	}
	
	private void setScrollCallback() {
		scrollCallback = new GLFWScrollCallback() {
			
			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				
				if (glfwGetKey(Renderer.mWindow, GLFW_KEY_C)==1) {
					mCamera.setScale((float)yoffset*0.01f);
				}
				
				else {
					mCamera.setScale((float)yoffset*0.1f);
				}
				
			}
		};
		
		glfwSetScrollCallback(Renderer.mWindow, scrollCallback);
	}
	
	
}
