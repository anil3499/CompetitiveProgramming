package org.ap.datastrutures.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MergeInterval {
    public static void main(String[] args) {
        //int[][] input = new int[][]{{1,3},{2,4},{6,8},{9,10}};

        // output {{1, 4}, {6, 8}, {9, 10}}
        MergeInterval mi = new MergeInterval();
        System.out.println(mergeInterval(mi.createInput()));
    }
    public List<Interval> createInput(){
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1,3));
        input.add(new Interval(2,4));
        input.add(new Interval(6,8));
        input.add(new Interval(9,10));
        return input;
    }
    class Interval{
        int start;
        int end;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static LinkedList<Interval> mergeInterval(List<Interval> input){
        LinkedList<Interval> output = new LinkedList<>();
        int index =0;
        Collections.sort(input,(o1,o2) -> {
                    if (o1 instanceof Interval && o2 instanceof Interval)
                        return o1.start - o2.start;
                    return 0;
                });
        output.add(input.get(0));

        for(int i =1;i<input.size();i++){
            if(output.getLast().end >= input.get(i).start) {
               output.getLast().end = Math.max(output.getLast().end, input.get(i).end);
            }else {
                output.add(input.get(i));
            }
        }
        return output;
    }
}
