package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/merge-two-sorted-lists/
public class Merge2SortedList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode root = null;
        ListNode node = null;
        while(list1 !=null && list2 !=null) {
            if(list1.val <= list2.val) {
                if(root==null){
                    root = list1;
                    node = root;
                }
                else {
                    node.next=list1;
                    node = node.next;
                }
                list1 =  list1.next;
            }else {
                if(root==null){
                    root = list2;
                    node = root;
                }
                else {
                    node.next=list2;
                    node = node.next;
                }
                list2 =  list2.next;
            }
        }
        while(list1!=null) {
            if(root==null){
                root = list1;
                node = root;
            }
            else {
                node.next=list1;
                node = node.next;
            }
            list1 =  list1.next;
        }

        while(list2!=null) {
            if(root==null){
                root = list2;
                node = root;
            }
            else {
                node.next=list2;
                node = node.next;
            }
            list2 =  list2.next;
        }
        return root;
    }
}
