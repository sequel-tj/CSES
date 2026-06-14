import java.io.*;
import java.util.*;

public class RestaurantCustomers {

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

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int a = fr.nextInt();
            int b = fr.nextInt();

            map.put(a, map.getOrDefault(a, 0) + 1);
            map.put(b, map.getOrDefault(b, 0) - 1);
        }

        int current = 0;
        int answer = 0;

        for (int delta : map.values()) {
            current += delta;
            answer = Math.max(answer, current);
        }

        System.out.println(answer);
    }
}