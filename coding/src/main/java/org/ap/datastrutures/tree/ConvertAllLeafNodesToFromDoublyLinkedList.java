package org.ap.datastrutures.tree;

import org.ap.datastrutures.linkedlist.linkedlist.DoublyLinkedListNode;
import org.ap.utils.TreeNode;

public class ConvertAllLeafNodesToFromDoublyLinkedList {
    TreeNode solve(TreeNode root, DoublyLinkedListNode ans, DoublyLinkedListNode curr){
        if (root==null) return null;

        //if its a leaf node,means left and right both child are null
        if(root.left==null && root.right==null){
         if(ans==null){
             ans = new DoublyLinkedListNode(root.int_val);
             curr = ans;
         }else {
             curr.next = new DoublyLinkedListNode(root.int_val);
             curr.next.prev = curr;
             curr = curr.next;
         }
            return null;
        }
        root.left = solve(root.left,ans,curr);
        root.right = solve(root.right,ans,curr);
        return root;
    }
    DoublyLinkedListNode convertTODLL(TreeNode root){
        DoublyLinkedListNode ans = null;
        DoublyLinkedListNode curr = null;
        solve(root, ans, curr);
        return ans;
    }
}
