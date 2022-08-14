import org.omg.CORBA.PUBLIC_MEMBER;

public class SelectionSort {
    private SelectionSort() {
    }

    //带约束，判断所选参数是否可比较
    public static <E extends Comparable<E>> void sort(E[] arr) {
        //arr[0,i)是已经排完序的，arr[i,n)是未排完序的
        for (int i = 0; i < arr.length; i++) {
            //选择arr[i,n)当中的最小值索引
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0)
                    minIndex = j;
            }
            swap(arr, i, minIndex);
        }
    }
    //另外一种排序方法，找到最大值后往后挪动
    public static<E extends Comparable<E>> void sort2(E[] arr){
        for (int i = arr.length-1; i >0; i--) {
            //选择arr[i,n)当中的最大值索引
            int maxIndex = i;
            for (int j = i; j >0; j--) {
                if (arr[j].compareTo(arr[maxIndex]) >0)
                    maxIndex = j;
            }
            swap(arr, i, maxIndex);
        }
    }

    //找到最小值索引后，交换两个值
    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
    /*   Integer[] arr = {1, 4, 2, 3, 6, 5};
       // SelectionSort.sort(arr);   第一种排序方式
       SelectionSort.sort2(arr);
        for (int e : arr)
            System.out.print(e + " ");
        System.out.println();
        Student[] students={new Student("Alice",98),
                            new Student("Bobo",100),
                            new Student("Charles",66)};

        SelectionSort.sort(students);
        for(Student student:students)
            System.out.println(student +" ");
        System.out.println();
*/
        //通用性
        int[] dataSize = {10000,100000};
        for (int n : dataSize) {
            Integer[] arr = generateRandomArray.generateRandomArray(n, n);
            SortingHelper.sortTest("SelectionSort", arr);
        }
    }
}