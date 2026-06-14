import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SumOfTwoValues {
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

    static class Pair {
        int value;
        int index;

        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        int n = fr.nextInt();
        int target = fr.nextInt();

        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(fr.nextInt(), i);
        }

        Arrays.sort(arr, (a, b) -> a.value - b.value);

        int left = 0, right = n - 1;
        while (left < right) {
            int sum = arr[left].value + arr[right].value;
            if (sum == target) {
                System.out.println((arr[left].index + 1) + " " + (arr[right].index + 1));
                return;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
