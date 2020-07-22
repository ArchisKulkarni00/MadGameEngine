package widgets;

import java.util.Vector;

import madgui.Quad;
import madgui.Renderer;

public class Entity {
	
//	quad member variable
	protected Quad mQuad = null;
//	parent entity
	private Entity mParEntity = null;
//	is this entitiy active 
	private boolean isActive = true;
	
	
//	########## Public functions ########## 
	public Entity getParEntity() {
		return mParEntity;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setSubTexture(int row,int column,float tileWidth,float tileHeight) {
		float tempX = column*tileWidth;
		float tempY = row*tileHeight;
		mQuad.setCoordinates(tempX, tempY, tileWidth, tileHeight);
	}
	
	public void setTexture(int idx) {
		mQuad.setTexture((float)idx);
	}
	
	public void setBgColour(float r,float g, float b,float a) {
		mQuad.setBgColour(r, g, b, a);;
	}
	
	public void push(Vector<Quad> pQuads) {
		pQuads.add(mQuad);
	}
	
//	########## Protected functions ##########
	
	protected void setParEntity(Entity mParEntity) {
		this.mParEntity = mParEntity;
	}
	
//	########## temporary functions ##########
//	temporary function!
	public void setLocation(float x,float y,float width,float height) {
		if(mParEntity==null) {
			float tempX = (x*2)-1;
			float tempY = 1-(y*2);
			float tempW = (width*2);
			float tempH = (height*2);
			mQuad = new Quad(tempX, tempY, tempW, tempH);
		}
		else {
			float tX = mParEntity.mQuad.getXCord();
			float tY = mParEntity.mQuad.getYCord();
			float tW = mParEntity.mQuad.getWidth();
			float tH = mParEntity.mQuad.getHeight();
			
			
			float tempX = tX+(x*tW);
			float tempY = tY-(y*tH);
			float tempW = (width*tW);
			float tempH = (height*tH);
			System.out.println("X:"+tempX);
			System.out.println("Y:"+tempH);
			mQuad = new Quad(tempX, tempY, tempW, tempH);
		}
		
//		add object to ui vector
		
	}
	
	public void setLocation(int x,int y,int width,int height) {
		float tempX =((float)x/(float)Renderer.mWidth);
		float tempY =((float)y/(float)Renderer.mHeight);
		float tempW =((float)width/(float)Renderer.mWidth);
		float tempH =((float)height/(float)Renderer.mHeight);
		setLocation(tempX, tempY, tempW, tempH);
	}
	
//	temporary function!
//	called by layouts for auto positioning of entities
	protected void setParentBasedPosition(float x,float y,float w,float h) {
		mQuad = new Quad(x, y, w, h);
	}
	
	protected void setParentBasedPosition(Label pLabel,float x,float y,float w,float h) {
		pLabel.setLocation(x, y, w, h);
	}
	
}
