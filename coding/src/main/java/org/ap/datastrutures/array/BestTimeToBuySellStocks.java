package org.ap.datastrutures.array;
/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * */

/*https://leetcode.com/problems/best-time-to-buy-and-sell-stock/solution/
  leetcode : 121
  https://www.youtube.com/watch?v=d0PZQWyeGZI*/
public class BestTimeToBuySellStocks {
    //Brute force

    public int bestTimeToBuySellStocksBF(int[] prices){ //TC O(n2)
        int n = prices.length;
        int maxSoFar = 0;
        for(int i =0; i<n ; i++){
            for(int j=i+1; j< n ; j++){
                if(prices[j]>prices[i])
                    maxSoFar = Math.max(maxSoFar,prices[j] - prices[i]);

            }
        }
        return maxSoFar;
    }
    //by using space O(n)
    // here we are checking if we buy today how much max proffit we can make in future
    public int bestTimeToBuySellStocksWithSpace(int[] prices){
        int n = prices.length;
        int[] auxArr = new int[n];

        //below logic to calculate max from back of array
        auxArr[n-1] =prices[n-1];
        for(int i= auxArr.length-2; i>=0;i--)
            auxArr[i] = Math.max(auxArr[i+1], prices[i]);

        //now we can iterate main array and can check
        // how max proffit we can make if we buy today and sell max in future(value from aux arr
        int maxProffitCanMake = 0;
        for(int i=0; i< n ; i++){
            maxProffitCanMake = Math.max(maxProffitCanMake, auxArr[i] - prices[i]);
        }
    return maxProffitCanMake;
    }


    // here we are checking if we sell today what was min value in past when I could have bought and what proofit I could have make max
    public int bestTimeToBuySellStocks(int[] prices) {
        int minSoFar = prices[0];
        int maxProffitCanMake = 0;
        for(int i =1; i<prices.length; i++){
            minSoFar = Math.min(minSoFar,prices[i]); //update min so far
            int proffit = prices[i] - minSoFar; //proffit if we sell today
            maxProffitCanMake = Math.max(maxProffitCanMake,proffit);
        }
        return maxProffitCanMake;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{7,1,5,3,6,4};
        //nums = new int[]{2,4,1};
        System.out.println(new BestTimeToBuySellStocks().bestTimeToBuySellStocks(nums));
    }

}

/**
 * Leetcode - https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * Soln on Youtube- https://www.youtube.com/watch?v=34WE6kwq49U&t=500s
 * **/