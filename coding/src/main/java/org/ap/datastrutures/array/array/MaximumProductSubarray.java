package org.ap.datastrutures.array.array;

/*
https://leetcode.com/problems/maximum-product-subarray/
https://www.youtube.com/watch?v=keVevSvaDKo
https://www.techiedelight.com/maximum-product-subset-problem/

Input:  A[] = { -6, 4, -5, 8, -10, 0, 8 }
Output: The maximum product of a subset is 15360
The subset with the maximum product of its elements is { -6, 4, 8, -10, 8 }

Input:  A[] = { 4, -8, 0, 8 }
Output: The maximum product of a subset is 32
The subset with the maximum product of its elements is { 4, 8 }

3 possible choice
1. i th element
2. max-neg before ith * ith element
3. max-pos before ith * ith element

 */

public class MaximumProductSubarray {

    public static int maxProduct(int[] arr){
        int n=arr.length;
        if(n==0){
            return -1;
        }
        int minProd = arr[0];
        int maxProd = arr[0];
        int result = arr[0];
        int choice1,choice2;

        for(int i=0; i<n ; i++){
            choice1=minProd * arr[i];
            choice2=maxProd * arr[i];
            minProd = Math.min(arr[i] , Math.min(choice1,choice2));
            maxProd = Math.max(arr[i] , Math.max(choice1,choice2));
            result = Math.max(result,maxProd);

        }
        return result;

    }


    public static void main(String[] args)
    {
        int[] arr = { -6, 4, -5, 8, -10, 0, 8 };
        System.out.print("The maximum product of a subset is " + maxProduct(arr));
    }
}
