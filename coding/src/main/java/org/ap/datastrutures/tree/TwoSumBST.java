package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;

public class TwoSumBST {
    static HashSet<Integer> set = new HashSet<>();

    public static boolean is2NoPresentWhoseSumIsK(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(root.int_val - k)) return true;
        set.add(root.int_val);
        return is2NoPresentWhoseSumIsK(root.left, k) || is2NoPresentWhoseSumIsK(root.right, k);
    }
    static ArrayList<Integer> inorderOutput = new ArrayList<>();
    public static void inorder(TreeNode root){
        if(root!=null) return;
        inorder(root.left);
        inorderOutput.add(root.int_val);
        inorder(root.right);
    }
    public static boolean is2NoPresentWhoseSumIsK_2PonterMethod(TreeNode root, int k) {
        int left =0;
        int right = set.size()-1;
       while(left<right){
            if(left + right == k)
                return true;
            else if(left+right <k)
                left++;
            else
                right++;
        }
       return false;
    }

}