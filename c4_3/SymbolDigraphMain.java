/****
➜  src ls
DepthFirstOrder.java   DirectedDFS.class      Topological.java
Digraph.class          DirectedDFS.java       largeDG-largeDG.txt
Digraph.java           SymbolDigraph.java     mediumDG-mediumDG.txt
DirectedCycle.java     SymbolDigraphMain.java tinyDG.txt
➜  src youtube-dl https://algs4.cs.princeton.edu/42digraph/routes.txt
[generic] routes: Requesting header
WARNING: Falling back on generic information extractor.
[generic] routes: Downloading webpage
WARNING: URL could be a direct video link, returning it as such.
[download] Destination: routes-routes.txt
[download] 100% of 144.00B in 00:00
➜  src ls
DepthFirstOrder.java   DirectedDFS.java       mediumDG-mediumDG.txt
Digraph.class          SymbolDigraph.java     routes-routes.txt
Digraph.java           SymbolDigraphMain.java tinyDG.txt
DirectedCycle.java     Topological.java
DirectedDFS.class      largeDG-largeDG.txt
➜  src javac SymbolDigraphMain.java
➜  src java SymbolDigraphMain routes-routes.txt " "
JFK
   ORD
   ATL
   MCO
ATL
   MCO
   HOU


 * **/
public class SymbolDigraphMain {

	public static void main(String[] args) {
		String filename = args[0];
		String delim = args[1];
		SymbolDigraph sg = new SymbolDigraph(filename, delim);
		Digraph G = sg.G();
		while (StdIn.hasNextLine()) {
			String source = StdIn.readLine();
			for (int w : G.adj(sg.index(source))) {
				StdOut.println("   " + sg.name(w));
			}
		}
	}

}
