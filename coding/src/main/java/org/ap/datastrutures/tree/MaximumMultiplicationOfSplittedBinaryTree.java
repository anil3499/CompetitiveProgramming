package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

public class MaximumMultiplicationOfSplittedBinaryTree {
    static Long ans = 0l;
    public static int findTotalSum(TreeNode root){
        if(root ==null) return 0;
        int leftSum = findTotalSum(root.left);
        int rightSum = findTotalSum(root.right);
        int sum = root.int_val+ leftSum+ rightSum;
        return sum;
    }
    public static Long maxProductOfSubtree(TreeNode root){
        int totalSum = findTotalSum(root);
         productMax(root,totalSum);
        return ans;//ans%10000000
    }
public static Long  productMax(TreeNode root,int totalSum) {
   if(root==null) return 0l;
   Long subTreeSum = 0l;
    subTreeSum += productMax(root.left,totalSum);
    subTreeSum += productMax(root.right,totalSum);
    subTreeSum += root.int_val;

    if(subTreeSum*(totalSum-subTreeSum)>ans){
        ans = subTreeSum*(totalSum-subTreeSum);
    }
    return subTreeSum;
}
}
