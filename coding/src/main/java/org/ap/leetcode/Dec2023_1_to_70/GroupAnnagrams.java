package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/group-anagrams/
public class GroupAnnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Map<Character,Integer>, List<String>> map= new HashMap<>();
        for(String str:strs) {
            Map<Character,Integer> inner =new HashMap<>();
            for(int i=0; i<str.length();i++)
                inner.put(str.charAt(i), inner.getOrDefault(str.charAt(i),0)+1);
            if(map.get(inner)==null){
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(inner,list);
            }else{
                map.get(inner).add(str);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for(Map inner: map.keySet()){
            result.add(map.get(inner));
        }
        return result;
    }
}
