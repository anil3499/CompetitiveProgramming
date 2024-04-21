package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/jump-game-ii/
public class JumpGame_II {
    public int jump(int[] nums) {
        int farthest =0, end=0,step=0;
        for(int i=0;i<nums.length-1; i++){ //running til length-2 only because we need to reach till length-1
            farthest = Math.max(farthest,i+nums[i]);
            //if in any case we have reached to end before finishing our loop itself then

            if(farthest>=nums.length-1) {
                step+=1; //take a step and return the ans
                break;
            }

            if(i==end) {
                // If I have reached to end of max reach then update end and step
                //because here we have to take another step to reach further
                end=farthest;
                step+=1;
            }
        }
        return step;
    }
}
