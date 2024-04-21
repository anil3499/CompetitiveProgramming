package org.ap.datastrutures.dp.tree;

import java.util.concurrent.atomic.AtomicInteger;

/*
https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/


 */

public class MaximumPathSumAnyNodeToAnyNode {

    //using DP
    static int  diameter(Node root, AtomicInteger result)
    {
        /* base case if tree is empty */
        if (root == null)
            return 0;

        /* get the diameter of left and right subtrees */
        int ldiameter = diameter(root.left,result);
        int rdiameter = diameter(root.right,result);

        //if diameter does not go thro current node
        int temp = Math.max(Math.max(ldiameter,rdiameter) + root.data, root.data);
        //if diameter does  go thro current node
        int ans =Math.max(temp, root.data + ldiameter + rdiameter);
        result.set(Math.max(result.get(),ans));
        return temp; //diameter does not go thro current node

    }

    public static int getDiameter(Node root)
    {
        AtomicInteger diameter = new AtomicInteger(0);
        diameter(root, diameter);

        return diameter.get();
    }


    //using recursion
    public static int findMaxPathSum(Node node, AtomicInteger result)
    {

        if (node == null) {
            return 0;
        }

        int left = findMaxPathSum(node.left, result);
        int right = findMaxPathSum(node.right, result);
        int max = result.get();
        // Try all possible combinations to get the optimal result
        max = Integer.max(max, node.data);
        max = Integer.max(max, node.data + left);
        max = Integer.max(max, node.data + right);
        max = Integer.max(max, node.data + left + right);
        result.set(max);
        // return the maximum path sum "starting" from the given node
        return Integer.max(node.data, node.data + Integer.max(left, right));
    }

    public static void main(String[] args) {
        /* creating a binary tree and entering the nodes */
        int result=Integer.MIN_VALUE;
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println("The diameter of given binary tree is : "
                + getDiameter(root));
    }
}
