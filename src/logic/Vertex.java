package logic;

public class Vertex {
	private int id;
	private int color; // stores color as color id
	//SHOULD WE USE COLOR CLASS AS PAINTING COLOR?
	public Vertex(){
		id = color = 0;
	}
	public Vertex(int id,int color){
		this.id = id;
		this.color = color;
	}
}
