import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridPaths1 {
    static final int MOD = 1000000007;

    public static void main(String[] args){
//        File file = new File("./Dynamic Programming/src/input.txt");
        try {
            Scanner input = new Scanner(System.in);

            int n = input.nextInt();
            input.nextLine();
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = input.nextLine();
            }

            if (arr[n-1].charAt(n-1) == '*' || arr[0].charAt(0) == '*') {
                System.out.println("0");
                return;
            }

            // System.out.println(recursion(arr, n, n));
            // System.out.println(dpMethod(arr, n));
            System.out.println(dpOptimized(arr, n));
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    static int dpOptimized(String[] arr, int n) {
        long[] prev = new long[n+1];
        long[] curr = new long[n+1];

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (arr[i-1].charAt(j-1) == '*') curr[j] = 0;
                else if (i==1 && j==1) curr[j] = 1;
                else curr[j] = (prev[j] + curr[j-1]) % MOD;
            }
            prev = curr;
        }

        return (int)curr[n];
    }

    static int dpMethod(String[] arr, int n) {
        int[][] dp = new int[n+1][n+1];

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (arr[i-1].charAt(j-1) == '*') dp[i][j] = 0;
                else if (i==1 && j==1) dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[n][n];
    }

    static int recursion(String[] arr, int i, int j) {
        if (i==1 && j == 1) return 1;
        if (i <= 0 || j <= 0) return 0;
        if (arr[i-1].charAt(j-1) == '*') return 0;

        return recursion(arr, i-1, j) + recursion(arr, i, j-1);
    }
}
