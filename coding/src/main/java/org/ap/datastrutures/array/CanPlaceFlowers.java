package org.ap.datastrutures.array;
//leetcoode 605
//https://leetcode.com/problems/can-place-flowers/description
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for(int i =0; i<flowerbed.length; i++) {
            if (flowerbed[i] == 0) { //current is empty
                //if left plot is empty or first plot
                boolean leftIsEmpty = (i == 0) || (flowerbed[i - 1] == 0);
                //if right plot is empty or last plot
                boolean rightIsEmpty = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);

                // if left and right both plots are empty plant the flower
                if (leftIsEmpty && rightIsEmpty) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }

        if(n<=0) return true;
        return false;
    }

}
