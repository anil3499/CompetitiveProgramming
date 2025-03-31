package org.ap.datastrutures.hashing;

import java.util.HashMap;
import java.util.Map;
//https://leetcode.com/problems/max-number-of-k-sum-pairs/description
//1679
public class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int noOfOperation =0;
        for(int i=0; i<nums.length; i++) {
            if(map.containsKey(nums[i])) {
                noOfOperation++;
                map.put(nums[i],map.get(nums[i])-1);
                if(map.get(nums[i])==0) {
                    map.remove(nums[i]);
                }
            } else {
                map.put(k-nums[i],map.getOrDefault(k-nums[i],0)+1);
            }
        }
        return noOfOperation;
    }
}
