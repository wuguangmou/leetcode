package problems.algorithm.dp;

/**
 * @User: 吴广谋
 * @Date: 2020/3/10
 * @Description: 假设你正在爬楼梯。需要n阶你才能到达楼顶。每次你可以爬1或2个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 例：输入：2 输出：2（1+1、2）；  输入：3 输出：3（1+1+1、1+2、2+1）
 */
public class ClimbingStairs_070 {

    /**
     * 作为DP经典入门题目，首先当然可以使用暴力破解的方法<br/>
     * 由于每次可以攀爬的阶数为1或2，所以可以得出climbStairs[n] = climbStairs[n-1] + climbStairs[n-2]<br/>
     * 时间复杂度：O(2^n),   空间复杂度：O(n),递归树的深度可以达到n
     */
    public static int solution1(int n){
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        return solution1(n - 1) + solution1(n - 2);
    }

    /**
     * 使用递归的方法求解后，如果画出递归树，可以看出递归树中存在着大量的重复计算，如果能用一个数组将每次计算的值存储下来，
     * 这样就可以省去很多重复的计算，这种自顶向下，记录每次状态变化的方式也称为记忆化搜索。<br/>
     * 时间复杂度：O(n)  空间复杂度：O(n)
     */
    public static int solution2(int n){
        int[] memo =new int[n +1];

        if (n == 0 || n == 1){
            return 1;
        } else {
            memo[1] = 1;
            memo[2] = 2;
            return dfs(n, memo);
        }
    }
    private static int dfs(int n, int[] array){
        if (array[n] != 0){
            return array[n];
        } else {
            array[n] = dfs(n - 1, array) + dfs(n -2, array);
            return array[n];
        }
    }

    /**
     * 由前面可以看出，此题的状态转移方程为：DP[n] = DP[n -1] + DP[n -2]，根据状态转移方程自底向上的求解问题，即使用动态规
     * 划求解<br/>
     * 时间复杂度：O:(n)   空间复杂度:O:(n)
     */
    public static int solution3(int n){
        if (n == 1 || n == 2){
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i -1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 此题还有一些其他解法，比如推导出数学公式，或者使用矩阵，或者直接计算出边界返回相应数字，在此就不演示了，主要为了理解DP思想。
     */
    public static void main(String[] args) {
        System.out.println(solution2(45));
        System.out.println(solution3(45));
    }
}
