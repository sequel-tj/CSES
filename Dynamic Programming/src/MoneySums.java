import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class MoneySums {
    static TreeSet<Integer> set = new TreeSet<>();

    public static void main(String[] args) {
        // File file = new File("./Dynamic Programming/src/input.txt");

        try {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            // method 1 => recursion
            // helper(arr, n, 0);

            // method 2 => dp (2D)
            // dpMethod(arr, n);

            // method 3 => dp (1D)
            dpOptimized(arr, n);

            System.out.println(set.size());
            for(Integer x: set) {
                System.out.print(x + " ");
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void dpOptimized(int[] arr, int n) {
        int total = IntStream.of(arr).sum();
        boolean[] dp = new boolean[total+1];
        dp[0] = true;

        for (int i=1; i<=n; i++) {
            for (int sum=total; sum >= arr[i-1]; sum--) {
                if (dp[sum-arr[i-1]]) {
                    dp[sum] = true;
                }
            }
        }

        for (int sum = 1; sum <= total; sum++) {
            if (dp[sum]) set.add(sum);
        }
    }

    static void dpMethod(int[] arr, int n) {
        int total = IntStream.of(arr).sum();
        boolean[][] dp = new boolean[n+1][total+1];
        dp[0][0] = true;

        for (int i=1; i<=n; i++) {
            for (int sum=0; sum <= total; sum++) {
                if (dp[i-1][sum]) {
                    dp[i][sum] = true;
                }

                if (sum >= arr[i-1] && dp[i-1][sum-arr[i-1]]) {
                    dp[i][sum] = true;
                }
            }
        }

        for (int sum = 1; sum <= total; sum++) {
            if (dp[n][sum]) set.add(sum);
        }
    }

    static void helper(int[] arr, int n, int sum) {
        if (n == 0) {
            if (sum != 0) set.add(sum);
            return;
        }

        helper(arr, n-1, sum);
        helper(arr, n-1, sum+arr[n-1]);
    }
}
