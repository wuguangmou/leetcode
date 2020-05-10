package interview.linkedlist;

/**
 * @User: 吴广谋
 * @Date: 2020/5/10
 * @Description: 来源于leetcode面试题02.03：实现一种算法，删除单向链表中间的某个节点（除了第一个和最后一个节点，不一定是
 * 中间节点），假定你只能访问该节点。
 * 例：输入：单向链表a->b->c->d->e->f中的节点c       结果：不返回任何数据，但该链表变为a->b->d->e->f
 */
public class DeleteMiddleNode_0203 {
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }
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
            ListNode curr = this;
            while (curr.next != null){
                sb.append(curr.val).append(" -> ");
                curr = curr.next;
            }
            sb.append(curr.val).append("]");
            return sb.toString();
        }
    }

    public static void deleteNode(ListNode node){
        node.val = node.next.val;       //当前节点的值赋为下一节点的值
        node.next = node.next.next;     //当前节点的指针变成下一个节点的指针
    }

    public static void main(String[] args) {
        int[] head = {2, 4, 6, 8};
        ListNode linkedList = new DeleteMiddleNode_0203().new ListNode(head);
        System.out.println(linkedList);
        deleteNode(linkedList.next);
        System.out.println(linkedList);
    }
}
