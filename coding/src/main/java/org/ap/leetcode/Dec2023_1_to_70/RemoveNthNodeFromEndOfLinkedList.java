package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/remove-nth-node-from-end-of-list/
public class RemoveNthNodeFromEndOfLinkedList {
    class ListNode {
     int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next==null && n==1) return null;
        ListNode node = head;
        ListNode root = head;
        while(node!=null && n>0){
            node= node.next;
            n--;
        }

        ListNode prev = null;
        if(node==null) {
            head = head.next;
            return head;
        }
        while(node!=null){
            node=node.next;
            prev = root;
            root = root.next;
        }

        prev.next = root.next;
        return head;
    }
}
