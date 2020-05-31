package problems.algorithm.sort;


import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @User: 吴广谋
 * @Date: 2020/2/11
 * @Description: 给定两个字符串s和t，判断s和t是否是字母异位词(可以假设字符串只包含字母，没有特殊字符)
 * 例：s="anagram",t="nagaram" return: true;  s="rat",t="car" return: false
 */
public class Anagram_242 {

    /**
     * 最先想到的方法，现将string转换成char类型数组，进行排序后在对比两个数组是否一致。<br/>
     * 主要的时间消耗在排序以及比较两个数组是否相等上面，排序用的双基准快排，时间复杂度为O(nlogn)，而比较数组是否相等时间复杂度为O(n)。<br/>
     * 时间复杂度：O(nlogn),空间复杂度：O(n)
     */
    public static boolean solution1(String s, String t){
        if (s == null || t == null || s.length() != t.length()){
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }

    /**
     * 为了比较两个字符是否是字母异位词，我们还可以统计每个字母出现的频率，如果频率一样，那么这两个字符就是字母异位词
     * 在代码执行结果时，此方法平均消耗50ms左右，是第一个方法的7—8倍，可能调用了太多次集合类的方法，不推荐的解决方案。
     */
    public static boolean solution2(String s, String t){
        if (s == null || t == null || s.length() != t.length()){
            return false;
        }

        Map<Character, Integer> map = new TreeMap<>();

        for (int i = 0; i < s.length(); i++){
            if (!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), 1);            //如果map中没有关键字key，则将其添加进去
            } else {
                int count = map.get(s.charAt(i));   //如果有的话，将这个key出现的次数+1
                map.put(s.charAt(i), ++count);
            }
        }

        for (int i = 0; i < t.length(); i++) {      //写在一个for循环中会有bug，因为使用的是map判断是否包含key
            if (map.containsKey(t.charAt(i))){
                int count = map.get(t.charAt(i));   //遍历字符串t则相反，将key相同的字符出现频率-1
                map.put(t.charAt(i),--count);
            } else {
                return false;                   //如果有不同的字符出现，则直接返回false
            }
        }

        Collection<Integer> values = map.values();
        for (Integer value: values){            //最后遍历值集合，如出现非0，说明有字符的出现频率不等，返回false
            if (value != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 使用map统计频率时，操作有些繁琐，完全可以使用数组来简化操作。<br/>
     * 时间复杂度：O(n)，空间复杂度：O(1)，因为开辟的是固定长度的数组
     */
    public static boolean solution3(String s, String t){
        if (s == null || t == null || s.length() != t.length()){
            return false;
        }

        int[] frequency = new int[26];          //假设字符串中只是26种小写字母的组合

        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i) - 'a']++;     //s中该字母出现的频率++
            frequency[t.charAt(i) - 'a']--;     //t中该字母出现的频率--
        }

        for (int count : frequency) {
            if (count != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 官方给出更快一些的解决方案：在方法3中，我们完全将两个字符遍历完之后再去对比，其实可以在遍历的时候就去对比，不一致立刻退出。<br/>
     * 时间复杂度：O(n)，空间复杂度：O(1)
     */
    public static boolean solution4(String s, String t){
        if (s == null || t == null || s.length() != t.length()){
            return false;
        }

        int[] frequency = new int[26];

        for (int i = 0; i < s.length(); i++) {
            frequency[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            frequency[t.charAt(i) - 'a']--;
            if (frequency[t.charAt(i) -'a'] < 0){       //在遍历t时，如果频率小于0，立刻退出
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        System.out.println(solution2(s, t));
    }
}
