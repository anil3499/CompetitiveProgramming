package org.ap.leetcode.Dec2023_1_to_70;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/zigzag-conversion/
public class StringZigZagConversion {
    public String convert(String s, int numRows) {
        if(numRows==1) return s;
        String result = "";
        for(int row =0;row<numRows; row++){
            int incrementFactor = 2*(numRows-1);
            for(int i=row; i< s.length() ;i+=incrementFactor) {
                result +=s.charAt(i);
                //special casefor between rows
                if(row > 0 && row < numRows-1 &&
                        (i + incrementFactor - 2 * row)  < s.length()) {
                    result+=s.charAt(i + incrementFactor - 2 * row);
                }
            }
        }

        return result;
    }

    public String convert1(String s, int numRows) {
        if(numRows==1) return s;
        Map<Integer,String> map= new HashMap<>();
        for(int i=0;i<numRows;i++) {
            map.put(i,"");
        }
        int k=0;
        boolean flag = false;
        while(true) {

            int i=0;
            for(;i< numRows; i++){
                if(k<s.length()) {
                    map.put(i,map.get(i)+s.charAt(k));
                    k++;
                } else {
                    flag = true;
                    break;
                }
            }
            for(int j=i-2; j >0 ; j--){
                if(k<s.length()) {
                    map.put(j,map.get(j)+s.charAt(k));
                    k++;
                }else  {
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
        }
        String result ="";
        for(int i=0;i<numRows;i++) {
            result += map.get(i);
        }
        return result;
    }
}
