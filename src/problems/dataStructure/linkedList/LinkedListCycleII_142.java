package problems.dataStructure.linkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * @User: 吴广谋
 * @Date: 2020/5/8
 * @Description: 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。为了表示给定链表中的环，我们使用整数pos来
 * 表示链表尾连接到链表中的位置（索引从0开始）。 如果pos是-1，则在该链表中没有环。(不允许修改给定的链表)
 * 例：输入：head = [3,2,0,-4], pos = 1   输出：tail connects to node index 1  解释：链表中有一个环，其尾部连接到第二个节点
 *    输入：head = [1,2], pos = 0        输出：tail connects to node index 0  解释：链表中有一个环，其尾部连接到第一个节点
 *   输入：head = [1], pos = -1         输出：no cycle                       解释：链表中没有环
 */
public class LinkedListCycleII_142 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

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
            return "[" + val +"]";      //toString方法返回当前节点的值，方便main方法测试
        }
    }

    /**
     * 在题目141中，我们使用了hashMap来解决问题，这里也是类似，不过我们可以新定义一个set，用于保存已经访问过的节点，当链表中
     * 有环时，返回当前节点，否则返回null。<br/>
     * 时间复杂度：O(n)-O(n^2)  取决于set.contains()方法   空间复杂度：O(n)
     */
    public static ListNode solution1(ListNode head){

        Set<ListNode> seen = new HashSet<>();
        ListNode node = head;
        while (node != null){
            if (seen.contains(node)){
                return node;            //如果当前节点在set中存在，说明有环，直接返回
            }
            seen.add(node);
            node = node.next;           //指向下一个节点，继续遍历
        }
        return null;
    }

    /**
     * 还是使用双指针法，不过这次额外引入了Floyd判圈算法，去判断环的入口，具体算法流程解释如下：<br/>
     * 设链表中共有a+b个节点，其中a为链表头部到环入口的节点数（不包含入口），b为链表环中的节点数<br/>
     * 1.设置两个快慢指针fast、slow，fast每次所走的距离是slow的两倍，若链表中有环，则fast一定会和slow相遇
     * 当第一次相遇时，由以上条件可知：<br/>
     *  <i>f=2s(fast路程是slow的2倍)</i><br/>
     *  <i>f=s+nb(fast比slow多走了n倍环的长度，n为未知数)</i><br/>
     * 求解以上两个公式，可以得出：s=nb f=2nb，即：fast指针走了2n个环的周长，slow指针走了n个环的周长（它们才相遇）。<br/>
     *
     * 2.当第一次相遇以后，目前slow指针所走的步数为nb(n倍环的长度)，这时候我们假设从链表头结点到环入口的距离
     * 为k，k=a+nb,a为头结点到环口的距离，b为环节点数，n∈N（n属于自然数，包括0），公式解释：从头结点出发，再
     * 走a个节点或者多循环n次b个节点，最终都能到达入口节点。<br/>
     * 此时我们可以发现：快慢指针走了(2)nb步，只需要继续再走a步，就可以到达环入口节点！这a步，就是从头结点开始的slow指
     * 针走到环入口的距离！<br/>
     * 时间复杂度：O(n)    空间复杂度：O(1)
     */
    public static ListNode solution2(ListNode head){
        ListNode slow = head, fast = head;      //定义两个指针
        do {
            if (fast == null || fast.next == null) {
                return null;                    //当链表中无环时，返回null
            }
            slow = slow.next;                   //慢指针每次走一步，快指针每次走两步
            fast = fast.next.next;
        } while (slow != fast);

        fast = head;                            //将fast指针重新指向head
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;                   //fast指针每次也只移动一步，再走a步后，它们相遇时，就是环的入口
        }
        return fast;
    }

    public static void main(String[] args) {

        int[] listData = {3, 2, 0, -4};
        int pos = 1;

        ListNode head = new ListNode(listData);
        ListNode curr = head.next;
        do {
            curr = curr.next;               //使用do-while循环获取链表的尾结点
        } while (curr.next != null);
        curr.next = head.next;              //将链表尾部指针指向head的下一个节点，即pos=1的位置

        System.out.println(solution1(head));
    }
}
