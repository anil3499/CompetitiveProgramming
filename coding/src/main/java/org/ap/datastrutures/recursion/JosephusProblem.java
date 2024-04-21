package org.ap.datastrutures.recursion;
/*
https://www.geeksforgeeks.org/josephus-problem-iterative-solution/
Input : N = 5, K = 2
Output : 3
Firstly, the child at position 2 is out,
then position 4 goes out, then position 1
Finally, the child at position 5 is out.
So the position 3 survives

 */
public class JosephusProblem {
    private static int josephus(int n, int k)
    {
        int sum = 0;
        for(int i = 2; i <= n; i++)
        {
            sum = (sum + k) % i;
        }
        return sum+1;
    }

    private static int josephusRecusion(int n, int k)
    {
        if (n == 1)
            return 1;
        else {
            /* The position returned by josephus(n - 1, k)
            is adjusted because the recursive call
            josephus(n - 1, k) considers the original
            position k%n + 1 as position 1 */
            return (josephus(n - 1, k) + k - 1) % n + 1;
        }
    }

    // Driver Program to test above method
    public static void main(String[] args)
    {
        int n = 14;
        int k = 2;
        System.out.println(josephus(n, k));
        System.out.println(josephusRecusion(n, k));
    }
}
