package algorithm;

import java.util.concurrent.CopyOnWriteArrayList;

import logic.Algorithm;
import logic.DataManager;
import logic.Edge;
import logic.Node;

public class MinimumSpanningTree extends Algorithm{
	public DataManager solve(DataManager dataManager){
		//dataManager.getInstance().getEdges();//SORTED?
		CopyOnWriteArrayList<Edge> el = dataManager.getInstance().getEdges();
		CopyOnWriteArrayList<Node> vl = dataManager.getInstance().getNodes();
		DataManager ret = new DataManager();
		ret.getInstance().getNodes().addAll(vl);
		el.sort((o1,o2) -> o1.getCost().compareTo(o2.getCost()));
		
		//el.sort((o1,o2)-> (o1.getId() - o2.getId()));
		//el.sort ((o1,o2) -> (o1.getId().compareTo(o2.getId())) );
		
		DisjointSet dsu = new DisjointSet(vl.size());
		for (Edge e : el ){
			int u = e.getFrom();
			int v = e.getTo();
			if (dsu.isUnion(u,v)) continue;
			dsu.unify(u, v);
			ret.getInstance().getEdges().add(e);
		}
		return ret;
	}
}
