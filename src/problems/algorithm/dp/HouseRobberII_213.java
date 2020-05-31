package problems.algorithm.dp;

/**
 * @User: 吴广谋
 * @Date: 2020/4/20
 * @Description: 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个
 * 房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * 例：输入[2,3,2]  输出：3（由于1号房屋（金额=2）和3号房屋（金额=2）是相邻的，因此只能偷取2号房屋（金额=3））
 *     输入[1,2,3,1]  输出：4（可以先偷窃1号房屋（金额=1），然后偷窃3号房屋（金额=3））
 */
public class HouseRobberII_213 {

    /**
     * 此题与打家劫舍I类似，不过多了一个限制条件，就是首尾可以相连，因此不能同时取首尾的数，我们可以将环形数组[0,n]分成两段，
     * 第一段为[0,n-1]，第二段为[1,n]，此时，只需要求得哪一段的值比较大，就可以知道偷窃的最高金额是多少。<br/>
     * 动态转移方程：dp[i] = max{dp[i-2]+arr[i], dp[i-1]}<br/>
     * 时间复杂度:O(n)   空间复杂度：O(n)
     */
    public static int solution1(int[] nums){
        int n = nums.length;
        if (n <= 1){
            return n == 0 ? 0 : nums[0];
        }
        if (n == 2){
            return Math.max(nums[0], nums[1]);
        }
        //分为两种情况，a为偷取第一间房屋，即[0,n-1];b为不偷取第一间房屋，即[1,n]
        int[] a = new int[n], b = new int[n];
        a[0] = nums[0];
        a[1] = Math.max(nums[0], nums[1]);
        b[1] = nums[1];
        b[2] = Math.max(nums[1], nums[2]);

        for (int i = 2; i < n - 1; i++) {
            a[i] = Math.max(a[i-2]+nums[i], a[i-1]);
        }
        for (int i = 3; i < n; i++) {
            b[i] = Math.max(b[i-2]+nums[i], b[i-1]);
        }
        return Math.max(a[n-2], b[n-1]);
    }


    public static void main(String[] args) {
        int[] nums = {1, 6, 7};
        System.out.println(solution1(nums));
    }
}
