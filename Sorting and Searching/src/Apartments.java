import java.io.*;
import java.util.*;

public class Apartments {

    static class FastReader {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }

    static int countOfApplicants(int n, int m, int k, int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int count = 0;
        int i = 0, j = 0;

        while (i < n && j < m) {
            if (Math.abs(a[i] - b[j]) <= k) {
                count++;
                i++;
                j++;
            } else if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();

        int n = fr.nextInt();
        int m = fr.nextInt();
        int k = fr.nextInt();

        int[] a = new int[n];
        int[] b = new int[m];

        for (int i = 0; i < n; i++) {
            a[i] = fr.nextInt();
        }

        for (int i = 0; i < m; i++) {
            b[i] = fr.nextInt();
        }

        System.out.println(countOfApplicants(n, m, k, a, b));
    }
}