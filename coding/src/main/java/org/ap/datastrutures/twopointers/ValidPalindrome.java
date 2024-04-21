package org.ap.datastrutures.twopointers;

import java.util.Collections;

//https://leetcode.com/problems/valid-palindrome/?envType=study-plan-v2&envId=top-interview-150
public class ValidPalindrome {
    public boolean is_valid_palindrome(String s){
        boolean flag = true;
        for( int i=0,j=s.length()-1; i<j; i++, j--){
            if(s.charAt(i) !=s.charAt(j)){
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        String s =  "A man, a plan, a canal: Panama";
        s = s.toLowerCase();
        s = s.replaceAll("[^a-zA-Z]", "");
        System.out.println(new ValidPalindrome().is_valid_palindrome(s));
    }
}


