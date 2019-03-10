import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class A {
    public static void main( String[] arg0) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> newArr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();

            for (int j = 0; j < 5 && j < arr.size() + 1; j++) {

            }

            for (int j = arr.size() - 1; j > -1; j--) {
                System.out.print(arr.get(j) + " ");
            }
            System.out.println();
        }
    }
}
