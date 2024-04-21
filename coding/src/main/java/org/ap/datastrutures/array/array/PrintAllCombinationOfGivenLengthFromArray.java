package org.ap.datastrutures.array.array;

/*
https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/

Given an array of size n, generate and print all possible combinations of r elements in array.
For example, if input array is {1, 2, 3, 4} and r is 2, then output should be
{1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4} and {3, 4}.

 */
public class PrintAllCombinationOfGivenLengthFromArray {

    /*
    arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    i ---> current index in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed
    */
    static void combinationUtil(int arr[], int size, int k,
                                int data[], int indexOfDataArray, int indexOfSourceArray)
    {
        if(indexOfDataArray == k){
            for (int j=0; j < k; j++) {
                System.out.print(data[j] + " ");
            }
            System.out.println("");
            return;
        }
        if(indexOfSourceArray >= size){
           return;
        }
        data[indexOfDataArray] =arr[indexOfSourceArray];
        combinationUtil(arr,size,k,data,indexOfDataArray+1, indexOfSourceArray+1);
        combinationUtil(arr, size,k,data,indexOfDataArray,indexOfSourceArray+1);
    }

    static void printCombination(int arr[], int n, int k)
    {
        int data[]=new int[k];
        combinationUtil(arr, n, k, data,0, 0);
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5};
        int k = 3;
        int n = arr.length;
        printCombination(arr, n, k);
    }
}
