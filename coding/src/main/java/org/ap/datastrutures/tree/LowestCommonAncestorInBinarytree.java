package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

//case 1
//both n1 and n are in same path means, parent child

//case 2
//n1 in left subtree and n2 in right subtree
// i.e. both left and right are saying we have n1 and n2 then root is the answer
//case3
//n1 presnt in left subtree but n2 doesnt present in right subtree
// one is null(could not found value) and another is not null(found value) then return the node which is not null
//case4
//left is not null(found n1)  and right is not null(found n2) then return root...

public class LowestCommonAncestorInBinarytree {

    public static TreeNode findLowestCommonAncestorForNodeN1N2(TreeNode root,int n1, int n2){
        if(root ==null)
            return null;
        //if found n1 or n2
        if(root.int_val == n1 || root.int_val ==n2)
            return root;
        //search in left and right subtree
        TreeNode left = findLowestCommonAncestorForNodeN1N2(root.left,n1,n2);
        TreeNode right = findLowestCommonAncestorForNodeN1N2(root.right,n1,n2);

        //if noth are not null means we have found our answer
        if (left!=null && right!=null)
            return root;
        //if result found in left but not in right
        else if (left ==null)
            return left;
        //if result found in right but not in left
        else //if(right==null)
            return right;
    }
}
