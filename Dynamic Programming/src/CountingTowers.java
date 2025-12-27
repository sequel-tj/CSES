import java.util.Scanner;

/*

states
0th - linked -> (1 x 2)
1st - separated -> (1 x 1)


(i-1)th block -> (1 x 2) => linked state
=>  1 x 1 (blocks) => separated --- 1 way
=>  1 x 2 (block) => linked --- 1 way
=>  1 x 2 (block) => linked --- 1 way

from linked state = 2*linked + 1*separated

(i-1)th block -> (1 x 1) => separated state
=> 1 x 2 (block) => linked --- 1 way
=> 1 x 1 (blocks) => separated --- 1 way
=> 1 x 1 (blocks) => extend + separated -> separated --- 1 way
=> 1 x 1 (blocks) => separated + extend -> separated --- 1 way
=> 1 x 1 (blocks) => separated + separated -> separated --- 1 way

from separated state = 4*separated + 1*linked

 */

public class CountingTowers {
    static final int MOD = (int) 1e9+7;
    static long[][] dp;
    static long[] curr, prev;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] N = new int[t];
        int max = 0;

        // dp (method 2)
        dp = new long[1000001][2];
        dpMethod();

        while (t-- > 0) {
            int n = sc.nextInt();

            // dp (method 2)
            System.out.println((dp[n][0] + dp[n][1]) % MOD);

            // recursion (method 1)
            // System.out.println((helper(n, 0) + helper(n, 1)) % MOD);
        }
    }

    static void dpOptimized(int n, int[] N) {
        prev[0] = 1;
        prev[1] = 1;
        int x = 0;

        for (int i=2; i<=n; i++) {
            curr[0] = (2*prev[0] + prev[1]) % MOD;
            curr[1] = (4*prev[1] + prev[0]) % MOD;
            prev = curr.clone();

            if (i == N[x]) {
                System.out.println((prev[0] + prev[1]) % MOD);
                x++;
            }
        }
    }

    static void dpMethod() {
        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int i=2; i<1000001; i++) {
            dp[i][0] = (2*dp[i-1][0] + dp[i-1][1]) % MOD;
            dp[i][1] = (4*dp[i-1][1] + dp[i-1][0]) % MOD;
        }
    }

    static long helper(int n, int state) {
        if (n == 1) return 1;

        long res = 0;

        if (state == 0) {
            res = (2*helper(n-1, 0) + helper(n-1, 1)) % MOD;
        }
        else {
            res = (4*helper(n-1, 1) + helper(n-1, 0)) % MOD;
        }

        return res;
    }
}