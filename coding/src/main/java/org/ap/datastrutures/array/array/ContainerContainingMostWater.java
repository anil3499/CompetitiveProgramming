package org.ap.datastrutures.array.array;
/*
https://leetcode.com/problems/container-with-most-water/
https://www.geeksforgeeks.org/container-with-most-water/
Input: array = [1, 5, 4, 3]
Output: 6
Explanation :
5 and 3 are distance 2 apart.
So the size of the base = 2.
Height of container = min(5, 3) = 3.
So total area = 3 * 2 = 6

Input: array = [3, 1, 2, 4, 5]
Output: 12
Explanation :
5 and 3 are distance 4 apart.
So the size of the base = 4.
Height of container = min(5, 3) = 3.
So total area = 4 * 3 = 12

 */
public class ContainerContainingMostWater {

    public static int containerContainingMostWater(int []arr){
        int ans=0;
        int l=0,r=arr.length-1;
        while(l<r){
            ans=Math.max(ans,Math.min(arr[l],arr[r]) * (r-l));
            if(arr[l] < arr[r]){
                l++;
            }else{
                r--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int []arr=new int[]{1,8,4,6,2,5,9,3};
        System.out.println(containerContainingMostWater(arr));
    }
}
