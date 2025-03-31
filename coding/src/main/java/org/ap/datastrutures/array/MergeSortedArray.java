package org.ap.datastrutures.array;

//https://leetcode.com/problems/merge-sorted-array/
public class MergeSortedArray {
    //below approach works when we needto return result array, since in this problem we dont need to return this approach doesn't work
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        if(nums2.length==0) return;
        int[] result = new int[m+n];
        int i=0,j=0,k=0;
        while(i<m && j<n) {
            if(nums1[i]<=nums2[j]) {
                result[k]=nums1[i];
                i++; k++;
            } else if(nums2[j]<nums1[i]) {
                result[k]=nums2[j];
                j++;k++;
            }
        }
        while(i<m) {
            result[k]=nums1[i];
            i++; k++;
        }
        while(j<n) {
            result[k]=nums2[j];
            j++;k++;
        }
        for(int num : result){
            System.out.print(" "+num);
        }
        nums1 = result;
    }

    private void placeElementAtRightPlace(int[] nums2) {
        int j=1;
        while(j<nums2.length && nums2[j]<nums2[j-1]) {
            int temp = nums2[j-1];
            nums2[j-1] = nums2[j];
            nums2[j] = temp;
            j++;
        }
    }
    //This method does inplace update without using extra O(N) space
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums2.length==0) return;
        int i=0,j=0;
        while(i<(m+n)) {
            if(i>=m) {
                nums1[i] =nums2[j];
                i++; j++;
            }else if(nums1[i]<=nums2[j]) {
                i++;
            } else {//if(nums1[i]>nums2[j]) {
                int temp = nums1[i];
                nums1[i] = nums2[j];
                nums2[j] = temp;
                i++;
                placeElementAtRightPlace(nums2);
            }
        }
    }

    // 3 pointer approach start from the end
    public int[] merge_array_3_pointer_approach(int[] nums1 , int m, int[] nums2, int n){

        int i = m-1;
        int j = n-1;
        int k = m+n-1;

        while(j>=0){
            if (i>=0 && nums1[i]>nums2[j]){
                nums1[k] = nums1[i];
                k--;i--;
            }else {
                nums1[k] = nums2[j];
                j--;k--;
            }

        }
        return nums1;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int m = 3;
        int [] nums2 = new int[]{2,5,6};
        int n = 3;

        /*int[] nums1 = new int[]{1};
        int m = 1;
        int [] nums2 = new int[]{};
        int n = 0;*/

       /* int[] nums1 = new int[]{0};
        int m = 0;
        int [] nums2 = new int[]{1};
        int n = 1;
*/
        int[] result= new MergeSortedArray().merge_array_3_pointer_approach(nums1,m,nums2,n);
        for (int i=0; i<result.length; i++)
            System.out.println(result[i]);
    }
}

/*
* leetcode -https://leetcode.com/problems/merge-sorted-array/
 * */