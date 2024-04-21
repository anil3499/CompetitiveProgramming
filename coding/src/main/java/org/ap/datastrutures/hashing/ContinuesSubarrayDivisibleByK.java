package org.ap.datastrutures.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
https://www.geeksforgeeks.org/count-sub-arrays-sum-divisible-k/
https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/hashmap-and-heaps/count_of_subarrays_with_sum_divisible_by_k/topic

Input  : arr[] = {4, 5, 0, -2, -3, 1},
         K = 5
Output : 7
// there are 7 sub-arrays whose is divisible by K
// {4, 5, 0, -2, -3, 1}
// {5}
// {5, 0}
// {5, 0, -2, -3}
// {0}
// {0, -2, -3}
// {-2, -3}

logic :
prefix sum + map (prefix sum % k , index)
 */
public class ContinuesSubarrayDivisibleByK {
    public static int solution(int[] arr, int k){
       int ans = 0;
       HashMap<Integer, Integer> map = new HashMap<>();
       map.put(0, 1);
       int sum = 0;
       int rem = 0;
       for (int i = 0; i < arr.length; i++) {
           sum = sum + arr[i];
           rem = sum % k;
           if (rem < 0) {////to handle negetive scerario for example we got rem as -5 then we can add k in this to convert positivve
               //becoz on scale diffrence between -5 and 2 is 7 in case of k=7
               rem += k;
           }
           if (map.containsKey(rem)) {
                ans += rem;
                map.put(rem, map.get(rem) + 1);
           } else {
                map.put(rem, 1);
           }
       }
       return ans;
    }

    public static void findsubarray(int []arr, int k){
        int sum=0;
        HashMap<Integer,Integer> map= new HashMap<>();
        for(int i=0; i<arr.length ; i++){
            sum=sum+arr[i];
            if(map.containsKey(sum%k)) {
                System.out.println(map.get(sum%k) +" to "+ i);
                System.out.print("{");
                for(int l=map.get(sum%k); l<=i; l++) {
                    System.out.print(" " + arr[l]);
                }
                System.out.println("}");

            }  else {
                map.put(sum % k, i);
            }
        }

    }
    public static void print(HashMap<Integer, List<Integer>> mapRemIndex,int[] arr ) {
        System.out.println(mapRemIndex);
        for(Integer key:mapRemIndex.keySet()){
            List<Integer> value = mapRemIndex.get(key);
            if(value.size()>1) {
                //0 1,0 2, 03, 12, 13,23
                System.out.println("***************************" + value);
                for(int i=0;i<value.size()-1;i++) {
                    for(int j=i+1; j<value.size();j++){
                        System.out.print("{");
                        for(int k=value.get(i);k<=value.get(j);k++)
                            System.out.print(arr[k] +" ");
                        System.out.println("}");
                    }
                }
            }
        }
    }
    public static int printSubarraySolution(int[] arr, int k){
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, List<Integer>> mapRemIndex = new HashMap<>();
        List<Integer> value1 = new ArrayList<>();
        value1.add(0);
        mapRemIndex.put(0,value1);
        map.put(0, 1);
        int sum = 0;
        int rem = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            rem = sum % k;
            if (rem < 0) {////to handle negetive scerario for example we got rem as -5 then we can add k in this to convert positivve
                //becoz on scale diffrence between -5 and 2 is 7 in case of k=7
                rem += k;
            }
            if (map.containsKey(rem)) {
                ans += map.get(rem);
                map.put(rem, map.get(rem) + 1);
                List<Integer> value = mapRemIndex.get(rem);
                value.add(i);
                //print( value,i);
            } else {
                map.put(rem, 1);
                List<Integer> value = new ArrayList<>();
                value.add(i);
                mapRemIndex.put(rem,value);
            }
        }
        print(mapRemIndex,arr);
        return ans;
    }


    public static void main(String[] args) {
        int []arr=new int[]{4, 5, 0, -2, -3, 1}; // prefix sum (23,25,29,35,42)
        //findsubarray(arr,5);
        System.out.println(printSubarraySolution(arr,5));

    }
}
