package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/permutation-sequence/
public class PermutationSequence {
    //generate sequence in sorted fashion
    /*******************************approach-2**************************************************************************** */
    int count = 0;
    String ans = "";
    public String getPermutation(int n, int k) {
        boolean[] vis = new boolean[n];
        count = k;
        StringBuilder s = new StringBuilder();
        for(int i=0; i<n; i++)
            s.append((char) ((i+1) + '0'));
        permutations(s, new StringBuilder(), vis);
        return ans;
    }

    public void permutations(StringBuilder s, StringBuilder temp, boolean[] vis) {
        if(count == 0)
            return;
        if(temp.length() == s.length()) {
            count--;//found one ofstring combination then decrease the count
            if(count == 0)
                ans = temp.toString();
            return;
        }

        for(int i=0; i<s.length(); i++) {
            if(!vis[i]) {
                vis[i] = true;
                temp.append(s.charAt(i));
                permutations(s, temp, vis);
                temp.deleteCharAt(temp.length()-1);
                vis[i] = false;
            }
        }
    }
    /*******************************another way to write approach -1**************************************************************************** */
    public String getPermutation2(int n, int k) {
        List<Integer> num = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++)
            num.add(i);
        int[] fact = new int[n];  // factorial
        fact[0] = 1;
        for (int i = 1; i < n; i++)
            fact[i] = i*fact[i-1];

        k = k-1;
        StringBuilder sb = new StringBuilder();

        for (int i = n; i > 0; i--){
            int ind = k/fact[i-1];
            k = k%fact[i-1];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }

    /********************************Appraoch1*************************************************************************** */
    List<Integer> fact = new ArrayList<>();
    List<Integer> digits = new ArrayList<>();

    public String getPermutation1(int n, int k) {
        //Store all factorials from 0 to N
        fact.add(1);
        int f=1;
        for(int i=1;i<n;++i)
        {
            f*=i;
            fact.add(f);
        }
        //Push your digits in array
        for(int i=1;i<=n;++i)
            digits.add(i);

        StringBuilder ans = new StringBuilder();
        solve(ans,n,k);
        return ans.toString();
    }

    public void solve(StringBuilder ans,int n,int k)
    {
        if(n==1){ //Insert whatever digit is left at the end
            ans.append(digits.get(digits.size()-1));
            return;
        }
        //This runs if we have more than 1 digit in digits array
        int index = (k-1)/fact.get(n-1); //Defines no of blocks to skip (each block of size fact[n-1])
        //either we can use above line or the below 3 lines
        //int index = (k)/fact.get(n-1);
        //if(k%fact.get(n-1)==0)  //We need convert 1 based indexing to 0 based.So, decrese index by 1
        //   index-=1;

        ans.append(digits.get(index));  //Add new character
        digits.remove(index);    //Erase digit after using

        k -= fact.get(n-1)*index;  //Decrease K value
        solve(ans,n-1,k);
    }
    /*********************************************************************************************************** */

}
/***
 Algorithm
 Index = factorial of (k/(n-1))
 Ans+=char(digits[index])
 erase used digit(as digits are unique in t number)
 New K = k- fact(n-1)* index
 //Base case if n==1 add the last digit
 digit[1,2,3,4]
 sincen=9
 so we can preapare static factorial arrray from 0-9

 */

