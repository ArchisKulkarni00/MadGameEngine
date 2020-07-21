package madgui;
import java.io.IOException;

import widgets.Layout;
import widgets.PushButton;
import widgets.Label;

public class Main {

	public static void main(String[] args) throws IOException {
		Renderer renderer = new Renderer(1920,1080,"GameWindow");
		boolean isInitialized = renderer.init();
		
		renderer.initShader("Shaders/vs001", "Shaders/fs002");
		renderer.enableCamera();
		
//		craeting ui
		Layout layout = new Layout();
//		layout.setLocation(0.0f, 0.0f, 0.15f, 1.0f);
		layout.setLocation(0, 0, 250, 1080);
		layout.setTitle("Layout1");
		layout.setScalingFactor(0.05f);
		layout.setSubTexture(0, 1, 0.25f, 0.25f);
		layout.setTexture(1);
		layout.setBgColour(0.4f, 0.4f, 0.4f, 1.0f);

		PushButton pushButton = new PushButton();
		layout.setChild(pushButton);
		pushButton.setSubTexture(0, 1, 0.25f, 0.25f);
		pushButton.setTexture(1);
		pushButton.setBgColour(0.2f, 0.0f, 0.0f, 1.0f);
		pushButton.setText("Add elements");
		
		
		PushButton pushButton2 = new PushButton();
		layout.setChild(pushButton2);
		pushButton2.setSubTexture(0, 1, 0.25f, 0.25f);
		pushButton2.setTexture(1);
		pushButton2.setBgColour(0.2f, 0.0f, 0.0f, 1.0f);
		pushButton2.setText("Delete");
		
		Label label1 = new Label("MyLabel1");
		label1.setLocation(0, 0, 50, 50);
		
		Texture texture = new Texture("images/Theme.png",1);
		renderer.mTextureVector.add(texture);
		Texture text = new Texture("images/SegoeUISemibold.png",0);
		renderer.mTextureVector.add(text);
		
//		craeting viewport
		
		
		Quad mQuad = new Quad(0.5f, 0.5f, 0.3f, 0.3f);
		renderer.mVQuadVector.add(mQuad);
		mQuad.setTexture(2);
		
		Text mText = new Text("archiskulkarni00@gmail.com",-0.5f,0.0f,0.6f,0.3f);
		mText.setBgColour(1.0f, 1.0f, 0.3f, 1.0f);
		
		Texture texture2 = new Texture("images/af32T.png",2);
		renderer.mTextureVector.add(texture2);
		
	
		
		if (isInitialized) {
			
			renderer.initVertexArray();
			renderer.runloop();
		}
		renderer.terminate();
	}

}
