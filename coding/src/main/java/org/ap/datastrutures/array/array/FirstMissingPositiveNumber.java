package org.ap.datastrutures.array.array;
/*
LC:41
https://leetcode.com/problems/first-missing-positive/

You are given an unsorted array with both positive and negative elements.
You have to find the smallest positive number missing from the array in O(n)
time using constant extra space. You can modify the original array.
Examples
 Input:  {2, 3, 7, 6, 8, -1, -10, 15}
 Output: 1
 Input:  { 2, 3, -7, 6, 8, 1, -10, 15 }
 Output: 4
 Input: {1, 1, 0, -1, -2}
 Output: 2
 Input: nums = [7,8,9,11,12]
 Output: 1

logic :
1. replace the negative ele and ele with greater than array size with a random number
2. change index value to negative (if they are not) based on current value
ex : if current value is 3 then change value to negative on 3rd index
3. index with value with positive sign will be the missing
 */
public class FirstMissingPositiveNumber {

    public static int missingNumber(int []arr){
        for(int i=0; i < arr.length ; i++){
            if(arr[i] < 0 || arr[i] > arr.length){
               arr[i]=arr.length+2;
            }
        }
        //negative value conversion
        for(int i=0; i < arr.length ; i++){
            if(Math.abs(arr[i]) != arr.length+2 ){
                arr[Math.abs(arr[i])-1]=-Math.abs(arr[Math.abs(arr[i])-1]);
            }
        }
        for(int i=0; i < arr.length ; i++){
            if(arr[i]>0){
                return i+1;
            }
        }
        return arr.length+2;
    }

    public static void main(String[] args) {
        int []arr=new int[]{3,4,-1,1};
        System.out.println(missingNumber(arr));

    }
}
