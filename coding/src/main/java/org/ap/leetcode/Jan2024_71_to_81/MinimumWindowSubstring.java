package org.ap.leetcode.Jan2024_71_to_81;

import java.util.HashMap;
import java.util.Map;
//https://leetcode.com/problems/minimum-window-substring/description/
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        String result ="";
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0; i<t.length(); i++)
            map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)+1);

        int count =map.size();

        for(int i=0, j=0; j<s.length(); ){
            map.put(s.charAt(j),map.getOrDefault(s.charAt(j),0)-1);
            if(map.get(s.charAt(j))==0)
                count--;
            if(count==0){
                while(count==0){
                    if(result.equals("") || result.length()>j-i+1) {
                        result = s.substring(i,j+1);
                    }
                    map.put(s.charAt(i), map.get(s.charAt(i))+1);
                    if(map.get(s.charAt(i))>0) // whenwe have postive value then only we will have count increase
                        count++;
                    i++;
                }
            }
            j++;
        }
        return result;
    }
    public String minWindow1(String s, String t) {
        String result ="";
        int count =0;
        Map<Character,Integer> tMap = new HashMap<>();
        for(int i=0; i<t.length(); i++)
            tMap.put(t.charAt(i),tMap.getOrDefault(t.charAt(i),0)+1);

        Map<Character,Integer> sMap = new HashMap<>();
        for(int i=0, j=0; j<s.length(); ){
            sMap.put(s.charAt(j),sMap.getOrDefault(s.charAt(j),0)+1);
            count++;
            if(!compareMap(sMap,tMap)){
                j++;
            }else {
                while(compareMap(sMap,tMap)==true){
                    if(result.equals("") || result.length()>j-i+1) {
                        //System.out.println(result);
                        result = s.substring(i,j+1);
                        //System.out.println("new result "+result);
                    }
                    //System.out.println(sMap +" i " +i +" j "+j);
                    sMap.put(s.charAt(i), sMap.get(s.charAt(i))-1);
                    count--;
                    if(sMap.get(s.charAt(i))==0)
                        sMap.remove(s.charAt(i));
                    i++;
                }
                j++;
            }
        }
        return result;
    }
    public boolean compareMap(Map<Character,Integer> sMap, Map<Character,Integer> tMap) {
        for(Character tMapChar: tMap.keySet()){
            if(sMap.containsKey(tMapChar)==false) return false;
            if(sMap.get(tMapChar) < tMap.get(tMapChar)) return false;
        }
        return true;
    }
}
