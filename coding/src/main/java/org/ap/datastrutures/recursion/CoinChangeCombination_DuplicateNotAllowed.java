package org.ap.datastrutures.recursion;
/*
input : [5,2,3,6,7]
k = 12
output :
2-3-7 = 12
5-7 = 12
 */
public class CoinChangeCombination_DuplicateNotAllowed {
    public static void coinChange(int i, int[] coins, int amtsf, int tamt, String asf){
        if(i == coins.length){
            if(amtsf == tamt){
                System.out.println(asf + ".");
            }
            return;
        }
        coinChange(i + 1, coins, amtsf + coins[i], tamt, asf + coins[i] + "-");
        coinChange(i + 1, coins, amtsf, tamt, asf + "");
    }
    public static void main(String[] args) throws Exception {
        int []coins = new int[] {2 ,3 ,5 ,6 ,7};
        int amt = 12;
        coinChange(0, coins, 0, amt, "");
    }
}


