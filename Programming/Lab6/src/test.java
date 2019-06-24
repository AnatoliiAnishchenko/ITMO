import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(new Integer("0"));
        arr.add(new Integer("1"));
        arr.add(new Integer("2"));
        arr.add(new Integer("3"));
        func(arr);

        for (Integer i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void func(ArrayList<Integer> arr) {
        Integer i = arr.get(0);

        arr.add(new Integer("4"));
    }
}
