package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/string-to-integer-atoi/
public class StringToIntegerAToI {
    public int myAtoi1(String s) {
        int sign=1;
        int result=0;
        int i=0;
        int n=s.length();
        while(i<n && s.charAt(i)==' ')
            i++;

        if(i<n && s.charAt(i)=='+')
        {
            sign=1;
            i++;
        }
        else if(i<n && s.charAt(i)=='-')
        {
            sign=-1;
            i++;
        }


        while(i<n && 0<=(int)s.charAt(i)-'0' && (int)s.charAt(i)-'0'<=9 )
        {
            if(result>Integer.MAX_VALUE/10 ||
                    (result==Integer.MAX_VALUE/10 && (int)(s.charAt(i)-'0')>Integer.MAX_VALUE%10))
            {
                return result=(sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE);
            }
            result*=10;
            result+=((int)s.charAt(i)-'0');
            i++;
        }
        return result*sign;
    }

    public int myAtoi(String s) {
        int result =0;
        int n= s.length();
        int i=0;
        int sign = 1;

        while(i<n && (s.charAt(i)==' '))
            i++;
        if(i<n && s.charAt(i) =='+'){
            i++;
            sign =1;
        }
        else if(i<n && s.charAt(i) =='-'){
            sign=-1;
            i++;
        }

        while(i<n && (int)s.charAt(i) >= (int)'0' && (int)s.charAt(i)<=(int)'9' )
        {

            if(result > Integer.MAX_VALUE/10 || //either result become greater than integer limit
                    (result==Integer.MAX_VALUE/10 && (int)(s.charAt(i)-'0')>Integer.MAX_VALUE%10)) //or result last digit is leeser than the digit we are reading
            {
                if(sign==1) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            }
            result*=10;
            result+=((int)s.charAt(i)-'0');
            i++;
        }
        return result*sign;
    }
}
