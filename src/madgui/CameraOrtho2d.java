package madgui;

import org.joml.Matrix4f;

public class CameraOrtho2d {
	
	private Matrix4f projection;
	float scale = 1.0f;
	private boolean isEnabled;
	float width,height;
	
	public CameraOrtho2d(int sWidth, int sHeight) {
//		setProjection(sWidth, sHeight);
		width = sWidth;
		height=sHeight;
		projection = new Matrix4f().ortho2D(-width/height, width/height, -height/height, height/height).scale(scale);
		isEnabled = true;
	}
	
	public void setProjection(int width,int height) {
		this.width = (float)width;
		this.height = (float)height;
		updateProjection();
	}
	
	private void updateProjection() {
		projection = projection.identity();
		projection =  projection.ortho2D(-width/height, width/height, -height/height, height/height).scale(scale);
	}
	
	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = Math.abs(this.scale+scale);
		updateProjection();
	}

	public Matrix4f getProjection() {
		return projection;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}


}
