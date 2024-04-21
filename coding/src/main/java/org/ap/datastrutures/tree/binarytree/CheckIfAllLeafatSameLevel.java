package org.ap.datastrutures.tree.binarytree;

import java.util.concurrent.atomic.AtomicInteger;

  /* Construct below tree
		          1
		        /   \
		       /     \
		      2       3
		     / \     /
		    4   5   6
	    */

public class CheckIfAllLeafatSameLevel {

    private static boolean checkAllLevelAtSameLevel(TreeNode<Integer> root, AtomicInteger levelOfLeaf, int level) {
        if(root == null)
            return true;

        if(root.left==null && root.right==null){
            //set first leaf node level
            if(levelOfLeaf.get() == 0){
                levelOfLeaf.set(level);
                return true;
            }
            //comparing first leave node with all leaf;
             return levelOfLeaf.get() == level;
        }

        return checkAllLevelAtSameLevel(root.left,levelOfLeaf,level+1)
                && checkAllLevelAtSameLevel(root.right,levelOfLeaf,level+1);
    }

    public static void main(String[] args) {
        AtomicInteger levelOfLeaf=new AtomicInteger(0);
        int level=1;
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.right.left = new TreeNode<>(6);
        System.out.println("CheckIfAllLeafatSameLevel : " + checkAllLevelAtSameLevel(root,levelOfLeaf,level));
    }


}
