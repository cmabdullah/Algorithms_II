import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class CycleMain {

	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		Cycle finder = new Cycle(G);
		// we have to work in future
		/****
		if (finder.hasCycle()) {
			for (int v : finder.hasCycle()) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
		else {
			StdOut.println("Graph is acyclic");
		}
		***/
	}

}
