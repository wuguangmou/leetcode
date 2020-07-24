package problems.dataStructure.string;

/**
 * @User: 吴广谋
 * @Date: 2020/7/17
 * @Description: 编写一个函数来查找字符串数组中的最长公共前缀，如果不存在公共前缀，返回空字符串""。
 * 例：输入：["flower","flow","flight"]      输出："fl"
 *     输入：["dog","racecar","car"]         输出：""
 */
public class LongestCommonPrefix_014 {

    /**
     * 将字符数组的第一个元素作为起始元素，然后一个一个去和后面的字符串逐位比较，每次比较取公共前缀，最后返回
     * 时间复杂度：O(mn)      空间复杂度：O(1)
     */
    public static String solution1(String[] strs){
        if (strs == null || strs.length == 0){
            return "";
        }
        String prefix = strs[0];
        if (prefix.length() == 0){
            return prefix;
        }
        for (int i = 1; i < strs.length; i++) {
            StringBuilder temp = new StringBuilder();
            int length = prefix.length() > strs[i].length() ? strs[i].length() : prefix.length();
            //逐个对比字符是否一致
            for (int j = 0; j < length; j++) {
                if (prefix.charAt(j) == strs[i].charAt(j)){
                    temp.append(prefix.charAt(j));
                } else {
                    break;
                }
            }

            prefix = temp.toString();
            if (prefix.length() == 0){
                break;
            }
        }
        return prefix;
    }

    //solution1代码精简
    public static String longestCommonPrefix(String[] strs){
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0){
                break;
            }
        }
        return prefix;
    }
    //找到str1和Str2的最长公共子前缀
    public static String longestCommonPrefix(String str1, String str2){
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)){
            index++;
        }
        return str1.substring(0, index);
    }

    public static void main(String[] args) {
        String[] strs = {"aaa","aa","aaa"};
        System.out.println(solution1(strs));
    }
}
