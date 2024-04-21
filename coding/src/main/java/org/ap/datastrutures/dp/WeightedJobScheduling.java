package org.ap.datastrutures.dp;

import java.beans.IntrospectionException;
import java.util.*;
import java.util.stream.Collectors;

/*
Input: Number of Jobs n = 4
       Job Details {Start Time, Finish Time, Profit}
       Job 1:  {1, 2, 50}
       Job 2:  {3, 5, 20}
       Job 3:  {6, 19, 100}
       Job 4:  {2, 100, 200}
Output: The maximum profit is 250.
We can get the maximum profit by scheduling jobs 1 and 4.
Note that there is longer schedules possible Jobs 1, 2 and 3
but the profit with this schedule is 20+50+100 which is less than 250.


logic to find max weight:
Procedure WeightedJobScheduling(Job)
sort Job according to finish time in non-decreasing order
for i -> 2 to n
    for j -> 1 to i-1
        if Job[j].finish_time <= Job[i].start_time
            if Acc_Prof[j] + Profit[i] > Acc_Prof[i]
                Acc_Prof[i] = Acc_Prof[j] + Profit[i]
            endif
        endif
    endfor
endfor

maxProfit = 0
for i -> 1 to n
    if maxProfit < Acc_Prof[i]
        maxProfit = Acc_Prof[i]
return maxProfit



logic to find jobs
Procedure FindingPerformedJobs(Job, Acc_Prof, maxProfit):
S = stack()
for i -> n down to 0 and maxProfit > 0
    if maxProfit is equal to Acc_Prof[i]
        S.push(Job[i].name
        maxProfit = maxProfit - Job[i].profit
    endif
endfor

 */
public class WeightedJobScheduling {

    static class Job implements Comparable<Job>{
        Integer startTime;
        Integer endTime;
        Integer weight;

        public Job(Integer startTime, Integer endTime, Integer weight) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.weight = weight;
        }

        @Override
        public int compareTo(Job o) {
            return this.endTime.compareTo(o.endTime);
        }

        @Override
        public String toString() {
            return "Job{" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Job> jobs= Arrays.asList(
                new Job( 0, 6, 60 ),
                new Job( 1, 4, 30 ),
                new Job( 3, 5, 10 ),
                new Job( 5, 7, 30 ),
                new Job( 5, 9, 50 ),
                new Job( 7, 8, 10 ));
        WeightedJobScheduling(jobs);

    }

    public static void WeightedJobScheduling(List<Job> jobs){
        Integer maxProfit=Integer.MIN_VALUE;
        Collections.sort(jobs);
        List<Integer> allProfit=jobs.stream().map(t -> t.weight).collect(Collectors.toList());
        for (int i = 1; i < jobs.size(); i++) {
            for (int j = 0; j < i; j++) {
                if(jobs.get(j).endTime <= jobs.get(i).startTime){
                    if(allProfit.get(j) + jobs.get(i).weight > allProfit.get(i)){
                        allProfit.set(i,allProfit.get(j) + jobs.get(i).weight);
                    }
                }
            }
        }
        for(Integer max : allProfit){
            if(max > maxProfit){
                maxProfit=max;
            }
        }
        System.out.println("Max profit" + maxProfit);
        findingPerformedJobs(jobs,allProfit,maxProfit);

    }

    public static void findingPerformedJobs(List<Job> jobs, List<Integer> allProfit, Integer maxProfit){
        Stack<Job> stack=new Stack<>();
        Integer tempMax=maxProfit;
        for (int i = allProfit.size()-1; i >= 0; i--) {
            if(tempMax == allProfit.get(i)){
                stack.push(jobs.get(i));
                tempMax=tempMax-jobs.get(i).weight;
            }
        }
        stack.forEach(System.out::println);
    }
}
