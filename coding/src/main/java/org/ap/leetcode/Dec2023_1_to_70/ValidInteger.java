package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/valid-number/description/
public class ValidInteger {
    public boolean isNumber(String s) {
        //e k bad no aana hi chiye
        // double sign together is inavalid
        //other chars apart fro e are invalid
        //after edecimal can not come
        //e can not come twise
        //only e is not valid

        //only decimal not valid
        //

        //digitseen
        //eseen
        //dotseen
        //signcount

        boolean digitSeen=false,eSeen=false,dotSeen=false;
        int signCount=0;

        for(int i=0;i<s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {//digit scenario
                digitSeen=true;
            }else if(ch=='+' || ch=='-'){ //sign
                if(signCount ==2)
                    return false;
                //what if sign is on e
                if(i>0 && (s.charAt(i-1)!='e' && s.charAt(i-1)!='E'))
                    return false;
                //sign at the last of strig
                if(i==s.length()-1)
                    return false;
                signCount++;
            }else if(ch=='.'){ //if char is dot
                if(eSeen || dotSeen)
                    return false;
                if( i==s.length()-1 && !digitSeen)
                    return false;
                dotSeen=true;
            }else if(ch=='e' || ch=='E'){
                if(eSeen || !digitSeen || i == s.length()-1)
                    return false;
                eSeen=true;
            }else{
                return false;
            }
        }
        return true;
    }
}
