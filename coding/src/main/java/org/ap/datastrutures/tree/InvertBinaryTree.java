package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

public class InvertBinaryTree {
    public static TreeNode invertBT(TreeNode root){
        if(root==null) return null;
        TreeNode left = invertBT(root.left);
        TreeNode right = invertBT(root.right);
        root.right=left;
        root.left=right;
        return root;

    }
    public static TreeNode invertBinaryTree(TreeNode root){
        if(root==null) return null;
        invertBT(root.left);
        invertBT(root.right);
        TreeNode temp = root.right;
        root.right=root.left;
        root.left=temp;
        return root;

    }
}
