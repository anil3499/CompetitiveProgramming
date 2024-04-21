package org.ap.leetcode.Dec2023_1_to_70;
///https://leetcode.com/problems/add-two-numbers/
 class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
public class AddTwoNumbers {
    public ListNode addTwoNumbersMoreShortenCode(ListNode l1, ListNode l2) {
        ListNode root = null,prev=null;
        int reminder =0,val =0,sum=0;
        while(l1 != null || l2 != null || reminder!=0) {
            int  l1val = 0;
            if(l1!=null) l1val = l1.val;
            int  l2val =   0;
            if(l2!=null) l2val = l2.val;

            sum = reminder + l1val + l2val;
            val = sum  % 10;
            reminder = sum / 10;
            System.out.println(val);
            ListNode node= new ListNode(val);
            if(root == null) root = node;
            else prev.next = node;
            prev = node;
            if(l1==null) l1= null; else l1 = l1.next;
            if(l2==null) l2=null; else l2 = l2.next;
        }

        return root;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = null,prev=null;
        int reminder =0,val =0,sum=0;
        while(l1 != null && l2 != null) {
            sum = reminder + l1.val + l2.val;
            val = sum  % 10;
            reminder = sum / 10;
            System.out.println(val);
            ListNode node= new ListNode(val);
            if(root == null) root = node;
            else prev.next = node;
            prev = node;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 !=  null){
            sum = reminder + l1.val;
            val = sum%10;
            reminder = sum/10;
            ListNode node= new ListNode(val);
            if(root == null)
                root = node;
            else
                prev.next = node;

            prev = node;
            l1 = l1.next;
        }

        while(l2 != null){
            sum = reminder + l2.val;
            val = sum%10;
            reminder = sum/10;
            ListNode node= new ListNode(val);
            if(root == null) root = node;
            else prev.next = node;
            prev = node;
            l2 = l2.next;
        }
        while(reminder>0) {
            val = reminder%10;
            reminder = reminder/10;
            ListNode node= new ListNode(val);
            if(root == null) root = node;
            else prev.next = node;
            prev = node;
        }
        return root;
    }
}
