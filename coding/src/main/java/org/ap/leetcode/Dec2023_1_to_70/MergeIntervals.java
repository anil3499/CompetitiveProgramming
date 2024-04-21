package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/merge-intervals/description/
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(o1, o2)-> o1[0]-o2[0]);
        List<int[]> result= new ArrayList<>();
        int[] prev = intervals[0];
        for(int i=1;i<intervals.length;i++){
            if(prev[0] <= intervals[i][0] && prev[1] >= intervals[i][0]) {
                prev = new int[]{Math.min(prev[0],intervals[i][0]), Math.max(prev[1],intervals[i][1])};
            }else {
                result.add(prev);
                prev=intervals[i];
            }
        }
        result.add(prev);

        int[][] resultArr = new int[result.size()][2];
        int k =0;
        for(int[] interval :result)
            resultArr[k++] =interval;
        return resultArr;
    }
}
