package org.ap.datastrutures.recursion;

public class CoinChangePermutation_DuplicateAllowed {

    public static void coinChange(int[] coins, int amtsf, int tamt, String asf, boolean[] used){
        if(amtsf > tamt){
            return;
        } else if(amtsf == tamt){
            System.out.println(asf + ".");
            return;
        }

        for(int i = 0; i < coins.length; i++){
            if(used[i] == false){
                used[i] = true;
                coinChange(coins, amtsf + coins[i], tamt, asf + coins[i] + "-", used);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int []coins = new int[] {2 ,3 ,5 ,6 ,7};
        int amt = 12;
        boolean[] used = new boolean[coins.length];
        coinChange(coins, 0, amt, "", used);
    }

}
