package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CousinsOfBT {
    public static List<Integer> cousinsOfBT(TreeNode root, TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean marker = false;
        while(!queue.isEmpty() && !marker){
            int levelSize = queue.size();
            for(int i=0; i<levelSize;i++){
                TreeNode curr = queue.poll();
                if(curr.left.int_val == node.int_val || curr.right.int_val == node.int_val)
                {
                    marker = true; //we have found the ans
                 //we dont need to push such element in the queue
                }else {
                    if (curr.left != null) queue.offer(curr.left);
                    if (curr.right != null) queue.offer(curr.right);
                }
            }
        }

        List<Integer> ans = new ArrayList();
        if(queue.isEmpty()) ans.add(-1);
        else{
            while(!queue.isEmpty()){
                ans.add(queue.poll().int_val);
            }
        }
        return ans;
    }
}
