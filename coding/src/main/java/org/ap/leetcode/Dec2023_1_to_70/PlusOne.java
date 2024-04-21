package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/plus-one/description/
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int carry =0;
        for(int i= digits.length-1;i>=0; i--){
            int digit =  digits[i];
            if(i==digits.length-1)
                digit= carry + digit +1;
            else digit+=carry;
            digits[i] = digit%10;
            carry = digit/10;
            if(carry==0){
                break;
            }
        }
        for(int k=0; k<digits.length; k++)
            System.out.println(digits[k]);
        int[] result;
        int i=0;
        if(carry>0){
            result = new int[digits.length+1];
            result[0] = carry%10;
            carry/=10;
            i++;
            for( int j=0; j<digits.length;j++)
                result[i++] = digits[j];
            return result;
        }

        return digits;
    }
}
