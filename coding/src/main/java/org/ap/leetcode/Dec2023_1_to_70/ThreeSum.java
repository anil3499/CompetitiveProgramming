package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/3sum/
//https://www.youtube.com/watch?v=jzZsG8n2R9A
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i =0;i< nums.length;i++) {
            if(i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i+1;
            int right = nums.length-1;
            while(left<right) {
                int threeSum =nums[i] + nums[left] +nums[right];
                if(threeSum>0) //0is our target and array is sorted so we will move right
                    right--;
                else if(threeSum<0)
                    left++;
                else {
                    // thresum ==0
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[i]);res.add(nums[left]);res.add(nums[right]);
                    result.add(res);
                    //belowcode  for ex [-2,-2,0,0,2,2]
                    left++;
                    while(nums[left]==nums[left-1] && left<right)
                        left++;
                }
            }
        }
        return result;
    }
    //complexity n to power 2 log n
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); //nlogn
        int prev = nums[0];
        for(int i=0; i<nums.length;i++) {
            if(i==0 || (i!=0 && prev != nums[i])) {  //it will save us from generating duplicate set
                List<List<Integer>> res =twoSum(nums, i+1,0-nums[i]);
                if(res!=null){
                    for(List<Integer> list :res) {
                        list.add(nums[i]);
                        result.add(list);
                    }
                }
            }
            prev= nums[i];
        }
        return result;
    }
    public List<List<Integer>> twoSum(int[] nums, int from, int target) {
        List<List<Integer>> op = new ArrayList<>();
        if(from>=nums.length) return null;
        int prev = nums[from];
        for(int i = from; i<nums.length; i++) {
            if(i==from || (i!=from && prev != nums[i])){
                int index = binarySearch(nums, i+1, target - nums[i]);
                if(index!=-1){
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[i]);res.add(nums[index]);
                    op.add(res);
                }
            }
            prev=nums[i];
        }
        if(op.size()>0) return op;
        return null;
    }
    public int binarySearch(int[] nums, int left, int find) {
        int right = nums.length-1;
        int index= -1;
        while(left<right) {
            int mid= left + (right - left)/2;
            if(nums[mid] == find) index = mid;
            if(nums[mid] < find) left = mid +1;
            else right = mid-1;
        }
        return index;
    }
}
