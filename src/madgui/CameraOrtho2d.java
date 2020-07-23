package madgui;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class CameraOrtho2d {
	
	private Matrix4f projection;
	private Vector3f position;
	float scale = 1.0f;
	private boolean isEnabled;
	float width,height;
	
	public CameraOrtho2d(int sWidth, int sHeight) {
//		setProjection(sWidth, sHeight);
		width = sWidth;
		height=sHeight;
		position = new Vector3f(0.3f,0.1f,0);
		projection = new Matrix4f().ortho2D(-width/height, width/height, -height/height, height/height)
				.translate(position)
				.scale(scale);
		isEnabled = true;
	}
	
	public void setProjection(int width,int height) {
		this.width = (float)width;
		this.height = (float)height;
		updateProjection();
	}
	
	private void updateProjection() {
		projection = projection.identity();
		projection =  projection.ortho2D(-width/height, width/height, -height/height, height/height)
				.translate(position)
				.scale(scale);
	}
	
	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = Math.abs(this.scale+scale);
		updateProjection();
	}
	
	public void setTransaltion(Vector3f pVector) {
		position.add(pVector);
		updateProjection();
	}

	public Matrix4f getProjection() {
		return projection;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}


}
