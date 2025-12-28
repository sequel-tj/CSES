import java.util.Scanner;

public class RectangleCutting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        // Method 1 => recursion
        // System.out.println(helper(a, b));

        // Method 2 => dp method
        System.out.println(dpMethod(a, b));
    }

    static int dpMethod(int a, int b) {
        int[][] dp = new int[a+1][b+1];

        for(int i=1; i<=a; i++) {
            for(int j=1; j<=b; j++) {
                if (i == j) dp[i][j] = 0;
                else {
                    int res = Integer.MAX_VALUE;
                    for (int k = 1; k < j; k++) {
                        res = Math.min(res, dp[i][k] + dp[i][j - k] + 1);
                    }

                    for (int k = 1; k < i; k++) {
                        res = Math.min(res, dp[k][j] + dp[i - k][j] + 1);
                    }
                    dp[i][j] = res;
                }
            }
        }

        return dp[a][b];
    }

    static int helper(int a, int b) {
        if (a == b) return 0;
        int res = Integer.MAX_VALUE;

        for (int i=1; i<b; i++) {
            res = Math.min(res, helper(a, i) + helper(a, b-i) + 1);
        }

        for (int i = 1; i<a; i++) {
            res = Math.min(res, helper(i, b) + helper(a-i, b) + 1);
        }
        return res;
    }
}
