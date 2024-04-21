package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

public class ValidateBST {
    public static boolean validateBST(TreeNode root){
        return bst(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    public static boolean bst(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if(root.int_val<=min && root.int_val >= max) return false;
        return bst(root.left,min,root.int_val) && bst(root.right,root.int_val,max);
    }

}
