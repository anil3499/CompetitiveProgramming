package org.ap.datastrutures.array.array;
/*
//https://www.geeksforgeeks.org/number-buildings-facing-sun/

Input : arr[] = {7, 4, 8, 2, 9}
Output: 3
Explanation: As 7 is the first element, it can
see the sunset.
4 can't see the sunset as 7 is hiding it.
8 can see.
2 can't see the sunset.
9 also can see the sunset.

 */
public class SunnyBuilding {


    static int countBuildings(int arr[], int n)
    {
        int count = 1;
        int curr_max = arr[0];
        for (int i=1; i<n; i++)
        {
            if (arr[i] > curr_max)
            {
                count++;
                curr_max=arr[i];
            }
        }

        return count;
    }

    public static void main(String[] args)
    {
        int arr[] = {7, 4, 8, 2, 9};

        System.out.println(countBuildings(arr, arr.length));

    }
}
