package problems.dataStructure.string;

/**
 * @User: 吴广谋
 * @Date: 2020/6/19
 * @Description: 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写(本题中，我们将空字符串定义为有效的回文串)。
 * 例：输入："A man, a plan, a canal: Panama"    输出：true
 *     输入："race a car"      输出：false
 */
public class ValidPalindrome_125 {

    /**
     * 我们可以考虑使用两个指针去遍历字符串，一个正序，一个逆序，然后每次判断每次指针指向的字符是否相等
     * 时间复杂度为：O(n) (不考虑正则的替换)       空间复杂度为：O(n) (由于字符串的不可变性，s其实是一个新的对象)
     */
    public static boolean solution1(String s){
        //将字符串中的非数字字母替换成空
        s = s.replaceAll("[^0-9a-zA-Z]", "");
        //空字符串为有效的回文串
        if (s.equals("")){
            return true;
        }

        int i, j;
        for (i = 0, j = s.length() - 1; i < s.length() || j >= 0; i++, j--) {
            String a = String.valueOf(s.charAt(i));
            String b = String.valueOf(s.charAt(j));
            if (!a.equalsIgnoreCase(b)){
                return false;
            }
        }
        return i + j == s.length() - 1;
    }

    /**
     * 如果使用正则替换，还有更简短的代码，StringBuilder封装了字符串反转的方法，然后再通过大小写忽略比对判断是否回文
     * 时间复杂度为：O(n) (不考虑正则的替换)       空间复杂度为：O(n) (创建了新对象)
     */
    public static boolean solution2(String s){
        s = s.replaceAll("[^0-9a-zA-Z]", "");
        StringBuilder reserveStr = new StringBuilder(s).reverse();

        return s.equalsIgnoreCase(reserveStr.toString());
    }

    /**
     * 上面的代码实际有点取巧，我们能否不通过正则表达式，通过遍历字符串返回正确的结果呢？应该也是可以的。
     * 实际使用双指针遍历判断，指针移动到字符串中间的位置就行，无需到底。
     * 时间复杂度：O(n)       空间复杂度：O(n)
     */
    public static boolean solution3(String s){
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)){
                newStr.append(Character.toLowerCase(c));
            }
        }
        int left = 0, right = newStr.length() - 1;
        while (left < right){
            if (newStr.charAt(left) != newStr.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 以上三种解法的空间复杂度都是O(n)，能否继续优化，在O(1)的时间复杂度下完成呢，应该也是可以的。
     * 时间复杂度：O(n)   空间复杂度：O(1)
     */
    public static boolean solution4(String s){
        int left = 0, right = s.length() - 1;
        while (left < right){
            //左指针遇到非数字字母时，向右迭代一位，继续遍历
            if (!Character.isLetterOrDigit(s.charAt(left))){
                left++;
                continue;
            }
            //右指针遇到非数字字母时，向左迭代一位，继续遍历
            if (!Character.isLetterOrDigit(s.charAt(right))){
                right--;
                continue;
            }
            //当两个字符不相等时，立刻返回false，否则，继续遍历
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        System.out.println(solution4(str));
    }
}
