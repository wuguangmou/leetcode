package problems.dataStructure.linkedList;

import java.util.*;

/**
 * @User: 吴广谋
 * @Date: 2020/5/6
 * @Description: 给定一个链表，判断链表中是否有环。为了表示给定链表中的环，我们使用整数pos来表示链表尾连接到链表中
 * 的位置（索引从0开始）。 如果pos是-1，则在该链表中没有环。
 * 例：输入：head = [3,2,0,-4], pos = 1  输出：true  解释：链表中有一个环，其尾部连接到第二个节点。
 *     输入：head = [1,2], pos = 0  输出：true    解释：链表中有一个环，其尾部连接到第一个节点。
 *     输入：head = [1], pos = -1   输出：false   解释：链表中没有环。
 *  进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 */
public class LinkedListCycle_141 {

    //单链表定义
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x){
            val = x;
            next = null;
        }

        //新增参数为数组的构造方法，便于测试
        public ListNode(int[] arr){
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

    }

    /**
     * 对于这样一个单链表，由于没有给出链表具体长度，因此，无法通过创建数组的方式去记录当前节点是否已经走过，换一
     * 种方式思考，我们可以使用hashMap去记录当前节点是否已经走过。<br/>
     * 时间复杂度：介于O(n)-O(n^2)，取决于map.containsValue()方法     空间复杂度：O(n)
     */
    public static boolean solution1(ListNode head){
        //使用ListNode作为key是因为map.containsKey()的时间复杂度比map.containsValue的时间复杂度低
        Map<ListNode, Integer> seen = new HashMap<>();
        int index = 0;                      //记录当前节点索引
        while (head != null){
            if (seen.containsKey(head)){
                return true;
            } else {
                seen.put(head, index++);
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 从方案1可以看出，想要判断链表中是否有环，肯定是需要遍历链表的，我们可以声明两个快慢指针，去遍历链表，
     * 快指针每次前进两步，慢指针每次前进一步，如果快指针能够追到慢指针，说明链表有环。<br/>
     * 时间复杂度：O(n)   空间复杂度：O(1)
     */
    public static boolean solution2(ListNode head){
        if (head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast){
            if (fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;           //慢指针每次走一步
            fast = fast.next.next;      //快指针每次走两步
        }
        return true;
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

        System.out.println(solution2(head));
    }
}
