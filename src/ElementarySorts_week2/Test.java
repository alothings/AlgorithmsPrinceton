import java.util.Arrays;

/**
 * Created by alonso on 12/12/16.
 */
public class Test
{
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int len = a.length;
        int i = 0, j = len, lo = 0, hi = len - 1;

        System.out.println("Beginning Test\n");
        System.out.println("Array: " + Arrays.toString(a) + "\n");
        System.out.println("Array size: " + len + "\n");

//        System.out.println("++i: " + ++i + "\n");
//        i = 0;  //returns 1
//        System.out.println("i++: " + i++ + "\n");
//        //returns 0

        System.out.println("lo: " + lo + "\n");

//        while (less(a[lo], a[--j])) {
//            System.out.println(" a[--j]: " + a[j] + " j: " + j);
//            if (j == lo) break;
//        }

    }

    private static boolean less(int v, int w)
    { return v < w ; }
}
