package org.ap.datastrutures.array.array;
/*

The task is to find the length of substring which is having maximum
difference of number of 0s and number of 1s
Input : S = "11000010001"
Output : 6
From index 2 to index 9, there are 7
0s and 1 1s, so number of 0s - number
of 1s is 6.

logic:
1. consider 1 as -1 and 0 as 1
2. subarray with max sum will have max diff 1s and 0s
basically apply Kadanes algorithm
 */

import java.util.Scanner;

public class MaximumDifferenceOfZerosAndOnes_Kadanes {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        System.out.println(solution(str));
    }

    public static int solution(String str) {
        int ans = 0;
        int currSum = 0;
        for (int i = 0; i < str.length(); i++) {
            currSum += str.charAt(i) == '0' ? 1 : -1;
            if (currSum < 0) {
                currSum = 0;
            }
            ans = Math.max(ans, currSum);
        }
        return ans == 0 ? -1 : ans;
    }

}
