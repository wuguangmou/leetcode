package base.algorithm.sort;

import java.util.Arrays;

/**
 * @User: 吴广谋
 * @Date: 2020/5/31
 * @Description: 冒泡排序java实现，算法思想：将小的元素往前调或将大的元素往后调，由于值相等的元素并不会发生交换，因此冒泡
 * 排序式稳定的
 */
public class BubbleSort {

    /**
     * 冒泡排序java实现，思想：将值较大的元素向后调整
     * 时间复杂度（平均）：O(n^2)       空间复杂度：O(1)
     * @param arr 待排序数组
     */
    public static void bubbleSort(int[] arr){
        int temp;
        //外层循环为排序的次数，n个元素需要进行n-1次排序
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环为每趟比较的次数，第i次循环需要比较n-i次
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 在冒泡排序的过程中，数据的顺序排好之后，冒泡算法仍会进行下一轮的比较，这是没有意义的(例如i=6的时候，此时数据已经全部排序
     * 完成，后面的遍历可直接终止)
     * 时间复杂度：O(n^2)       空间复杂度：O(1)
     * @param arr 待排序数组
     */
    public static void bubbleSortWithFlag(int[] arr){
        int temp;
        boolean flag;               //额外定义一个数据交换的标志位
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;    //发生数据交换时，数据交换标志位设为true
                }
            }
            if (!flag){
                break;              //若一轮冒牌排序下来没有发生数据交换，说明数组有序，跳出循环
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,9,1,7,6,4,8,2,5};
        bubbleSortWithFlag(arr);
        System.out.println(Arrays.toString(arr));
    }
}
