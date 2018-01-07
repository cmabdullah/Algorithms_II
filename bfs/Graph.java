import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Graph {
	private final int V;// number of vertices
	private int E;// number of edges
	private Bag<Integer>[] adj; // adjacency lists
	public Graph(int V){// Read vertex from the file
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];// Create array of lists.
		for (int v = 0; v < V; v++)// Initialize all lists
			adj[v] = new Bag<Integer>();//to empty.
	}

	public Graph(In in){
		this(in.readInt()); // Read V and construct this graph.
		int E = in.readInt();// Read E.
		for (int i = 0; i < E; i++){  // Add an edge.
			int v = in.readInt();// Read a vertex,
			int w = in.readInt(); // read another vertex,
			addEdge(v, w);// and add edge connecting them.
		}
	}
	

	/****
	public Graph(Graph G) {
		this(G.V());
		E = G.E();
		for (int v = 0; v < G.V(); v++) {
			Stack<Integer> reverse = new Stack<>();
			for (int w : G.adj[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adj[v].add(w);
			}
		}
	}
	****/
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
		StdOut.println("adj[v] is : "+adj[v] +"\tadj[w] is : "+adj[w]+"\tE is :"+E);
	}
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public void display() {
	StdOut.println("\nadj[7] is : "+adj[7] +"\tadj[8] is : "+adj[8] );
	StdOut.print("value of adj5 where Bag@d716361 : ");
		for (int w : this.adj(5)) {
			StdOut.print(w+" ");
		}
		StdOut.println();
	}
	

	@Override
	public String toString() {
		String s = V + " vertices, " + E + " edges\n";
		for (int v = 0; v < V; v++) {
			s += v + ": ";
			//s = s + v + ": ";
			for (int w : this.adj(v)) {
				s += w + " ";
			}
			s += "\n";
		}
		return s;
	}
}
