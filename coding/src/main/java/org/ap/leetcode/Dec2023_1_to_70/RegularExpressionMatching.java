package org.ap.leetcode.Dec2023_1_to_70;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/regular-expression-matching/
//https://www.youtube.com/watch?v=HAA8mgxlov8
    public class RegularExpressionMatching {
    public boolean isMatch(String s, String p){
        //return patternMatchRecursive(s,p,0,0);
        Map<String,Boolean> map=new HashMap<>();
        return patternMatchRecursiveMemoize(s,p,0,0,map);
    }
    public boolean patternMatchRecursive(String s, String p , int i, int j){
        //both out ofbound meanswe have found our solution
        if(i>=s.length() && j>= p.length())
            return true;

        //yes ex String=a andpattern a*b*
        //if(i>=s.length() && j<p.length())
        //    return true;

        //only if j is out of bund and not i then string wont match ex input aa, patter=a
        if( j>=p.length()) //(i< s.length() && j>=p.length())
            return false;

        boolean match = i<s.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.');
        if(j+1 < p.length() && p.charAt(j+1)=='*') {
            boolean flag1 =false, flag2=false;
            //use star once only if characters are matches
            if(match)
                flag1 = patternMatchRecursive(s,p,i+1,j); // i will move but j will not move
            //do not use * so increase pointer j twice
            flag2 = patternMatchRecursive(s,p,i,j+2); //will not move i only j will move
            return flag1 || flag2;
        }
        if (match){
            return patternMatchRecursive(s,p,i+1,j+1);
        }        return false;
    }

    public boolean patternMatchRecursiveMemoize(String s, String p , int i, int j,Map<String,Boolean> map){
        String key = i +"_" +j;
        if(map.containsKey(key)) return map.get(key);
        //both out ofbound meanswe have found our solution
        if(i>=s.length() && j>= p.length())
            return true;

        //yes ex String=a andpattern a*b*
        // if(i>=s.length() && j<p.length())
        //   return true;

        //only if j is out of bund and not i then string wont match ex input aa, patter=a
        if( j>=p.length()) //(i< s.length() && j>=p.length())
            return false;

        boolean match = i<s.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.');
        if(j+1 < p.length() && p.charAt(j+1)=='*') {
            boolean flag1 =false, flag2=false;
            //use star once only if characters are matches
            if(match)
                flag1 = patternMatchRecursiveMemoize(s,p,i+1,j,map); // i will move but j will not move
            //do not use * so increase pointer j twice
            flag2 = patternMatchRecursiveMemoize(s,p,i,j+2,map); //will not move i only j will move
            map.put(key, flag1 || flag2);
            return map.get(key);
        }
        if (match){
            boolean res = patternMatchRecursiveMemoize(s,p,i+1,j+1,map);
            map.put(key,res);
            return map.get(key);
        }
        map.put(key,false);
        return map.get(key);
    }

        public boolean isMatch1(String s, String p) {
        boolean ans = false;
        int i=0,j=0;
        char prevCh =' ';
        char prevPCh=' ';
        while(j<s.length() && i<p.length()){
            System.out.println("str " + s.charAt(j) + " pattern "+ p.charAt(j));
            if(s.charAt(j) == p.charAt(i)){

                prevCh = s.charAt(i);
                prevPCh = p.charAt(j);
                i++;j++;
            }
            else if(s.charAt(j) == prevCh && p.charAt(i)=='*'){
                prevCh = s.charAt(i);
                prevPCh = p.charAt(j);
                j++;
            }
            else if(p.charAt(i)=='.'){

                prevCh = s.charAt(i);
                prevPCh = p.charAt(j);
                i++;j++;
            }
            else if(p.charAt(j)=='*' && prevPCh=='.') {
                j++;
            } else if(i==0 && s.charAt(j) != p.charAt(i)) {
                //this case is for c*a*b aab pattern string
                //it says we are not gonna use current pattern char
                prevPCh = p.charAt(j);
                i++;
            } else {
                return false;
            }
        }
        if(j<s.length())
            return false;
        return true;
    }
}
