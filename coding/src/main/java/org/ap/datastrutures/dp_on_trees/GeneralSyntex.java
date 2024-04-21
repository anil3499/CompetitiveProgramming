package org.ap.datastrutures.dp_on_trees;/*
package ap.dynamicprogramming.dp_on_trees;

import static java.lang.Math.max;

//
//every tree will have 3 part
//Base Conditionn
//Hypothesis
//Induction
public class GeneralSyntex {
    class Node {
        int value;
        Node left;
        Node right;

    }
    public int solve(Node root,int res){
        //base condition
        if (root ==null) return 0;

        //hypothesis
        int l = solve(root.left, res);
        int r = solve((root.right, res);

        //Induction  -- when a node want to be part of ans or dont want to be part of ans
        int temp = 1 + max(l,r);  //calculate temp ans
        int ans = max(temp,relation);
        res = max(res,ans);

        return temp;
    }

    public static void main(String[] args) {
        Node root = new Node();
        int res = new GeneralSyntex().solve(root,Integer.MAX_VALUE);
        System.out.println(res);
    }
}
*/
