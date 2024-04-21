package org.ap.datastrutures.array;

import java.util.Arrays;

/*
There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
You are giving candies to these children subjected to the following requirements:
Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

Example 1:
Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 */
public class CandyDistribution {

    //best technique is tto san all left array and assign candie and then scan thru right array and assign candies and at last choose max out of both
    // for this we have to store 2 array so to optimize this we can do below
    public static int candyDistribution(int []arr){
        int []candy=new int[arr.length];
        //assign min 1 candy to everyone
        Arrays.fill(candy,1);
        //scan all left student
        for(int i=1;i<arr.length;i++){
            //if element is greater than its neighbouring element then assign more cndy then previously assigned
            if(arr[i]>arr[i-1])
               candy[i]=candy[i-1]+1;
        }

        //scan all right student
        int rightVar = 1;
        for(int i=arr.length-2;i>=0;i--){
            if(arr[i] > arr[i+1]) {
                //increament rightVar value
                rightVar++;
                candy[i] = Math.max(rightVar, candy[i]); //chosse max from rightvar and left array
            }else {
                rightVar=1;
            }
            /*if(arr[i] > arr[i+1]) {
                if(candy[i] <= candy[i+1]){
                    candy[i] = candy[i]+1;
                }
            }*/
        }
        return Arrays.stream(candy).sum();
    }

    public static void main(String[] args) {
        int []arr=new int[]{12,4,3,11,34,34,1,67};
        System.out.println(candyDistribution(arr));
    }
}
/*
* leetcode - https://leetcode.com/problems/candy/
* youtube - https://www.youtube.com/watch?v=PzBYQA6FshA&t=1s
 * */