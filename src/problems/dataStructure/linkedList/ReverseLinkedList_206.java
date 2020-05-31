package problems.dataStructure.linkedList;

import java.util.Stack;

/**
 * @User: 吴广谋
 * @Date: 2020/5/27
 * @Description: 反转一个单链表
 * 示例：输入：1->2->3->4->5->NULL     输出：5->4->3->2->1->NULL
 */
public class ReverseLinkedList_206 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        //新增参数为数组的构造方法，便于测试
        ListNode(int[] arr){
            if(arr == null || arr.length == 0){
                return;
            }

            this.val = arr[0];
            ListNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            ListNode current = this;
            while (current.next != null){
                sb.append(current.val).append(" -> ");
                current = current.next;
            }
            sb.append(current.val).append("]");
            return sb.toString();
        }
    }

    /*
     * 由于需要反转一个链表，即把原链表最后面的节点放在新链表的最前面，很容易想到，可以通过栈来存储链表节点，实现反转功能。<br/>
     * 时间复杂度：O(n)，解释：入栈与出栈的时间复杂度都是O(1)   空间复杂度：O(n)
     */
    public static ListNode solution1(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null){
            stack.push(new ListNode(head.val)); //重新构造一个值相同的node节点入栈，防止出现环形链表
            head = head.next;
        }

        ListNode curr = stack.pop();
        ListNode root = curr;
        while (!stack.empty()){
            curr.next = stack.pop();            //当栈不为空时，将当前节点出栈
            curr = curr.next;                   //同时迭代该节点，即指向下一个节点
        }
        return root;
    }

    /**
     * 方案一我们使用了额外的空间来解决问题，这也是最容易想到的，那么能否在相同的时间复杂度下不使用额外的空间完成呢？<br/>
     * 我们可以在每次遍历的时候，让当前节点指向它的前一个节点，即：1 <- 2 <- 3 <- 4 <- 5<br/>
     * 时间复杂度：O(n)           空间复杂度：O(1)
     */
    public static ListNode solution2(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null){
            ListNode nextTemp = curr.next;  //暂存curr节点的指针信息，因为要将其指向prev节点
            curr.next = prev;               //遍历过程中curr节点的下一个指针指向prev节点
            prev = curr;                    //给当前的prev节点赋值
            curr = nextTemp;                //赋值后，curr节点指针向前前进一位
        }
        return prev;
    }

    /**
     * 方案二中通过迭代链表解决了问题，那我们应该也可以通过递归的方式去实现反转链表。<br/>
     * 时间复杂度：O(n)            空间复杂度：O(n)，解释：递归时会使用隐式的栈空间，递归深度有可能会达到n层
     */
    public static ListNode solution3(ListNode head){
        if (head == null || head.next == null){
            return head;                        //递归出口，递归到尾节点或倒数第二个尾节点时，返回当前节点
        }
        ListNode curr = solution3(head.next);
        head.next.next = head;                  //head的下一个节点的指针指向head
        head.next = null;                       //将head的所指向的对象置空，防止出现环形链表
        return curr;
    }

    public static void main(String[] args) {
        int[] values = new int[]{1, 2, 3, 4, 5};
        ListNode listNode = new ListNode(values);
        ListNode newList = solution3(listNode);
        System.out.println(newList);
    }
}
