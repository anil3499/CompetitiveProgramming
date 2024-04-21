package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

public class InsertIntoBST {
    public static TreeNode insertIntoBST(TreeNode root,int n){
        TreeNode node =  new TreeNode();
        node.int_val = n;

        return insertNode(root,node);
    }
    public static TreeNode insertNode(TreeNode root, TreeNode node){
        if(root==null) return node;
        else if(node.int_val > root.int_val)
            root.right = insertNode(root.right,node);
        else
            root.left = insertNode(root.left,node);
        return root;
    }

}
