package org.ap.datastrutures.linkedlist.linkedlist;

//https://leetcode.com/problems/merge-two-sorted-lists/
public class Merge2SortedList {
    public ListNode merge2Lists(ListNode list1, ListNode list2) {
        ListNode result=null, end=null;
        // iterate thru both lists
        // if list1.node >= list2.node result = list2 list2 = list2.next
        // if list2.node > list1.node result = list1 list1 = list1.next
        // iterate thru remaining elements of list1 and list2 and add them to result
        while(list1!=null && list2 !=null) {
            if(list1.val >= list2.val) {
                if(result ==null) {
                    result = list2;
                } else {
                    end.next = list2;
                }
                end = list2;
                list2 = list2.next;

            } else if(list2.val > list1.val) {
                if(result ==null) {
                    result = list1;
                } else {
                    end.next = list1;
                }
                end = list1;
                list1 = list1.next;
            }
        }
        while(list1!=null){
            if(result ==null) {
                result = list1;
            } else {
                end.next = list1;
            }
            end = list1;
            list1 = list1.next;
        }
        while(list2!=null){
            if(result ==null) {
                result = list2;
            } else {
                end.next = list2;
            }
            end = list2;
            list2 = list2.next;
        }
        return result;
    }

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

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
