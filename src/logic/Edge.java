package logic;

public class Edge {
	private int id,colorID;

	private Cost cost;
	private Vertex from,to;
	
	public Edge(){
		id = 0;
		cost = null;
		from = to = null;
	}
	
	public Edge(int id,Vertex from,Vertex to){
		this.id = id;
		this.from = from;
		this.to= to;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vertex getFrom() {
		return from;
	}

	public void setFrom(Vertex from) {
		this.from = from;
	}

	public Vertex getTo() {
		return to;
	}

	public void setTo(Vertex to) {
		this.to = to;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
	
	public int getColorID() {
		return colorID;
	}

	public void setColorID(int colorID) {
		this.colorID = colorID;
	}
}
