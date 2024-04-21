package org.ap.datastrutures.tree;

import org.ap.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLArgestValueInEachLevelOfBT {
    public static List<Integer> findLargestValueInEachLevel(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            ans.add(-1);
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxValueOfLevel = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                maxValueOfLevel = Math.max(maxValueOfLevel, curr.int_val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            ans.add(maxValueOfLevel);
        }
        return ans;
    }

    static List<Integer> answer = new ArrayList<>();

    public static List<Integer> findLargestValueInEachLevelRecursive(TreeNode root) {
        traversal(root, 0); //0 is level 0 where root presents
        return answer;
    }

    public static void traversal(TreeNode root, int level) {
        if(root==null) return;
        if(level < answer.size()) {
            //then we need to compare existing ans value with root data ans store max
            answer.add(level ,Math.max(answer.get(level), root.int_val));
        }else {
            answer.add(level,root.int_val);
        }
        traversal(root.left,level+1);
        traversal(root.right,level+1);
    }
}

