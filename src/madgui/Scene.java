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

	public Shader mShader = new Shader();
	public CameraOrtho2d mCamera = null;
	private Vector3f cameraPosition = new Vector3f(0,0,0);
	
	GLFWScrollCallback scrollCallback = null;
	
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
//		setScrollCallback();
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
		DrawViewPort();
		DrawUI();
		DrawText();
	}
	
	//########## Internal private functionality ##########
	
//	Drawing functions
	private void DrawViewPort() {
		for(int i=0;i<Renderer.mTextureVector.size();i++) {
			Renderer.mTextureVector.get(i).setActive();
		}
		mShader.setCamera(mCamera.getProjection());
		mShader.setActive();
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
