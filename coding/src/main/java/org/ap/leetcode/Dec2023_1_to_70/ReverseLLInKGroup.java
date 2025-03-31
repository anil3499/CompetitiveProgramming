package org.ap.leetcode.Dec2023_1_to_70;


public class ReverseLLInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int i=0;

        //to check whether list size is less than k
        while(i!=k && curr!=null) {
            curr=curr.next;
            i++;
        }
        if(i!=k) return head;

        curr = head;
        boolean firstOccur= true;
        ListNode localHead=head;
        while(curr!=null) {
            i=0;
            ListNode start = null;
            ListNode end= null;
            while(i<k && curr!=null){
                if(i==0) start = curr;
                if(i==k-1) end =curr;
                curr = curr.next;
                i++;
            }
            if(end==null) {
                localHead.next =start;
                return head;
            }
            end.next= null;
            ListNode local= reverseLinkedList(start,end);
            if(firstOccur) {
                System.out.println(local.val);
                head = local;
                firstOccur=false;
            } else {
                localHead.next =local;
            }
            localHead = start;
        }
        return head;
    }
    public ListNode reverseLinkedList(ListNode start, ListNode end){
        ListNode curr=start, prev = null;
        while(curr!=null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
