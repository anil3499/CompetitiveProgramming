package org.ap.datastrutures.hashing;

import java.util.*;
/*
"aabbcc", k = 1
Max substring can be any one from {"aa" , "bb" , "cc"}.

"aabbcc", k = 2
Max substring can be any one from {"aabb" , "bbcc"}.

"aabbcc", k = 3
There are substrings with exactly 3 unique characters
{"aabbcc" , "abbcc" , "aabbc" , "abbc" }
Max is "aabbcc" with length 6.

"aaabbb", k = 3
There are only two unique characters, thus show error message.
 */
public class LongestSubstringWithAtMostKUniqueCharacters {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        int k = scn.nextInt();
        System.out.println(solution(str,k));
    }

    public static int solution(String str, int k) {
        HashMap<Character,Integer> map = new HashMap<>();
        int ans = 0;
        for(int i = 0 , j = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            map.put(ch,map.getOrDefault(ch, 0) + 1);
            while(map.size() > k){
                char chj = str.charAt(j);
                map.put(chj,map.get(chj) - 1);
                if(map.get(chj) == 0){
                    map.remove(chj);
                }
                j++;
            }
            ans = Math.max(ans,i - j + 1);
        }
        return ans;
    }

}




