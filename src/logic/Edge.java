package logic;

public class Edge {
	private int id, from, to;
	private Cost cost;
	
	public Edge(){
		id = from = to = 0;
		cost = null;
	}
	
	public Edge(int id,int from,int to){
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

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
	
	
}
