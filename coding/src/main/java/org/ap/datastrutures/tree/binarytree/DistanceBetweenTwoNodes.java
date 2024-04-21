package org.ap.datastrutures.tree.binarytree;

/*
https://www.geeksforgeeks.org/find-distance-between-two-nodes-of-a-binary-tree/
		          1
		        /   \
		       /     \
		      2       3
		            /   \
		           /     \
		          5       6
		        /   \       \
		       /     \       \
		      7       8      11
		            /   \
		           /     \
		          9      10

1. find the LCA of given node
2. Find level for both node from LCA as root
3. sum the level and return

 */

public class  DistanceBetweenTwoNodes {

    private static TreeNode<Integer> findLCA(TreeNode<Integer> root, TreeNode<Integer> p1, TreeNode<Integer> p2){
        if(p1.data < root.data && p2.data < root.data){
            return findLCA(root.left,p1,p2);
        }else if((p1.data > root.data && p2.data > root.data)){
            return findLCA(root.right,p1,p2);
        }else{
            return root;
        }
    }

    private static int findLevel(TreeNode<Integer> root, TreeNode<Integer> treeNodeTofindLevel, int level){
        if(root == null){
            return -1;
        }
        if(root == treeNodeTofindLevel){
            return level;
        }
        int result=findLevel(root.left, treeNodeTofindLevel,level+1);
        if(result != -1){
            return result;
        }
        result=findLevel(root.right, treeNodeTofindLevel,level+1);
        return result;
    }

    private static int findDistanceBetweenTwoNodes(TreeNode<Integer> root, TreeNode<Integer> p1, TreeNode<Integer> p2) {
      TreeNode<Integer> lca=findLCA(root,p1,p2);
      int dis1=findLevel(lca,p1,0);
      int dis2=findLevel(lca,p2,0);
      return dis1+dis2;

    }

    public static void main(String[] args) {

        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        root.right.left = new TreeNode<>(5);
        root.right.right = new TreeNode<>(6);
        root.right.left.left = new TreeNode<>(7);
        root.right.left.right = new TreeNode<>(8);
        root.right.left.right.left = new TreeNode<>(9);
        root.right.left.right.right = new TreeNode<>(10);
        root.right.right.right=new TreeNode<>(11);
        System.out.println("DistanceBetweenTwoNodes : " + findDistanceBetweenTwoNodes(root,root.left,root.right.right.right));
    }



}
