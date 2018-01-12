import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
/******************************************************************************
 *  Compilation:  javac DirectedDFS.java
 *  Execution:    java DirectedDFS digraph.txt s
 *  Dependencies: Digraph.java Bag.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/mediumDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/largeDG.txt
 *
 *  Determine single-source or multiple-source reachability in a digraph
 *  using depth first search.
 *  Runs in O(E + V) time.
 *
 *  % java DirectedDFS tinyDG.txt 1
 *  1
 *
 *  % java DirectedDFS tinyDG.txt 2
 *  0 1 2 3 4 5
 *
 *  % java DirectedDFS tinyDG.txt 1 2 6
 *  0 1 2 3 4 5 6 8 9 10 11 12 
 *
 ******************************************************************************/
/*****
➜  src ls
Digraph.java          largeDG-largeDG.txt   tinyDG-tinyDG.txt
DirectedDFS.java      mediumDG-mediumDG.txt
➜  src javac DirectedDFS.java
Note: ./Digraph.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
➜  src javac -Xlint:unchecked   DirectedDFS.java
➜  src ls
Digraph.class         DirectedDFS.java      tinyDG.txt
Digraph.java          largeDG-largeDG.txt
DirectedDFS.class     mediumDG-mediumDG.txt
➜  src java  DirectedDFS tinyDG.txt 2
0 1 2 3 4 5 
➜  src 
 * ***/
public class DirectedDFS {
	private boolean[] marked;

	public DirectedDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	public DirectedDFS(Digraph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		for (int s : sources) {
			if (!marked[s]) {
				dfs(G, s);
			}
		}
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}

	public boolean marked(int v) {
		return marked[v];
	}

	public static void main(String[] args) {
		Digraph G = new Digraph(new In(args[0]));

		Bag<Integer> sources = new Bag<Integer>();
		for (int i = 1; i < args.length; i++) {
			sources.add(Integer.parseInt(args[i]));
		}

		DirectedDFS reachable = new DirectedDFS(G, sources);

		for (int v = 0; v < G.V(); v++) {
			if (reachable.marked(v)) {
				StdOut.print(v + " ");
			}
		}
		StdOut.println();
	}
}