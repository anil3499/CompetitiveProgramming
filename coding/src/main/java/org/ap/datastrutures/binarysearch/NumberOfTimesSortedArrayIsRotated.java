package org.ap.datastrutures.binarysearch;
/*
https://www.youtube.com/watch?v=4WmTRFZilj8&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=7
1. smaller element index will tell the no. of rotation
2. element where nearest left and right is larger will be smallest one
3. where to move (left or right ) ? - > move where subarray is not shorted
 */

public class NumberOfTimesSortedArrayIsRotated {

    private static int findNoOfRotation(int[] arr , int n) {
        int start=0;
        int end=arr.length-1;
        while(start<=end){

            int mid=(start+end)/2;
            /*
            if in case test case failes bcoz of integer limit
            int mid= start + (end-start)/2;
             */
            int next = (mid + 1) % n;
            int prev = (mid + n -1) % n;
            if(arr[mid] <= arr[prev] && arr[mid] <= arr[next]){
                return mid;
            }else {
                if(arr[mid]>=arr[start] && arr[mid]<=arr[end]){
                    return start;
                }else if (arr[start] <= arr[mid]){
                    start=mid+1;
                }else if (arr[mid] <= arr[end]){
                    end=mid-1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int []arr={2,5,6,8,11,12,15,18};
        //rotated above array 4 times
        int []rotatedArr={11,12,15,18,2,5,6,8};
        System.out.println("count "+ findNoOfRotation(rotatedArr , arr.length));
    }

}

   // int[] arr = {11, 12, 15, 18, 2, 5, 6, 8};
//{2,5,6,8,11,12,15,18
//if you observ 2 is at 0 index means arrya is rotated at 0 times
//Number of times array rorated is Index of min element
//mid element can compare from mid-1 and mis+1 and if element at mid is smaller than both sideselement then this is min element


/**
 * mid =  (start+end)/2
 * prev and next of mid we need to find which part of array is not sorted and whihch part of array is sorted
 * because our element (min) lie in unsorted array only
 *next= ((mid+1)%N
 * prev = (mid+N-1)%N
 *if we would have done next = mid+1 the for last index we would have gone to out of bound
 * // so we will do mid+1%N so we don't go out of bound and reach starting of array
 *
 * For Prev we would do mid-1, so if mid is 0 then we would get negetive
 * (mid+N-1)%N
 *
 * below stateent means mid is smaller then its both neighbour then mid will be ans
 * if(arr[mid] < = arr[next] && arr[mid]<= arr[prev]
 * return mid //this will be ans
 *
 * //otherwise we need to find unsorted array
 * // we wil compare start and mid && mid and end
 * else
 *  if(arr[left] < arr[mid) //means first arrayis sorted
 *      left = mid+1 //and we need to move towards unsorted array because our element min is there
 *      //for that we will do left = mid+1
 *   elseif arr[mid] <= arr[right]) // means second array is sorted
 *      right = mid -1
 *      //and we need to move towards unsorted array because our element min is there
 *          *      //for that we will do right = mid -1
 *
 *
 * */