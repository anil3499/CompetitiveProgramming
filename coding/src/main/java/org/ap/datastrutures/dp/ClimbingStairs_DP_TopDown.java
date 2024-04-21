package org.ap.datastrutures.dp;

import java.util.Scanner;

/*
ClimbingStairs_DP_TopDown.JGP

https://www.geeksforgeeks.org/count-ways-reach-nth-stair/

step1: storage and meaning
step2: identify direction
step3: travel and solve

logic:
at any position n we have to check n-1 , n-2, n-3 no. of ways and sum up where n-k >= 0
[0 1 2 3 4 5 6]
0 -> 1
1 -> 1(0th index no. of ways) // can not go -ve
2 -> 1(0th index no. of ways) + 1(1st index no. of ways) =2 // can not go -ve
3 -> 1 + 1 +2 = 4
4 -> 1 +2 +4 = 7
5 -> 2 +4 + 7 = 13
6 -> 4 + 7 + 13 = 24
 */
public class ClimbingStairs_DP_TopDown {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter stair count :");
        int stair=sc.nextInt();
        System.out.println(stairClimb(stair));
    }

    public static int stairClimb(int k){
        int []arr = new int[k+1];
        arr[0]=1;
        for(int i=1 ; i<=k ; i++){
            if(i==1){
                arr[i]=1;
            }
            if(i==2){
                arr[i]=2;
            }
            arr[i]=arr[i-1] +arr[i-2] +arr[i-3] ;
        }
        return arr[arr.length-1];

    }
}
