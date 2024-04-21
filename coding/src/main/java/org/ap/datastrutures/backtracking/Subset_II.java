package org.ap.datastrutures.backtracking;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * */
public class Subset_II {
    public static void printSubsetArray(int[] ip, int index, LinkedList<Integer> op) {
        //using backtracking
        if(index >= ip.length){
            System.out.println(op);
            return;
        }
        //All subsets that includes the arr[i]
        op.addLast(ip[index]);
        printSubsetArray(ip,index+1, op);

        //All subsets that does not includes the arr[i]
        op.removeLast();

        //below is special case to ignore duplicates in case we have duplicate elemtn in arr
        while (index+1<ip.length && ip[index] == ip[index+1]) {
            index += 1;
        }

        printSubsetArray(ip,index+1,op);
    }

    public static void main(String[] args) {
        int [] arr = new int[]{1,2,2,3};
        Arrays.sort(arr); //we have to sort to we can skip duplicates
        printSubsetArray(arr,0,new LinkedList<Integer>());
    }

}

/**
 * Leetcode - https://leetcode.com/problems/subsets-ii/
 *youtube - https://www.youtube.com/watch?v=Vn2v6ajA7U0
 * */