package org.ap.datastrutures.recursion;
/*
[2 3 5 6 7]
k= 12
output :
2-2-2-2-2-2-.
2-2-2-3-3-.
2-2-2-6-.
2-2-3-5-.
2-3-7-.
2-5-5-.
3-3-3-3-.
3-3-6-.
5-7-.
6-6-.
 */
public class CoinChangeCombination_DuplicateAllowed {
    public static void coinChange(int i, int[] coins, int amtsf, int tamt, String asf) {
        if (i == coins.length) {
            if (amtsf == tamt) {
                System.out.println(asf + ".");
            }
            return;
        }

        for (int j = tamt / coins[i]; j > 0; j--) {
            String pasf = "";
            for(int k = 0; k < j; k++){
                pasf += coins[i] + "-";
            }
            coinChange(i + 1, coins, amtsf + coins[i] * j, tamt, asf + pasf);
        }
        coinChange(i + 1, coins, amtsf, tamt, asf);
    }

    public static void main(String[] args) throws Exception {
        int []coins = new int[] {2 ,3 ,5 ,6 ,7};
        int amt = 12;
        coinChange(0, coins, 0, amt, "");
    }
}


