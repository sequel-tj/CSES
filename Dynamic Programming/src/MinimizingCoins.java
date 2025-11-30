import java.util.*;

public class MinimizingCoins {
    final static int MOD = 1000000007;
    private int[][] dp;

    private int helper(int n, int target, int[] arr) {
        if (target < 0) return Integer.MAX_VALUE - 1;
        if (target == 0) return 0;
        if (n <= 0) return Integer.MAX_VALUE - 1;

        if (dp[n][target] != -1) return dp[n][target];

        int res = helper(n-1, target, arr);
        res = Math.min(res, helper(n, target - arr[n-1], arr) + 1);
        return dp[n][target] = res;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int x = input.nextInt();

        int[] arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = input.nextInt();
        }

        MinimizingCoins mc = new MinimizingCoins();

//        memoization
//        mc.dp = new int[n+1][x+1];
//        for (int row[] : mc.dp) Arrays.fill(row, -1);
//        System.out.println(mc.helper(n, x, arr));

        int[] prev = new int[x+1];
        int[] curr = new int[x+1];

        Arrays.fill(prev, Integer.MAX_VALUE-1);

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=x; j++) {
                curr[j] = prev[j];
                if (j-arr[i-1] >= 0)
                    curr[j] = Math.min(curr[j], curr[j-arr[i-1]] + 1);
            }
            prev = curr;
        }

        System.out.println(curr[x] == Integer.MAX_VALUE-1 ? -1 : curr[x]);
    }
}