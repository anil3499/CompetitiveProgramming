package org.ap.datastrutures.tree.binarytree;
//https://leetcode.com/problems/binary-tree-vertical-order-traversal/
/* Construct below tree
		          1
		        /   \
		       /     \
		      2       3
		            /   \
		           /     \
		          5       6
		        /   \
		       /     \
		      7       8
		            /   \
		           /     \
		          9      10

root =0
left=root-1
right=root+1
Horizontal distance :
-1 : [2,7]
0 : [1,5,9]
1 : [3,8]
2 : [10,6]
node with same HD will be counted in same vertical
*/
//Recursive code will not work for this use case
import org.javatuples.Triplet;

import java.util.*;

public class VerticalOrderTraversal_HD {
    public static void insertIntoMultiMap(Map<Integer,List<Integer>> map, Integer key, Integer value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }
    public static void main(String[] args)
    {
        /*TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        root.right.left = new TreeNode<>(5);
        root.right.right = new TreeNode<>(6);
        root.right.left.left = new TreeNode<>(7);
        root.right.left.right = new TreeNode<>(8);
        root.right.left.right.left = new TreeNode<>(9);
        root.right.left.right.right = new TreeNode<>(10);*/
        TreeNode<Integer> root = new TreeNode<>(3);
        root.left = new TreeNode<>(9);
        root.right = new TreeNode<>(8);
        root.right.left = new TreeNode<>(1);
        root.right.right = new TreeNode<>(7);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(0);
        root.left.right.left = new TreeNode<>(5);
        root.right.left.right = new TreeNode<>(2);
        printVertical(root);
        System.out.println(getVerticalOrder(root));

    }
    private static List<List<Integer>> getVerticalOrder(TreeNode<Integer> root) {
        TreeMap<Integer,List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(Pair.of(root,0));
        while(!queue.isEmpty()) {
            Pair<TreeNode<Integer> , Integer> pair = queue.poll();
            TreeNode<Integer> node = pair.first;
            Integer hdist = pair.second;
            insertIntoMultiMap(map, hdist, node.data);
            if(node.left!=null) {
                queue.offer(Pair.of(node.left,hdist-1));
            }
            if(node.right!=null) {
                queue.offer(Pair.of(node.right,hdist+1));
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        map.entrySet().forEach(entry -> result.add(entry.getValue()));
        return result;
    }

    /* recursive code wont work...*/
    private static void printVertical(TreeNode<Integer> root, int hdist, Map<Integer, List<Integer>> map) {
        if(root==null){
            return;
        }
        insertIntoMultiMap(map,hdist,root.data);
        printVertical(root.left,hdist-1,map);
        printVertical(root.right,hdist+1,map);
    }

    private static void printVertical(TreeNode<Integer> root) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        printVertical(root, 0, map);
        map.forEach((k, v) -> System.out.println(k + "-->" + v.toString()));
    }
}
