package org.ap.datastrutures.dp_on_trees;/*
package ap.dynamicprogramming.dp_on_trees;

import static java.lang.Math.max;
//starting and ending node will be any  node

public class MaximumPathSumFromAnyNodeToAny {
    class Node {
        int value;
        DiameterOfABinaryTree.Node left;
        DiameterOfABinaryTree.Node right;

    }

    public int max_path_sum(Node root, int res){
        //base condition
        if (root ==null) return 0;

        //hypothesis
        int l = max_path_sum(root.left, res);
        int r = max_path_sum((root.right, res);

        //Induction  -- when a node want to be part of ans or dont want to be part of ans
        //3 things we need to nikalna
        // temp value, ans, and res
        // now whne a node is not root to give max path
        int temp = max(max(l,r)+root.value,root.value);
        //calculate temp ans , doing 1 + to add node itself
        // now  whne a node is root to give max path
        int ans = max(temp, 1+ l + root.value);

        res = max(res,ans);

        return ans;
    }
    public static void main(String[] args) {
        Node root = new Node();
        int res = new MaximumPathSumFromAnyNodeToAny().max_path_sum(root,Integer.MIN_VALUE);
        System.out.println(res);
    }
    }
*/
