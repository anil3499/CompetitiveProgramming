package org.ap.datastrutures.string;
//1071 leetcode
//https://leetcode.com/problems/greatest-common-divisor-of-strings/description/
public class GCDOfStrings {
    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        // we will start loop from min of strings length and go till 1 since substrin 0,0 will give empty string
        for(int i = Math.min(len1,len2); i > 0; i--) {
            String gcdString = str1.substring(0,i);
            if(isDivisor(i, len1, len2, gcdString, str1, str2)) {
                return gcdString;
            }
        }
        return "";
    }
    public boolean isDivisor(int k, int len1, int len2, String gcdString, String str1, String str2) {
        if (len1 % k >0 || len2 % k >0) { // means we cannot form string if length of string is not with multiple of k
            return false;
        }
        return str1.replace(gcdString,"").isEmpty() && str2.replace(gcdString,"").isEmpty();
    }
}
