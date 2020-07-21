package widgets;

import madgui.Text;

public class Label extends Entity {
	
	private Text mText;
	
	public Label(String pString,float x,float y,float width,float height) {
		mText = new Text(pString, x, y, width, height);
	}
	
	public Label(String pString) {
		mText = new Text(pString);
	}
	
	public void setLocation(float x,float y,float width,float height) {
		mText.setLocation(x, y, width, height);
	}

}
