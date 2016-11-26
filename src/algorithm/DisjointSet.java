package algorithm;

public class DisjointSet {
	
	private int[] id;
	
	public DisjointSet(int size){
		id = new int[size];
		for (int i=0;i<size;i++){//REMAPPING NEEDED 
			id[i] = i;
		}
	}
	
	public int findRoot(int x){
		while (x != id[id[x]]){
			id[x] = id[id[x]];
			x = id[id[x]];
		}
		return x;
	}
	
	public void unify(int x,int y){
		x = findRoot(x);
		y = findRoot(y);
		if (x==y) return;
		id[x] = y;
	}
	
	public boolean isUnion(int x,int y){
		return findRoot(x)==findRoot(y);
	}
}
