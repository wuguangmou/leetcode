package problems.math;

/**
 * @User: 吴广谋
 * @Date: 2020/2/23
 * @Description: 给定一个Excel表格中的列名称，返回其相应的列序号
 * 例：输入：'A'  输出：1;       输入：'AA'  输出：27；        输入：'ZY'  输出：701
 */
public class ExcelColumnNumber_171 {
    /**
     * 有了问题168的经验后，同样以进制转换的视角看待问题，这个问题就比较简单了
     * X0*26^0 + X1*26^1 + X2*26^2 + ... + Xn*26^n = num
     */
    public static int solution1(String s){
//        //从输入字母的低位向高位遍历
//        int n = 0;
//        for (int i = s.length() -1; i >= 0; i--) {
//            int p = s.charAt(i) - 'A' + 1;
//            n += p * ((int)Math.pow(26, s.length() -i -1));     //先计算第一位对应的十进制数，然后是第二位、第三位，进行累加
//        }

        int n = 0, mul = 1;
        for (int i = s.length() -1; i >=0; i--) {
            n = n + mul * (s.charAt(i) - 'A' +1);       //使用mul变量，简化了计算的公式，看起来没有那么繁琐
            mul *= 26;
        }
        return n;
    }

    /**
     * 在方案1中遍历的方式是从字母的低位向高位，即从字符的后面向前面遍历，如果反着遍历也是可以的。
     * 其实在十进制运算中我们经常使用的，比如要还原的数字是 2019，依次给你数字 2,0,1,9。就可以用下边的算法。
     * int res = 0;
     * res = res * 10 + 2; //2
     * res = res * 10 + 0; //20
     * res = res * 10 + 1; //201
     * res = res * 10 + 9; //2019
     * 而对于 26 进制是一样的道理，只需要把 10 改成 26 即可。
     */
    public static int solution2(String s){
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = n*26 + (s.charAt(i) - 'A' + 1);         //这里也可以使用上面第一次使用的繁琐公式，不过没有必要
        }
        return n;
    }
    public static void main(String[] args) {
        System.out.println(solution2("ABCD"));
    }
}
