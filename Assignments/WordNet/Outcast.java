import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/***

Last login: Sun Jan 14 08:37:58 on console
➜  ~ Desktop 
➜  Desktop mkdir WordNet
➜  Desktop Word
zsh: command not found: Word
➜  Desktop      WordNet 
➜  WordNet vim SAP.java
➜  WordNet vim WordNet.java
➜  WordNet vim Outcast.java
➜  WordNet youtube-dl http://coursera.cs.princeton.edu/algs4/testing/wordnet/digraph1.txt
[generic] digraph1: Requesting header
WARNING: Falling back on generic information extractor.
[generic] digraph1: Downloading webpage
WARNING: URL could be a direct video link, returning it as such.
[download] Destination: digraph1-digraph1.txt
[download] 100% of 72.00B in 00:00
➜  WordNet ls
Outcast.java          WordNet.java
SAP.java              digraph1-digraph1.txt
➜  WordNet javac Outcast.java
➜  WordNet ls
Outcast.class         SAP.java              digraph1-digraph1.txt
Outcast.java          WordNet.class
SAP.class             WordNet.java
➜  WordNet java Outcast digraph1-digraph1.txt
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 1
    at Outcast.main(Outcast.java:33)
➜  WordNet more digraph1-digraph1.txt
13
11
 7  3
 8  3
 3  1
 4  1
 5  1
 9  5
10  5
11 10
12 10
 1  0
 2  0
➜  WordNet java-algs4 SAP digraph1-digraph1.txt
3 11
length = 4, ancestor = 1
9 12
length = 3, ancestor = 5
q
Exception in thread "main" java.util.InputMismatchException: attempts to read an 'int' value from standard input, but the next token is "q"
    at edu.princeton.cs.algs4.StdIn.readInt(StdIn.java:346)
    at SAP.main(SAP.java:153)
➜  WordNet vim outcast5.txt
➜  WordNet ls
Outcast.class         SAP.java              digraph1-digraph1.txt
Outcast.java          WordNet.class         outcast5.txt
SAP.class             WordNet.java
➜  WordNet java-algs4 Outcast  outcast5.txt
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 1
    at Outcast.main(Outcast.java:33)
➜  WordNet vim outcast8.txt
➜  WordNet java Outcast  outcast5.txt outcast8.txt
Exception in thread "main" java.lang.NumberFormatException: For input string: "horse zebra cat bear table"
    at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
    at java.lang.Integer.parseInt(Integer.java:580)
    at java.lang.Integer.parseInt(Integer.java:615)
    at WordNet.<init>(WordNet.java:25)
    at Outcast.main(Outcast.java:33)
➜  WordNet java-algs4 Outcast synsets.txt hypernyms.txt outcast5.txt outcast8.txt outcast11.txt
outcast5.txt: table
outcast8.txt: bed
outcast11.txt: potato
➜  WordNet 


***/
public class Outcast {
    private WordNet wordnet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int max = -1;
        String result = null;

        for (String noun : nouns) {
            int d = 0;
            for (String other: nouns)
                d += wordnet.distance(noun, other);

            if (d > max) {
                max = d;
                result = noun;
            }
        }

        return result;
    }

    // see test client below
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}

