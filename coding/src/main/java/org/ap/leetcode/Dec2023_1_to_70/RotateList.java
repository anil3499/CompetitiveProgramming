package org.ap.leetcode.Dec2023_1_to_70;


public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0) {
            return head;
        }
        ListNode p = head;
        int len = 1;
        while(p.next != null) {
            p = p.next;
            len++;
        }
        p.next = head;
        k %= len;
        for(int i = 0; i < len - k; i++) {
            p = p.next;
        }
        head = p.next;
        p.next = null;
        return head;
    }

    public ListNode rotateRight1(ListNode head, int k) {
        int size=getListSize(head);
        if(head==null||k==0)
            return head;
        while(size-k<0)
            k= k-size;

        if(size-k==0) {
            return head;
        }

        //1 2 3 4 5 k=2
        int k1=k;
        ListNode curr=head;
        while(k1>0){
            //if(curr==null) return head;
            System.out.println(curr.val);
            curr = curr.next;
            k1--;
        }
        //curr 1
        ListNode root = head;
        while(curr.next!=null){
            curr=curr.next;
            root=root.next;
        }
        //curr 2 root 1
        curr.next = head;
        //root is new end of list
        //end node
        ListNode end = root;

        //new root
        root=root.next;
        end.next=null;

        return root;

    }

    public int getListSize(ListNode head){
        ListNode curr = head;
        int count =0;
        while(curr!=null){
            count++;
            curr=curr.next;
        }
        return count;
    }
}
