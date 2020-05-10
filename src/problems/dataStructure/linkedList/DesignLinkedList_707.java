package problems.dataStructure.linkedList;

/**
 * @User: 吴广谋
 * @Date: 2020/3/8
 * @Description: 设计链表的实现。可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val和next。val是当前节点的值，next是指向
 * 下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性prev以指示链表中的上一个节点。假设链表中的所有节点都是0-index的。
 * 在链表中需要实现这些功能：
 *     get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 *     addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 *     addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 *     addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 *     deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 */
public class DesignLinkedList_707 {

    //node节点对象
    class Node{
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    class MyLinkedList {
        int length;
        Node head;

        public MyLinkedList() {
            length = 0;
            head = new Node(-1);
        }

        //获取链表中索引值为x位的节点的值，如果索引值不存在，返回-1
        public int get(int index) {
            if (index >= length || index < 0){
                return -1;
            }
            Node current = head;
            for (int i = 0; i <= index; i++) {
                current = current.next;
            }
            return current.val;
        }

        //在链表的表头前新增一个元素节点
        public void addAtHead(int val) {
            Node temp = new Node(val);
            temp.next = head.next;
            head.next = temp;
            length++;
        }

        //在链表的末尾新增一个元素节点
        public void addAtTail(int val) {
            Node current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = new Node(val);
            length++;
        }

        //在链接列表的第index个节点之前添加一个值为val的节点。如果index等于链表的长度，则该节点将附加到链表的末尾。如果索引大于长度，则不会插入该节点
        public void addAtIndex(int index, int val) {
            if (index > length){
                return;
            }
            if (index == length){
                addAtTail(val);
                return;
            }
            if (index < 0){
                index = index + length + 1;
            }
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node ptr = current.next;
            Node temp = new Node(val);
            current.next = temp;
            temp.next = ptr;
            length++;
        }

        //删除链表中的第x个节点
        public void deleteAtIndex(int index) {
            if (index >= length || index < 0){
                return;
            }
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            length--;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            Node current = head.next;
            while (current.next != null){
                sb.append(current.val).append(" -> ");
                current = current.next;
            }
            sb.append(current.val).append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new DesignLinkedList_707().new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3

        System.out.println(linkedList);

        System.out.println(linkedList.get(1));   //返回2
        linkedList.deleteAtIndex(1);             //现在链表是1-> 3

        System.out.println(linkedList.get(1));   //返回3
        System.out.println(linkedList);
    }
}
