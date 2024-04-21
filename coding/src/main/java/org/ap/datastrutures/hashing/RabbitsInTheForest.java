package org.ap.datastrutures.hashing;

import java.util.*;
/*
Examples:
Input: answers = [1, 1, 2]
Output: 5
Explanation:
The two rabbits that answered "1" could both be the same color, say red.
The rabbit than answered "2" can't be red or the answers would be inconsistent.
Say the rabbit that answered "2" was blue.
Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.

Input: answers = [10, 10, 10]
Output: 11

Input: answers = []
Output: 0
 */
public class RabbitsInTheForest {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n; i++){
            arr[i] = scn.nextInt();
        }
        System.out.println(solution(arr));
    }

    private static int solution(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ;i < arr.length ;i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        ArrayList<Integer> keys = new ArrayList<Integer>(map.keySet());
        ArrayList<Integer> values = new ArrayList<Integer>(map.values());
        int ans = 0;
        for(int i = 0 ;i < keys.size() ; i++) {
            int key = keys.get(i);
            int val = values.get(i);
            if(key >= val) {
                ans += (key + 1);
            }else {
                if(val % (key + 1) == 0) {
                    ans += ((val / (key + 1)) * (key + 1));
                }else {
                    ans += (((val / (key + 1)) + 1) * (key + 1));
                }
            }
        }

        return ans;
    }

}




