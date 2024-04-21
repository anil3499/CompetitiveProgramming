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

public class LowestCommonAncestorInBinarySearchtree {
   
    public static int findLowestCommonAncestorForNodeN1N2(TreeNode root,int n1, int n2){
        //n1 and n2 both are lesser than root
        if(root.int_val<n1 && root.int_val<n2)
            return findLowestCommonAncestorForNodeN1N2(root.right,n1,n2);
        //n1 and n2 both are greater than root
        if(root.int_val>n1 && root.int_val>n2)
            return findLowestCommonAncestorForNodeN1N2(root.left,n1,n2);
        //n1 is lesser and n2 is greater than root
        return root.int_val;
    //space complexity hight of BST
        //time complexity O(n)
    }

    public static int findLowestCommonAncestorForNodeN1N2IterativeApproach(TreeNode root,int n1, int n2) {
        //n1 and n2 both are lesser than root
        while (root != null) {
            if (root.int_val < n1 && root.int_val < n2) {
                root = root.right;
            } else if (root.int_val > n1 && root.int_val > n2) {
                root = root.left;
            } else {
                break;
            }
        }
        return root.int_val;
    }
}
