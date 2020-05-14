package problems.dataStructure.linkedList;

/**
 * @User: 吴广谋
 * @Date: 2020/5/11
 * @Description: 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 例：输入：1->2->4, 1->3->4     输出：1->1->2->3->4->4
 */
public class MergeTwoSortedLists_21 {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
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

    /**
     * 很容易想到，遍历l1和l2，然后比较每个节点的值，每次都将小的值赋给合并后的链表，通过这样，合并两个链表
     * 时间复杂度：O(m+n)  空间复杂度：O(1)    解释：m和n分别为链表l1、l2的长度
     */
    public static ListNode solution1(ListNode l1, ListNode l2){
        ListNode mergedList = new ListNode(-1);

        ListNode prev = mergedList;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                prev.next = l1;
                l1 = l1.next;       //当l1小的时候，prev的下一节点为l1，然后迭代l1
            } else {
                prev.next = l2;
                l2 = l2.next;       //当l2小的时候，prev的下一节点为l2，然后迭代l2
            }
            prev = prev.next;       //prev指针指向下一节点
        }
        prev.next = l1 == null ? l2 : l1;   //当其中一个list遍历完时，next指针指向未遍历完的另一个list
        return mergedList.next;             //mergedList的头结点还是-1，因此要返回next
    }

    /**
     * 方法1通过迭代的方式遍历l1和l2，那么肯定也可以通过递归的方式去遍历l1和l2
     * 时间复杂度：O(m+n)     空间复杂度：O(m+n)    解释：m和n分别为链表l1、l2的长度
     */
    public static ListNode solution2(ListNode l1, ListNode l2){
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        if (l1.val < l2.val){
            l1.next = solution2(l1.next, l2);
            return l1;
        } else {
            l2.next = solution2(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1,3};
        int[] arr2 = {4,5,6};

        ListNode l1 = new ListNode(arr1);
        ListNode l2 = new ListNode(arr2);

        ListNode mergedList = solution1(l1, l2);
        System.out.println(mergedList);
    }
}
