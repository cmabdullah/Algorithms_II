import edu.princeton.cs.algs4.StdOut;
/****
Element of G object is

G is : 13 vertices, 13 edges
0: 6 2 1 5 
1: 0 
2: 0 
3: 5 4 
4: 5 6 3 
5: 3 4 0 
6: 0 4 
7: 8 
8: 7 
9: 11 10 12 
10: 9 
11: 9 12 
12: 11 9 
**/
public class DepthFirstSearch {
	private boolean[] marked;
	private int count;
	Graph G;
	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];// define marked length
		//this.G= G;
		dfs(G, s);
	}
	public void show() {
		StdOut.println("marked array length is : "+marked.length);
		for (int i = 0;i<13;i++) {
			StdOut.println("index : "+i+" marked status :"+marked[i]);
		}
	}
	private void dfs(Graph G, int v) {
		marked[v] = true;
		count++;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}

	public boolean marked(int w) {
		StdOut.println("\n marked value :"+marked[w]);
		return marked[w];
	}

	public int count() {
		return count;
	}
}