import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DiceCombinations {
    final static int MOD = 1000000007;
    private int[] dp;

    private int helper(int target) {
        if (target < 0) return 0;
        if (target == 0) return 1;

        if (dp[target] != -1) return dp[target];

        int count = 0;
        for (int i=1; i<=6; i++) {
            count = (count % MOD + helper(target-i) % MOD) % MOD;
        }

        return dp[target] = count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        DiceCombinations dc = new DiceCombinations();
//        memoization
//        dc.dp = new int[n+1];
//        Arrays.fill(dc.dp, -1);
//        System.out.println(dc.helper(n));

        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= 6; j++) {
                if (j>i) continue;
                count = (count % MOD + dp[i-j] % MOD) % MOD;
            }
            dp[i] = count;
        }

        System.out.println(dp[n]);
    }
}