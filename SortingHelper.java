public class SortingHelper {
    private SortingHelper(){}
    public static<E extends Comparable<E>> boolean isSorted(E[]arr){
        for(int i=1;i<arr.length;i++)
            if(arr[i-1].compareTo(arr[i])>0)
                return false;
        return true;
    }
    public static<E extends Comparable<E>>  void sortTest(String sortname,E[]arr){
        long starTime=System.nanoTime();
        if(sortname.equals("SelectionSort"))
            SelectionSort.sort(arr);
        else if(sortname.equals("InsertionSort"))
            InsertionSort.sort(arr);
        else if(sortname.equals("InsertionSort2"))
            InsertionSort.sort2(arr);
        else if(sortname.equals("MergeSort"))
            MergeSort.sort(arr);
        else if(sortname.equals("MergeSort2"))
            MergeSort.sort2(arr);
        else if(sortname.equals("MergeSort3"))
            MergeSort.sort3(arr);
        else if(sortname.equals("MergeSort4"))
            MergeSort.sort4(arr);
        else if(sortname.equals("MergeSort5"))
            MergeSort.sortBu(arr);
        else if(sortname.equals("MergeSort6"))
            MergeSort.sortBu2(arr);
        long endTime=System.nanoTime();
        double time =(endTime-starTime)/1000000000.0;
        //判断排序是否正确
        if(!SortingHelper.isSorted(arr))
            throw new RuntimeException(sortname+" failed");
        System.out.println(String.format("%s, n=%d:%f s",sortname,arr.length,time));
    }

}
