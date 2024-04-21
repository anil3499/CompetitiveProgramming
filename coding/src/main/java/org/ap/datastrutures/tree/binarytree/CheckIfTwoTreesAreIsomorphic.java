package org.ap.datastrutures.tree.binarytree;
/*
Two trees are called isomorphic if one of them can be obtained from other by a series of flips,
i.e. by swapping left and right children of a number of nodes.
Any number of nodes at any level can have their children swapped.
Two empty trees are isomorphic.
 */
public class CheckIfTwoTreesAreIsomorphic {

    private static boolean areIsomorphic(TreeNode<Integer> a, TreeNode<Integer> b) {
        if(a == null && b == null ){
            return true;
        }
        if(a == null || b == null ){
            return false;
        }
        if(a.data!=b.data){
            return false;
        }
        return areIsomorphic(a.left,b.left) && areIsomorphic(a.right , b.right)
                || areIsomorphic(a.left,b.right) && areIsomorphic(a.right , b.left);
    }
    public static void main(String[] args) {
        TreeNode<Integer> a = new TreeNode<>(1);
        a.left = new TreeNode<>(2);
        a.right = new TreeNode<>(3);
        a.left.left = new TreeNode<>(4);
        a.left.right = new TreeNode<>(5);

        TreeNode<Integer> b = new TreeNode<>(1);
        b.left = new TreeNode<>(3);
        b.right = new TreeNode<>(2);
        b.right.left = new TreeNode<>(5);
        b.right.right = new TreeNode<>(4);

        if (areIsomorphic(a, b) == true)
            System.out.println("Yes");
        else
            System.out.println("No");

    }
}
