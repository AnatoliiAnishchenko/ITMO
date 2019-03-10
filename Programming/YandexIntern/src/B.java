import java.util.Scanner;

public class B {
    public static void main( String[] arg0) {
        Scanner in = new Scanner(System.in);

        long n = in.nextLong();

        if (n == 0) {
            System.out.println(0);
        } else {
            long res = 1;
            int i = 1;

            while (res * 2 <= n) {
                res *= 2;
                i++;
            }


            System.out.println(i);
        }
    }
}
