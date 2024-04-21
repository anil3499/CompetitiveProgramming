package org.ap.datastrutures.array.array;


/*
https://www.techiedelight.com/longest-increasing-subsequence/
{2,6,3,4,1,2,9,5,8}
logic :
Initialize to an empty set S = {}
Inserting 2 -- S = {2} — New largest LIS
Inserting 6 -- S = {2, 6} — New largest LIS
Inserting 3 -- S = {2, 3} — Replaced 6 with 3
Inserting 4 -- S = {2, 3, 4} — New largest LIS
Inserting 1 -- S = {1, 3, 4} — Replaced 2 with 1
Inserting 2 -- S = {1, 2, 4} — Replaced 3 with 2
Inserting 9 -- S = {1, 2, 4, 9} — New largest LIS
Inserting 5 -- S = {1, 2, 4, 5} — Replaced 9 with 5
Inserting 8 -- S = {1, 2, 4, 5, 8} — New largest LIS

Logic : if X is more than last element then append X at the end of Set <found new largest>
else find the samllest element in Set which is more than or equal to X and replace it with X
 */

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequenceWithoutDP {

    public static int findLISSize(int []arr){
        List<Integer> temp=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
          if(temp.contains(arr[i])){
              continue;
          }
          if(temp.isEmpty()){
              temp.add(arr[i]);
          }
          if(temp.get(temp.size()-1) < arr[i]){
              temp.add(arr[i]);
          }else{
              for(int j=0;j<temp.size();j++){
                  if(temp.get(j) > arr[i]){
                      temp.set(j,arr[i]);
                      break;
                  }
              }
          }
        }
        temp.forEach(e-> System.out.print(e + " , "));
        return temp.size();
    }

    public static void main(String[] args) {
        int []arr={2,6,3,4,1,2,9,5,8};
        System.out.println("Size of LIS " +findLISSize(arr));

        int []arr1={0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println("Size of LIS "+findLISSize(arr1));

    }


}
