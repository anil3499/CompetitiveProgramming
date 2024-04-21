package org.ap.datastrutures.array;

/*
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 */

public class MajorityElement {
    /*Numbers in array  will be always between 1 to n
    * There must be an element which occured more thanequal n/2 times
    * there are 2 steps to do this
    * step1- finding the candidaate for majority element
    * step 2- verify wether tha t candidate element is a majority element or not
    *
    * //Boyer-Moore Majority Voting Algorithm
    * what we do here is
    * we cancel one element from another element
    * as
    * */
    public int majority_elements(int[] arr) {
        int count = 0;
        int candidate = -1;
        for (int i = 0; i < arr.length; i++) {

            //asume 1st element is majority element and countwilll become 1

            if (count == 0) {
                candidate = arr[i];
                count = 1;
            } else {
                //if element is notequa to candidate then we will increase else decrease the count
                //whenever count become 1 we will change candidate which is in above if
                if (arr[i] == candidate) {
                    count++;
                } else {
                    count--;
                }
            }
        }
    return candidate;
    }
    public boolean verifyFrequency(int[] arr, int candidate) {
        int count  =0;
        for (int i=0; i<arr.length; i++) {
            if(candidate==arr[i]) count++;
            if(count>arr.length/2) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1,1,1,2,2};
        int result = new MajorityElement().majority_elements(nums);
        boolean freq = new MajorityElement().verifyFrequency(nums,result);
        System.out.println("\n\n freq " + freq);
    }
}

/*
* Leetcode - https://leetcode.com/problems/majority-element/
* gfg - https://www.geeksforgeeks.org/boyer-moore-majority-voting-algorithm/
* youtube- https://www.youtube.com/watch?v=n5QY3x_GNDg
* */