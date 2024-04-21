package org.ap.datastrutures.tree.binarytree;

/*
https://java2blog.com/count-subtree-sum-equal-target-binary-tree/
Input :
             5
           /   \
        -10     3
        /  \   /  \
       9    8 -4   7

       x = 7

Output : 2
There are 2 subtrees with sum 7.

1st one is leaf node:
7.

2nd one is:

      -10
     /   \
    9     8

 */
public class CountSubtreeWithSumEqualToTargetSum {
    public static class Node {
        int data;   // Data of current Node.
        Node left;  // Pointer to left child Node.
        Node right; // Pointer to right child Node.

        public Node(int data) {
            this.data = data;
        }
    }

    public static class pair {
        int count;
        int sum;

        public pair(int count, int sum) {
            this.count = count;
            this.sum = sum;
        }
    }



    public static pair solve(Node node, int target)
    {
        if(node == null)
        {
            return new pair(0, 0);
        }

        pair left = solve(node.left, target);
        pair right = solve(node.right, target);

        int sum = left.sum + right.sum + node.data;
        int count = left.count + right.count;
        if(sum==target)
        {
            count++;
        }
        return new pair(count, sum);
    }

    public static void main(String[] args) {

        Node root =  new Node(4);
        root. left = new Node(5);
        root. right = new Node(-2);
        root. left. left = new Node(-1);
        root. left. right = new Node(2);
        root. right. right = new Node(5);
        root. right. left = new Node(8);
        root. right. left. left = new Node(7);
        root. right. left. right = new Node(-9);

        System.out.println(solve(root, 6).count);

    }

}
