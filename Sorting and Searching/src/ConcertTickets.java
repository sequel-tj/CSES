import java.io.IOException;
import java.io.InputStream;
import java.util.TreeMap;

public class ConcertTickets {

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

    public static void main(String[] args) throws Exception {

        FastReader fr = new FastReader();

        int n = fr.nextInt();
        int m = fr.nextInt();

        TreeMap<Integer, Integer> priceMap = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int price = fr.nextInt();
            priceMap.put(price, priceMap.getOrDefault(price, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int offer = fr.nextInt();

            Integer price = priceMap.floorKey(offer);

            if (price == null) {
                sb.append("-1\n");
            } else {
                sb.append(price).append("\n");

                int freq = priceMap.get(price);
                if (freq == 1) {
                    priceMap.remove(price);
                } else {
                    priceMap.put(price, freq - 1);
                }
            }
        }

        System.out.print(sb.toString());
    }
}