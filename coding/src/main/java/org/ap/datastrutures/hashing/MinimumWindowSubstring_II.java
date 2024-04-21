package org.ap.datastrutures.hashing;
/*

smallest-substring-of-a-string-containing-all-unique-characters-of-itself

Input: aabcbcdbca
Output: dbca
Explanation:
Possible substrings= {aabcbcd, abcbcd,
bcdbca, dbca....}
Of the set of possible substrings 'dbca'
is the shortest substring having all the
distinct characters of given string.

Input: aaab
Output: ab
Explanation:
Possible substrings={aaab, aab, ab}
Of the set of possible substrings 'ab'
is the shortest substring having all
the distinct characters of given string.
 */

import java.util.*;

public class MinimumWindowSubstring_II {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        System.out.println(solution(str));
    }

    public static int solution(String str){
        HashMap<Character,Integer> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        for(int i = 0 ; i < str.length(); i++){
            char ch = str.charAt(i);
            set.add(ch);
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0, j = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            map.put(ch,map.getOrDefault(ch,0) + 1);
            while(map.size() == set.size() && j < str.length()){
                char chj = str.charAt(j);
                map.put(chj,map.get(chj) - 1);
                if(map.get(chj) <= 0){
                    map.remove(chj);
                    ans = Math.min(ans,i - j + 1);
                }
                j++;
            }
        }
        return ans;
    }

}




