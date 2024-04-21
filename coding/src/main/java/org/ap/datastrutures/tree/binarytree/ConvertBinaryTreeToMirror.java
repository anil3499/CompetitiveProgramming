package org.ap.datastrutures.tree.binarytree;


import org.ap.datastrutures.tree.binarysearchtree.BinarySearchTree;

/*
Input:
        5
       / \
      3   6
     / \
    2   4
Output:
Inorder of original tree: 2 3 4 5 6
Inorder of mirror tree: 6 5 4 3 2
Mirror tree will be:
  5
 / \
6   3
   / \
  4   2
 */
public class ConvertBinaryTreeToMirror {


    public static TreeNode<Integer> convertToMirror(TreeNode<Integer> root) {
       if(root ==null){
           return null;
       }
        TreeNode<Integer> mirror = new TreeNode<>(root.data);
        mirror.right=convertToMirror(root.left);
        mirror.left=convertToMirror(root.right);
       return mirror;
    }

    public static void main(String[] args)
    {

        BinarySearchTree binarySearchTree=new BinarySearchTree();

        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(7);
        binarySearchTree.inorderTraversalRecursion(root);
        TreeNode<Integer> mirror=convertToMirror(root);
        System.out.println();
        binarySearchTree.inorderTraversalRecursion(mirror);
    }


}
