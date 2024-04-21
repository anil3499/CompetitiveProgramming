package org.ap.datastrutures.string;

import java.util.*;

public class AutoCorrecPrototype {
    public static void main(String[] args) {
        String[] words = new String[]{"duel","speed","dule","cars"};
        String[] queries = new String[]{"spede","deul"};
        //output [["speed"],["duel","dule"]]
        System.out.println(autocorrectPrototype1(words,queries));
    }
    public static List<List<String>> autocorrectPrototype(String[] words, String[] queries){
        List<List<String>> result = new ArrayList<>();
        Map<Character,Integer>[]  mapArr = new HashMap[words.length];
        for(int i=0; i<words.length; i++){
            Map<Character,Integer> map = new HashMap<>();
            for(char ch: words[i].toCharArray()){
                map.put(ch,map.getOrDefault(ch,0)+1);
            }
            mapArr[i] = map;
        }
        for(String query : queries){
            List<String> res = new ArrayList<>();
            for(int i=0; i<words.length; i++){
                System.out.println(query + " " + words[i] + " " +i);
                if(query.length() == words[i].length()){
                    Map<Character,Integer> map = mapArr[i];
                    System.out.println(map);
                    boolean flag = true;
                    for(char ch: query.toCharArray()){
                        if(!map.containsKey(ch)) flag=false;
                    }
                    if (flag) {
                        for (char ch : query.toCharArray()) {
                            map.put(ch, map.get(ch) - 1);
                            if (map.get(ch) == 0)
                                map.remove(ch);
                        }
                        if (map.size() == 0) {
                            res.add(words[i]);
                        }
                    }
                }
            }
            result.add(res);
            }
    return result;
    }

    public static List<List<String>> autocorrectPrototype1(String[] words, String[] queries){
        List<List<String>> result = new ArrayList<>();
        Map<Map<Character,Integer>,List<String>> queryMap = new LinkedHashMap<>();

        for(String query : queries){
            HashMap<Character,Integer> chMap = new HashMap<>();
            for(char ch : query.toCharArray()){
                chMap.put(ch,chMap.getOrDefault(ch,0)+1);
            }
            queryMap.put(chMap,new ArrayList<>());
        }

        for(String word: words){
            HashMap<Character,Integer> chMap = new HashMap<>();
            for(char ch : word.toCharArray()){
                chMap.put(ch,chMap.getOrDefault(ch,0)+1);
            }
            if(queryMap.containsKey(chMap)) {
                queryMap.get(chMap).add(word);
            }
        }
        for(Map key : queryMap.keySet()){
            result.add(queryMap.get(key));
        }
        return result;
    }
}
