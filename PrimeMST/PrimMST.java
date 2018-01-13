import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
/*****
➜  src ls
Edge.java                  largeEWG-largeEWG.txt.part
EdgeWeightedGraph.java     mediumEWG-mediumEWG.txt
PrimMST.java               tinyEWG-tinyEWG.txt
➜  src javac PrimMST.java
Note: ./EdgeWeightedGraph.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
➜  src javac -Xlint:unchecked PrimMST.java 
➜  src java PrimMST mediumEWG-mediumEWG.txt 
158-249 0.06
185-248 0.03
190-247 0.07
11-246 0.07
154-245 0.04
30-244 0.01
.
.
.
10.463509999999994
➜  src 

**/
public class PrimMST {
		private Edge[] edgeTo;
		private double[] distTo;
		private boolean[] marked;
		private IndexMinPQ<Double> pq;

		public PrimMST(EdgeWeightedGraph G) {
			edgeTo = new Edge[G.V()];
			distTo = new double[G.V()];
			marked = new boolean[G.V()];
			for (int v = 0; v < G.V(); v++) {
				distTo[v] = Double.POSITIVE_INFINITY;
			}
			pq = new IndexMinPQ<Double>(G.V());

			distTo[0] = 0.0;
			pq.insert(0, 0.0);
			while (!pq.isEmpty()) {
				visit(G, pq.delMin());
			}
		}

		private void visit(EdgeWeightedGraph G, int v) {
			marked[v] = true;
			for (Edge e : G.adj(v)) {
				int w = e.other(v);

				if (marked[w]) {
					continue;
				}
				if (e.weight() < distTo[w]) {
					edgeTo[w] = e;

					distTo[w] = e.weight();
					if (pq.contains(w)) {
						pq.changeKey(w, distTo[w]);
					} else {
						pq.insert(w, distTo[w]);
					}
				}
			}
		}

		/**
		 * Exercise 4.3.21
		 * 
		 * @return
		 */
		public Iterable<Edge> edges() {
			Bag<Edge> mst = new Bag<>();
			for (int i = 1; i < edgeTo.length; i++) {
				mst.add(edgeTo[i]);
			}
			return mst;
		}

		/**
		 * Exercise 4.3.31
		 * 
		 * @return
		 */
		public double weight() {
			double weight = 0;
			for (int i = 0; i < distTo.length; i++) {
				weight += distTo[i];
			}
			return weight;
		}
	public static void main(String[] args) {


		In in = new In(args[0]);
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		PrimMST mst = new PrimMST(G);
		for (Edge e : mst.edges())
			StdOut.println(e);
		StdOut.println(mst.weight());

	}

}
