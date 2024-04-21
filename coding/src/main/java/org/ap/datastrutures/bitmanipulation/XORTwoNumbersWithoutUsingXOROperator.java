package org.ap.datastrutures.bitmanipulation;
/*
0 ^ 0 = 0
1 ^ 1 = 0
0 ^ 1 = 1
1 ^ 0 =1

01000001    |               (x = 65)
01010000                    (y = 80)
~~~~~~~~
01010001                    (x | y)


01000001    &               (x = 65)
01010000                    (y = 80)
~~~~~~~~
01000000                    (x & y)

(x | y) - (x & y)x ^ y

x ^ y(x | y) - (x & y) = (01010001 - 01000000) = 00010001
 */
public class XORTwoNumbersWithoutUsingXOROperator {
    public static int findBits(int x, int y) {
        return (x | y) - (x & y);
    }

    public static void main(String[] args)
    {
        int x = 65;
        int y = 80;
        System.out.println("The first number in binary is " +
                Integer.toBinaryString((x | y)));
        System.out.println("The second number in binary is " +
                Integer.toBinaryString((x & y)));
        System.out.println("\nXOR is " + Integer.toBinaryString(findBits(x, y)));
    }
}
