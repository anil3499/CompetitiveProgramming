package org.ap.datastrutures.array;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 * Find and return the maximum profit you can achieve.
 *
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 * */
public class BestTimeToBuySellStocks_II {
    public int bet_time_to_buysell_stock(int[] prices){
        int proffit = 0;
        //we can daily check if price increased from prevv buy price then we can sell and can add to proffit
        //we dont need to find min because we can do as many as transacion we want
        for (int i=1; i< prices.length; i++){
            if (prices[i] > prices[i-1]){
                proffit = proffit + prices[i] - prices[i-1];
            }
        }
        System.out.println("" + proffit);
        return proffit;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,1,5,3,6,4};

        new BestTimeToBuySellStocks_II().bet_time_to_buysell_stock(nums);
    }

}


/**
 * LeetCode -  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * Youtube -
 * */