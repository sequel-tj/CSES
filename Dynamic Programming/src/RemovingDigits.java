import java.util.Scanner;

public class RemovingDigits {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String num = Integer.toString(input.nextInt());
        System.out.println(helper(num));
    }

    static int helper(String num) {
        if (num.charAt(0) - '0' == 0) return 0;
        int n = num.length();

        int maxx = 0;
        for (int x = 0; x<n; x++) {
            maxx = Math.max(num.charAt(x)-'0', maxx);
        }

        return 1 + helper(Integer.toString(Integer.parseInt(num) - maxx));
    }
}
