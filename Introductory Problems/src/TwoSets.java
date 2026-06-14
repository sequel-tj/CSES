import java.util.Scanner;

public class TwoSets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long sum = 1L * n * (n + 1) / 2;
        if (sum % 2 == 1) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            long half = sum / 2;
            long currentSum = 0L;
            long a = 0L, b = 0L;

            StringBuilder set1 = new StringBuilder();
            StringBuilder set2 = new StringBuilder();

            for (int i = n; i >= 1; i--) {
                if (currentSum + i <= half) {
                    set1.append(i).append(" ");
                    currentSum += i;
                    a++;
                } else {
                    set2.append(i).append(" ");
                    b++;
                }
            }

            System.out.println(a);
            System.out.println(set1.length() > 0 ? set1.toString().trim() : "");
            System.out.println(b);
            System.out.println(set2.length() > 0 ? set2.toString().trim() : "");
        }
    }
}
