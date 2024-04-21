package org.ap.datastrutures.tree.binarytree;

/*
                        15
                      /    \
                    10      20
                  /   \    /  \
                8     12  16  25
 */
public class IsBinarySearchTree {

    public static boolean isBST(TreeNode<Integer> root)
    {
       if(root == null){
           return true;
       }
       if(root.left!=null && root.left.data > root.data){
           return false;
       }
       if(root.right!=null && root.right.data < root.data){
           return false;
       }
       return isBST(root.left) && isBST(root.right);
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(15);
        root.left = new TreeNode<>(10);
        root.right = new TreeNode<>(20);
        root.left.left = new TreeNode<>(8);
        root.left.right = new TreeNode<>(12);
        root.right.left = new TreeNode<>(16);
        root.right.right = new TreeNode<>(25);
        System.out.println( "is BST ? : " + isBST(root));
    }

}
