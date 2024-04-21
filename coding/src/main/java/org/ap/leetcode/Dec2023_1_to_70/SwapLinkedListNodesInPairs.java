package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/swap-nodes-in-pairs/description/
public class SwapLinkedListNodesInPairs {
    class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode swapPairs(ListNode head) {
        if(head==null) return null;
        int k =2;
        ListNode begining = null;
        ListNode LHead = head;
        ListNode start = null;
        ListNode end =null;
        // ListNode Ltail = null;

        ListNode curr = head;

        //belo code to handel case when list size is given lesser than  k
        int i=0;
        while(i!=k && curr!=null){
            curr=curr.next;
            i++;
        }
        if(i!=k) return head;

        //reinitializing curr tp head
        curr = head;
        boolean flag = true;

        while(curr!=null) {
            int l =0;
            while(l<k && curr!=null) {
                if(l==0) start =curr;
                if(l==k-1) end = curr;
                curr = curr.next;
                l++;
            }
            if(end==null) {
                LHead.next = start;
                return head;
            }
            end.next = null; // end we are assigning null so reverse code can work perfectly
            ListNode local = reverse(start ,end);
            if(flag) {
                head = local;
                flag=false;  //flag is just assign main headonce at startof list
            }
            else
                LHead.next = local;

            LHead = start;
            end=null;  // we are assiging endas null so ifin subsequent loop if k elements ot present then we can recognize it.
        }
        return head;
    }

    public ListNode reverse(ListNode start,ListNode end){
        ListNode prev =null;
        ListNode currL = start;
        ListNode next= null;
        while(currL!=null) {
            next= currL.next;
            currL.next= prev;
            prev = currL;
            currL = next;
        }
        return prev;
    }
}
