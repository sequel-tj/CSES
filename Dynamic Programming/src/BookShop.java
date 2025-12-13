import java.io.File;
import java.sql.SQLOutput;
import java.util.Scanner;

public class BookShop {
    public static void main(String[] args) {
//        File file = new File("./Dynamic Programming/src/input.txt");
        try {
            Scanner sc = new Scanner(System.in);

            int n = sc.nextInt();
            int x = sc.nextInt();
            sc.nextLine();
            int[] prices = new int[n];
            int[] pages = new int[n];

            for (int i = 0; i<n; i++) {
                prices[i] = sc.nextInt();
            }

            for (int i=0; i<n; i++) {
                pages[i] = sc.nextInt();
            }

          //  System.out.println(recursion(prices, pages, n, x));
          //  System.out.println(dpMethod(prices, pages, n, x));
          //  System.out.println(dpOptimized(prices, pages, n, x));
            System.out.println(dpOptimized2(prices, pages, n, x));
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    static int dpOptimized2(int[] prices, int[] pages, int n, int total) {
        /*
        for bounded knapsack, for unbounded reverse (as we need to reuse the value)
        ✅ If recurrence uses dp[i‑1][…], you must loop backwards in 1D DP.
        ✅ If recurrence uses dp[i][…] (same row), you loop forward.
         */
        int[] curr = new int[total+1];

        for (int i=1; i<=n; i++) {
            for (int j=total; j>=prices[i-1]; j--) {
                curr[j] = Math.max(curr[j], curr[j-prices[i-1]] + pages[i-1]);
            }
        }

        return curr[total];
    }

    static int dpOptimized(int[] prices, int[] pages, int n, int total) {
        int[] prev = new int[total+1];
        int[] curr = new int[total+1];

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=total; j++) {
                curr[j] = prev[j];
                if (prices[i-1] <= j) {
                    curr[j] = Math.max(curr[j], prev[j-prices[i-1]] + pages[i-1]);
                }
            }
        }

        return curr[total];
    }

    static int dpMethod(int[] prices, int[] pages, int n, int total) {
        int[][] dp = new int[n+1][total+1];

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=total; j++) {
                dp[i][j] = dp[i-1][j];
                if (prices[i-1] <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-prices[i-1]] + pages[i-1]);
                }
            }
        }

        return dp[n][total];
    }

    static int recursion(int[] prices, int[] pages, int n, int total) {
        if (n == 0) return 0;

        int res = recursion(prices, pages, n-1, total);
        if (prices[n-1] <= total) {
            res = Math.max(res, recursion(prices, pages, n - 1, total - prices[n - 1]) + pages[n-1]);
        }

        return res;
    }
}
