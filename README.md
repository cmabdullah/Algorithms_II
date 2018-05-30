# Algorithms

Installation <br />
------------
Download the libraries for MAC and Linux (algs4.jar,stdlib.jar) <br />
for **windows** see this [libraries](https://github.com/cmabdullah/Algorithms_I/tree/master/libraries)

The last stable release is available on sudo and can be installed with `` sudo ``:: <br />


    $ sudo wget http://algs4.cs.princeton.edu/code/algs4.jar
    $ sudo wget http://algs4.cs.princeton.edu/code/stdlib.jar


* Java wrapper scripts from javac-algs4 and java-algs4


MAC <br />


    $ sudo wget http://algs4.cs.princeton.edu/mac/java-algs4
    $ sudo wget http://algs4.cs.princeton.edu/mac/javac-algs4


Linux <br />


	$ cd /usr/local
	$ sudo mkdir algs4
	$ sudo chmod 755 algs4
	$ cd algs4
	$ pwd
	$ /usr/local/algs4
	$ sudo wget http://algs4.cs.princeton.edu/code/algs4.jar
	$ sudo wget http://algs4.cs.princeton.edu/code/stdlib.jar
	$ sudo wget http://algs4.cs.princeton.edu/linux/javac-algs4
	$ sudo wget http://algs4.cs.princeton.edu/linux/java-algs4
	$ sudo chmod 755 javac-algs4 java-algs4
	$ sudo mv javac-algs4 /usr/local/bin
	$ sudo mv java-algs4 /usr/local/bin


Documentation <br />
-------------


* Documentation of Linux is available online: http://algs4.cs.princeton.edu/linux/
* Documentation of MAC is available online: http://algs4.cs.princeton.edu/mac/
* Documentation of Windows is available online: http://algs4.cs.princeton.edu/windows/


## Very Basic Algorithms that every computer programmer should know.

* 01 BinarySearch

* 02 graph api
* 03 depth first search
* 04 breadth first search
* 05 digraph api
* 06 digraph search
* 07 topological sort
* 08 greedy algorithm
* 09 edge weighted graph api
* 10 kruskals algorithm
* 11 prims algorithm
* 12 mst context
* 13 shortest paths apis
* 14 dijkstras algorithm
* 15 edge weighted dags
* 16 negative weights
* 17 ford fulkerson algorithm
* 18 ~~maxflow-mincut-theorem~~



## Input Method
```cpp
public static void main(String[] args){	
	StdOut.println("Enter range :");
	int v = StdIn.readInt();
	String[] a = new String[v];
	heap obj = new heap();
	for (int i = 0;i< v; i++){
	a[i] = StdIn.readString();
}
```

[All Slide](http://algs4.cs.princeton.edu/home/)