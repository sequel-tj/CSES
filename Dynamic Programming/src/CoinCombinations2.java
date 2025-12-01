import java.util.*;

public class CoinCombinations2 {
    final static int MOD = 1000000007;
    private static int[][] dp;

    private int helper(int n, int target, int[] arr) {
        if (target < 0) return 0;
        if (target == 0) return 1;
        if (n <= 0) return 0;

        if (dp[n][target] != -1) return dp[n][target];

        return dp[n][target] = (helper(n-1, target, arr) % MOD + helper(n, target - arr[n-1], arr) % MOD) % MOD;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int x = input.nextInt();

        int[] arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = input.nextInt();
        }

//        CoinCombinations2 cc = new CoinCombinations2();
//        cc.dp = new int[n+1][x+1];
//        for(int[] row: cc.dp) Arrays.fill(row, -1);
//        System.out.println(cc.helper(n, x, arr));

        int[] prev = new int[x+1];
        int[] curr = new int[x+1];
        prev[0] = 1;
        curr[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = arr[i-1]; j <= x; j++) {
                long res = (prev[j] + curr[j-arr[i-1]]) % MOD;
                curr[j] = (int) res;
            }
            prev = curr;
        }

        System.out.println(curr[x]);
    }
}