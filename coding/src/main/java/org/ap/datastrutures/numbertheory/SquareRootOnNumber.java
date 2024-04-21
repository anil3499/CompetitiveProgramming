package org.ap.datastrutures.numbertheory;

public class SquareRootOnNumber {
    //Time Complexity: O(√ n)
    static int floorSqrt(int x)
    {

        if (x == 0 || x == 1)
            return x;
        int i = 1, result = 1;
        while (result <= x) {
            i++;
            result = i * i;
        }
        return i - 1;
    }

    //Time complexity: O(log n)
    public static int floorSqrtBS(int x)
    {
        // Base Cases
        if (x == 0 || x == 1)
            return x;
        // Do Binary Search for floor(sqrt(x))
        long start = 1, end = x, ans=0;
        while (start <= end)
        {
            long mid = (start + end) / 2;
            // If x is a perfect square
            if (mid*mid == x)
                return (int)mid;
            // Since we need floor, we update answer when mid*mid is
            // smaller than x, and move closer to sqrt(x)
            if (mid*mid < x)
            {
                start = mid + 1;
                ans = mid;
            }
            else   // If mid*mid is greater than x
                end = mid-1;
        }
        return (int)ans;
    }

    // Driver program
    public static void main(String[] args)
    {
        int x = 11;
        System.out.print(floorSqrt(x));
        System.out.print(floorSqrtBS(x));
    }
}
