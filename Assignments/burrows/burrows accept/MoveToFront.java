import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.LinkedList;

public class MoveToFront {
	private static int lgR = 8;

	// apply move-to-front encoding, reading from standard input and writing to standard output
	//transform()
	public static void encode() {
		LinkedList<Integer> alphabet = new LinkedList<>();
		for (int i = 0; i < 256; i++)
			alphabet.add(i);

		while (!BinaryStdIn.isEmpty()) {
			int c = BinaryStdIn.readChar();
			int index = alphabet.indexOf(c);
			BinaryStdOut.write(index, lgR);

			alphabet.remove(index);
			alphabet.add(0, c);
		}
		BinaryStdOut.close();
	}

	// apply move-to-front decoding, reading from standard input and writing to standard output
	//inverseTransform
	public static void decode() {
		LinkedList<Integer> alphabet = new LinkedList<>();
		for (int i = 0; i < 256; i++)
			alphabet.add(i);

		while (!BinaryStdIn.isEmpty()) {
			int index = BinaryStdIn.readChar();
			int c = alphabet.get(index);
			BinaryStdOut.write(c, lgR);

			alphabet.remove(index);
			alphabet.add(0, c);
		}
		BinaryStdOut.close();
	}

	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args) {
		if (args.length == 0) return;
		if ("-".equals(args[0]))
			encode();
		else if ("+".equals(args[0]))
			decode();
	}
}
