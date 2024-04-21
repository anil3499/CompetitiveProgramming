package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.List;

public class MultiplyStrings {
    public static void main(String[] args) {
        System.out.println("final output " +multiply("456","123"));
    }
    public static String multiply(String s1, String s2) {
        if(s1.equals("0") || s2.equals("0")) return "0";
        if(s1.equals("1") && s2.equals("1")) return s1;
        if(s1.equals("1")) return s2;
        if(s2.equals("1")) return s1;

        //s2 should be smaller in length which willbe our multiplyer
        if(s1.length()< s2.length())
            return multiply(s2,s1);
        List<String> intermediateResult= new ArrayList<>();
        int i= s2.length()-1, l=0;
        while(i>=0) { // i on s2
            int carry=0,sum =0;
            String resultLocal = "";
            for(int k =0;k<l;k++)
                resultLocal= '0' + resultLocal;

            int j = s1.length()-1;
            int s2digit = (int)s2.charAt(i)-(int)'0';
            while(j>=0){ //j on s1
                int s1digit = (int)s1.charAt(j)-(int)'0';
                sum = sum +  (s1digit * s2digit);
                carry = sum%10;
                sum= sum/10;
                resultLocal= carry + resultLocal;
                j--;
            }

            if(sum>0)
                resultLocal= sum + resultLocal;
            // System.out.println(s1 +" " +s2 +" " +resultLocal);
            intermediateResult.add(resultLocal);
            i--; l++;
        }
        String result ="";
        for(String str : intermediateResult){
            result = add(str,result);
        }
        return result;
    }

    public static String add(String s1, String s2){
        if(s1=="" && s2=="") return "";
        if(s1=="") return s2;
        if(s2=="") return s1;
        //always s1 should be small
        if(s1.length()>s2.length()) {
            return add(s2, s1);
        }
        //System.out.println(s1 +" " + s2);

        String result ="";
        int carry,sum=0,i=s1.length()-1, j=s2.length()-1;
        while(i>=0){
            int s1dig = s1.charAt(i)-(int) '0';
            int s2dig = s2.charAt(j)-(int) '0';
            sum = sum+ s1dig + s2dig;
            carry = sum%10;
            sum/=10;
            result =carry + result;
            i--;j--;
        }
        while(j>=0) {
            int s2dig = s2.charAt(j)-(int) '0';
            sum = sum + s2dig;
            carry = sum%10;
            sum/=10;
            result =carry + result;
            carry=0;
            j--;
        }
        if (sum > 0)
            result = sum + result;
        return result;
    }
}
