package base.algorithm.sort;

import java.util.Arrays;

/**
 * @User: 吴广谋
 * @Date: 2020/5/31
 * @Description: 插入排序java实现，算法思想：打扑克牌思想，将摸到的牌一张一张按照大小顺序插入手中，严谨一点的描述为：构建
 * 一个有序的序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。由于在实现上插入排序并不会改变值相同的元
 * 素的对应顺序，因此，插入排序也是稳定的
 */
public class InsertionSort {

    /**
     * 插入排序java实现，最好情况：数组正序，此时仅需比较n-1次；最坏情况：数组逆序，此时需比较n(n-1)/2次。
     * 时间复杂度（平均）：O(n^2)       空间复杂度：O(1)
     * @param: arr 待排序数组
     */
    public static void insertionSort(int[] arr){
        int temp;
        //外层循环为每次需要插入的数组元素下标，可以理解为发牌过程中每次摸到的牌
        for (int i = 1; i < arr.length; i++) {
            //内层循环为从待插入元素下标开始，一直到数组起始坐标，需要交换比较的次数
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]){
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,9,1,7,6,4,8,2,5};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
