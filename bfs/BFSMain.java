import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/***
➜  src javac BFSMain.java 
➜  src java BFSMain tinyCG-tinyCG.txt 2
adj[v] is : edu.princeton.cs.algs4.Bag@d716361	adj[w] is : edu.princeton.cs.algs4.Bag@6ff3c5b5	E is :1
adj[v] is : edu.princeton.cs.algs4.Bag@4d7e1886	adj[w] is : edu.princeton.cs.algs4.Bag@3cd1a2f1	E is :2
adj[v] is : edu.princeton.cs.algs4.Bag@4d7e1886	adj[w] is : edu.princeton.cs.algs4.Bag@2f0e140b	E is :3
adj[v] is : edu.princeton.cs.algs4.Bag@7440e464	adj[w] is : edu.princeton.cs.algs4.Bag@4d7e1886	E is :4
adj[v] is : edu.princeton.cs.algs4.Bag@d716361	adj[w] is : edu.princeton.cs.algs4.Bag@7440e464	E is :5
adj[v] is : edu.princeton.cs.algs4.Bag@2f0e140b	adj[w] is : edu.princeton.cs.algs4.Bag@3cd1a2f1	E is :6
adj[v] is : edu.princeton.cs.algs4.Bag@2f0e140b	adj[w] is : edu.princeton.cs.algs4.Bag@6ff3c5b5	E is :7
adj[v] is : edu.princeton.cs.algs4.Bag@d716361	adj[w] is : edu.princeton.cs.algs4.Bag@4d7e1886	E is :8
The value of G is : 6 vertices, 8 edges
0: 2 1 5 
1: 0 2 
2: 0 1 3 4 
3: 5 4 2 
4: 3 2 
5: 3 0 

Args is : 2
2 to 0: 2-0
2 to 1: 2-1
2 to 2: 2
2 to 3: 2-3
2 to 4: 2-4
2 to 5: 2-0-5
➜  src 

 * **/
public class BFSMain {

	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]));
		StdOut.println("The value of G is : "+G);
		
		int s = Integer.parseInt(args[1]);
		StdOut.println("Args is : "+s);
		
		BreadthFirstPaths search = new BreadthFirstPaths(G, s);
		for (int v = 0; v < G.V(); v++) {
			StdOut.print(s + " to " + v + ": ");
			if (search.hasPathTo(v)) {
				for (int x : search.pathTo(v)) {
					if (x == s) {
						StdOut.print(x);
					} else {
						StdOut.print("-" + x);
					}
				}
			}
			StdOut.println();
		}

	}

}
