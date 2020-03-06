package problems.dataStructure.stack;

import java.util.Stack;

/**
 * @User: 吴广谋
 * @Date: 2020/3/6
 * @Description: 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *      push(x) -- 将元素 x 推入栈中。        pop() -- 删除栈顶的元素。
 *      top() -- 获取栈顶元素。               getMin() -- 检索栈中的最小元素。
 * 例：MinStack minStack = new MinStack();
 *     minStack.push(-2);
 *     minStack.push(0);
 *     minStack.push(-3);
 *     minStack.getMin();   --> 返回 -3.
 *     minStack.pop();
 *     minStack.top();      --> 返回 0.
 *     minStack.getMin();   --> 返回 -2.
 */
public class MinStack_155 {

    class MinStack{
        private Stack<Integer> stack;       //使用栈 + min变量来实现最小栈
        private int min;
        public MinStack() {
            stack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if (x <= min){
                stack.push(min);            //每次放入栈中的值，一旦比最小值小，栈顶多入栈一次上次的最小值，同时更新最小值
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            if (min == stack.peek()){       //每次出栈时，如果栈顶的值是最小值，多出栈一次，同时获取到上次多入栈的最小值
                stack.pop();
                min = stack.pop();
            } else {
                stack.pop();
            }
            if (stack.isEmpty()){
                min = Integer.MAX_VALUE;    //栈中为空时，更新最小值
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }


    }

    public static void main(String[] args) {
        MinStack_155 instance = new MinStack_155();
        MinStack minStack = instance.new MinStack();

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());      // 返回 -3
        minStack.pop();
        System.out.println(minStack.top());         // 返回 0
        System.out.println(minStack.getMin());      // 返回 -2
    }
}
