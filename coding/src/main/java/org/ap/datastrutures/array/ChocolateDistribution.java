package org.ap.datastrutures.array;

import java.util.Arrays;

/*
* Given an array A[ ] of positive integers of size N, where each value represents the number of chocolates in a packet.
* Each packet can have a variable number of chocolates.
* There are M students, the task is to distribute chocolate packets among M students such that :
* 1. Each student gets exactly one packet.
* 2. The difference between maximum number of chocolates given to a student and minimum number of chocolates given to a student is minimum.
* Input: N = 8, M = 5 A = {3, 4, 1, 9, 56, 7, 9, 12}
* Output: 6
 * Explanation: The minimum difference between maximum chocolates and minimum chocolates is 9 - 3 = 6
 * by choosing following M packets :{3, 4, 9, 7, 9}.
* Example :[2,5,7,1,3] and students : 3
* if gives 5,7,3 -> diff of 7-3 = 4 -> not correct
* if gives 1,2,3 -> diff of 3-1 = 2 -> correct
*
*
 */
public class ChocolateDistribution {
    /**
    * logic :
    * 1. sort the element
    * 2. take 2 pointer and move second one to no. of student
    * 3. keep track of min diff b/w first and second pointer
    **/
    //first we need to create groups of student and distribute the chocolate among them
    //we need to return min diff between maximum and minimmum of every group
    public static int chocolateFistribution(int[] packates, int students){
        int n = packates.length;
        int [] chocolates = new int[n];
        //we wil sort array first
        Arrays.sort(packates);
        //create group
        int start = 0;
        int end = start + students -1;
        int minDiff = Integer.MAX_VALUE;
        while(end < n) {
            minDiff = Math.min(minDiff,packates[end] - packates[start]);
            start++;
            end++;
        }
        return minDiff;

    }

    public static void main(String[] args) {
        int[] packates =  {3, 4, 1, 9, 56, 7, 9, 12};
        System.out.println(chocolateFistribution(packates,5));
        System.out.println(chocolateFistribution(new int[]{3, 4, 9, 7, 9},3));
    }
}
/*
*gfg - https://practice.geeksforgeeks.org/problems/chocolate-distribution-problem/0
* */