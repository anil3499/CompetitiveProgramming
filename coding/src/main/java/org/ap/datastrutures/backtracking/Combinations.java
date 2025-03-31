package org.ap.datastrutures.backtracking;

import java.util.ArrayList;
import java.util.List;
// Leetcode -77
//https://leetcode.com/problems/combinations/description/
//Solution https://www.youtube.com/watch?v=QQ9Tn2i269I&t=18s

public class Combinations {
    //1,2,3,4
    //1,2 1,3 1,4
    //2,3 2,4
    //3,4

    //Appraoch 3 with no extra space and with for loop
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        createCombinations(1, n, k, temp,result);
        return result;
    }
    private void createCombinations(int start, int n, int k, List<Integer> temp, List<List<Integer>> result) {
        //termination condition
        if(k==0) {
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        for(int i=start; i<=n; i++) {
            temp.add(i);
            createCombinations(i+1, n, k-1, temp,result);
            temp.remove(temp.size()-1);
        }
        /*if(start > n) {
            return;
        }
        temp.add(start);
        createCombinations(start+1, n, k-1, temp,result);
        temp.remove(temp.size()-1);
        createCombinations(start+1, n, k, temp,result);*/
    }

    //approach 2 with for loop but creating extra space Manish's approach
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result= new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i] = i+1;
        createCombinations2(arr,0, k,temp,result);
        return result;
    }

    public void createCombinations2(int[] arr,int index, int k, List<Integer> temp, List<List<Integer>> result){
        if(temp.size()>k)
            return;
        if(temp.size()==k){
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        for(int i=index; i<arr.length; i++) {
            temp.add(arr[i]);
            createCombinations2(arr,i+1, k,temp,result);
            temp.remove(temp.size()-1);
        }
    }

    //Approach 1 without for loop
    public List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        createCombinations1(1, n, k, temp,result);
        return result;
    }
    private void createCombinations1(int start, int n, int k, List<Integer> temp, List<List<Integer>> result) {
        //termination condition
        if(k==0) {
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        if(start > n) {
            return;
        }
        temp.add(start);
        createCombinations1(start+1, n, k-1, temp,result);
        temp.remove(temp.size()-1);
        createCombinations1(start+1, n, k, temp,result);
    }

}
/**Amazon 2
 Microsoft 2
 Bloomberg 2
 6 months - 1 year
 Apple 2
 Adobe 2
 1 year - 2 years
 Yahoo 2
 * */