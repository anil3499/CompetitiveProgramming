package org.ap.datastrutures.bitmanipulation;

public class TwoNumberSumWithoutArithmeticOperator {
    public static void main(String[] args) {
        System.out.println(add(5,8));
    }
    static int add(int a, int b)
    {
        if (b==0) {
            return a;
        }

        int sum = a ^ b;
        int carry = (a & b) << 1;

        return add(sum, carry);
    }
}
