package org.ap.datastrutures.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
public class IndexOfAllAnagramOfSpringPInStingA {
    public static void main(String[] args) {
        String s = "cbaecabacd";
        String p = "abc";
        System.out.println(finadAllAnnagramindexes(s,p));
    }
    public static List<Integer> finadAllAnnagramindexes(String s,String p){
        List<Integer> ans = new ArrayList<>();
        Map<HashMap<Character,Integer>,String> pMap = new HashMap<>();
        HashMap<Character,Integer> chMap = new HashMap<>();
        for(char ch : p.toCharArray())
            chMap.put(ch,chMap.getOrDefault(ch,0)+1);
        pMap.put(chMap,null);

        HashMap<Character,Integer> sMap = new HashMap<>();
        int windowSize = p.length();
        for(int i =0,j=0; j<s.length(); ){
            sMap.put(s.charAt(j),sMap.getOrDefault(s.charAt(j),0)+1);

            if(j-i+1 < windowSize){
                j++;
            }else {
                if(pMap.containsKey(sMap)) {
                    ans.add(i);
                }
                sMap.put(s.charAt(i), sMap.get(s.charAt(i))-1);
                if(sMap.get(s.charAt(i)) == 0)
                    sMap.remove(s.charAt(i));
                i++;
                j++;
            }
        }
        return ans;
    }
}
