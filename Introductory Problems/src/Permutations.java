import java.util.Scanner;
import java.io.*;

public class Permutations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        if (n==1) {
            out.print("1");
            out.flush();
            return;
        }
        if (n==2 || n==3) {
            out.print("NO SOLUTION");
            out.flush();
            return;
        }

        for (int i=2; i<=n; i+=2) {
            out.print(i + " ");
        }

        for (int i=1; i<=n; i+=2) {
            if (i + 2 <= n) out.print(i + " ");
            else out.print(i);
        }
        out.flush();
    }
}

