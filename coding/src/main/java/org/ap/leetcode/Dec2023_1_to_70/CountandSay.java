package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/count-and-say/
public class CountandSay {
    //count and say is a sequence see in video thenyou willl understand this question
    ///https://www.youtube.com/watch?v=5uJitfSM3vk

    public String countAndSay(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        StringBuilder sb = new StringBuilder("1");
        for (int i = 2; i <= n; i++) {
            sb = getNextState(sb);
        }
        return sb.toString();
    }

    private StringBuilder getNextState(StringBuilder curSb) {
        StringBuilder nextSb = new StringBuilder();
        int len = curSb.length();
        int i = 0;

        while (i < len) {
            char prev = curSb.charAt(i);
            i++;
            int count = 1;
            //see how many counts of same char we hae further
            while (i < len && prev == curSb.charAt(i)) {
                count++;
                i++;
            }
            nextSb.append(count).append(prev);
        }

        return nextSb;
    }

    public String countAndSay1(int n) {
        if(n==1) return ""+1;

        String result ="1";
        for(int i=2; i<=n; i++){
            result=seeAboveAndReturn(result);
        }
        return result;
    }
    //1  1
    //2 1 11
    //3 11  21
    //4 21  1211
    //5 1211 111221
    public String seeAboveAndReturn(String given) {
        if(given.length()==1) return 1+given;


        int count = 1;
        StringBuilder result=  new StringBuilder();
        char prev = given.charAt(0);
        //21
        for(int i=1 ;i<given.length();i++) {
            char currChar = given.charAt(i);
            if(currChar==prev && i==given.length()-1){
                //in case of duplicate if curr islast element of string then add it in output
                count++;
                result.append(count);
                result.append(prev);
            } else if(currChar==prev) { //in case of duplicate  otherwise just increase count
                count++;
            } else { //non duplicate case, just add it to result and moveon
                result.append(count);
                result.append(prev);
                prev = currChar;
                count=1;
                if(i==given.length()-1) { //to handle the last occurance of string
                    result.append(count);
                    result.append(prev);
                }
            }
        }
        return result.toString();
    }
}
