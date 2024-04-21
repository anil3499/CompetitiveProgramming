package org.ap.datastrutures.binarysearch;


/**
 * You have N books, each with A[i] number of pages. M students need to be allocated contiguous books, with each student getting at least one book.
 * Out of all the permutations, the goal is to find the permutation where the student with the most books allocated to him gets the minimum number of pages, out of all possible permutations.
 *
 * Note: Return -1 if a valid assignment is not possible, and allotment should be in contiguous order (see the explanation for better understanding).
 *Input:
 * N = 4
 * A[] = {12,34,67,90}
 * M = 2
 * Output:113
 * Explanation:Allocation can be done in
 * following ways:
 * {12} and {34, 67, 90} Maximum Pages = 191
 * {12, 34} and {67, 90} Maximum Pages = 157
 * {12, 34, 67} and {90} Maximum Pages =113.
 * Therefore, the minimum of these cases is 113,
 * which is selected as the output.
 *
 * */

public class AllocateMinimumNuberOfPages {
    public static void main(String[] args) {
        System.out.println(allocateMinNoOfPages(new int[]{12,34,67,90},2));
    }
    public static int allocateMinNoOfPages(int[] arr, int students){
        int max =0, sum=0;
        for(int i=0; i<arr.length;i++) {
            max= Math.max(max,arr[i]);
            sum+=arr[i];
        }
        int start = max;
        int end = sum;
        int n = arr.length;
        int result = -1; //for not possible case

        if(n < students) return -1;

        while(start <= end) {
            int mid = start + (end-start)/2;
            if(isValid(arr,n, students, mid)) {
                result = mid;
                end = mid-1;
            }else {
                start = mid+1;
            }
        }
        return result;
    }
    public static boolean isValid(int[] arr,int n, int students, int mx) {
        int st = 1;
        int sum =0;
        for(int i=0; i<n; i++){
            sum = sum + arr[i];
            if(sum>mx) {
                st++;
                sum = arr[i];
            }
            if(st>students)
                return false;
        }
        return true;
    }

}
/**
 * https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
 * https://www.youtube.com/watch?v=2JSQIhPcHQg
 * */
