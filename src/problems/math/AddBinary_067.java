package problems.math;

import java.math.BigInteger;

/**
 * @User: 吴广谋
 * @Date: 2020/3/6
 * @Description: 给定两个二进制字符串，返回他们的和（用二进制表示）,输入为非空字符串且只包含数字 1 和 0。
 * 例：输入：a = "11", b = "1" 输出："100"；           输入：a = "1010", b = "1011"  输出："10101"
 */
public class AddBinary_067 {
    /**
     * 因为给定的a、b都为二进制字符串，可以将二进制字符串转换成十进制数字，相加后再返回二进制字符串。<br/>
     * 使用内置的转换数字方法，可以部分实现题中要求，但问题一是这样解题没有意义，问题二是超过33位时，无法转换Integer，
     * 超过65位时，无法转换成Long，并且此方法也无法通过所有的测试用例，因此放弃。
     */
    public static String solution1(String a, String b){
        long p = Long.valueOf(a, 2);
        long q = Long.valueOf(b, 2);
        return Long.toBinaryString(p + q);
    }

    /**
     * 不能使用内置的方法后，自己计算二进制字符串之和，首先想到的就是逐位相加，然后返回。<br/>
     * 时间复杂度：O(max(A,B))    空间复杂度：O(max(A,B))   其中，A、B为输入字符串a、b的长度。
     */
    public static String solution2(String a, String b){
        StringBuilder ans = new StringBuilder();
        int carry = 0;                                  //进一位
        for (int i = a.length() - 1, j = b.length() -1 ; i >= 0 || j >=0; i--, j--) {
            int sum = carry;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;      //判断i、j是否大于0，防止因为字符串长度不等时，数组下标越界
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            carry = sum /2;
        }
        ans.append(carry == 1 ? carry : "");
        return ans.reverse().toString();                //正向append，逆向输出
    }

    public static void main(String[] args) {
        System.out.println(solution2("101", "1011"));
    }
}
