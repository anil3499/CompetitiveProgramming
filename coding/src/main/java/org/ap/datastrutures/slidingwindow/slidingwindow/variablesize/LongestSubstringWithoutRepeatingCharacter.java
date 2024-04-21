package org.ap.datastrutures.slidingwindow.slidingwindow.variablesize;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacter {
    public static void longestSubstringWithoutRepeatingCharacter(String str){
        int j=0, i=0, max=Integer.MIN_VALUE;
        Map<Character , Integer> map=new HashMap<>();
        while(j < str.length()){
            map.put(str.charAt(j),map.getOrDefault(str.charAt(j),0)+1);
            //means map has same unique no of chars as thw window size means everything is unique
            if(map.size() == (j-i+1)){
                max=Math.max(max,j-i+1);
                j++;
                //it means map me koi duplicate char aa gya h so size reduced to window size
            }else if(map.size() < (j-i+1)){
                while(map.size() < (j-i+1) ){
                    map.put(str.charAt(i),map.get(str.charAt(i))-1);
                    if(map.get(str.charAt(i)) == 0){
                        map.remove(str.charAt(i));
                    }
                    i++;
                }
                j++;
            }
        }
        System.out.println("max length: " + max);
    }

    public static void main(String[] args) {
        String str="abcabcbb";
        longestSubstringWithoutRepeatingCharacter(str);

    }

}
