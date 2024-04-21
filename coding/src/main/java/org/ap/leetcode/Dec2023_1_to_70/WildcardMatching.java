package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/wildcard-matching/description/
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        Boolean[][] dp= new Boolean[s.length()+1][p.length()+1];
        for(int i=0; i<s.length()+1; i++) {
            for(int j=0;j<p.length()+1; j++)
                dp[i][j] = null;
        }
        //return match(s,p,0,0);
        return matchWithMemo(s,p,0,0,dp);
    }

    public boolean match(String s, String p,int i, int j) {
        if(i>=s.length() && j>=p.length()) //both out from range
            return true;
        if(j>=p.length()) return false; //if string is not but pattern out of range

        if(i>=s.length()){ //if string out ofbound but pattern is not
            if(p.charAt(j)=='*')
                return match(s,p,i,j+1);
            return false;
        }

        if(i<s.length()) {
            if(s.charAt(i)==p.charAt(j) ||  p.charAt(j)=='?') {
                return match(s,p,i+1,j+1);
            } else if (p.charAt(j)=='*') {
                return match(s,p,i+1,j) || match(s,p,i+1,j+1) || match(s,p,i,j+1);
            }
        }
      /*  boolean matchflag = i<s.length() && (s.charAt(i)==p.charAt(j)  || p.charAt(j)=='?'||p.charAt(j)=='*') ;
         //|| p.charAt(j)=='*') && (p.charAt(j)=='*' || (j+1< p.length() && p.charAt(j+1)=='*'));

        if(i<s.length() && p.charAt(j)=='*'){
            boolean flag1=false , flag2=false;
            //choose
            if(matchflag)
             flag1 =  match(s,p,i+1,j);
            //not choose any
             flag2= match(s,p,i,j+1);
            return flag1|| flag2;
        }
         if(matchflag)
            return match(s,p,i+1,j+1);
        */
        return false;
    }

    public boolean matchWithMemo(String s, String p,int i, int j, Boolean[][] dp) {
        if(i>=s.length() && j>=p.length()) //both out from range
            return true;
        if(j>=p.length()) return false; //if string is not but pattern out of range

        if(dp[i][j]!=null) return dp[i][j];

        if(i>=s.length()){ //if string out ofbound but pattern is not
            if(p.charAt(j)=='*'){
                dp[i][j]= matchWithMemo(s,p,i,j+1,dp);
                return dp[i][j];
            }
            return false;
        }
        if(i<s.length()) {
            if(s.charAt(i)==p.charAt(j) ||  p.charAt(j)=='?') {
                dp[i][j] = matchWithMemo(s,p,i+1,j+1,dp);
                return dp[i][j];
            } else if (p.charAt(j)=='*') {
                dp[i][j] =  matchWithMemo(s,p,i+1,j,dp) || matchWithMemo(s,p,i+1,j+1,dp) || matchWithMemo(s,p,i,j+1,dp);
                return dp[i][j];
            }
        }
      /*  boolean matchflag = i<s.length() && (s.charAt(i)==p.charAt(j)  || p.charAt(j)=='?'||p.charAt(j)=='*') ;
         //|| p.charAt(j)=='*') && (p.charAt(j)=='*' || (j+1< p.length() && p.charAt(j+1)=='*'));

        if(i<s.length() && p.charAt(j)=='*'){
            boolean flag1=false , flag2=false;
            //choose
            if(matchflag)
             flag1 =  match(s,p,i+1,j);
            //not choose any
             flag2= match(s,p,i,j+1);
            return flag1|| flag2;
        }
         if(matchflag)
            return match(s,p,i+1,j+1);
        */
        return false;
    }
}
