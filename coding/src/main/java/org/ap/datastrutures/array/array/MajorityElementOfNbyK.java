package org.ap.datastrutures.array.array;

/*
https://www.youtube.com/watch?v=cIgDFIoA_s0
https://www.geeksforgeeks.org/given-an-array-of-of-size-n-finds-all-the-elements-that-appear-more-than-nk-times/

Given an array of size n, find all elements in array that appear more than n/k times. For example,
if the input arrays is {3, 1, 2, 2, 1, 2, 3, 3} and k is 4, then the output should be [2, 3].
Note that size of array is 8 (or n = 8), so we need to find all elements that appear more than 2 (or 8/4) times.
There are two elements that appear more than two times, 2 and 3.

Consider k = 4, n = 9
Given array: 3 1 2 2 2 1 4 3 3

i = 0
         3 _ _
temp[] has one element, 3 with count 1

i = 1
         3 1 _
temp[] has two elements, 3 and 1 with
counts 1 and 1 respectively

i = 2
         3 1 2
temp[] has three elements, 3, 1 and 2 with
counts as 1, 1 and 1 respectively.

i = 3
         - - 2
         3 1 2
temp[] has three elements, 3, 1 and 2 with
counts as 1, 1 and 2 respectively.

i = 4
         - - 2
         - - 2
         3 1 2
temp[] has three elements, 3, 1 and 2 with
counts as 1, 1 and 3 respectively.

i = 5
         - - 2
         - 1 2
         3 1 2
temp[] has three elements, 3, 1 and 2 with
counts as 1, 2 and 3 respectively.

logic :
create array of size k-1
keep increasing count if same element comes
decrease all element counts if not present in array
 */

import java.util.Arrays;

public class MajorityElementOfNbyK {
    static class ElementCount{
        Integer ele;
        Integer count;

        @Override
        public String toString() {
            return "ElementCount{" +
                    "ele=" + ele +
                    ", count=" + count +
                    '}';
        }
    }
    public static void findMajorityOfNbyK(int []arr, int k){
        ElementCount []karray=new ElementCount[k-1];
        for(int i=0 ; i<k-1 ; i++){
            karray[i]=new ElementCount();
            karray[i].count=0;
        }
        for(Integer i : arr){
            boolean ifMatch=false;
            for(ElementCount e : karray){
                if(e.ele == null){
                    e.ele=i;
                    e.count=e.count+1;
                    ifMatch=true;
                    break;
                }else if(e.ele == i){
                    e.count= e.count+1;
                    ifMatch=true;
                    break;
                }
            }
            if(!ifMatch) {
                for (ElementCount e : karray) {
                    if(e.count>0){
                        e.count= e.count-1;
                        if(e.count == 0){
                            e.ele=null;
                        }
                    }
                }
            }
        }
        Arrays.stream(karray).filter(e -> e.count >=k).forEach(System.out::println);
    }
    public static void main(String[] args) {
        int []arr=new int[] {1,1,2,3,4,2,1,1,1,2,2,2};
        int k=3;
        findMajorityOfNbyK(arr , k);

    }
}
