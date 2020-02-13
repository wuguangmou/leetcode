package problems.dataStructure.stack;

import java.util.Stack;

/**
 * @User: 吴广谋
 * @Date: 2020/2/13
 * @Description: 给定一个只包括'('，')'，'{'，'}'，'['，']'的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 *      左括号必须用相同类型的右括号闭合。
 *      左括号必须以正确的顺序闭合。
 *  例：输入 "()"   输出：true；    输入："{[]}"   输出：true;   输入："([)]"   输出：false
 */
public class ValidParentheses_020 {

    /**
     * 括号匹配解决的方法一般都是用栈来解决，这里也是一样，首先用栈来实现
     * 时间复杂度：O(n)  空间复杂度：O(n)，栈的推入和弹出操作时间消耗均为O(1)
     */
    public static boolean solution1(String s){
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                stack.push(s.charAt(i));    //遇见左括号，推入栈中
            }
            else {
                if (stack.isEmpty()){       //其他情况时，先判断栈是否为空，否则遇到某些字符串在peek操作时会报空栈异常
                    return false;
                }
                if (stack.peek() == '(' && s.charAt(i) != ')'){         //不满足匹配规则时，立刻返回false
                    return false;
                }
                else if (stack.peek() == '[' && s.charAt(i) != ']'){
                    return false;
                }
                else if (stack.peek() == '{' && s.charAt(i) != '}'){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();     //最后判断栈中是否为空，即全部匹配完毕
    }

    /**
     * 方案1思想简单，但代码中的判断较为繁琐，我们可以换一种解决方案：
     * 每次遇到左括号时，就将对应的右括号推入栈中，这样，当遇到第一个右括号时，如果整个字符是正确的，第一个右括号一定会和之前的
     * 左括号匹配，如果不匹配，则直接返回就行。
     * 时间复杂度：O(n)   空间复杂度：O(n)
     */
    public static boolean solution2(String s){
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){            //遇见左括号，则将对应的右括号推入栈中
                stack.push(')');
            }
            else if (s.charAt(i) == '['){
                stack.push(']');
            }
            else if (s.charAt(i) == '{'){
                stack.push('}');
            }
            else if (stack.isEmpty() || stack.pop() != s.charAt(i)){
                return false;       //遇到右括号时，判断是否与当前栈顶的字符相等（即匹配），同时，将栈顶元素弹出
            }
        }
        return stack.isEmpty();     //最后判断栈中是否为空，即是否全部匹配
    }

    /**
     * 基于方案二的思想，可以发现：正确的字符串中，总能够剔除一个匹配的括号，直至整个字符串全部为空，因此代码还可以再简化
     * 不推荐此种方法，因为其时间复杂度为指数级别，不过可以将这种消消乐的思想写出来
     */
    public static boolean solution3(String s){
        if (s.length() % 2 != 0){
            return false;
        }
        while (s.contains("()") || s.contains("[]") || s.contains("{}")){
            s = s.replace("()","").replace("[]","").replace("{}","");
        }
        return s.length() == 0;
    }

    public static void main(String[] args)   {
        String s = "{[]()}";
        System.out.println(solution3(s));
    }
}
