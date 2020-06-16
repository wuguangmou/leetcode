package base.algorithm.sort;

import java.util.Arrays;

/**
 * @User: 吴广谋
 * @Date: 2020/5/31
 * @Description: 选择排序java实现，算法思想：从未排序的数组中找到最小（大）的元素，然后将其放置到排序数组的起始位，然后再在未
 * 排序数组中寻找最小（大）的元素，将其放置到已排序数组的末尾，以此类推，直到排序完成。简而言之，就是每次挑一个最小（大）的元素，
 * 一个一个排序。
 */
public class SelectionSort {

    /**
     * 选择排序java实现，思想：每次总是选一个最小的元素，进行交换
     */
    public static void selectionSort(int[] arr){
        //i表示总共要进行N-1轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            //j表示每轮需要比较的次数
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]){
                    //在这轮比较中，总是记录最小的下标
                    minIndex = j;
                }
            }
            //如果下标发生了变化，说明存在一个最小的值，将最小值所在下标元素与起始下标所在的元素进行替换
            if (minIndex != i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,4,3,7,5,1};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
