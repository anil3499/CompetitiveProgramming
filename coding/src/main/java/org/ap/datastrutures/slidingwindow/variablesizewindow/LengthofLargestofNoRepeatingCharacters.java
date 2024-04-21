package org.ap.datastrutures.slidingwindow.variablesizewindow;

import java.util.HashMap;

import static java.lang.Math.max;

public class LengthofLargestofNoRepeatingCharacters {
    public static int noOfUniqueCharaxteers(String s){
        int i =0;
        int j =0;
        int noOfUniqueChars = 0;

        HashMap<Character,Integer> map = new HashMap<>();
        while (j<s.length()){
            int window = j-i +1;// window size , map size ko window size se comare krege

            map.put(s.toCharArray()[j],map.getOrDefault(s.toCharArray()[j],0)+1);

            //This condition will never hit
            //map size says noo of cahars in the window, and if map ssize is 4 window size cant b more than map size
            //if(map.size() >  window ){
            //    j++;
            //}else
            if(map.size() == window){
                noOfUniqueChars = Math.max(noOfUniqueChars, window); //strong better window size in mx variable
                j++;
            } else if(map.size() < window) {  //changes here on cndition
                while(map.size() < window) {//changes here on cndition
                    //if window ka size map k size se match nhi kr rha matlb we have some repeating chars in our map
                    //so we will remove chars fromm map untill we dont make ou map size equal to window size
                    if (map.get(s.toCharArray()[i]) != null) {
                        int val = map.get(s.toCharArray()[i]);
                        if (val == 0)
                            map.remove(s.toCharArray()[i]);
                        else
                            map.put(s.toCharArray()[i], map.get(val - 1));
                        i++;
                    }
                }
                j++;
            }
        }
        return noOfUniqueChars;
    }
    public static void main(String[] args) {
        String s = "pvwkew";
        int k = 2;
        System.out.println(noOfUniqueCharaxteers(s));
    }
}
