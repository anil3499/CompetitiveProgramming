package org.ap.datastrutures.interval;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/insert-interval/
// leetcode 57
public class InsertInterval {
    /*case 1
           end of current interval is lesser than start of newInterval
       case 2
           start of current interval is lesser or equal to end of new interval
               update new interval start as Min of current start and newInterval start
               update new interval end as Max of current end and newInterval end
               run the loop until condition matches
           add new interval to list
       case3
           add remaining interval as is to list
           i.e. start of current interval is greater than to end of new interval
       */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i =0, n = intervals.length;
        //case 1
        //We iterate through all intervals, checking whether the endpoint of the current interval (intervals[i][1]) is less than the starting point of the new interval (newInterval[0]).
        //If this condition holds true, it indicates there is no overlap before merging, and we add the current interval to the result.
        // Update the newInterval's start point to the minimum of its current start and the current interval's start.
        while(i < n && intervals[i][1] < newInterval[0]) { //curr [1,4]  new [5,6]
            result.add(intervals[i]);
            i++;
        }
        //case 2
        /*
        During the iteration, we identify overlap by comparing the endpoint of the new interval (newInterval[1]) with the starting point of the current interval (intervals[i][0]).
        When an overlap is detected, we merge the intervals by updating the start and end values of the new interval.
        The index (i) is then incremented to move to the next interval.
        After merging, the new interval is added to the result.
        * */
        while(i < n && newInterval[1] >= intervals[i][0]) { //[1,3] [2,5]
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        //case 3 Loop through the remaining intervals (from indexi) and add them to theresarray.
        while(i < n) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }

}
