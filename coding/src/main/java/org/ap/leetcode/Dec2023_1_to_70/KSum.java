package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//https://www.youtube.com/watch?v=EYeR-_1NRlQ
//https://www.youtube.com/watch?v=LfB2tkmsrCA
//https://leetcode.com/problems/4sum/
public class KSum {
    /**
     * def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
     *         # sort array,this way we can safely ignore repeating values
     *         # because after sort,same values come one after one
     *         nums.sort()
     *         result = []
     *
     *         for i in range(len(nums)):
     * 			# if repeating value,ignore the value
     *             if i > 0 and nums[i] == nums[i - 1]:
     *                 continue
     *
     *             # solve 3sum problem with nums[i]
     *             for j in range(i + 1, len(nums)):
     *                 if j > i + 1 and nums[j] == nums[j - 1]:
     *                     continue
     *
     *                 # solve 2sum problem with nums[i]+nums[j]
     *                 l = j + 1
     *                 r = len(nums) - 1
     *                 while l < r:
     *                     # if total sum of 4 numbers equal to target,append to result list
     *                     tot = nums[i] + nums[j] + nums[l] + nums[r]
     *                     if tot == target:
     *                         result.append([nums[i], nums[j], nums[l], nums[r]])
     *                         l+=1
     *                         while l < r and nums[l] == nums[l - 1]:
     *                             l += 1
     *                     # if total is less than target value,we need a bigger total
     *                     # move p pointer forward to make bigger total
     *                     elif tot < target:
     *                         l += 1
     *                     # if total is bigger than target value,we need a smaller total
     *                     # move q pointer backward to make small total
     *                     else:
     *                         r -= 1
     * */
    public List<List<Integer>> KSum(int[] nums, int target,int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, target, k, temp, result);
        return result;
    }
    public void helper(int[] nums, int index, int target,int k, List<Integer> temp, List<List<Integer>> result) {
        System.out.println("k "+k + " indx "+index +" trgt "+target + "  tmp " + temp);
        if(k!=2) {
            for(int i = index; i < nums.length - k + 1; i++){
                //whill iterate from start/0 index to length - last k values +1
                //understand it like when you arerunning for k=2 you willrun loop til i<n-1 which is n-2+1
                if(i > 0 && nums[i]==nums[i-1])
                    continue;
                temp.add(nums[i]);
                System.out.println("calling for i+1 "+ i+1);
                helper(nums, i+1, target-nums[i], k-1, temp, result);
                temp.remove(temp.size()-1);
            }
            return;
        }

        //Base condition for 2 sum k<=2
        int left = index;
        int right = nums.length -1;
        System.out.println(left +" "+ right);
        while(left < right){
            int sum = nums[left] + nums[right];
            if(sum < target) {
                left++;
            } else if(sum > target){
                right--;
            } else{
                List<Integer> res = new ArrayList<>(temp);
                res.add(nums[left]);
                res.add(nums[right]);
                result.add(res);
                left++;
                while(left<right && nums[left] ==nums[left-1])
                    left++;
            }
        }
    }
}
