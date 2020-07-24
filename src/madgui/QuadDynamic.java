package madgui;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class QuadDynamic {
	
	private Matrix4f mModelMatrix = new Matrix4f().identity();
	
//	transformations for quad
	private Vector3f transformation = new Vector3f(0,0,0);
	private Vector3f scale = new Vector3f(1,1,1);
	
	public Matrix4f getModelMatrix() {
		return mModelMatrix.identity().translate(transformation).scale(scale);
	}
	
	public Vector3f getTransformation() {
		return transformation;
	}
	public void TranslateBy(Vector3f transformation) {
		this.transformation.add(transformation);
	}
	
	public void TranslateBy(float x,float y) {
		transformation.x=0.0f;
		transformation.y=0.0f;
		this.transformation.add(x,y,0.0f);
	}
	
	public void setTrackTo(float x,float y) {
		transformation.x=0.0f;
		transformation.y=0.0f;
		this.transformation.add(x,y,0.0f);
	}
	
	public void Scale(Vector3f scale) {
		this.scale = scale;
	}
	
	public void Scale(float x, float y) {
		this.scale.x = x;
		this.scale.y = y;
	}
}
