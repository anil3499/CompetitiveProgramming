package org.ap.datastrutures.stack;
/*
https://www.pepcoding.com/resources/online-java-foundation/stacks-and-queues/celebrity-problem-official/ojquestion

Input:        0  1  2  3
MATRIX = { 0 {0, 0, 1, 0},
           1 {0, 0, 1, 0},
           2  {0, 0, 0, 0},
           3  {0, 0, 1, 0} }
Output:id = 2
Explanation: The person with ID 2(row) does not
know anyone but everyone knows him

Who is celebrity?
1. every other member knows him
2. he dont know any body

1- indicate know him
0 - indicate dont know him

 */

import java.io.*;
import java.util.*;

public class CelebrityProblem {

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            for (int k = 0; k < n; k++) {
                arr[j][k] = line.charAt(k) - '0';
            }
        }

        findCelebrity(arr);
    }

    public static void findCelebrity(int[][] arr){
        // if a celebrity is there print it's index (not position), if there is not then print "none"
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < arr.length; i++){
            st.push(i);
        }

        while(st.size() > 1){
            int i = st.pop();
            int j = st.pop();

            if(arr[i][j] == 1){
                st.push(j);
            } else {
                st.push(i);
            }
        }

        int pot = st.pop();
        boolean flag = true;
        for(int i = 0; i < arr.length; i++){
            if(i != pot){
                if(arr[i][pot] == 0 || arr[pot][i] == 1){
                    flag = false;
                    break;
                }
            }
        }

        if(flag){
            System.out.println(pot);
        } else {
            System.out.println("none");
        }
    }

}



