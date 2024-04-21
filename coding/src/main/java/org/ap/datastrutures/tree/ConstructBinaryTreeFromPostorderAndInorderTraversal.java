package org.ap.datastrutures.tree;/*
package org.ap.datastrutures.tree;

public class ConstructBinaryTreeFromPostorderAndInorderTraversal {
 public TreeNode buildTree(int[] preorder, int[] inorder) {
     ArrayDeque<TreeNode> stack = new ArrayDeque<>();
     int n = postorder.length;
     int inorderIndex = n-1;
     TreeNode root = new TreeNode(postorder[n-1]);
     stack.push(root);

     for(int i = n-2; i >= 0; i--){
         TreeNode prev = null;
         while(!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]){
             prev = stack.pop();
             inorderIndex--;
         }

         TreeNode toInsert = new TreeNode(postorder[i]);
         if(prev == null){
             prev = stack.peek();
             prev.right = toInsert;
         }
         else{
             prev.left = toInsert;
         }
         stack.push(toInsert);
     }
     return root;
 }*/
/* Recursive

class Solution {
    int postorder[], inorder[];
    int curr;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        int n = inorder.length;
        this.curr = n-1;

        Map<Integer, Integer> positions = new HashMap<>();
        for(int i = 0; i < n; i++)
            positions.put(inorder[i], i);

        TreeNode root = buildTree(positions, 0, n-1);
        return root;
    }

    private TreeNode buildTree(Map<Integer, Integer> positions, int start, int end){
        if(start > end) return null;

        int pos = positions.get(postorder[curr]);
        TreeNode root = new TreeNode(postorder[curr--]);

        root.right = buildTree(positions, pos+1, end);
        root.left = buildTree(positions, start, pos-1);

        return root;
    }
}
*//*

}
*/
