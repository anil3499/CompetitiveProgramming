package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

import java.util.HashMap;

public class MaxSumOfNonAdjecentNodesInBT {
    //with recus=rsive approach
    int getMaxSum(TreeNode root){
        if(root==null) return 0;
        //case 1 with root node
        int withRootNode = root.int_val;
        if(root.left!=null) {
            withRootNode += getMaxSum(root.left.left);
            withRootNode += getMaxSum(root.left.right);
        }
        if(root.right!=null) {
            withRootNode += getMaxSum(root.right.left);
            withRootNode += getMaxSum(root.right.right);
        }
        //case2
        //without root Node
        int withoutRootNode = 0;
        withoutRootNode = getMaxSum(root.left) + getMaxSum(root.right);

        return Math.max(withRootNode,withoutRootNode);
    }

    HashMap<TreeNode,Integer> map = new HashMap<>();
    int getMaxSumOptimized(TreeNode root){
        if(root==null) return 0;
        //optimization lines
        if(map.containsKey(root))
            return map.get(root);

        //case 1 with root node
        int withRootNode = root.int_val;
        if(root.left!=null) {
            withRootNode += getMaxSumOptimized(root.left.left);
            withRootNode += getMaxSumOptimized(root.left.right);
        }
        if(root.right!=null) {
            withRootNode += getMaxSumOptimized(root.right.left);
            withRootNode += getMaxSumOptimized(root.right.right);
        }
        //case2
        //without root Node
        int withoutRootNode = 0;
        withoutRootNode = getMaxSumOptimized(root.left) + getMaxSumOptimized(root.right);

        map.put(root,Math.max(withRootNode,withoutRootNode));
        return map.get(root);
    }
}
