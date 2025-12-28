import java.util.Scanner;

public class EditDistance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String t = sc.nextLine();

        // Method 1 => recursion
        // System.out.println(helper(s, t, s.length(), t.length()));

        // Method 2 => dp
        // System.out.println(dpMethod(s, t));

        // Method 3 => dp optimized
        System.out.println(dpOptimized(s, t));
    }

    static int dpOptimized(String s, String t) {
        int m = s.length(), n = t.length();
        int[] curr = new int[n+1];
        int[] prev = new int[n+1];

        for (int j = 0; j <= n; j++) prev[j] = j;

        for(int i=1; i<=m; i++) {
            curr[0] = i;
            for (int j=1; j<=n; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                   curr[j] = prev[j-1];
                }
                else {
                    curr[j] = Math.min(curr[j-1], Math.min(prev[j], prev[j-1])) + 1;
                }
            }

            prev = curr.clone();
        }

        return curr[n];
    }

    static int dpMethod(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for(int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])) + 1;
                }
            }
        }

        return dp[m][n];
    }

    static int helper(String s, String t, int m, int n) {
        if (m == 0) return n;
        if (n == 0) return m;

        int res = 0;

        if (s.charAt(m-1) == t.charAt(n-1)) {
            res = helper(s, t, m-1, n-1);
        }
        else {
            res = Math.min(helper(s, t, m, n-1), Math.min(helper(s, t, m-1, n), helper(s, t, m-1, n-1))) + 1;
        }

        return res;
    }
}
