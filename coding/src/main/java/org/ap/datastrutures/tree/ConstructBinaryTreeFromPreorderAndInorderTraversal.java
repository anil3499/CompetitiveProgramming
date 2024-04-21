package org.ap.datastrutures.tree;/*
package org.ap.datastrutures.tree;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
        int preorder[];
        int curr;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            curr = 0;
            this.preorder = preorder;
            Map<Integer, Integer> positions = new HashMap<>();
            int n = inorder.length;
            for(int i = 0; i < n; i++)
                positions.put(inorder[i], i);
            TreeNode root = buildTree(positions, 0, n-1);
            return root;
        }

        private TreeNode buildTree(Map<Integer, Integer> positions, int start, int end){
            if(start > end) return null;

            TreeNode node = new TreeNode(preorder[curr]);
            int pos = positions.get(preorder[curr++]);
            node.left = buildTree(positions, start, pos-1);
            node.right = buildTree(positions, pos+1, end);
            return node;
        }
        */
/*
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(preorder[0]);
        int inorderIndex = 0;
        stack.push(root);

        for(int i = 1; i < preorder.length; i++){
            TreeNode prev = null;
            while(!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]){
                prev = stack.pop();
                inorderIndex++;
            }
            TreeNode toInsert = new TreeNode(preorder[i]);
            if(prev == null){
                prev = stack.peek();
                prev.left = toInsert;
            }
            else{
                prev.right = toInsert;
            }
            stack.push(toInsert);
        }
        return root;
    }
         *//*

}
*/
