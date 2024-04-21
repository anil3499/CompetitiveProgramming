package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/sqrtx/
public class SqrRootX {
    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        int left = 1, right = x/2;
        while (true) {
            int mid = left + (right - left)/2;
            if (mid > x/mid) { /* same as mid * mid > x but prevents overflow */
                right = mid - 1;/* answer <= (mid - 1) */
            } else {
                if (mid + 1 > x/(mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }
}
