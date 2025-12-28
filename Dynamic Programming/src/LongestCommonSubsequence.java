import java.util.Scanner;
import java.util.Stack;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] a1 = new int[m];
        int[] a2 = new int[n];

        for (int i = 0; i < m; i++) {
            a1[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            a2[i] = sc.nextInt();
        }

        // Method 1 => recursion
        // System.out.println(helper(a1, a2, m, n));

        // Method 2 => dp
        dpMethod(a1, a2, m, n);
    }

    static void dpMethod(int[] s, int[] t, int m, int n) {
        int[][] dp = new int[m+1][n+1];

        for(int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (s[i-1] == t[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }

        System.out.println(dp[m][n]);
        Stack<Integer> st = new Stack<Integer>();

        while(m>0 && n>0) {
            if (dp[m][n] == dp[m][n-1]) n--;
            else if (dp[m][n] == dp[m-1][n]) m--;
            else {
                st.push(t[n-1]);
                m--;
                n--;
            }
        }

        while(!st.empty()) {
            System.out.print(st.pop() + " ");
        }
    }

    static int helper(int[] s, int[] t, int m, int n) {
        if (m == 0) return 0;
        if (n == 0) return 0;

        int res = 0;

        if (s[m-1] == t[n-1]) {
            res = helper(s, t, m-1, n-1) + 1;
        }
        else {
            res = Math.max(helper(s, t, m-1, n), Math.max(helper(s, t, m, n-1), helper(s, t, m-1, n-1)));
        }

        return res;
    }
}
