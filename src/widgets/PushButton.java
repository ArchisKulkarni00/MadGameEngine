package widgets;

public class PushButton extends Entity{
	
	@SuppressWarnings("unused")
	private Label mLabel = null;
	
	public void setText(String pString) {
		float tempX = mQuad.getXCord() + (0.1f*mQuad.getWidth());
		float tempY = mQuad.getYCord() - (0.1f*mQuad.getHeight());
		mLabel = new Label(pString, tempX, tempY, mQuad.getWidth()*0.8f, mQuad.getHeight()*0.8f);
	}
	
}
