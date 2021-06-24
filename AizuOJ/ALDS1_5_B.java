import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main{
	public static void main(String[] args){
		OnlineJudge pgm = new ALDS1_5_A();
		pgm.execute();
	}
}

interface OnlineJudge {
	final MyScanner sc = new MyScanner(System.in);
	public default void execute() {
		execute(sc);
		sc.close();
	}
	abstract void execute(MyScanner sc);
	/**
	 * nextIntよりもnextからのparseの方が早いらしいので
	 * @return
	 */
	public default int nexti() {
		return sc.nextInt();
	}
	public default String nexts() {
		return sc.next();
	}
}

class ALDS1_5_A implements OnlineJudge{

	public void execute(MyScanner sc){
		int n = nexti();
		int[] s = new int[n];
		for(int i = 0; i < n; i++) {
			s[i] = nexti();
		}
		mergeSort(s, 0, s.length);
		System.out.print(s[0]);
		for(int i = 1, ilen = s.length; i < ilen; i++) {
			System.out.print(" " +s[i]);
		}
		System.out.println();
		System.out.println(cnt);
	}

	int cnt = 0;
	private int mergeSort(int[] ary, int l, int r) {
		if(l + 1 < r) {
			int m = (l + r)/2;
			mergeSort(ary, l, m);
			mergeSort(ary, m, r);
			merge(ary, l, m, r);
		}
		return cnt;
	}
	private int merge(int[] ary, int l, int m, int r) {
		int ali = m - l;
		int ari = r - m;
		int[] al = new int[ali+1];
		int[] ar = new int[ari+1];
		for(int i = 0; i < ali; i++) {
			al[i] = ary[l+i];
		}
		for(int i = 0; i < ari; i++) {
			ar[i] = ary[m+i];
		}
		al[ali] = 2000000000;
		ar[ari] = 2000000000;
		int li = 0;
		int ri = 0;
		for(int i = l; i < r; i++) {
			if(al[li] <= ar[ri]) {
				ary[i] = al[li];
				li++;
				cnt++;
			}else {
				ary[i] = ar[ri];
				ri++;
				cnt++;
			}
		}
		return cnt;
	}

}


class MyScanner {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int ptr = 0;
	private int buflen = 0;

	public MyScanner(InputStream stream) {
		this.stream = stream;
	}
	private boolean hasNextByte() {
		if (ptr < buflen) {
			return true;
		}else{
			ptr = 0;
			try {
				buflen = stream.read(buf);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (buflen <= 0) {
				return false;
			}
		}
		return true;
	}
	private int readByte() {
		if (hasNextByte()) return buf[ptr++]; else return -1;
	}
	private static boolean isPrintableChar(int c) {
		return 33 <= c && c <= 126;
	}
	public boolean hasNext() {
		while(hasNextByte() && !isPrintableChar(buf[ptr])) ptr++;
		return hasNextByte();
	}
	public String next() {
		if (!hasNext()) throw new NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while(isPrintableChar(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	public long nextLong() {
		if (!hasNext()) throw new NoSuchElementException();
		long n = 0;
		boolean minus = false;
		int b = readByte();
		if (b == '-') {
			minus = true;
			b = readByte();
		}
		if (b < '0' || '9' < b) {
			throw new NumberFormatException();
		}
		while(true){
			if ('0' <= b && b <= '9') {
				n *= 10;
				n += b - '0';
			}else if(b == -1 || !isPrintableChar(b)){
				return minus ? -n : n;
			}else{
				throw new NumberFormatException();
			}
			b = readByte();
		}
	}
	public int nextInt() {
		long nl = nextLong();
		if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
		return (int) nl;
	}
	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public void close() {
		try{
			stream.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}


