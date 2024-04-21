package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

public class DiameterOfBt {
    static int diameterMax = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        findDiameterOfBT(root);
        return diameterMax;
    }
    public static int findDiameterOfBT(TreeNode root){
        if(root==null) return 0;
        int leftHight = findDiameterOfBT(root.left);
        int rightHieght = findDiameterOfBT(root.right);
        int hight =  1 + Math.max(leftHight,rightHieght);
        diameterMax = Math.max(diameterMax ,leftHight + rightHieght);
        return hight;
    }
    public static int hightofBT(TreeNode root){
        if(root == null) return 0;
        int leftHight = hightofBT(root.left);
        int rightHieght = hightofBT(root.right);
        return 1+ Math.max(leftHight,rightHieght);
    }
    public static int diameterofBT(TreeNode root){
        if(root==null) return 0;
        int leftHight = hightofBT(root.left);
        int righthieght = hightofBT(root.right);

        int leftDiameter = diameterofBT(root.left);
        int rightDiameter = diameterofBT(root.right);

        return Math.max(1+leftHight+righthieght , Math.max(leftDiameter,rightDiameter));
    }
}
