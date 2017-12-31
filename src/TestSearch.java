import edu.princeton.cs.algs4.Bag;

class Graph{
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	public Graph(int V){
		this.V = V; this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}
	public Graph(In in){
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++){  // Add an edge.
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	public int V() {
		return V; 
	}
	public int E() {
		return E;
	}
	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
}

class Search{
	private boolean[] marked;
	private int count;
	Search(Graph G, int s){
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
	 private void dfs(Graph G, int v){
		 marked[v] = true;
		 count++;
		 for (int w : G.adj(v))
			 if (!marked[w]) dfs(G, w);
	 }
	boolean marked(int v) {
		return marked[v];
	}
	int count() {
		return count;
	}
}
public class TestSearch {
	public static void main(String[] args){
		Graph G = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		Search search = new Search(G, s);
		
		for (int v = 0; v < G.V(); v++)
			if (search.marked(v))
				StdOut.print(v + " ");
		StdOut.println();
		if (search.count() != G.V())
			StdOut.print("NOT ");
		StdOut.println("connected"); 
	}
}
