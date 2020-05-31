package problems.algorithm.dp;

/**
 * @User: 吴广谋
 * @Date: 2020/3/27
 * @Description: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 例：输入：[-2,1,-3,4,-1,2,1,-5,4]  输出：6（4-1+2+1）
 */
public class MaximumSubarray_053 {
    /**
     * 同样的，我们可以使用暴力求解法，使用暴力求解法并不是目的，而是在迫不得已的情况下达成目的的一种手段。对于复杂的问题，
     * 如果我们一般没有好的思路，都可以先尝试使用暴力求解法去求解问题，然后再去尝试从其他角度思考，优化问题。<br/>
     * 时间复杂度：O(n^2)   空间复杂度：O(1)
     */
    public static int solution1(int[] nums){
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;                                //每次循环前，将最大值清0
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];                         //累加，记录最大值
                if (sum > maxVal){
                    maxVal = sum;
                }
            }
        }
        return maxVal;
    }

    /**
     * 使用动态规划解决问题，当前状态的最大值 = max(前一状态的最大值+当前值，当前值)。
     * 由此可以列出状态转移方程：dp[i] = max(dp[i-1] + arr[i], arr[i])<br/>
     * 时间复杂度：O(n)   空间复杂度：O(1)
     */
    public static int solution2(int[] nums){
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);       //从数组的第二位开始，进行状态的迭代
            res = Math.max(dp[i], res);                         //记录当前状态的最大值
        }
        return res;
    }

    /**
     * 使用贪心算法解决问题，在每次遍历的时候，我们记录最大的累加值，并且保证它的值始终大于0，否则置零。这样在遍历的时候，
     * 我们总是能够获取到最大的累加值。使用贪心算法，在保证局部最优的时候，从而达到全局最优。<br/>
     * 时间复杂度：O(n)   空间复杂度：O(1)
     */
    public static int solution3(int[] nums){
        int max = Integer.MIN_VALUE, sum = 0;
        for (int num : nums) {
            sum += num;
            if (max < sum)
                max = sum;                  //记录当前最大累加值
            if (sum < 0)
                sum = 0;                    //如果累加值小于0，则将其置零
        }
        return max;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution3(arr));
    }
}
