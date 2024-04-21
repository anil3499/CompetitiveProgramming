package org.ap.datastrutures.metrix_chain_multiplication;

public class Basics {
}
/**
 * *****************Identification format*********************
 * Either String or Array will be given
 * we break i........j to  i...k and k+1.....j
 * from these 2 sub problem we will get 2 temp ans
 * and at last we have to calculate one ans from these temp ans
 * */
/**
 * **********************Format***********
 *  int solve(int arr[]. int i, int j) {
 *      //Base condition
 *        //type-1 think of smallest valid input
 *        //type-2 think of the invalid input
 *
 *        in our case type-2 will help us more
 *
 *        if(i>j) return 0;
 *        for(int k=i; k<j; k++){
 *            tempAns = solve(arr,i,k) + solve(arr,k+1,j);
 *            //calculate final ans from this temporary ans
 *        }
 *        return ans;
 *
 *  }
 *
 *  step -1 find i and j vallue
 *  step-2 find base condition
 *  step-3 move k i to j
 *  step-4 calculate final ans from temp ans
 * **/