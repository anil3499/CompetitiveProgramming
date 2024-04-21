package org.ap.datastrutures.array.array;
/*
VISA
https://www.geeksforgeeks.org/find-element-array-sum-left-array-equal-sum-right-array/
Input: 1 4 2 5
Output: 2
Explanation: If 2 is the partition,
      subarrays are : {1, 4} and {5}

Input: 2 3 4 1 4 5
Output: 1
Explanation: If 1 is the partition,
 Subarrays are : {2, 3, 4} and {4, 5}

 logic :
 1.create array with prefix sum
 2. create array with suffix sum
 3. compare array on that element it will be equal
 */
public class FindElementWithLeftAndRightElementSumEqual {
    // Finds an element in an array such that
    // left and right side sums are equal
    static int findElement(int arr[], int n)
    {
        // Forming prefix sum array from 0
        int[] prefixSum = new int[n];
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++)
            prefixSum[i] = prefixSum[i - 1] + arr[i];

        // Forming suffix sum array from n-1
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--)
            suffixSum[i] = suffixSum[i + 1] + arr[i];

        // Find the point where prefix and suffix
        // sums are same.
        for (int i = 1; i < n - 1; i++)
            if (prefixSum[i] == suffixSum[i])
                return arr[i];

        return -1;
    }

    static int findElement2(int arr[], int size)
    {
        int rightSum = 0, leftSum = 0;
        // Maintains left cumulative sum
        leftSum = 0;

        // Maintains right cumulative sum
        rightSum=0;
        int i = -1, j = -1;

        for( i = 0, j = size-1 ; i < j ; i++, j-- ){
            leftSum += arr[i];
            rightSum += arr[j];

            // Keep moving i towards center until
            // left_sum is found lesser than right_sum
            while(leftSum < rightSum && i < j){
                i++;
                leftSum += arr[i];
            }
            // Keep moving j towards center until
            // right_sum is found lesser than left_sum
            while(rightSum < leftSum && i < j){
                j--;
                rightSum += arr[j];
            }
        }
        if(leftSum == rightSum && i == j)
            return arr[i];
        else
            return -1;
    }

    public static void main(String args[])
    {
        int arr[] = { 1, 4, 2, 5 };
        int n = arr.length;
        System.out.println(findElement(arr, n));
        System.out.println(findElement2(arr, n));
    }
}
