package org.ap.datastrutures.metrix_chain_multiplication;

/**
 * If you have seen Balloon Burst and this problem, not able to find the solution .
 * Just read the following pattern carefully .
 *
 * These both are the child problem of MCM .
 *
 * Problem : If a chain of matrices is given, we have to find the minimum number of the correct sequence of matrices to multiply.
 *
 * The problem is not actually to perform the multiplications, but merely to decide in which order to perform the multiplications.
 * Note:no matter how we parenthesize the product, the result will be the same. For example, if we had four matrices A, B, C, and D, we would have:
 *
 * (ABC)D = (AB)(CD) = A(BCD) = ....
 * However, the order in which we parenthesize the product affects the number of simple arithmetic operations needed to compute the product,
 * or the efficiency. For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,
 *
 * (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
 * A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
 * Clearly the first parenthesization requires less number of operations.
 * Note ; We'll be given an array arr[ ] which represents the chain of matrices such that the ith matrix arr[i] is of dimension arr[i-1] x arr[i].
 * That's why we start out 'k' i.e partition from 'i' =1 so that arr[ 1] is of dimentions arr[1-1] * arr[1] else we'll get index out of bound error
 * Eg arr[0-1] * arr[0] is not possible
 * So first half of the array is from i to k & other half is from k+1 to j
 * Also we need to find the cost of multiplication of these 2 resultant matrixes (first half & second half) which is nothing but arr[i-1] * arr[k] * arr[j]
 *
 * Recursion
 * Here is the recursive algo :
 * */
public class MetricsChainMultilication {
    //find i and j
    //find base condition
    // find k loop scheme
    //find temp ans

    /**
     * Suppose we have 3 metrics lie A -> 10 * 30 , B --> 30*5 , C--> 5*60
     * Now how to calculate minimum cost
     * This can be calculated like A(BC) or (AB)C
     * calculate for A(BC)
     * Cost(BC)          +   Cost(A(BC)) =
     * (30*5) * (5*60)        (10*30) * (30*60)
     * 30*5*60                10*30*60
     * 30*50*60 + 10*30*60 = total cost of A(BC) = 2700
     *
     * calculate for (AB)C
     * Cost(AB)          +   Cost((AB)C) =
     * (10*30) * (30*5)        (10*5) * (5*60)
     * 10*30*5                10*5*60
     * 10*30*5  + 10*5*60 = total cost of A(BC) = 4500
     *
     * We need to return min(2700,4500)
     * **/

    /**
     * arr : 40,20,30,10,30
     * if array size is 5 means we have 4 metrix are given
     * A1--> 40*20  arr[i-1] * arr[i] //dimentions
     * A2 --> 20*30
     * A3 --> 30*10
     * A4 --> 10*30
     * */


    //recursive code
    public static int mcm_recursive(int[]arr,int i,int j){
        if (i >= j){ // i==1 also not allowed beause array with 1 len also not useful for this MCM
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for(int k=i; k<=j-1; k++) { //k will go till j-1 only because we will need metrix with 2 dimentions from array
            // so inshort we are just moving from  1 to n-2

            //below we will calculate the cost
            //cost of metrix i to k and cost of metrix k+1 to j and then multiply both cost
            //A1-> 40*20 , i(which is strting from1),k A2-> 20 30 ,k+1,j
            //so cost will be
            int costOf_i_k =  mcm_recursive(arr, i, k);
            int costOf_k1_j = mcm_recursive(arr, k + 1, j) ;
            int costOfMultiplying_i_k__and__k1_j = arr[i - 1] * arr[k] * arr[j];   //mcm(arr, i, k) will return i-1,k size and mcm(arr, k + 1, j) will return k,j
            int tempans = costOf_i_k + costOf_k1_j + costOfMultiplying_i_k__and__k1_j;
            ans = Math.min(ans,tempans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{40,20,30,10,30};
        System.out.println(mcm_recursive(arr,1,arr.length-1));
    }
    public int mcm_memoize(int[]arr,int i,int j){
        if (i >= j){ // i==1 also not allowed beause array with 1 len also not useful for this MCM
            return 0;
        }
        if (dp[i][j]!=-1){
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for(int  k=i; k<=j-1; k++) {
            int tempans = mcm_memoize(arr, i, k) + mcm_memoize(arr, k + 1, j) + arr[i - 1] * arr[k] * arr[j];
            if (min > tempans){
                min = tempans;
            }
        }
        return dp[i][j] = min;

    }
    static int[][] dp;
    public static void main1(String[] args) {
        int[] arr = {40,20,30,10,30};
        //brackets kuch aise lagana h ki cost minimum aaye
        //given a metrics, A1,A2,A3...
        //Cost should be minimum, means number of multiplication should be minimum
        //axb, cxd cost of multiplication will be axbxd and as per multiplication rule b==c be should be equal to c
        //axb, bxc so cost will be axbxc
        //now dimentions from array
        //A11 40x20, A2 20x30 A3 30x10, A4 10X30
         // i =1 , j=n-1
        int n = arr.length;
        dp = new int[n+1][n+1];
        //initialize
        for (int i =0; i<n; i++){
            for (int j =0 ; j<n; j++){
                dp[i][j] = -1;
            }
        }
        int res =new MetricsChainMultilication().mcm_memoize(arr,1,n-1);
        System.out.println(res);
    }
}

/**
 * ps- https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
 * youtube - https://www.youtube.com/watch?v=kMK148J9qEE&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=33
 * */



//Identification
// i start of array , j end of array
//k value will mve between i to j
// func call first i->j
//i->k, k->j recursion
//
//base condition
// think of sammalest valid input or invalid input
// for MCM invalid will more help
//if i is starting and j is ending
//i=0,j=n-1
// i>j will be invalid input
//
//
//for (int k=i;k<j;k++)
//{
//  solve(arr,i,k) + solve (arr,k+1,j)
//  ans --> fun_temp()
// }