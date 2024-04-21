package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;
//https://www.youtube.com/watch?v=Hr5cWUld4vU
public class MaximumPathSumInBinaryTree {
    static int ans = Integer.MIN_VALUE;
    public static int findMaxPathSumForAnyNode(TreeNode root){
        if(root==null) return 0;
        int left =  findMaxPathSumForAnyNode(root.left);
        int right =  findMaxPathSumForAnyNode(root.right);
        int bothSum = root.int_val + left + right;
       
        ans = Math.max(ans,bothSum);

        return root.int_val + Math.max(left, right);

    }

    public static int findMaxPathSum(TreeNode root){
        if(root==null) return 0;
        int left =  findMaxPathSum(root.left);
        int right =  findMaxPathSum(root.right);
        int straightPath =  Math.max(root.int_val,Math.max(left + root.int_val,right + root.int_val));
        int curvedPath = root.int_val + left + right;

        ans = Math.max(ans,Math.max(straightPath,curvedPath));

        return straightPath;
        //here in this case we just need to return straight path

    }
}
