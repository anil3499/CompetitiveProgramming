package org.ap.datastrutures.dp;

public class EditDistanceMemoization {

    public static int minDistance(String word1, String word2) {
        //recursion
        //int ans = rec(word1, word2);

        //memoization
        int memo[][] = new int[word1.length()+1][word2.length()+1];
        int ans  = memo(word1, word2, memo);
        return ans;
    }


    //memoization
    public static int memo(String s, String t, int arr[][]){
        if(s.length() == 0)
            return t.length();

        if(t.length() == 0)
            return s.length();

        if(arr[s.length()][t.length()] > 0)
            return arr[s.length()][t.length()];

        if(s.charAt(0)  == t.charAt(0)){
            arr[s.length()][t.length()] = memo(s.substring(1), t.substring(1), arr);
            return memo(s.substring(1), t.substring(1), arr);
        }

        else{
            int op1 =  memo(s.substring(1), t.substring(1), arr); //replace
            int op2 =  memo(s, t.substring(1), arr); //insert
            int op3 =  memo(s.substring(1), t, arr); //delete

            arr[s.length()][t.length()] = 1 + Math.min(op1, Math.min(op2, op3));
            return 1 + Math.min(op1, Math.min(op2, op3));
        }

    }
    // Driver Code
    public static void main(String args[])
    {
        String str1 = "sunday";
        String str2 = "saturday";
        System.out.println(minDistance(str1, str2));
    }
}
