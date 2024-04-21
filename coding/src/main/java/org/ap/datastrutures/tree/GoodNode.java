package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

public class GoodNode {
    static int count = 0;
    public static int findGoodNodeCount(TreeNode root){
        if(root==null) return count;
        findGoodNodes(root,root.int_val);
        return count;
    }
    public static void findGoodNodes(TreeNode root,int maxForThisStep){
        if(root==null) return;
        if(root.int_val > maxForThisStep){
            count++;
            maxForThisStep = root.int_val;
        }
        findGoodNodes(root.left,maxForThisStep);
        findGoodNodes(root.right, maxForThisStep);
    }
}
