package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/divide-two-integers/
public class DivideTwoIntegers {
    public int divide(int dividendi, int divisori) {
        long dividend = (long) dividendi;
        long divisor =(long) divisori;

        long dividendSign=1,divisorSign=1;

        if(dividend<0) {
            dividendSign= -1;
            dividend = (-1) * dividend;
        }

        if(divisor<0) {
            divisorSign= -1;
            divisor = (-1) * divisor;
        }

        long i=0;
        long mult = 100000000;

        while(mult>=100){
            while(dividend >= divisor*(1* mult)) {
                dividend-= divisor*(1* mult);
                i= i + (1* mult);;
            }
            mult/=100;
        }
        while(dividend >= divisor) {
            dividend-= divisor;
            i++;
        }
        i =  dividendSign * divisorSign * i;

        if(i > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if(i < Integer.MIN_VALUE) return Integer.MIN_VALUE;

        return (int) i;

    }
}
