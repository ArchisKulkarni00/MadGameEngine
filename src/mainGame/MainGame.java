package mainGame;

import java.io.IOException;

import madgui.Quad;
import madgui.Renderer;
import madgui.Scene;
import madgui.Texture;

public class MainGame {

	public static void main(String[] args) throws IOException {
		Renderer renderer = new Renderer(1920,1080,"Trial");
		boolean isRunning = renderer.init();
		
		Scene mScene = new Scene();
		renderer.addScene(mScene);
		
		mScene.initShader("Shaders/vs001", "Shaders/fs002");		
		mScene.enableCamera();
		
		Quad mQuad = new Quad(-1.0f, 1.0f, 20.0f, 20.0f);
		mQuad.setTexture(1);
		mQuad.setCoordinates(0.0f, 0.0f, 40.0f, 40.0f);
		mScene.mVQuadVector.add(mQuad);
		
		Scene mScene1 = new Scene();
		renderer.addScene(mScene1);
		
		mScene1.initShader("Shaders/vs001", "Shaders/fs002");		
		mScene1.enableCamera();
		
		Quad mQuad1 = new Quad(-1.0f, 1.0f, 20.0f, 20.0f);
		mQuad1.setTexture(2);
		mQuad1.setCoordinates(0.0f, 0.0f, 40.0f, 40.0f);
		mScene1.mVQuadVector.add(mQuad1);

		Texture mTexture = new Texture("images/af32T.png", 1);
		Renderer.mTextureVector.add(mTexture);
		
		Texture mTexture1 = new Texture("images/beach_soil.jpg", 2);
		Renderer.mTextureVector.add(mTexture1);
		
		if (isRunning) {
			renderer.setActiveScene(0);
//			mScene2.initVertexArray();
			renderer.runloop();
		}
		renderer.terminate();
	}
}
