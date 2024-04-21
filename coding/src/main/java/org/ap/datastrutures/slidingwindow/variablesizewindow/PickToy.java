package org.ap.datastrutures.slidingwindow.variablesizewindow;

import java.util.HashMap;

import static java.lang.Math.max;

public class PickToy {
    //pick toys in line should pick in sequence
    //max 2 typ of toys he can pick k=2
    //Give max no oftoys to jhon
    String s = "abaccab";
    public static int solve(String s, int k){

        int maxNoOfToys = 0;
        HashMap<Character,Integer> map = new HashMap<>();

        for(int i=0,j=0;j<s.length();){
            int currWindow = j-i+1;
            map.put(s.toCharArray()[j],map.getOrDefault(s.toCharArray()[j],0)+1);

            if(map.size() < k ){ //no of unique char should be k
                j++;
            }else if(map.size() == k){
                maxNoOfToys = Math.max(maxNoOfToys,currWindow);  //store better window size in mx variable
                j++;
            } else if(map.size() > k){
                while(map.size() > k){
                    if (map.get(s.toCharArray()[i])!=null) {
                        int val = map.get(s.toCharArray()[i]);
                        if (val ==0) map.remove(s.toCharArray()[i]);
                        else  map.put(s.toCharArray()[i],map.get(val-1));
                        i++;
                    }
                    j++;
                }
            }
        }
        return maxNoOfToys;
    }

    public static void main(String[] args) {
        String s = "abaccab";
        int k =2;
        System.out.println(solve(s,k));
    }
}
