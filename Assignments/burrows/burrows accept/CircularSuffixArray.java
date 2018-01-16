import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Quick3way;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class CircularSuffixArray {
	private static final int R = 256;
	private static final int M = 30;
	private final int N;
	private int[] indices;
	private OffsetString[] aux;

	public CircularSuffixArray(String s) {
		if (s == null) throw new NullPointerException();

		N = s.length();
		indices = new int[N];
		Set<Character> alphabet = new HashSet<>();

		// build fake suffix string array
		OffsetString[] suffix = new OffsetString[N];
		for (int offset = 0; offset < N; offset++) {
			suffix[offset] = new OffsetString(s, offset);
			alphabet.add(s.charAt(offset));
		}

		if (alphabet.size() > 7)
			msdSort(suffix);
		else if (alphabet.size() > 1)
			Quick3way.sort(suffix);

		// get indices
		for (int i = 0; i < N; i++)
			indices[i] = suffix[i].getOffset();
	}

	public int length() {
		return N;
	}

	public int index(int i) {
		if (i < 0 || i >= N) throw new IndexOutOfBoundsException();
		return indices[i];
	}

	private void msdSort(OffsetString[] a) {
		aux = new OffsetString[a.length];
		msdSort(a, 0, a.length -1, 0);
		aux = null;
	}

	private void msdSort(OffsetString[] a, int lo, int hi, int d) {
		if(hi <= lo + M) {
			Insertion.sort(a, lo, hi, ComparatorAtD(d));
			return;
		}

		int[] count = new int[R + 2];
		for (int i = lo; i <= hi; i++)
			count[a[i].charAt(d) + 2]++;
		for (int r = 0; r < R + 1; r++)
			count[r + 1] += count[r];
		for (int i = lo; i <= hi; i++)
			aux[count[a[i].charAt(d) + 1]++] = a[i];
		for (int i = lo; i <= hi; i++)
			a[i] = aux[i - lo];

		for (int r = 0; r < R; r++)
			msdSort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
	}

	private void exch(OffsetString[] a, int i, int j) {
		OffsetString temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static class OffsetString implements Comparable<OffsetString> {
		private final String s;
		private final int N;
		private final int offset;

		public OffsetString(String s, int offset) {
			this.s = s;
			this.N = s.length();
			this.offset = offset;
		}

		public int charAt(int i) {
			if (i >= N) return 0;
			return s.charAt((i + offset) % N);
		}

		public int getOffset() {
			return offset;
		}

		@Override
		public int compareTo(OffsetString that) {
			for (int i = 0; i < N; i++) {
				int diff = this.charAt(i) - that.charAt(i);
				if (diff != 0)
					return diff;
			}
			return 0;
		}
	}

	private Comparator<OffsetString> ComparatorAtD(int d) {
		return new Comparator<OffsetString>() {
			@Override
			public int compare(OffsetString o1, OffsetString o2) {
				for (int i = d; i < N; i++) {
					int diff = o1.charAt(i) - o2.charAt(i);
					if (diff != 0)
						return diff;
				}
				return 0;
			}
		};
	}

	public static void main(String[] args) {
		CircularSuffixArray c = new CircularSuffixArray("ABRACADABRA!");
		for (int i = 0; i < 12; i++)
			System.out.println(c.index(i));
	}
}