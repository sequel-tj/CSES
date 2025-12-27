import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArrayDescription {
    public static void main(String[] args) {
//        File file = new File("./Dynamic Programming/src/input.txt");
        try {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine();
            int[] arr = new int[n];
            for (int i=0; i<n; i++) {
                arr[i] = sc.nextInt();
            }

            long res = 0;
//            for (int i=1; i<=m; i++) {
//                res = (res + recursion(arr, m, n, i)) % mod;
//            }

//            res = dpMethod(arr, m, n);
            res = dpOptimized(arr, m, n);

            System.out.println(res);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    static int mod = 1000000007;

    static long recursion(int[] arr, int m, int n, int x) {
        if (x < 1 || x > m) return 0;
        if (n==1) {
            if (arr[0] == 0 || arr[0] == x) return 1;
            return 0;
        }
        if (arr[n-1] != 0 && arr[n-1] != x) return 0;

        long res = recursion(arr, m, n-1, x) % mod;
        res = (res + recursion(arr, m, n-1, x-1)) % mod;
        res = (res + recursion(arr, m, n-1, x+1)) % mod;

        return res;
    }

    static long dpMethod(int[] arr, int m, int n) {
        long[][] dp = new long[n][m + 1];

        if (arr[0] == 0) {
            for (int v = 1; v <= m; v++) dp[0][v] = 1;
        } else {
            dp[0][arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {

                if (arr[i] != 0 && arr[i] != j) {
                    dp[i][j] = 0;
                    continue;
                }

                long res = dp[i - 1][j];
                if (j > 1) res = (res + dp[i - 1][j - 1]) % mod;
                if (j < m) res = (res + dp[i - 1][j + 1]) % mod;

                dp[i][j] = res;
            }
        }

        long ans = 0;
        for (int v = 1; v <= m; v++) ans = (ans + dp[n - 1][v]) % mod;

        return ans;
    }

    static long dpOptimized(int[] arr, int m, int n) {
        long[] prev = new long[m + 2];
        long[] curr = new long[m + 2];

        // Base case
        if (arr[0] == 0) {
            for (int v = 1; v <= m; v++) prev[v] = 1;
        } else {
            prev[arr[0]] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int v = 1; v <= m; v++) {

                if (arr[i] != 0 && arr[i] != v) {
                    curr[v] = 0;
                    continue;
                }

                long ways = prev[v];
                ways = (ways + prev[v - 1]) % mod;
                ways = (ways + prev[v + 1]) % mod;

                curr[v] = ways;
            }

            // swap arrays
            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        long ans = 0;
        for (int v = 1; v <= m; v++) ans = (ans + prev[v]) % mod;

        return ans;
    }
}
