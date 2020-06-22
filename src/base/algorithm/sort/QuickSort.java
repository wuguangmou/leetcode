package base.algorithm.sort;

import java.util.Arrays;

/**
 * @User: 吴广谋
 * @Date: 2020/6/16
 * @Description: 快速排序java实现，算法思想：从数组中取一个数作为基准数，将比这个小的数放在数组的左边，比这个数大的数放在数组的
 * 右边，然后对左右区间重复此过程，直到各区间只剩一个数为止。由于快速排序依赖于划分基准数，以下面的数组为例：[9,5,5,5,3]，当选择
 * 中间那个5作为划分的基准元素时，不管判断条件是'<='还是'<'，都会导致相对顺序发生变化，因此，快排是不稳定的。
 */
public class QuickSort {

    /**
     * 快速排序java实现，快排优化方案：三路快排、双基准快排
     * 时间复杂度：O(nlogn)（平均）    O(n^2)（最坏）      空间复杂度：O(logn)
     * @param arr 待排序数组
     * @param left 排序数组起始下标
     * @param right 排序数组终止下标
     */
    public static void quickSort(int[] arr, int left, int right){
        if (left < right){
            int p = partition(arr, left, right);
            quickSort(arr, left, p - 1);
            quickSort(arr, p + 1, right);
        }
    }

    /**
     * 将数组的最后一个元素作为基准元素，划分整个数组，小于基准数的放在左边，大于基准数的在右边
     * @param a 待排序数组
     * @param left 待排序数组的左区间
     * @param right 待排序数组的右区间
     * @return 划分的基准元素下标
     */
    private static int partition(int[] a, int left, int right){
        //基准元素所在索引，从数组左边界的前一位开始
        int i = left-1;
        int temp;
        //由于使用划分数组的最后一个元素作为主元，因此循环比较的边界是[left, right-1]
        for (int j = left; j <= right-1 ; j++) {
            //每次比较的过程中，如果当前元素小于等于主元，主元下标索引自增，同时将当前元素依次放在数组左边
            if(a[j] <= a[right]){
                i++;
                //交换a[i]和a[j]
                temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }
        }
        //遍历完后说明有i+1个元素小于等于基准元素，交换a[i+1]和a[right],使基准元素左边是小于等于它的，右边是大于它的
        temp = a[right];
        a[right] = a[i+1];
        a[i+1] = temp;
        //返回基准元素所在下标
        return i+1;
    }

    public static void main(String[] args) {
        int[] arr = {3,9,1,7,6,4,8,2,5};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
