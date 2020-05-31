package problems.math;

/**
 * @User: 吴广谋
 * @Date: 2020/2/14
 * @Description: 给定一个正整数，返回它在Excel表中相对应的列名称
 *  例：输入：1  输出：A;   输入：27  输出：AA;      输入：701  输出：ZY
 */
public class ExcelColumnTitle_168 {

    /**
     * 最开始的想法就是构建一个字母表，然后根据数字和字母的对应关系，将相应的字母组合起来，然后输出。<br/>
     * 由于1对应的是'A'，26对应的是'Z'，因此当取模的值为0时，要将其换成26，并且n的值减1。
     */
    public static String solution1(int n){
        String[] alphabet = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

        StringBuilder sb = new StringBuilder();
        while (n > 0){
            int c = n % 26;
            if (c == 0){
                c = 26;
                n --;
            }
            sb.append(alphabet[c -1]);          //从低位到高位将字母粘贴起来
            n = n /26;
        }
        return sb.reverse().toString();         //最后逆序输出
    }

    /**
     * 看了别人的题解后，意识到其实问题的本质是进制转换，只不过是从1开头的26进制转换。
     * 对于字符表，完全没有必要，可以使用字符对应的ASCII码替代
     */
    public static String solution2(int n){
        StringBuilder sb = new StringBuilder();
        while (n > 0){
            int c = n % 26;
            if (c == 0){
                c = 26;
                n--;
            }
            sb.insert(0, (char) ('A' + c -1));      //字符从'A'开头，需要减1，每次都在起始位置插入，最后无需反转字符
            n /= 26;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(solution1(701));
    }
}
