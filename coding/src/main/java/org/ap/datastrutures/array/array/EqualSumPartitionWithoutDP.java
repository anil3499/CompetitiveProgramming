package org.ap.datastrutures.array.array;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/*
https://www.techiedelight.com/partition-array-into-two-sub-arrays-with-same-sum/


*/
public class EqualSumPartitionWithoutDP {

    public static int partition(int[] arr)
    {
        int totalSum = Arrays.stream(arr).sum();
        int sumSoFar = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (sumSoFar == totalSum - sumSoFar) {
                return i;
            }
            sumSoFar = sumSoFar +  arr[i];
        }
        return -1;
    }

    // Utility function to print the sub-array arr[i,j]
    public static void printSubArray(int[] arr, int i, int j)
    {
        System.out.println(IntStream.range(i, j + 1).
                mapToObj(k -> arr[k])
                        .collect(Collectors.toList()));
    }

    public static void main(String[] args)
    {
        int[] arr = { 6, -4, -3, 2, 3 };

        // get index i that points to starting of second sub-array
        int i = partition(arr);

        if (i != -1)
        {
            // print the first sub-array [0, i-1]
            printSubArray(arr, 0, i - 1);

            // print the second sub-array [i, arr.length)
            printSubArray(arr, i, arr.length - 1);
        }
        else {
            System.out.print("The array can't be partitioned");
        }
    }
}
