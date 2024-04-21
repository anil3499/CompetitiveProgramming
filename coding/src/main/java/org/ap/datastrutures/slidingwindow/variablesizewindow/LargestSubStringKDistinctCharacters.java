package org.ap.datastrutures.slidingwindow.variablesizewindow;

import java.util.*;

import static java.lang.Math.max;

//geeneral format
//while(j<size){
//         calculations
//          if(condition_from_prob < k)
//                  j++
//          elseif (winsize ==k)
//          {
//              ans  <<---- calculatios
//              j++
//            }
//          else if (condition >k){
//              while(condition >k)
//              {
//                  remove elements from sum
//                  i--
//             }
//              search for possible candidate here by max/min
//              j++
//
//          }
// }
public class LargestSubStringKDistinctCharacters  {
    public static int noOfAllUniqueChars(String s,int k){

        int noOfUniqueChars = 0;

        HashMap<Character,Integer> map = new HashMap();
        for(int i=0,j=0;j < s.length();){

            int window = j-i +1;// window size , map size ko window size se comare krege

            map.put(s.toCharArray()[j],map.getOrDefault(s.toCharArray()[j],0)+1);

            if(map.size() < k ){
                j++;
            } else if(map.size() == k){
                noOfUniqueChars = Math.max(noOfUniqueChars,window); //strong better window size in mx variable
                j++;
            } else{ // if(map.size() > k){  //changes here on cndition
                while(map.size() > k){//changes here on cndition
                    //if window ka size map k size se match nhi kr rha matlb we have some repeating chars in our map
                    //so we will remove chars fromm map untill we dont make ou map size equal to window size
                    if (map.get(s.toCharArray()[i]) != null)
                        map.put(s.toCharArray()[i], map.get(s.toCharArray()[i])-1);
                    if (map.get(s.toCharArray()[i]) == 0)
                            map.remove(s.toCharArray()[i]);
                    i++;
                }
                j++;
            }
        }
        return noOfUniqueChars;
    }
    public static void main(String[] args) {
        String s = "pwwkew";
        int k = 3;
        System.out.println(noOfAllUniqueChars(s,k));
    }

}
