package mainGame;

import java.io.IOException;

import madgui.Quad;
import madgui.Renderer;
import madgui.Scene;
import madgui.Texture;
import widgets.Layout;
import widgets.PushButton;

public class MainGame {

	public static void main(String[] args) throws IOException {
		Renderer renderer = new Renderer(1920,1080,"Trial");
		boolean isRunning = renderer.init();
		
		Scene mScene = new Scene();
		renderer.addScene(mScene);
		
		mScene.initShader("Shaders/vs001", "Shaders/fs002");		
		mScene.enableCamera();
		
		Layout layout = new Layout();
		layout.setLocation(0, 0, 250, 250);
//		layout.setTitle("Layout1");
		layout.setScalingFactor(0.3f);
		layout.setSubTexture(0, 1, 0.25f, 0.25f);
		layout.setTexture(1);
		layout.setBgColour(0.4f, 0.4f, 0.4f, 1.0f);
		layout.push(mScene.mUIQuadVector);
		
		PushButton pushButton = new PushButton();
		layout.setChild(pushButton);
		pushButton.setSubTexture(0, 1, 0.25f, 0.25f);
		pushButton.setTexture(1);
		pushButton.setBgColour(0.2f, 0.0f, 0.0f, 1.0f);
		pushButton.push(mScene.mUIQuadVector);
		
		
		PushButton pushButton2 = new PushButton();
		layout.setChild(pushButton2);
		pushButton2.setSubTexture(0, 1, 0.25f, 0.25f);
		pushButton2.setTexture(1);
		pushButton2.setBgColour(0.2f, 0.0f, 0.0f, 1.0f);
		pushButton2.push(mScene.mUIQuadVector);
		
		Scene mScene2 = new Scene();
		renderer.addScene(mScene2);
		mScene2.initShader("Shaders/vs001", "Shaders/fs002");		
		mScene2.enableCamera();
		
		Layout layout1 = new Layout();
		layout1.setLocation(0, 0, 300, 1080);
//		layout.setTitle("Layout1");
		layout1.setScalingFactor(0.05f);
		layout1.setSubTexture(0, 1, 0.25f, 0.25f);
		layout1.setTexture(1);
		layout1.setBgColour(0.4f, 0.4f, 0.4f, 1.0f);
		layout1.push(mScene2.mUIQuadVector);
		
		PushButton pushButton3 = new PushButton();
		layout1.setChild(pushButton3);
		pushButton3.setSubTexture(0, 1, 0.25f, 0.25f);
		pushButton3.setTexture(1);
		pushButton3.setBgColour(0.2f, 0.0f, 0.0f, 1.0f);
		pushButton3.push(mScene2.mUIQuadVector);
		
		
		PushButton pushButton4 = new PushButton();
		layout1.setChild(pushButton4);
		pushButton4.setSubTexture(0, 1, 0.25f, 0.25f);
		pushButton4.setTexture(1);
		pushButton4.setBgColour(0.2f, 0.0f, 0.0f, 1.0f);
		pushButton4.push(mScene2.mUIQuadVector);
		
		Quad mQuad = new Quad(0.5f, 0.5f, 0.3f, 0.3f);
		mQuad.setTexture(1);
		mScene2.mVQuadVector.add(mQuad);

		Texture mTexture = new Texture("images/Theme.png", 1);
		Renderer.mTextureVector.add(mTexture);
		
		if (isRunning) {
			renderer.setActiveScene(1);
//			mScene2.initVertexArray();
			renderer.runloop();
		}
		renderer.terminate();
	}
}
