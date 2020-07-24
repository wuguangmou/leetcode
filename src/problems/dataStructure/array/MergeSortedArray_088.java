package problems.dataStructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @User: 吴广谋
 * @Date: 2020/7/23
 * @Description: 给你两个有序整数数组 nums1和nums2，请你将nums2合并到nums1中，使nums1成为一个有序数组。
 * 说明：初始化nums1和nums2的元素数量分别为m和n 。
 *      你可以假设nums1有足够的空间（空间大小大于或等于m + n）来保存nums2中的元素。
 * 例：输入：nums1 = [1,2,3,0,0,0], m = 3        输出：[1,2,2,3,5,6]
 *          nums2 = [2,5,6],       n = 3
 */
public class MergeSortedArray_088 {

    /**
     * 我们可以创建一个额外的数组空间，然后每次取最小的数放入这个数组空间中，最后这个数组即是我们所需要的合并了元素的数组
     * 时间复杂度：O(m+n)         空间复杂度：O(m+n)
     */
    public static void solution1(int[] nums1, int m, int[] nums2, int n){
        List<Integer> arrayList = new ArrayList<>();

        int i, j;
        //使用两个指针i、j去遍历nums1和nums2，每次总是取最小的放入list中
        for (i = 0, j = 0; i < m && j < n; i++, j++) {
            if (nums1[i] < nums2[j]){
                arrayList.add(nums1[i]);
                j--;
            } else {
                arrayList.add(nums2[j]);
                i--;
            }
        }

        //如果list未放满，将剩余的元素继续放入list
        if (arrayList.size() != m + n){
            if (i == m){
                for (j = j; j < n; j++) {
                    arrayList.add(nums2[j]);
                }
            } else {
                for (i = i; i < m; i++) {
                    arrayList.add(nums1[i]);
                }
            }
        }

        //最后，将list的值一个个放入nums1数组中
        for (int k = 0; k < m + n; k++) {
            nums1[k] = arrayList.get(k);
        }
    }

    /**
     * 使用JDK自带的方法实现
     * 时间复杂度：主要为排序的时间复杂度，O((m+n)log(m+n))       空间复杂度：O(1)
     */
    public static void solution2(int[] nums1, int m, int[] nums2, int n){
        //将nums2数组拷贝到nums1中，拷贝元素索引从0开始，长度为n，插入到nums1数组的索引为m
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    //solution1思想代码精简
    public static void merge(int[] nums1, int m, int[] nums2, int n){
        int len1 = m - 1;
        int len2 = n -1;
        int len = m + n -1;
        while (len1 >= 0 && len2 >= 0){
            //比较哪个值小，然后将较小的放入nums1中
            nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
        }
        //将剩余未放置的元素放入nums1中
        System.arraycopy(nums2, 0, nums1, 0, len2 + 1);
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0}, nums2 = {2,5,6};
        merge(nums1, 3, nums2, 3);
    }
}
