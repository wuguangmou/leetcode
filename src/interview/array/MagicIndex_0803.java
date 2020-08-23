package interview.array;

/**
 * @User: 吴广谋
 * @Date: 2020/7/31
 * @Description: 来源于leetcode面试题08.03：魔术索引。在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，
 * 编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
 * 例：输入：nums = [0, 2, 3, 4, 5]          输出：0 （说明：0下标的元素为0）
 *     输入：nums = [1, 1, 1]                输出：1
 */
public class MagicIndex_0803 {

    /**
     * 对于有序数组，很容易想到，遍历一遍，如果数组元素等于下标索引，就返回索引
     */
    public static int findMagicIndex(int[] nums){
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == i){
//                return i;
//            }
//        }
//        return -1;

        //由于要找的元素需要满足A[i]=i，所以可以进行一些优化，减少遍历次数
        int i = 0;
        while (i < nums.length){
            if (i < nums[i]){
                i = nums[i];        //若当前索引i小于其对应值，直接对i赋值，减少遍历次数
            } else if(i > nums[i]){
                i++;
            } else {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 2, 3, 4, 5};
        System.out.println(findMagicIndex(nums));
    }
}
