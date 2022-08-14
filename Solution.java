import java.util.Arrays;
//求数组中的逆序对数
public class Solution {
   // private static int res=0;
    public int reversePairs(int[] nums){
        int res=0;
        for(int i = 0;i<nums.length;i++)
            for(int j = i+1;j<nums.length;j++)
                if(nums[i]>nums[j])
                    res++;
                return res;

    }
    //使用递归求数组中的逆序对数
    public int reversePairs2(int[] nums) {
        int[] temp = new int[nums.length];
        return sort(nums,0,nums.length-1,temp);
      /*
        res=0;
        sort(nums,0,nums.length-1,temp);
        return res;
       */
    }
    private int sort(int[] arr, int l,int r, int []temp){
        if(l>=r) return 0;
        int res=0;
        //防止整型溢出
        int mid =l+(r-l)/2;
        //对arr[l,mid]进行排序
       res+= sort(arr,l,mid,temp);     //深度加一
        //对arr[mid+1,r]进行排序
       res+= sort(arr,mid+1,r,temp);      //深度加一
        if(arr[mid]>arr[mid +1])
            res +=merge(arr,l,mid,r,temp);
        return res;
    }
    //归并排序法的内存操作优化
    private int merge(int[] arr, int l, int mid, int r, int[] temp){
        //复制arr[l,r]
        // E [] temp= Arrays.copyOfRange(arr,l,r+1);
        System.arraycopy(arr,l,temp,l,r-l+1);
        int i =l, j=mid+1,res=0;
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
            else if(temp[i]<temp[j]){
                arr[k]=temp[i];
                i++;
            }
            else{
                res +=mid-i+1;
                arr[k]=temp[j];
                j++;
            }
            //比较arr[i]和arr[j]
        }
        return res;
    }
}
