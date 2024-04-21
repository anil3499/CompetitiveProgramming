package org.ap.leetcode.Dec2023_1_to_70;
///https://leetcode.com/problems/powx-n/
public class Pow_xn {
    public double myPow(double x, int n) {
        if(n==0)
            return 1;
        //return Math.pow(x,n);
        if(n < 0) {
            x=1/x;
            n = Math.abs(n);
        }

        if(n % 2 == 0){
            double y = myPow(x,n/2);
            return y * y;
        } else
            return x * myPow(x,n-1);
    }
}
