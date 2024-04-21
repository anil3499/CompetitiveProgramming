package org.ap.datastrutures.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*https://leetcode.com/discuss/interview-question/912009/doordash-available-time-problem
    [3,20],[0,2] [-2, 0],, [16,17], [19,23], [30,40], [27, 33]
    starttime: -5 1 2 3
    endtime: 27   7
    minduration: 1-5  1
    * 1 - 5
    [-2, 2][3,20], [16,17]
    * [2-3]
    *
    * 0 1 2 3
    * */
// Given a list of time blocks where a particular person is already booked/busy, a start and end time to search between, a minimum duration to search for, find all the blocks of time that a person is free for a potential meeting that will last the aforementioned duration.
// Given: starttime, endtime, duration, meetingslist -> suggestedmeetingtimes
// Let's assume we abstract the representation of times as simple integers, so a valid time is any valid integer supported by your environment. Here is an example input:
// meetingslist: [3,20], [-2, 0], [0,2], [16,17], [19,23], [30,40], [27, 33]
// starttime: -5
// endtime: 27
// minduration: 2
// expected answer:
// freetime: [-5, -2], [23,27]*
public class DoorDashInterview{
    public List<int[]> availableTime(int[][] schedules, int startTime, int endTime, int duration) {
        List<int[]> list = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)-> a[0] - b[0]);

        for(int[] schedule : schedules) {
            pq.offer(schedule);
        }

        int[] temp = pq.poll();
        if (startTime < temp[0] && Math.abs(temp[0] - startTime) >= duration) {
            list.add(new int[]{startTime, temp[0]});
        }

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (temp[1] < cur[0] && Math.abs(cur[0] - temp[1]) >= duration) {
                list.add(new int[]{temp[1], cur[0]});
                temp = cur;
            } else {
                temp[1] = temp[1] > cur[1] ? temp[1] : cur[1];
            }
        }

        if (temp[1] < endTime && Math.abs(endTime - temp[1]) >= duration) {
            list.add(new int[]{temp[1], endTime});
        }

        return list;
    }

    public static void main(String []args){
        int[][] schedules = {{3,20}, {-2, 0}, {0,2}, {16,17}, {19,23}, {30,40}, {27, 33}};
        DoorDashInterview doorDashInterview = new DoorDashInterview();
        List<int[]> list = doorDashInterview.availableTime(schedules, -5, 27, 2);
        for(int[] subList : list) {
            System.out.println(Arrays.toString(subList));
        }
    }
}