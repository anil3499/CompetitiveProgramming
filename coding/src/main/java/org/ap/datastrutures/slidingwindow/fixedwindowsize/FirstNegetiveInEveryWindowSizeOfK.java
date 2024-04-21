package org.ap.datastrutures.slidingwindow.fixedwindowsize;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Math.max;

public class FirstNegetiveInEveryWindowSizeOfK {
    public static List<Integer> findfirstNegetiveInEveryWindowOfSizeK(int[] arr, int sizeK) {
        List<Integer> output = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0, j = 0; j < arr.length; ) {
            if (arr[j] < 0)
                queue.add(arr[j]);
            int windowsize = j - i + 1;
            if (windowsize < sizeK) {
                j++;
            } else {
                //if there is no element in sub array who is negetive then qqueue need to add zero then queue will be empty
                if(queue.isEmpty()) {
                    output.add(0);
                } else {
                    //will take first element from queue
                    int topOfQueue = queue.getFirst();
                    //will check before shifting the window that
                    //is front of queue is equl to the first element of window
                    //if its not that means might be second or third element of array is negetive, then just take and add it to answer
                    if (arr[i] != topOfQueue) {
                        output.add(topOfQueue);
                    } else { //if first element is negetive then add elemnt to ans and remove first element from queue
                        output.add(topOfQueue);
                        queue.removeFirst();
                    }
                }
                j++;
                i++;
            }
        }
        return output;
    }

    public static void main(String[] args) {
     int [] arr = new int [] {12,-1,-7,-8,-15,30,16,28};
     int k =3;
        System.out.println(findfirstNegetiveInEveryWindowOfSizeK(arr,k));
    }
}

