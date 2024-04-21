package org.ap.datastrutures.tree.binarytree;
/*
https://www.techiedelight.com/check-given-binary-tree-symmetric-structure-not/
Given a binary tree, check whether it is a mirror of itself.
For example, this binary tree is symmetric:

     1
   /   \
  2     2
 / \   / \
3   4 4   3

But the following is not:
    1
   / \
  2   2
   \   \
   3    3

Logic :
Same as

 */
public class IsSymmetricTree {
    static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
    public static boolean isSymmetric(Node X, Node Y) {

        if (X == null && Y == null) {
            return true;
        }

        // return true if
        // 1. Both trees are non-empty, and
        // 2. The left subtree is the mirror of the right subtree, and
        // 3. The right subtree is the mirror of the left subtree
        return (X != null && Y != null) &&
                isSymmetric(X.left, Y.right) &&
                isSymmetric(X.right, Y.left);
    }

    public static boolean isSymmetric(Node root) {
        return isSymmetric(root.left, root.right);
    }

    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);

        if (isSymmetric(root)) {
            System.out.print("The binary tree is symmetric");
        } else {
            System.out.print("The binary tree is not symmetric");
        }
    }
}
