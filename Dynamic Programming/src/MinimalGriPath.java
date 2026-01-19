import java.io.*;
import java.util.*;

public class MinimalGriPath {
    // Fast I/O Reader
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        try {
            int n = fr.nextInt();
            char[][] arr = new char[n][n];
            for (int i = 0; i < n; i++) {
                arr[i] = fr.next().toCharArray();
            }
            System.out.println(helper(arr, n));
        } catch (Exception e) {}
    }

    static String helper(char[][] arr, int n) {
        // Use arrays instead of Queue<Pair> to avoid object creation
        int[] qX = new int[n * 2];
        int[] qY = new int[n * 2];
        int head = 0, tail = 0;

        boolean[][] visited = new boolean[n][n];
        StringBuilder res = new StringBuilder();

        // Start
        qX[tail] = 0;
        qY[tail] = 0;
        tail++;
        visited[0][0] = true;
        res.append(arr[0][0]);

        // Standard step-based BFS
        while (head < tail) {
            int size = tail - head;
            char small = '{';

            // Temporary arrays for the next frontier to avoid re-scanning
            int[] nextX = new int[n * 2];
            int[] nextY = new int[n * 2];
            int nextCount = 0;

            // Step 1: Find the minimum character in the next diagonal
            for (int i = 0; i < size; i++) {
                int r = qX[head + i];
                int c = qY[head + i];

                if (r + 1 < n) {
                    if (arr[r + 1][c] < small) small = arr[r + 1][c];
                }
                if (c + 1 < n) {
                    if (arr[r][c + 1] < small) small = arr[r][c + 1];
                }
            }

            if (small == '{') break;
            res.append(small);

            // Step 2: Only add neighbors matching 'small' to the next frontier
            for (int i = 0; i < size; i++) {
                int r = qX[head++];
                int c = qY[head - 1];

                // Down
                if (r + 1 < n && arr[r + 1][c] == small && !visited[r + 1][c]) {
                    visited[r + 1][c] = true;
                    nextX[nextCount] = r + 1;
                    nextY[nextCount] = c;
                    nextCount++;
                }
                // Right
                if (c + 1 < n && arr[r][c + 1] == small && !visited[r][c + 1]) {
                    visited[r][c + 1] = true;
                    nextX[nextCount] = r;
                    nextY[nextCount] = c + 1;
                    nextCount++;
                }
            }

            // Reset queue for next level
            head = 0;
            tail = nextCount;
            System.arraycopy(nextX, 0, qX, 0, nextCount);
            System.arraycopy(nextY, 0, qY, 0, nextCount);
        }
        return res.toString();
    }
}