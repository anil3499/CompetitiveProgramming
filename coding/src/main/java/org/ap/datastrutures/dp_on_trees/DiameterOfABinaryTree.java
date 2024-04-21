package org.ap.datastrutures.dp_on_trees;/*
package ap.dynamicprogramming.dp_on_trees;

import static java.lang.Math.max;

    //
//every tree will have 3 part
//Base Conditionn
//Hypothesis
//Induction
public class DiameterOfABinaryTree {
    //2 leaf node k bich me including and between number of nodes called as diameter
        //Max we ned to retrun
        class Node {
            int value;
            Node left;
            Node right;

        }
        public int solve(Node root, int res){
            //base condition
            if (root ==null) return 0;

            //hypothesis
            int l = solve(root.left, res);
            int r = solve((root.right, res);

            //Induction  -- when a node want to be part of ans or dont want to be part of ans
            //3 things we need to nikalna
            // temp value, ans, and res
            // now whne a node is not root to give max path
            int temp = 1 + max(l,r);  //calculate temp ans , doing 1 + to add node itself
            // now whne a node is root to give max path
            int ans = max(temp,1+ l + r);
            res = max(res,ans);

            return temp;
        }

        public static void main(String[] args) {
            Node root = new Node();
            int res = new DiameterOfABinaryTree().solve(root,Integer.MIN_VALUE);
            System.out.println(res);
        }
    }

}
*/
