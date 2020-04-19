package problems.algorithm.dp;

import java.util.Arrays;

/**
 * @User: 吴广谋
 * @Date: 2020/4/16
 * @Description: 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋
 * 装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * 例：输入：[1,2,3,1]  输出：4 (能偷到的最高金额为1+3=4)
 *     输入：[2,7,9,3,1]   输出：12（能偷到的最高金额为2+9+1=12）
 */
public class HouseRobber_198 {

    /**
     * 首先，分析问题，使用自顶向下的分析方法：
     * 对于到第n个房子的最高金额，方案无非有两种，偷或者不偷，我们使用opt(i)表示偷取第i个房子的最优解，即最高金额。
     * 当偷取时，它的最高金额为opt(i)=opt(i-2)+arr[i],不偷取时，它的最高金额为opt(i)=opt(i-1),此时只需要比较它们谁更大即可。
     * 递归方程：opt(i)=max{opt(i-2)+arr[i], opt(i-1)} 递归出口为：opt(0)=arr[0],opt(1)=max{arr[0], arr[1]}
     * 时间复杂度：O(2^n)   空间复杂度为递归树的高度：O(n)
     */
    public static int solution1(int[] nums){
        if (nums.length == 0){
            return 0;
        }
        return optimal(nums, nums.length - 1);
    }
    private static int optimal(int[] arr, int i){
        if (i == 0)
            return arr[0];
        else if (i == 1)
            return Math.max(arr[0], arr[1]);
        else {
            int hasStolen = optimal(arr, i - 2) + arr[i];
            int notSteal = optimal(arr, i - 1);
            return Math.max(hasStolen, notSteal);
        }
    }

    /**
     * 在使用递归的方式求解时，如果画出递归树，可以发现计算了大量重复的子问题，因此我们可以使用动态规划来求解问题。
     * 状态转移方程：dp[i]=max{dp[i-2]+arr[i], dp[i-1]}
     * 时间复杂度:O(n)   空间复杂度：O(n)
     */
    public static int solution2(int[] nums){
        //判断边界条件，提前返回
        if (nums.length <= 1){
            return nums.length == 0 ? 0 : nums[0];
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[nums.length - 1];
    }

    /**
     * 当然，我们也可以使用记忆化搜索的方式，自顶向下的去调用，只不过会略微显得有点繁琐
     * 状态转移方程：dp[i]=max{dp[i-2]+arr[i], dp[i-1]}
     * 时间复杂度:O(n)   空间复杂度：O(n)
     */
    public static int solution3(int[] nums){
        //判断边界条件，提前返回
        if (nums.length <= 1){
            return nums.length == 0 ? 0 : nums[0];
        }

        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);                      //初始化一个元素为-1的数组
        return dfs(nums, memo, nums.length - 1);
    }
    private static int dfs(int[] arr, int[] memo, int i){
        if (i <= 0){
            return i < 0 ? 0 : arr[i];
        } else if (memo[i] != -1){
            return memo[i];                             //如果发现memo[i]有值，即计算过，直接返回计算过的值
        } else {
            int hasStolen = dfs(arr, memo, i - 2) + arr[i];
            int notSteal = dfs(arr, memo,i - 1);
            memo[i] = Math.max(hasStolen, notSteal);    //记录最优值，避免重复计算
        }
        return memo[i];
    }

    public static void main(String[] args) {
        int[] arr = {2,1};
        System.out.println(solution3(arr));
    }
}
