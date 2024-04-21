package org.ap.datastrutures.sorting;

import java.util.Arrays;
import java.util.List;

public class QuickSort {

    public static void quickSort(int []arr, int start, int end){
        if(start < end){
            int pindex=partition(arr, start, end);
            quickSort(arr, start,pindex-1);
            quickSort(arr, pindex+1,end);
        }
    }

    private static int partition(int []arr, int start, int end) {
        int pivot=arr[end];
        int pindex=start;
        for(int i=start ; i<end ;i++){
            if(arr[i] <= pivot){
                int temp=arr[i];
                arr[i]=arr[pindex];
                arr[pindex]=temp;
                pindex++;
            }
        }
        int temp = arr[pindex];
        arr[pindex] = arr[end];
        arr[end]=temp;
        return pindex;
    }

    public static void main(String[] args) {
        int []arr={5,2,8,6,2,9,11,10};
        quickSort(arr,0, arr.length-1);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
