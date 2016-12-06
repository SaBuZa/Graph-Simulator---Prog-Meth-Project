package algorithm;

import java.util.concurrent.CopyOnWriteArrayList;

import logic.Algorithm;
import logic.DataManager;
import logic.Edge;
import logic.Vertex;

public class MinimumSpanningTree extends Algorithm{
	public DataManager solve(DataManager dataManager){
		//dataManager.getInstance().getEdges();//SORTED?
		CopyOnWriteArrayList<Edge> el = dataManager.getInstance().getEdges();
		CopyOnWriteArrayList<Vertex> vl = dataManager.getInstance().getVertices();
		DataManager ret = new DataManager();
		ret.getInstance().getVertices().addAll(vl);
		el.sort((o1,o2) -> o1.getCost().compareTo(o2.getCost()));
		
		//el.sort((o1,o2)-> (o1.getId() - o2.getId()));
		//el.sort ((o1,o2) -> (o1.getId().compareTo(o2.getId())) );
		
		DisjointSet dsu = new DisjointSet(vl.size());
		for (Edge e : el ){
			int u = e.getFrom().getId();
			int v = e.getTo().getId();
			if (dsu.isUnion(u,v)) continue;
			dsu.unify(u, v);
			ret.getInstance().getEdges().add(e);
		}
		return ret;
	}
}
