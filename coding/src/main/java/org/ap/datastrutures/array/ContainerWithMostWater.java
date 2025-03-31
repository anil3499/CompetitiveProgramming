package org.ap.datastrutures.array;

import java.util.Arrays;

/* Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 */
//https://leetcode.com/problems/container-with-most-water/description
// 11
public class ContainerWithMostWater {

    //we can assume we can store max water if start storing from mid
  public static int maxArea(int[] height) {
      int left = 0, right = height.length - 1;
      int water = 0;
      while (left < right) {
          int min = Math.min(height[left], height[right]);

          //water we can store will be right -left * min hight beween left, right
          int waterCanStore =  (right - left) *  min;

          water = Math.max(water, waterCanStore);

          //we will move lft towards mid if left hight is lower than  else move right towards mid
         if (height[left] < height[right])
              left += 1;
          else
              right -= 1;
      }
      return water;
  }
    public int maxArea1(int[] height) {

        int n = height.length;
        if(n<2) return 0;
        if(n==2) return Math.min(height[0],height[1]);
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        for(int i=1;i<n;i++)
            left[i] =Math.max(height[i-1],height[i]);
        right[n-1] = height[n-1];
        for(int j= n-2;j>=0;j--)
            right[j] =Math.max(height[j+1],height[j]);

        int i = 0;
        int j = n-1;
        int water = 0;
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        while(i<j) {
            //System.out.println(left[i] + " " +right[j]);
            int min = Math.min(left[i],right[j]);
            water = Math.max(water, (j-i)*min);
            //System.out.println(water +" "+ min +" "+j + "  " + i);

            if(left[i]<=right[j]) i++;
            else j--;
        }
        return water;
    }

    public static void main(String[] args) {
        int[] hight = {1,8,6,2,5,4,8,3,7};
        System.out.println(new ContainerWithMostWater().maxArea(hight));
    }
}

/**
 * LeetCode - https://leetcode.com/problems/container-with-most-water/
 * */
