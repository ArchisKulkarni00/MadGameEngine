package mainGame;

import java.io.IOException;

import madgui.Quad;
import madgui.QuadDynamic;
import madgui.Renderer;
import madgui.Scene;
import madgui.Texture;

public class MainGame {

	public static void main(String[] args) throws IOException {
		Renderer renderer = new Renderer(1920,1080,"Trial");
		boolean isRunning = renderer.init();
		
		Scene mScene = new Scene();
		renderer.addScene(mScene);
		
		mScene.initDynShader("Shaders/vs_dyn", "Shaders/fs_dyn");
		mScene.initShader("Shaders/vs001", "Shaders/fs002");
		mScene.enableCamera();
		
		QuadDynamic mQuad1 = new QuadDynamic();
		mQuad1.TranslateBy(0.7f,0.7f);
		mQuad1.Scale(0.1f, 0.1f);
		mScene.add(mQuad1);

//		QuadDynamic mQuad3 = new QuadDynamic();
//		mQuad3.TranslateBy(-0.5f,0.6f);
//		mScene.add(mQuad3);
		
		Quad mQuad = new Quad(-1.0f, 1.0f, 20.0f, 20.0f);
		mQuad.setTexture(1);
		mQuad.setCoordinates(0.0f, 0.0f, 40.0f, 40.0f);
		mScene.add(mQuad);
		
		Texture mTexture = new Texture("images/af32T.png", 1);
		Renderer.mTextureVector.add(mTexture);
		
		if (isRunning) {
			renderer.setActiveScene(0);
//			mScene2.initVertexArray();
			renderer.runloop();
		}
		renderer.terminate();
	}
}
