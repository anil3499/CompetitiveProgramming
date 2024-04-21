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
public class LongestSubstringWithExactlyKUniqueCharacters {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        int k = scn.nextInt();
        System.out.println(solution(str,k));
    }

    public static int solution(String str, int k){
        int maxLen = -1;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0, j = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if (map.size() == k) {
                maxLen = Math.max(maxLen, i - j + 1);
            } else {
                while (map.size() > k) {
                    char l = str.charAt(j);
                    int count = map.get(l);
                    if (count == 1) {
                        map.remove(l);
                    } else {
                        map.put(l, map.get(l) - 1);
                    }
                    j++;
                }
            }
        }
        return maxLen;
    }

}




