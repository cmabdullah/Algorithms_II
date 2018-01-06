import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/***
➜  src javac  -Xlint:deprecation TestSearch.java
➜  src javac  -Xlint:unchecked TestSearch.java  
➜  src java TestSearch dfs.txt 2, here 2 is a argument
0 1 2 3 4 5 6 
NOT connected
➜  src 
 * **/
public class TestSearch {

	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]));
		G.display();
		StdOut.println("The value of G is : "+G);
		
		int s = Integer.parseInt(args[1]);
		StdOut.println("Args is : "+s);
		
		DepthFirstSearch search = new DepthFirstSearch(G, s);
		search.show();
		for (int v = 0; v < G.V(); v++)
			if (search.marked(v))
				StdOut.print(v + " ");
		StdOut.println();
		if (search.count() != G.V())
			StdOut.print("NOT connected");
			
	}

}
