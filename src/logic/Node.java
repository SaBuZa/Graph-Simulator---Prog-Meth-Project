package logic;

public class Node {
	private int id;
	private int color; // stores color as color id
	public Node(){
		id = color = 0;
	}
	public Node(int id,int color){
		this.id = id;
		this.color = color;
	}
}
