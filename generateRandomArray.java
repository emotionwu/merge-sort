import java.util.Random;
//生成随机数组  长度为n的随机数组，每个数字的范围为[0,bound]
public class generateRandomArray {
    public static Integer[] generateRandomArray(int n,int bound){
        Integer[] arr= new Integer[n];
        Random rnd=new Random();
        for (int i=0;i<n;i++)
            arr[i]=rnd.nextInt(bound);
        return arr;
    }
    public static Integer[] generateOrderdArray(int n)  //得到顺序数组
    {

        Integer[] arr=new Integer[n];
        for(int i=0;i<n;i++)
            arr[i]=i;
        return arr;
    }
}
