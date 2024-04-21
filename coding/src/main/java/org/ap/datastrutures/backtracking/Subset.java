package org.ap.datastrutures.backtracking;

import java.util.LinkedList;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 * */
public class Subset {

    public static void printSubsetArray(int[] arr) {

        /**
         * There is an easy way too
         * we knows number of subset will get created will be equal to 2 to power n where n is no of elements in array
         * [1,2,3]
         * s to pwer 3 = 8 subset can be created
         *  for(int i =0; i< (2powern); i++){
         *      i== 0 {}
         *      i==1 1  {1}
         *      i=2  10 {2}
         *      i=3  011 {1,2}
         *      i=4   111  {1,2,3}
         *      need to take element at corrosponding index
         *  }
         * */
        for(int i=0; i< Math.pow(2,arr.length); i++) {
            String binary = Integer.toBinaryString(i);
            LinkedList<Integer> subset = new LinkedList<>();
            int k=0;
            for(int j= binary.length()-1; j>=0; j--){
                if(binary.charAt(j) == '1') {
                    subset.add(arr[k]);
                }
                k++;
            }
            System.out.println(subset);
        }
    }
    public static void printSubsetArray(int[] ip, int index, LinkedList<Integer> op) {
        //using backtracking
        if(index >= ip.length){
            System.out.println(op);
            return;
        }
        op.addLast(ip[index]);
        printSubsetArray(ip,index+1, op);
        op.removeLast();
        printSubsetArray(ip,index+1,op);
    }


    public static void printSubsetsOfString(String ip, String op) {
        //using recursion
        if(ip.length()==0) {
            System.out.println(op);
            return;
        }
        String op1 = op;
        String op2 = op + ip.charAt(0);
        ip = ip.substring(1,ip.length()); //remove 0th index from input
        printSubsetsOfString(ip,op1);
        printSubsetsOfString(ip,op2);
    }

    public static void main(String[] args) {
        //printSubsetsOfString("abc","");

        printSubsetArray(new int[]{1,2,3},0, new LinkedList<>());
        //printSubsetArray(new int[]{1,2,3});
    }
}
/**
 * Leetcode - https://leetcode.com/problems/subsets/
 * Youtube recursion - https://www.youtube.com/watch?v=lfFqW1DTsqM
 * binary way - https://www.youtube.com/watch?v=h4zNvA4lbtc&t=450s
 * backtracking https://www.youtube.com/watch?v=REOH22Xwdkk
 * */