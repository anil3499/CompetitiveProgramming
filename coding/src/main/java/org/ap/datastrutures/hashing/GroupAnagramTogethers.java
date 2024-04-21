package org.ap.datastrutures.hashing;

import java.util.*;
/*
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class GroupAnagramTogethers {

    public static void gatg(String []str){
        HashMap<HashMap<Character, Integer>, List<String>> bmap=new HashMap<>();
        for(String s: str){
            HashMap<Character,Integer> fmap=new HashMap<>();;
            for(Character ch: s.toCharArray()){
                fmap.put(ch, fmap.getOrDefault(ch,0)+1);
            }
            if(bmap.containsKey(fmap)){
                bmap.get(fmap).add(s);
            }else{
                List<String> sl=new ArrayList<>();
                sl.add(s);
                bmap.put(fmap,sl);
            }

        }
        for(Map.Entry<HashMap<Character, Integer>, List<String>> entry: bmap.entrySet()){
            entry.getValue().forEach(e -> System.out.print(e + " "));
            System.out.println();
        }

    }

    static final int MAX_CHAR = 26;

    // Generates a key from given string. The key
    // contains all unique characters of given string
    // in sorted order consisting of only distinct elements.
    static String getKey(String str)
    {
        boolean[] visited = new boolean[MAX_CHAR];
        Arrays.fill(visited, false);

        // store all unique characters of current
        // word in key
        for (int j = 0; j < str.length(); j++)
            visited[str.charAt(j) - 'a'] = true ;
        String key = "";
        for (int j=0; j < MAX_CHAR; j++)
            if (visited[j])
                key = key + (char)('a'+j);
        return key;
    }

    static void wordsWithSameCharSet(String words[], int n) {
        // Stores indexes of all words that have same
        // set of unique characters.
        //unordered_map <string, vector <int> > Hash;
        HashMap<String, ArrayList<Integer>> Hash = new HashMap<>();

        // Traverse all words
        for (int i = 0; i < n; i++) {
            String key = getKey(words[i]);

            // if the key is already in the map
            // then get its corresponding value
            // and update the list and put it in the map
            if (Hash.containsKey(key)) {
                ArrayList<Integer> get_al = Hash.get(key);
                get_al.add(i);
                Hash.put(key, get_al);
            }

            // if key is not present in the map
            // then create a new list and add
            // both key and the list
            else {
                ArrayList<Integer> new_al = new ArrayList<>();
                new_al.add(i);
                Hash.put(key, new_al);
            }
        }
    }

    public static void main(String[] args) {
        String []str= new String[]{"abc","cbd","ejk", "cba","jek", "bca"};
        gatg(str);
    }
}
