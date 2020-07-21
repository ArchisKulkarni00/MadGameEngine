package widgets;

import java.util.Vector;

public class Layout extends Entity {
	
	private Vector<Entity> mChildVector = new Vector<>();
	private boolean isVertical = true;
	float scalingFactor = 0.1f;
	
	Label mLabel;

	public void setTitle(String pString) {
		mLabel = new Label(pString);
		setChild(mLabel);
	}
	
	public float getScalingFactor() {
		return scalingFactor;
	}

	public void setScalingFactor(float scalingFactor) {
		this.scalingFactor = scalingFactor;
	}

	public boolean isVertical() {
		return isVertical;
	}

	public void setIsVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}
	
	public void setChild(Entity entity) {
		mChildVector.add(entity);
		entity.setParEntity(this);
		
		if (isVertical) {
			float offset = 0.1f*this.mQuad.getWidth();
			int entityNumber = mChildVector.size()-1;
			
			float x = this.mQuad.getXCord()+offset;
			float y = (this.mQuad.getYCord()-offset);
			float w = (this.mQuad.getWidth()-(2*offset));
			float h = (this.mQuad.getHeight()*scalingFactor);
						
			entity.setParentBasedPosition(x, y-((h+offset)*entityNumber), w, h);
		}
		else {
			float offset = 0.1f*this.mQuad.getHeight();
			int entityNumber = mChildVector.size()-1;
			
			float x = this.mQuad.getXCord()+offset;
			float y = (this.mQuad.getYCord()-offset);
			float w = (this.mQuad.getWidth()*scalingFactor);
			float h = (this.mQuad.getHeight()-(2*offset));
			
			entity.setParentBasedPosition(x+((w+offset)*entityNumber), y, w, h);
		}
	}
	
	public void setChild(Label pLable) {
		mChildVector.add(pLable);
		pLable.setParEntity(this);
		
		if (isVertical) {
			float offset = 0.1f*this.mQuad.getWidth();
			int entityNumber = mChildVector.size()-1;
			
			float x = this.mQuad.getXCord()+offset;
			float y = (this.mQuad.getYCord()-offset);
			float w = (this.mQuad.getWidth()-(2*offset));
			float h = (this.mQuad.getHeight()*scalingFactor);
			
			pLable.setParentBasedPosition(pLable,x, y-((h+offset)*entityNumber), w, h/2);
		}
		else {
			float offset = 0.1f*this.mQuad.getHeight();
			int entityNumber = mChildVector.size()-1;
			
			float x = this.mQuad.getXCord()+offset;
			float y = (this.mQuad.getYCord()-offset);
			float w = (this.mQuad.getWidth()*scalingFactor);
			float h = (this.mQuad.getHeight()-(2*offset));
			
			pLable.setParentBasedPosition(pLable,x+((w+offset)*entityNumber), y, w, h);
		}
	}
}
