package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/median-of-two-sorted-arrays/
public class MedianOf2SortedArray {
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[]res=merge2SortedArray(nums1,nums2);
        if(res.length%2==0){
            return  (double)(res[(res.length/2)-1] + res[(res.length/2)])/2;
        } else
            return res[res.length/2];
    }
    public int[] merge2SortedArray(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int i=0,j=0,k=0;
        while(i<nums1.length && j<nums2.length) {
            if(nums1[i] < nums2[j]) {
                res[k] = nums1[i];
                i++;k++;
            }else if(nums2[j] < nums1[i]) {
                res[k] = nums2[j];
                j++;k++;
            }else{
                res[k] = nums1[i];
                i++;k++;
            }
        }
        while(i<nums1.length ) {
            res[k] = nums1[i];
            i++;k++;
        }
        while(j<nums2.length) {
            res[k] = nums2[j];
            j++;k++;
        }
        return res;
    }

    /**
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     *
     * Solution
     * Take minimum size of two array. Possible number of partitions are from 0 to m in m size array.
     * Try every cut in binary search way. When you cut first array at i then you cut second array at (m + n + 1)/2 - i
     * Now try to find the i where a[i-1] <= b[j] and b[j-1] <= a[i]. So this i is partition around which lies the median.
     *
     * Time complexity is O(log(min(x,y))
     * Space complexity is O(1)
     *
     * https://leetcode.com/problems/median-of-two-sorted-arrays/
     * https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation/4
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //if input1 length is greater than switch them so that input1 is smaller than input2.

        if(nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2,nums1);
        }
        if(nums1.length ==0 && nums2.length==0) return 0.0;
        if(nums1.length ==0) {
            if(nums2.length%2==0)
                return (double)(nums2[(nums2.length/2) -1] +  nums2[nums2.length/2])/2;
            else
                return nums2[nums2.length/2];
        }
        int left=0;
        int right=nums1.length;
        int x = nums1.length;
        int y = nums2.length;
        while(left <= right) {
            int partitionX = (left +right)/2;
            int partitionY = (x+y+1) /2 - partitionX;
            //if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
            int leftX = (partitionX==0)? Integer.MIN_VALUE:nums1[partitionX-1];
            int rightX = (partitionX== x)? Integer.MAX_VALUE: nums1[partitionX];
            int leftY = (partitionY==0)? Integer.MIN_VALUE: nums2[partitionY-1];
            int rightY =(partitionY==y)? Integer.MAX_VALUE: nums2[partitionY];

            if(leftX <= rightY && leftY <=rightX) { //possible partition
                //We have partitioned array at correct place
                // Now get max of left elements and min of right elements to get the median in case of even length combined array size
                // or get max of left for odd length combined array size.
                if((x+y)%2==0) {//result generate frome ven
                    return (double) (Math.max(leftX,leftY) + Math.min(rightX,rightY) )/2;
                }else {//result from odd
                    return (double) Math.max(leftX,leftY);
                }
            } else if(leftX >rightY) {//we are too far on right side for partitionX. Go on left side.
                right =partitionX-1;
            } else { //we are too far on left side for partitionX. Go on right side.
                left  =partitionX+1;
            }
        }
        //Only we we can come here is if input arrays were not sorted. Throw in that scenario.
        throw new IllegalArgumentException();
    }

    }
