package problems.algorithm.dp;

/**
 * @User: 吴广谋
 * @Date: 2020/3/9
 * @Description: 一条包含字母 A-Z 的消息通过以下方式进行了编码：'A' -> 1,'B' -> 2,...,'Z' -> 26。给定一个只包含数字的非空
 * 字符串，请计算解码方法的总数。
 * 例：输入："12" 输出：2(它可以解码为"AB"(1 2)或者"L"(12))； 输入："226" 输出：3(它可以解码为"BZ" (2 26),"VF"(22 6), 或者"BBF"(2 2 6))
 */
public class DecodeWays_091 {

    /**
     * 使用DP求解问题，仔细分析后，可以得出这个题类似于爬楼梯问题，但是需要注意边界问题。<br/>
     * 当数字中出现0时，没有对应字母，必须与前一个数字相关联。当连续的两个字母大于26时，没有对应字母，必须拆开算两种解码方式。<br/>
     * 不加限制条件的状态转移方程为：dp[i] = dp[i-1] + dp[i - 2]
     */
    public static int solution1(String s) {
        char[] arr = s.toCharArray();
        int dp[] = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = arr[0] == '0' ? 0 : 1;
        if (s.length() <= 1) {
            return dp[1];
        }
        for (int i = 2; i <= s.length(); i++) {
            int n = (arr[i-2] - '0') * 10 + (arr[i-1] - '0');
            if (arr[i-1] == '0' && arr[i-2] == '0'){ //1.当出现两个连续的0时，无法解码，返回0
                return 0;
            } else if (arr[i-2] == '0') {
                dp[i] = dp[i-1];                     //2.当i-2位是0时，第i-1位必须与i位结合，即dp[i]=dp[i-1]，只有一种组合方式
            } else if (arr[i-1] == '0') {
                if (n > 26)                          //3.同样，当i-1位是0时，如果i-2位与i-1位组合大于26，即没有对应的字母，返回0
                    return 0;
                dp[i] = dp[i-2];                     //4.有对应字母时，即i-2位与i-1位结合，因此dp[i]=dp[i-2]
            } else if (n > 26){                      //5.没有出现0的情况，且i-2位与i-1位组合大于26，则i-1位只能与i结合，即dp[i]=dp[i-1]
                dp[i] = dp[i-1];
            } else {
                dp[i] = dp[i-1] + dp[i-2];           //6.越过其他所有限制条件，即不加限制条件的状态转移方程
            }
        }
        return dp[s.length()];
    }


    public static void main(String[] args) {
        System.out.println(solution1("2020"));
    }
}
