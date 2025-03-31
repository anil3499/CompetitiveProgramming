package org.ap.leetcode.Dec2023_1_to_70;


//https://leetcode.com/problems/merge-k-sorted-lists/description/
public class MergeKSortedLinkedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        return partitionList(lists,0,lists.length-1);
    }
    public ListNode partitionList(ListNode[] lists, int left, int right) {
        if(right-left +1 <= 2) {
            //System.out.println(left +" " + right);
            ListNode leftNode = null;
            ListNode rightNode = null;
            if(left>=0 && left <lists.length)
                leftNode =lists[left];
            if(right >= 0 && right <lists.length)
                rightNode =lists[right];
            if(left==right)
                rightNode = null;
            return mergeSortedLists(leftNode, rightNode);
        }
        int mid = left + (right - left)/2;
        ListNode list1 = partitionList(lists, left, mid);
        ListNode list2 = partitionList(lists, mid+1, right);
        return mergeSortedLists(list1,list2);
    }
    public ListNode mergeSortedLists(ListNode list1, ListNode list2) {
        ListNode root =null;
        ListNode prev = null;
        if(root==null) {
            if(list1==null && list2==null){
                root=null;
            }else if(list1==null) {
                root = list2;
                list2=list2.next;
            }else if(list2==null){
                root= list1;
                list1=list1.next;
            } else if(list1.val<=list2.val){
                root= list1;
                list1=list1.next;
            }else{
                root= list2;
                list2=list2.next;
            }
            prev = root;
        }
        while(list1!=null && list2!=null) {
            if(list1.val <= list2.val){
                prev.next= list1;
                list1= list1.next;
            } else {
                prev.next= list2;
                list2= list2.next;
            }
            prev = prev.next;
        }
        while(list1!=null) {
            prev.next= list1;
            prev = prev.next;
            list1= list1.next;
        }
        while(list2!=null){
            prev.next= list2;
            prev = prev.next;
            list2= list2.next;
        }
        return root;
    }
}
