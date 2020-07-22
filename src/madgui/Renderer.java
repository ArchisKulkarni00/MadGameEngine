package madgui;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;

import java.util.Vector;

import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

public class Renderer {
	
	//########## Internal Data members ##########
	public static int mWidth,mHeight;
	private String mWindowTitle;
	private long mWindow;
	private GLFWWindowSizeCallback windowSizeCallback = null;
	private GLFWScrollCallback scrollCallback = null;
	public static Vector<Texture> mTextureVector = new Vector<>();
	private Vector<Scene> mScenesVector = new Vector<>();
	private int activeScene = 0;
	
//	public Scene mScene = new Scene();
	
	//########## External public functionality ##########

	public Renderer(int width,int height, String title) {
		mWidth = width;
		mHeight = height;
		mWindowTitle = title;
	}
	
	public  Renderer() {
		this(1280, 720, "GameWindow");
	}
	
	public boolean init() {
		if (!glfwInit()) {
			throw new IllegalStateException("cannot init glfw");
		}
		
//		glfwWindowHint(GLFW_RESIZABLE, 0);
		glfwWindowHint(GLFW_MAXIMIZED, 1);
		mWindow = glfwCreateWindow(mWidth,mHeight, mWindowTitle, 0, 0);
//		long mCursor = glfwCreateStandardCursor(GLFW_HRESIZE_CURSOR);
//		glfwSetCursor(mWindow, mCursor);
		
		if (mWindow==0) {
			throw new IllegalStateException("cannot start window");
		}
		
		//create opengl context
		glfwMakeContextCurrent(mWindow);
		GL.createCapabilities();
		
		//set colour buffer data
		glClearColor(0.3f, 0.3f, 0.3f, 0.0f);
		
		setAllCallbacks();
		
		//make window visible
		glfwShowWindow(mWindow);
		
		return true;
	}
	
	public void addScene(Scene pScene) {
		mScenesVector.add(pScene);
		pScene.setSceneIdentifier(mScenesVector.size());
	}
	
	public int getActiveScene() {
		return activeScene;
	}

	public void setActiveScene(int activeScene) {
		this.activeScene = activeScene;
		mScenesVector.get(activeScene).initVertexArray();
	}
	
	public void runloop() {
		while (!glfwWindowShouldClose(mWindow)) {
			ProcessInput();
			ProcessOutput();
		}
		
	}
	
	public void terminate() {
		for(int i=0;i<mTextureVector.size();i++) {
			mTextureVector.get(i).delete();
		}
		windowSizeCallback.free();
		scrollCallback.free();
		for(int i=0;i<mScenesVector.size();i++) {
			mScenesVector.get(i).delete();
		}
		glfwTerminate();
	}
	
	//########## Internal private functionality ##########
	
//	Drawing functions
	
	private void ProcessOutput() {
		
		glClear(GL_COLOR_BUFFER_BIT);
//		mScene.renderScene(mTextureVector);
		mScenesVector.get(activeScene).render();
		glfwSwapBuffers(mWindow);
		
	}
	
	private void ProcessInput() {
		glfwPollEvents();
		
		//add key maps here
		if (glfwGetKey(mWindow, GLFW_KEY_ESCAPE)==1) {
			glfwSetWindowShouldClose(mWindow,true);
		}
		
	}
	
//	find all the required callbacks here
	private void setAllCallbacks() {
		windowSizeCallback = new GLFWWindowSizeCallback() {
			
			@Override
			public void invoke(long window, int width, int height) {
				mWidth=width;
				mHeight=height;
				glViewport(0, 0, width, height);
				
			}
		};
		
		scrollCallback = new GLFWScrollCallback() {
			
			@Override
			public void invoke(long window, double xoffset, double yoffset) {
			}
		};
		
		glfwSetWindowSizeCallback(mWindow, windowSizeCallback);
		glfwSetScrollCallback(mWindow, scrollCallback);
	}
	
	
	
}
