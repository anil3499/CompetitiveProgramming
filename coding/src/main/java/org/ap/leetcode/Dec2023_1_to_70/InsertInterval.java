package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/insert-interval/
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i=0;
        List<int[]> result = new ArrayList<>();

        //add
        for(i=0;i< intervals.length;i++){
            // if new intervalis completely befoe our intervals[i]
            if(newInterval[1]<intervals[i][0]){
                result.add(newInterval);
                addReamainings(result,intervals,i);
                newInterval=new int[]{};
                break;
            }else if(intervals[i][1] < newInterval[0]) {
                // if new intervalis completely after our intervals[i]
                result.add(intervals[i]);
            }else {
                // if new intervalis and our intervals[i] is overlapping
                newInterval[0] = Math.min(newInterval[0],intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1],intervals[i][1]);
            }
        }
        if(newInterval.length!=0)
            result.add(newInterval);

        int[][] resultArr = new int[result.size()][2];
        int k=0;
        for(int[] arr:result) {
            resultArr[k++] = arr;
        }
        return resultArr;
    }

    public void addReamainings(List<int[]> result, int[][] intervals,int i){
        for(;i<intervals.length;i++) {
            result.add(intervals[i]);
        }
    }
}
