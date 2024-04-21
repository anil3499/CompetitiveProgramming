package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/reverse-integer/
public class ReverseInteger {
    public int reverse2(int x){
        long finalNum = 0;
        while(x!=0) {
            int lastDig = x%10;
            finalNum += lastDig;
            finalNum = finalNum * 10;
            x= x/10;
        }
        finalNum = finalNum/10;
        if(finalNum>Integer.MAX_VALUE || finalNum<Integer.MIN_VALUE){
            return 0;
        }
        if(x<0){
            return (int)(-1* finalNum);
        }
        return (int) finalNum;
    }
    public int reverse1(int x) {
        boolean neg= false;
        if(x<0) {
            neg = true;
            x=x*(-1);
        }
        String s= new StringBuilder(x +"").reverse().toString();
        try{
            x = Integer.parseInt(s);
        } catch(Exception e){
            x=0;
        }
        if(neg)
            x= x*(-1);
        return x;
    }
}
