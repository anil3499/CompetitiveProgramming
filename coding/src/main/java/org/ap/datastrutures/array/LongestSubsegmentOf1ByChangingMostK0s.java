package org.ap.datastrutures.array;

public class LongestSubsegmentOf1ByChangingMostK0s {

    /*
    * Given a binary array a[] and a number k,
    * we need to find length of the longest subsegment of ‘1’s possible by changing at most k ‘0’s.
    * Examples:
    * Input : a[] = {1, 0, 0, 1, 1, 0, 1},
    * k = 1.
    * Output : 4
    * Explanation : Here, we should only change 1
    * zero(0). Maximum possible length we can get
    * is by changing the 3rd zero in the array,
    * we get a[] = {1, 0, 0, 1, 1, 1, 1}
    * */

    public static int longestSubsegmentof1sByChangingK0s(int[] arr, int k){
        int maxCount = Integer.MIN_VALUE;
        int totalKs = k;
        int count = 0;
        for (int i=0; i<arr.length;i++){
            //if we found current elemtn as 1 then we will increment the count
            if(arr[i] ==1) {
                count++;
            }else if(arr[i]==0 && totalKs ==0) {
                //if we found zero and totalKs become 0
                // then we cant change it to zero so we will just taek our observations till now,
                // like max count and reinitialize count =0 and totalKs =k
                maxCount = Math.max(maxCount,count);
                count = 0;
                totalKs = k;
            }else { // we canreduce ttotalKs and increase count
                totalKs--;
                count ++;
            }

        }
        maxCount = Math.max(maxCount,count);
        return maxCount;
    }

    public static void main(String[] args) {
        int arr[] = {1, 0, 0, 1, 1, 0, 1};
        int k = 1;
        System.out.println(longestSubsegmentof1sByChangingK0s(arr,k));
    }
}

/*
* GeeksforGeeks - https://www.geeksforgeeks.org/longest-subsegment-1s-formed-changing-k-0s/
* */