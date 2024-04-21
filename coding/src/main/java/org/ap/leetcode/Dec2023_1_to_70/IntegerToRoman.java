package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/integer-to-roman/description/
public class IntegerToRoman {
    public String intToRoman(int x) {
        int[] num = new int [] {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] roman = new String[] {"M","CM", "D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder result =new StringBuilder();
        for(int i=0;i<num.length;i++){
            while(x>=num[i]){
                if(x>=num[i]) {
                    result.append(roman[i]);
                    x=x-num[i];
                }
            }
        }
        return result.toString();
    }
}
