import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class FerrisWheel {
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

    static int countGondolas(int[] weights, int x) {
        Arrays.sort(weights);
        int left = 0, right = weights.length - 1;
        int gondolas = 0;

        while (left <= right) {
            if (weights[left] + weights[right] <= x) {
                left++;
            }
            right--;
            gondolas++;
        }

        return gondolas;
    }

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();

        int n = fr.nextInt();
        int x = fr.nextInt();

        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = fr.nextInt();
        }

        System.out.println(countGondolas(weights, x));
    }
}
