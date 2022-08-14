import java.util.Arrays;
//归并排序法
public class MergeSort {
    private MergeSort(){}
    //正常归并排序，复杂度非nlog n
    public static <E extends  Comparable<E>> void sort(E[]arr){
        sort(arr,0,arr.length-1);
    }
    private static<E extends  Comparable<E>> void sort(E[] arr, int l,int r){
        if(l>=r) return;
        //防止整型溢出
        int mid =l+(r-l)/2;
        //对arr[l,mid]进行排序
        sort(arr,l,mid);     //深度加一
        //对arr[mid+1,r]进行排序
        sort(arr,mid+1,r);      //深度加一
        merge(arr,l,mid,r);
    }
    //打印输出测试
    public static <E extends  Comparable<E>> void sort1(E[]arr){
        sort1(arr,0,arr.length-1,0);
    }
    private static<E extends  Comparable<E>> void sort1(E[] arr, int l,int r, int depth){
        //生成深度字符串
        String depthString = generateDepthString(depth);
        //打印当前的sort处理的数组区间信息
        System.out.print(depthString);
        System.out.println(String.format("mergesort arr[ %d, %d]",1,r));
        if(l>=r) return;
        //防止整型溢出
        int mid =l+(r-l)/2;
        //对arr[l,mid]进行排序
        sort1(arr,l,mid,depth+1);     //深度加一
        //对arr[mid+1,r]进行排序
        sort1(arr,mid+1,r,depth+1);      //深度加一
        merge(arr,l,mid,r);
        //打印merge后的数组
        System.out.print(depthString);
        System.out.print(String.format("after mergesort arr[ %d, %d]:",1,r));
        for(E e:arr)
            System.out.print( e + " ");
        System.out.println();
    }
    //在有序数组中将归并算法的复杂度改为o（n）
    public static <E extends  Comparable<E>> void sort2(E[]arr){
        sort2(arr,0,arr.length-1);
    }
    private static<E extends  Comparable<E>> void sort2(E[] arr, int l,int r){
        if(l>=r) return;
        //防止整型溢出
        int mid =l+(r-l)/2;
        //对arr[l,mid]进行排序
        sort2(arr,l,mid);     //深度加一
        //对arr[mid+1,r]进行排序
        sort2(arr,mid+1,r);      //深度加一
        if(arr[mid].compareTo(arr[mid+1])>0)
        merge(arr,l,mid,r);
    }
    //使用插入排序对归并排序进行优化
    public static <E extends  Comparable<E>> void sort3(E[]arr){
        sort3(arr,0,arr.length-1);
    }
    private static<E extends  Comparable<E>> void sort3(E[] arr, int l,int r){
       // if(l>=r) return;
        if(r-l <=15){
           InsertionSort.sort4(arr,l,r);
           return;
        }
        //防止整型溢出
        int mid =l+(r-l)/2;
        //对arr[l,mid]进行排序
        sort3(arr,l,mid);     //深度加一
        //对arr[mid+1,r]进行排序
        sort3(arr,mid+1,r);      //深度加一
        if(arr[mid].compareTo(arr[mid+1])>0)
            merge(arr,l,mid,r);
    }
    //归并排序法的内存操作优化
    public static <E extends  Comparable<E>> void sort4(E[]arr)
    {
        E[] temp =Arrays.copyOf(arr,arr.length);
        sort4(arr,0,arr.length-1,temp);
    }
    private static<E extends  Comparable<E>> void sort4(E[] arr, int l,int r, E []temp){
        if(l>=r) return;
        //防止整型溢出
        int mid =l+(r-l)/2;
        //对arr[l,mid]进行排序
        sort4(arr,l,mid,temp);     //深度加一
        //对arr[mid+1,r]进行排序
        sort4(arr,mid+1,r,temp);      //深度加一
        if(arr[mid].compareTo(arr[mid+1])>0)
        merge2(arr,l,mid,r,temp);
    }
    //实现自底向上的归并排序
    public static<E extends Comparable<E>> void sortBu(E []arr){
        E[] temp=Arrays.copyOf(arr,arr.length);
        int n =arr.length;
        //遍历合并的区间长度
        for(int sz=1;sz<n;sz+=sz){
            //遍历合并两个区间的起始位置i
            //合并[i,i+sz-1]和[i+sz,i+sz+sz-1]
            for(int i =0; i+sz<n;i += sz+sz)
                //r可能越界了
                if(arr[i+sz-1].compareTo(arr[i+sz])>0)
                merge2(arr,i,i+sz-1,Math.min(i+sz+sz-1,n-1),temp);
        }
    }
    public static<E extends Comparable<E>> void sortBu2(E []arr){
        E[] temp=Arrays.copyOf(arr,arr.length);
        int n =arr.length;
        //对于自底向上的方法，一开始处理的是小规模的数据，所以对arr[i,i+15]区间的数据进行插入排序法
        //i每次增加16
        //16为一个超参数，当为16时，性能最佳
        for(int i =0;i<n;i +=16)
            InsertionSort.sort4(arr,i,Math.min(i+15,n-1));
        //遍历合并的区间长度,sz从16开始
        for(int sz=16;sz<n;sz+=sz){
            //遍历合并两个区间的起始位置i
            //合并[i,i+sz-1]和[i+sz,i+sz+sz-1]
            for(int i =0; i+sz<n;i += sz+sz)
                //r可能越界了
                if(arr[i+sz-1].compareTo(arr[i+sz])>0)
                    merge2(arr,i,i+sz-1,Math.min(i+sz+sz-1,n-1),temp);
        }
    }
    private static String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i<depth ; i++)
            res.append("--");
        return res.toString();
    }
    //合并两个有序的区间 arr[l,mid] 和 arr[mid +1,r]
    private static <E extends  Comparable<E>> void merge(E []arr,int l,int mid, int r){
     //复制arr[l,r]
        E [] temp= Arrays.copyOfRange(arr,l,r+1);
        int i =l, j=mid+1;
        //每轮循环为arr[k]进行赋值
        for(int k=l;k<=r;k++){
            if(i>mid){
                arr[k]=temp[j-l];
                j++;
            }
            else if(j>r){
                arr[k] =temp[i-l];
                i++;
            }
            else if(temp[i-l].compareTo(temp[j-l])<=0){
                arr[k]=temp[i-l];
                i++;
            }
            else{
                arr[k]=temp[j-l];
                j++;
            }
         //比较arr[i]和arr[j]
        }
    }
    //归并排序法的内存操作优化
    private static <E extends  Comparable<E>> void merge2(E []arr,int l,int mid, int r,E[] temp){
        //复制arr[l,r]
       // E [] temp= Arrays.copyOfRange(arr,l,r+1);
        System.arraycopy(arr,l,temp,l,r-l+1);
        int i =l, j=mid+1;
        //每轮循环为arr[k]进行赋值
        for(int k=l;k<=r;k++){
            if(i>mid){
                arr[k]=temp[j];
                j++;
            }
            else if(j>r){
                arr[k] =temp[i];
                i++;
            }
            else if(temp[i].compareTo(temp[j])<=0){
                arr[k]=temp[i];
                i++;
            }
            else{
                arr[k]=temp[j];
                j++;
            }
            //比较arr[i]和arr[j]
        }
    }
    public static void main(String[] args){
        // Integer [] arr1 ={7,1,4,2,8,3,6,5};
        //SortingHelper.sortTest("MergeSort",arr1);
        int n =100000;
        Integer[] arr= generateRandomArray.generateRandomArray(n,n);
        /*
        MergeSort.sort3(arr,0,n);
        for (int e : arr)
            System.out.print(e + " ");
        System.out.println();
         */
        Integer[] arr2 = Arrays.copyOf(arr,arr.length);
        Integer[] arr3 = Arrays.copyOf(arr,arr.length);
        Integer[] arr4 = Arrays.copyOf(arr,arr.length);
        Integer[] arr5 = Arrays.copyOf(arr,arr.length);
        Integer[] arr6 = Arrays.copyOf(arr,arr.length);
        SortingHelper.sortTest("MergeSort",arr);
        SortingHelper.sortTest("MergeSort2",arr2);
        SortingHelper.sortTest("MergeSort3",arr3);
        SortingHelper.sortTest("MergeSort4",arr4);
        SortingHelper.sortTest("MergeSort5",arr5);
        SortingHelper.sortTest("MergeSort6",arr6);
    }
}
