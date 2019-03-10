import java.util.Scanner;

public class D {
    public static void main( String[] arg0) {
        Scanner in = new Scanner(System.in);

        long s = in.nextLong();

        long l1 = in.nextLong();
        long r1 = in.nextLong();

        long l2 = in.nextLong();
        long r2 = in.nextLong();

        if (l1 + l2 > s || r1 + r2 < s) {
            System.out.println(-1);
        } else {
            if (l1 + r2 > s) {
                System.out.println(l1 + " " + (s - l1));
            } else {
                System.out.println((s - r2) + " " + r2);
            }
        }
    }
}
