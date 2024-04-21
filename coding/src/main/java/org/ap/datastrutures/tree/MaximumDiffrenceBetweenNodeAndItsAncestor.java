package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

public class MaximumDiffrenceBetweenNodeAndItsAncestor {
    static Integer  ans = Integer.MIN_VALUE;
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        findMaxFiffBetweenNodeAndItsAncestor(root);
        System.out.println(ans);
    }
    public static int findMaxFiffBetweenNodeAndItsAncestor(TreeNode root){
        if(root == null) {
            return Integer.MAX_VALUE;
        }
        int left = findMaxFiffBetweenNodeAndItsAncestor(root.left);
        int right = findMaxFiffBetweenNodeAndItsAncestor(root.right);

        //Calculate ans
        //your left child may have return something better
        //your right child may have return something better
        //so we will return maximum of curr ans, left childs ans, and right childs ans
        //and change the global ans
        ans = Math.max(ans,Math.max(root.int_val - left, root.int_val) - right);

        // we have to return to parent node
        //minimum of current value, left value and right value
        return Math.min(root.int_val,Math.min(left,right));
    }
}
