package org.ap.datastrutures.dp;
/*
RegularExpressionMatching.JPG
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        String s1 = "aab";
        String s2 = "c*a*b";
        System.out.println(validate(s1,s2));
        System.out.println(isMatch(s1,s2));
    }

    //recursion approach
    public static boolean isMatch(String s, String p) {
        //base case if both are empty
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (p.length() >= 2 && p.charAt(1) == '*') {
            //s=aab , p=c*a*b
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            //s=aab, p=a*b
            if ((s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && s.length() > 0) {
                return isMatch(s.substring(1), p);
            }
            return false;
        } else {
            //s=abe, p=abc
            if ((s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && s.length() > 0) {
                return isMatch(s.substring(1), p.substring(1));
            }
            return false;
        }

        /*
        example :
        s=aab
        p=c*a*b
        --------
        call stack :
        isMatch(b,b) ->true
        isMatch(b,a*b)
        isMatch (aab,a*b)
        isMatch (aab,c*a*b)
         */
    }

    public static boolean validate(String s, String p) {
        boolean[][] array = new boolean[p.length() + 1][s.length() + 1];
        for(int i = 0 ; i < array.length; i++){
            for(int j = 0 ; j < array[0].length; j++){
               if(i==0 && j==0){
                   array[i][j]=true;
               }else if(i==0){
                   array[i][j]=false;
               }else if(j==0){
                   char ch=p.charAt(i-1);
                   if(ch=='*'){
                       array[i][j]=array[i-2][j];
                   }else{
                       array[i][j]=false;
                   }
               }else{
                    char pch=p.charAt(i-1);
                    char sch=s.charAt(j-1);

                    if(pch=='*'){
                        array[i][j]=array[i-2][j];
                        char pslch=s.charAt(i-2);
                        if(pslch =='*' || pslch==sch){
                            array[i][j]= (array[i][j]  || array[i][j-1]);
                        }
                    }else if(pch == '.'){
                        array[i][j]=array[i-1][j-1];
                    }else if(pch == sch){
                        array[i][j]=array[i-1][j-1];
                    }else{
                        array[i][j]=false;
                    }
                }
            }
        }
        return array[array.length - 1][array[0].length - 1];
    }

}


