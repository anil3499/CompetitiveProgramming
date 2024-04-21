package org.ap.datastrutures.slidingwindow.variablesizewindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSustring {
    String a = "live to practice";
    String b = "toc";
    //b string k sare letters atleast same quantity  me a string me present hona chiye
    // suppose b me 2 quantty me present h the a me more than equal to 2 bar present hona chiye
    //s="timetopractice"
    //t = "toc"
    public static int findMinimumWindowSubstring(String s, String t){
        //create a map and store substring and create a cuount var
        int count = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(int i =0; i<t.length();i++)
            map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)+1);
        count = map.size();
        int maxWindowSize = -1;
        //variable window
        for(int i=0,j=0; j<s.length();){
                map.put(s.charAt(j),map.getOrDefault(s.charAt(j),0)-1);
                if(map.get(s.charAt(j))==0)
                    count--;

                if(count!=0){
                    j++;
                }else {
                    while(count==0){
                        maxWindowSize = Math.max(maxWindowSize,j-i+1);

                        map.put(s.charAt(i),map.get(s.charAt(i))+1);

                        if(map.get(s.charAt(i))>0)
                            count++;

                        i++;
                    }
                    j++;
                }
        }
        return maxWindowSize;
    }
}
