package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;
//https://leetcode.com/problems/invert-binary-tree/description/
//https://www.youtube.com/watch?v=_i0jqdVkObU
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if(root ==null)
            return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
