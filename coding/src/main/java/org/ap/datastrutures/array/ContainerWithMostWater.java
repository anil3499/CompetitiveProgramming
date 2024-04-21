package org.ap.datastrutures.array;

/* Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 */
public class ContainerWithMostWater {

    //we can assume we can store max water if start storing from mid
  public static int maxArea(int[] height) {
      int left = 0, right = height.length - 1;
      int water = 0;
      while (left < right) {
          int minHight_left_right = Math.min(height[left], height[right]);

          //water we can store will be right -left * min hight beween left, right
          int waterCanStore_left_right =  (right - left) * minHight_left_right;

          water = Math.max(water, waterCanStore_left_right);

          //we will move lft towards mid if left hight is lower than  else move right towards mid
         if (height[left] < height[right])
              left += 1;
          else
              right -= 1;
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
