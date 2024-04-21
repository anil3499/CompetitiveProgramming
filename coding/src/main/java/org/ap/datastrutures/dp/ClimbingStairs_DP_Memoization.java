package org.ap.datastrutures.dp;

import java.util.Scanner;

/*
https://www.geeksforgeeks.org/count-ways-reach-nth-stair/
 */
public class ClimbingStairs_DP_Memoization {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter stair count :");
        int stair=sc.nextInt();
        int []qb=new int[stair+1];
        System.out.println(stairClimb(stair, qb));
    }

    public static int stairClimb(int k, int []qb){
        if(k==0){
            return 1;
        }
        if(k<0){
            return 0;
        }
        if(qb[k] > 0){
            return qb[k];
        }
        int k1 = stairClimb(k-1,qb);
        int k2 = stairClimb(k-2,qb);
        int k3 = stairClimb(k-3,qb);
        int cp=k1+k2+k3;
        qb[k]=cp;
        return cp;

    }
}
