import java.io.*;
import java.util.*;

public class MovieFestival {

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

            int val = 0;
            do {
                val = val * 10 + (c - '0');
                c = read();
            } while (c > ' ');

            return val;
        }
    }

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();

        int n = fr.nextInt();

        Stack<Integer> stack = new Stack<>();

        int[][] movies = new int[n][2];
        for (int i = 0; i < n; i++) {
            int a = fr.nextInt();
            int b = fr.nextInt();
            movies[i][0] = a;
            movies[i][1] = b;
        }

        Arrays.sort(movies, (x, y) -> x[1] - y[1]);

        for (int i = 0; i < n; i++) {
            int a = movies[i][0];
            int b = movies[i][1];
            if (stack.isEmpty() || stack.peek() <= a) {
                stack.push(b);
            }
        }

        System.out.println(stack.size());
    }
}