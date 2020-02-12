package problems.dataStructure.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @User: 吴广谋
 * @Date: 2019/1/30
 * @Description: 给定一个整数数组 nums 和一个目标值 target，在数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 *  eg: nums=[2,7,11,15] target=9   return: [0,1]
 */
public class TwoSum_001 {

    /**
     * 暴力求解法，在数组中寻找target-nums[i]是否存在
     * 时间复杂度：O(n^2),空间复杂度：O(n)
     */
    public static int[] solution1(int[] nums, int target){
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++) {
                if (target - nums[i] == nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 将在数组中寻找target-nums[i]的方式改为二分查找
     * 时间复杂度：O(nlogn),空间复杂度：O(n)
     */
    private static int[] solution2(int[] nums, int target){
        for (int i = 0; i < nums.length; i++) {
            int j = binarySearch(nums,target-nums[i]);
            if (j >= 0){
                return new int[]{i,j};
            }
        }
        return new int[]{-1,-1};
    }

    private static int binarySearch(int[] nums, int target){
        Arrays.sort(nums);                      //为保证查找的数组有序，在二分查找前需进行一次排序操作
        int start = 0, end = nums.length - 1;   //在区间[start,end]中查找元素
        while (start <= end){
            int mid = (start + end) >>> 1;
            if (nums[mid] < target)
                start = mid + 1;                //重新在区间[mid+1,end]中查找
            else if (nums[mid] > target)
                end = mid - 1;                  //重新在区间[start,mid-1]中查找
            else
                return mid;                     //找到元素，返回元素对应数组下标，否则，返回-1
        }
        return -1;
    }

    /**
     * 考虑到这个问题既需要操作数组中的元素，又需要数组中相对应的下标，因此使用map来构建映射关系
     * 由于hashMap查找元素的时间复杂度为O(1)，因此时间复杂度为:O(n)，空间复杂度为O(n)
     */
    public static int[] solution3(int[] nums, int target){
        Map<Integer,Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i],i);             //初始化map
        }

        for (int i = 0; i < nums.length; i++) {
            int remainder = target - nums[i];
            if (hashMap.containsKey(remainder)){
                return new int[]{i, hashMap.get(remainder)};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 官方给出的更优解：由于将有n个元素的数组遍历了两次，我们完全可以在遍历一次的情况下完成相同的操作
     *
     */
    public static int[] solution4(int[] nums, int target){
        Map<Integer,Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int remainder = target - nums[i];

            if (hashMap.containsKey(remainder)){
                return new int[]{hashMap.get(remainder), i};    //由于先查找另一个元素，再放置，因此返回的下标应该将顺序颠倒
            }
            hashMap.put(nums[i],i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {0,2,3,5,7,9,11,13,17,19,20};
        int target = 25;

        int[] result = solution2(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
