import java.util.HashSet;
import java.util.Scanner;

public class C {
    public static void main( String[] arg0) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            set.add(in.nextInt());
        }

        System.out.println(set.size());
    }
}
