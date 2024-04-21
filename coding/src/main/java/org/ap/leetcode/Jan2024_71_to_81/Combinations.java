package org.ap.leetcode.Jan2024_71_to_81;

import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/problems/combinations/description/
public class Combinations {
    //1,2,3,4
    //1,2 1,3 1,4
    //2,3 2,4
    //3,4
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result= new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i] = i+1;
        createCombinations(arr,0, k,temp,result);
        return result;
    }
    public void createCombinations(int[] arr,int index, int k, List<Integer> temp, List<List<Integer>> result){
        if(temp.size()>k)
            return;
        if(temp.size()==k){
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        for(int i=index; i<arr.length; i++) {
            temp.add(arr[i]);
            createCombinations(arr,i+1, k,temp,result);
            temp.remove(temp.size()-1);
        }
    }
}
