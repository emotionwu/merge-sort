import java.lang.reflect.Array;
import java.util.Arrays;

public class InsertionSort {
    private InsertionSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //将arr[i]插入到合适的位置
            for (int j = i; j - 1 >= 0; j--)
                //如果arr[j]比arr[j-1]小
                if (arr[j].compareTo(arr[j - 1]) < 0)
                    swap(arr, j, j - 1);
                else break;

        }
    }

    //换个方式实现插入排序法，arr[0,i)无序，arr[i,n]有序
    public static <E extends Comparable<E>> void sort3(E[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            //将arr[i]插入到合适的位置
            for (int j = i; j < arr.length; j++)
                //如果arr[j-1]比arr[j]大
                if (arr[j].compareTo(arr[j - 1]) < 0)
                    swap(arr, j, j - 1);
                else break;

        }
    }

    //优化
    public static <E extends Comparable<E>> void sort2(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            E t = arr[i];
            int j;
            for (j = i; j - 1 >= 0 && t.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = t;
        }
    }

    public static <E extends Comparable<E>> void sort4(E[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            E t = arr[i];
            int j;
            for (j = i; j - 1 >= l && t.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = t;
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {

        int[] dataSize = {10000, 100000};
        for (int n : dataSize) {
            //无序数组
            System.out.println("Random Array:");
            Integer[] arr = generateRandomArray.generateRandomArray(n, n);
            Integer[] arr2 = Arrays.copyOf(arr, arr.length);
            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("SelectionSort", arr2);
            System.out.println();
            //有序数组
            System.out.println("Ordered Array: ");
            arr = generateRandomArray.generateOrderdArray(n);
            arr2 = Arrays.copyOf(arr, arr.length);
            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("SelectionSort", arr2);
            System.out.println();
            //测试另一种方法实现插入排序
            Integer[] arr3 = {1, 4, 2, 3, 6, 5};
            // SelectionSort.sort(arr);   第一种排序方式
            InsertionSort.sort4(arr3, 0, 6);
            for (int e : arr3)
                System.out.print(e + " ");
            System.out.println();

        }
    }
}

