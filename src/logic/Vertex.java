package logic;

public class Vertex {
	private int id;
	private int colorID; // stores color as color id
	private double x,y;

	public Vertex(){
		id = colorID = 0;
	}
	
	public Vertex(int id,int colorID){
		this.id = id;
		this.colorID = colorID;
		x = y = 0;
	}

	public Vertex(int id,int colorID,int x,int y){
		this.id = id;
		this.colorID = colorID;
		this.x = x;
		this.y = y;
	}

	public int getColorID() {
		return colorID;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
