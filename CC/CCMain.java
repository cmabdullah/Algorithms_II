import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
/***
 ➜  src javac CCMain.java
Note: CCMain.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
➜  src java CCMain tinyG-tinyG.txt
adj[v] is : edu.princeton.cs.algs4.Bag@d716361	adj[w] is : edu.princeton.cs.algs4.Bag@6ff3c5b5	E is :1
adj[v] is : edu.princeton.cs.algs4.Bag@4d7e1886	adj[w] is : edu.princeton.cs.algs4.Bag@3cd1a2f1	E is :2
adj[v] is : edu.princeton.cs.algs4.Bag@d716361	adj[w] is : edu.princeton.cs.algs4.Bag@2f0e140b	E is :3
adj[v] is : edu.princeton.cs.algs4.Bag@7440e464	adj[w] is : edu.princeton.cs.algs4.Bag@49476842	E is :4
adj[v] is : edu.princeton.cs.algs4.Bag@78308db1	adj[w] is : edu.princeton.cs.algs4.Bag@4d7e1886	E is :5
adj[v] is : edu.princeton.cs.algs4.Bag@6ff3c5b5	adj[w] is : edu.princeton.cs.algs4.Bag@4d7e1886	E is :6
adj[v] is : edu.princeton.cs.algs4.Bag@d716361	adj[w] is : edu.princeton.cs.algs4.Bag@27c170f0	E is :7
adj[v] is : edu.princeton.cs.algs4.Bag@5451c3a8	adj[w] is : edu.princeton.cs.algs4.Bag@49476842	E is :8
adj[v] is : edu.princeton.cs.algs4.Bag@7440e464	adj[w] is : edu.princeton.cs.algs4.Bag@2626b418	E is :9
adj[v] is : edu.princeton.cs.algs4.Bag@d716361	adj[w] is : edu.princeton.cs.algs4.Bag@78308db1	E is :10
adj[v] is : edu.princeton.cs.algs4.Bag@5a07e868	adj[w] is : edu.princeton.cs.algs4.Bag@76ed5528	E is :11
adj[v] is : edu.princeton.cs.algs4.Bag@7440e464	adj[w] is : edu.princeton.cs.algs4.Bag@5451c3a8	E is :12
adj[v] is : edu.princeton.cs.algs4.Bag@6ff3c5b5	adj[w] is : edu.princeton.cs.algs4.Bag@3cd1a2f1	E is :13
3 components
6 5 4 3 2 1 0 
8 7 
12 11 10 9 
➜  src 
 * **/
public class CCMain {

	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]));
		CC cc = new CC(G);

		int M = cc.count();
		StdOut.println(M + " components");

		Bag<Integer>[] components = (Bag<Integer>[]) new Bag[M];
		for (int i = 0; i < M; i++) {
			components[i] = new Bag<Integer>();
		}
		for (int v = 0; v < G.V(); v++) {
			components[cc.id(v)].add(v);
		}
		for (int i = 0; i < M; i++) {
			for (int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}

}
