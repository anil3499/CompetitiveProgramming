package org.ap.datastrutures.heap;

import java.util.PriorityQueue;


import java.io.*;
import java.util.*;
/*
1. You are required to complete the code of our MedianPriorityQueue class. The class should mimic the behaviour of
a PriorityQueue and give highest priority to median of it's data.
2. Here is the list of functions that you are supposed to complete
2.1. add -> Should accept new data.
2.2. remove -> Should remove and return median value, if available or print "Underflow" otherwise and return -1
2.3. peek -> Should return median value, if available or print "Underflow" otherwise and return -1
2.4. size -> Should return the number of elements available
3. Input and Output is managed for you.

Logic : Have 2 priority queues and divide the list of number into 2 PQ
first PQ1 will contain the first half of numbers
second PQ2 will contain second half of numbers

insert operation
1. by default insert in PQ1 with smaller number
2. add in PQ2 only if new element is greater than first element in PQ1
3. if size diff is 2 then adjust the size

peek operation :
1. if PQ1 size >  PQ2 size then return last element from PQ1
2. if PQ1 size <  PQ2 size then return first element from PQ2
3. if size same than return last from PQ1

pop/remove operation:
1. if PQ1 size >  PQ2 size then remove last element from PQ1
2. if PQ1 size <  PQ2 size then remove first element from PQ2
3. if size same than remove last from PQ1
 */
public class MedianQueue {

    public static class MedianPriorityQueue {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public MedianPriorityQueue() {
            left = new PriorityQueue<>(Collections.reverseOrder());
            right = new PriorityQueue<>();
        }

        public void add(int val) {
            if(right.size() > 0 && val > right.peek()){
                right.add(val);
            } else {
                left.add(val);
            }
            handleBalance();
        }

        private void handleBalance(){
            if(left.size() - right.size() == 2){
                right.add(left.remove());
            } else if(right.size() - left.size() == 2){
                left.add(right.remove());
            }
        }

        public int remove() {
            if (this.size() == 0) {
                System.out.println("Underflow");
                return -1;
            } else if (left.size() >= right.size()) {
                return left.remove();
            } else {
                return right.remove();
            }
        }

        public int peek() {
            if (this.size() == 0) {
                System.out.println("Underflow");
                return -1;
            } else if (left.size() >= right.size()) {
                return left.peek();
            } else {
                return right.peek();
            }
        }

        public int size() {
            return left.size() + right.size();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MedianPriorityQueue qu = new MedianPriorityQueue();

        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("add")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                qu.add(val);
            } else if (str.startsWith("remove")) {
                int val = qu.remove();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("peek")) {
                int val = qu.peek();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("size")) {
                System.out.println(qu.size());
            }
            str = br.readLine();
        }
    }
}
