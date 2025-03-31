package org.ap.datastrutures.slidingwindow.fixedsize;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MaxOfAllSubArrayOfSizeK {
    public static List<Integer> maxFromAllSubArraySizeK(int[] arr, int k) {
        List<Integer> output = new ArrayList<>();
        LinkedList<Integer> max_of_window = new LinkedList<>();
        for (int i = 0, j = 0; j < arr.length; ) {
            int window = j - i + 1;

            while ((max_of_window.size() > 0) && (max_of_window.getLast() < arr[j]))
                max_of_window.removeLast();

            max_of_window.add(arr[j]);

            if (window < k)
                j++;
            else { //window == k
                output.add(max_of_window.getFirst());
                //System.out.println(output);
                //revert the transaction

                if (max_of_window.size() > 0 && max_of_window.getFirst() == arr[i])
                    max_of_window.removeFirst();

                i++;
                j++;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(maxFromAllSubArraySizeK(arr, k));

    }
}