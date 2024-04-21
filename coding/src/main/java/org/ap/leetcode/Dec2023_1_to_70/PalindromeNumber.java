package org.ap.leetcode.Dec2023_1_to_70;
///https://leetcode.com/problems/palindrome-number/
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        int y =x;
        int reversed =0;

        while(y>0){
            int lastdigit = y%10;
            reversed = reversed*10 + lastdigit;
            System.out.println(reversed);
            y=y/10;
        }
        System.out.println(reversed);
        if(x==reversed) return true;
        return false;
    }
}
