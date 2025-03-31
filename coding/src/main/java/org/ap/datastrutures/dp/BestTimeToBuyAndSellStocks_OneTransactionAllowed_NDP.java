package org.ap.datastrutures.dp;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/solution/
leetcode : 121
https://www.youtube.com/watch?v=d0PZQWyeGZI
 */

public class BestTimeToBuyAndSellStocks_OneTransactionAllowed_NDP {

    public static  int maxProfit(int[] prices) {

        if(prices.length == 0){
            return 0;
        }
        int maxProffit = 0;
        int minSoFar = prices[0];
        for(int i=0; i < prices.length; i++) {
            int proffit = prices[i] - minSoFar;
            maxProffit = Math.max(proffit,maxProffit);
            minSoFar = Math.min(minSoFar,prices[i]);
        }
        return maxProffit;
    }

    public static void main(String[] args) {
        int[] price = { 1, 5, 2, 3, 7, 6, 4, 5 };
        System.out.println("\nTotal profit earned is " + maxProfit(price));

    }
}

