import java.util.*;

public class CoinCombinations1 {
    final static int MOD = 1000000007;
    private static int[] dp;

    private int helper(int n, int target, int[] arr) {
        if (target < 0) return 0;
        if (target == 0) return 1;
        if (n <= 0) return 0;

        if (dp[target] != -1) return dp[target];

        int ways = 0;
        for (int coin : arr) {
            ways = (ways % MOD + helper(n, target - coin, arr) % MOD) % MOD;
        }

        return dp[target] = ways;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int x = input.nextInt();

        int[] arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = input.nextInt();
        }

//        CoinCombinations1 cc = new CoinCombinations1();
//        cc.dp = new int[x+1];
//        Arrays.fill(cc.dp, -1);
//        System.out.println(cc.helper(n, x, arr));

        int[] dp = new int[x+1];
        dp[0] = 1;
        for (int sum = 1; sum <= x; sum++) {
            long ways = 0;
            for (int coin : arr) {
                if (sum >= coin) {
                    ways += dp[sum - coin];
                }
            }
            dp[sum] = (int)(ways % MOD);
        }

        System.out.println(dp[x]);
    }
}