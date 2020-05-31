package problems.algorithm.binarySearch;

/**
 * @User: 吴广谋
 * @Date: 2020/2/24
 * @Description: 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，
 *  返回它将会被按顺序插入的位置(可以假定数组中无重复元素)。
 *  输入：[1,3,5,6], 5  输出：2；  输入：[1,3,5,6], 7  输出：4；   输入：[1,3,5,6], 0  输出：0
 */
public class searchInsertPosition_035 {
    /**
     * 和JDK中的二分查找的方法类似，返回目标数字所在数组的下标，这里同样使用二分查找来实现，自己手写一个二分查找的方法。<br/>
     * 时间复杂度：O(logn)         空间复杂度：O(1)
     */
    public static int solution1(int[] nums, int target){
        int low = 0, high = nums.length-1;         //在[0,nums.length()-1]这个区间范围内进行搜索
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if (target < nums[mid] )
                high = mid - 1;
            else if (target > nums[mid]) {
                low = mid + 1;
            } else
                return mid;
        }
        return low;
    }

    /**
     * 除了二分查找，当然也可以使用遍历数组的方法求解，不过不太推荐。<br/>
     * 时间复杂度：O(n)         空间复杂度：O(1)
     */
    public static int solution2(int[] nums, int target){
//        int index;
//        for (index = 0; index < nums.length; index++) {
//            if (target == nums[index]){
//                return index;
//            } else if (target < nums[index]){
//                break;
//            }
//        }
//        return index;

        //看了一个特别简洁的代码
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i])
                return i;
        }
        return nums.length;
    }
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 2;
        System.out.println(solution2(nums, target));
    }
}
