package problems.algorithm.dp;

/**
 * @User: 吴广谋
 * @Date: 2020/3/23
 * @Description: 给定一个数组，它的第i个元素是一支给定股票第 i天的价格。如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），
 * 设计一个算法来计算你所能获取的最大利润。(注意：你不能在买入股票前卖出股票)
 * 例：输入: [7,1,5,3,6,4]   输出：5 （第二天买入，第五天卖出，最大利润：6-1=5）
 *    输入：[7,6,4,3,1]    输出：0 （没有交易完成，所以最大利润为0）
 *
 * 此题与最大连续子数组和极为类似，只不过一个求差，一个求和，可以对比学习，连续最大子数组和：053；
 */
public class BestTimeToBuyAndSellStock_121 {
    /**
     * 一般这类算法都可以使用暴力求解，首先使用暴力求解，循环遍历两次，记录最大利润差<br/>
     * 时间复杂度：O(n^2)   空间复杂度：O(1)
     */
    public static int solution1(int[] prices){
        if (prices == null || prices.length <= 1){
            return 0;
        }
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > max)
                    max = profit;
            }
        }
        return max;
    }

    /**
     * 用DP的思想求解,第i天的最大利润 = max{(第i-1天的最大利润)，(第i天的价格-前i-1天中的历史最低价格)}<br/>
     * 根据这个公式，可以列出状态转移方程：DP[i] = max(DP[i-1], price[i]-minPrice)，DP[i]表示第i天的最大利润。<br/>
     * 思路可能会比较复杂，但DP解决问题的思想就是这样，可以多体会几次。<br/>
     * 时间复杂度：O(n)  空间复杂度：O(n)。
     */
    public static int solution2(int[] prices){
        if (prices == null || prices.length <= 0){
            return 0;
        }
        int[] dp = new int[prices.length];
        int minPrice = prices[0];                   //最低价初始值为第1天的价格

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice){
                minPrice = prices[i];               //记录最低价格
            }
            dp[i] = Math.max(dp[i-1], prices[i] - minPrice);
        }
        return dp[prices.length -1];                //因为i最大循环到prices.length-1,因此返回dp[prices.length -1]
    }

    /**
     * 使用贪心算法求解问题，在求解问题的过程中，记录最大利润与历史最低价格，一旦发现有更高的最大利润，就重新赋值最大利润，
     * 通过这样，我们总是能够获取到最大利润。<br/>
     * 时间复杂度：O(n)   空间复杂度：O(1)
     */
    public static int solution3(int[] prices){
        int maxPrice = 0;                               //记录最大利润，如果不存在，则输出0
        int low = Integer.MAX_VALUE;                    //记录历史最低价格
        for (int price : prices) {
            if (low > price) {
                low = price;
            }
            maxPrice = Math.max(maxPrice, price - low);//在历史最大利润与现有价格-历史最低价中选出最大的
        }
        return maxPrice;
    }
    public static void main(String[] args) {
        int prices[] = new int[]{2,6,1,4};
        System.out.println(solution3(prices));
    }
}
