package madgui;

import org.lwjgl.opengl.GL33;

public class VertexArrayDyn {
	private int vertexArrayID, vertexBufferID,indexBufferID;
	
	private float[] vertices = {
		
			-0.5f,0.5f,  //tl
			 0.5f,0.5f,  //tr
		   -0.5f,-0.5f,  //bl
		     0.5f,-0.5f  //br
	};
	
	private int[] indices = {
			
			0,1,2,
			1,2,3
	};
	
	public int getVertexArrayID() {
		return vertexArrayID;
	}

	public void setVertexArrayID(int vertexArrayID) {
		this.vertexArrayID = vertexArrayID;
	}
	
	public void init() {
		
		//create vertex array
		vertexArrayID = GL33.glGenVertexArrays();
		GL33.glBindVertexArray(vertexArrayID);
		
		//create vertex buffer
		vertexBufferID = GL33.glGenBuffers();
		GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vertexBufferID);
		GL33.glBufferData(GL33.GL_ARRAY_BUFFER, vertices, GL33.GL_STATIC_DRAW);
		GL33.glEnableVertexAttribArray(0);
		GL33.glVertexAttribPointer(0, 2, GL33.GL_FLOAT, false, 4*2, 0);
		
		//create index buffer
		indexBufferID = GL33.glGenBuffers();
		GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, indexBufferID);
		GL33.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, indices, GL33.GL_STATIC_DRAW);
//		GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, 0);
		
		
		GL33.glBindVertexArray(0);
		
	}
	
	public void delete() {
		GL33.glDeleteVertexArrays(vertexArrayID);
		GL33.glDeleteBuffers(vertexBufferID);
		GL33.glDeleteBuffers(indexBufferID);
	}

	public void setActive() {
		GL33.glBindVertexArray(vertexArrayID);
		
	}

	public int getVertexCount() {
//		int numVerts=numQuads*6;
		return 6;
	}
}
