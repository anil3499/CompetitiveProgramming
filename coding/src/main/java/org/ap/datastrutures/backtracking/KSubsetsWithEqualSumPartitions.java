package org.ap.datastrutures.backtracking;

import java.util.ArrayList;
import java.util.Arrays;

/*
*Given an integer array nums and an integer k,
*  return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:

Input: nums = [4,3,2,3,5,2,1], k = 4
Output: true
Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Example 2:

Input: nums = [1,2,3,4], k = 3
Output: false

* */
public class KSubsetsWithEqualSumPartitions {
    static ArrayList<ArrayList<Integer>>  ans = new ArrayList<>();

    public static boolean isPossibleToDivideArrayInKNonEmptySubsetsWhoseSumsAreAllEqual(int[] arr, int k) {

        int sum = Arrays.stream(arr).sum();

        if(k==1) //if k=1 then whole array can be in one partition
            return true;

        int n = arr.length;
        if(k > n)  //can divide array in more partition than it has the elements
            return false;

        if(sum % k != 0) // if sum is not multiple of k then its not possible, if k partition then each partition's sum will be sum/k
            return false;

        int[] subsetSum = new int[k]; //each index will contain corrosponding subset sum
        //add subset as empty array list in subsets
        ArrayList<ArrayList<Integer>>  tempans = new ArrayList<>();

        for(int i=0; i<k; i++)
            tempans.add(new ArrayList<>());

        solve(arr,0,k,subsetSum,0,tempans);
        System.out.println("Print answers....");
        for(ArrayList<Integer> partition: ans) {
            System.out.println(partition);
        }
        if(ans.isEmpty()) return false;
        return true;
    }
    //sssfor represents how many non empty sets we have created
    public static void solve(int[] arr, int index, int k, int[] subsetSum, int sssfor,  ArrayList<ArrayList<Integer>>  tempans){
        //every element has 2 choice either get added into empty set or get addedinto existing set
        if(index == arr.length) {
            if (sssfor == k) { //it means we have equal number of partitions
                //now we need to check all subset sum are equals or not
                boolean flag = true;
                for (int i = 0; i < subsetSum.length - 1; i++) {
                    if (subsetSum[i] != subsetSum[i + 1]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) { //to print the partitions
                    for (ArrayList<Integer> partition : tempans) {
                        ArrayList<Integer> t = new ArrayList<>(partition);
                        if(ans.size()!=k) //to avoid duplicates
                            ans.add(t);
                        //System.out.println(partition);
                    }
                }
            }
            return;
        }
        for(int i=0;  i < tempans.size(); i++) {
            if(tempans.get(i).size() > 0) {
                tempans.get(i).add(arr[index]);
                subsetSum[i] += arr[index];
                //here we are not increasing ssfor because we are addding element in old exisitng set not adding in empty set
                solve(arr, index+1, k, subsetSum,sssfor,tempans);
                //ans.get(i).remove(arr[index]);
                tempans.get(i).remove(tempans.get(i).size()-1); //remove from index
                subsetSum[i] -= arr[index];
            } else {
                tempans.get(i).add(arr[index]);
                subsetSum[i] += arr[index];
                //here we are not increasing ssfor because we are addding element in old exisitng set not adding in empty set
                solve(arr, index +1, k, subsetSum,sssfor + 1,tempans);
                //ans.get(i).remove(arr[index]);
                tempans.get(i).remove(tempans.get(i).size()-1); //remove from index
                subsetSum[i] -= arr[index];
                break;
            }

        }
    }

    public static void main(String[] args) {
        System.out.println(isPossibleToDivideArrayInKNonEmptySubsetsWhoseSumsAreAllEqual(new int[] {4,3,2,3,5,2,1}, 4));
        System.out.println(isPossibleToDivideArrayInKNonEmptySubsetsWhoseSumsAreAllEqual(new int[] {1,2,3,4}, 3));
    }
}
/*
* leetcode- https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
* youtube - https://www.youtube.com/watch?v=rszwy53vaP0
 */