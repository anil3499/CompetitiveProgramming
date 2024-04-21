package org.ap.datastrutures.array.array;

import java.util.HashMap;
import java.util.Scanner;
/*
Input : arr[] = {10, 2, -2, -20, 10},
        k = -10
Output : 3
Subarrays: arr[0...3], arr[1...4], arr[3..4]
have sum exactly equal to -10.

Input : arr[] = {9, 4, 20, 3, 10, 5},
            k = 33
Output : 2
Subarrays : arr[0...2], arr[2...4] have sum
exactly equal to 33.

prefix sum:
if x is prefix sum at any position thn
going forward if we get x+k as prefix sum than
sum from x position to x+k position will be K

 */
public class CountOfSubarraysHavingSumEqualsToK {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n; i++){
            arr[i] = scn.nextInt();
        }
        int target = scn.nextInt();
        System.out.println(solution(arr,target));
    }

    public static int solution(int[] arr, int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        int prefixSum = 0;
        int ans = 0;
        map.put(0,1);
        for(int i = 0 ; i < arr.length; i++){
            prefixSum = prefixSum +  arr[i];
            if(map.containsKey(prefixSum - target)){
                ans =ans + map.get(prefixSum - target);
            }
            map.put(prefixSum,map.getOrDefault(prefixSum,0) + 1);
        }
        return ans;
    }

}




