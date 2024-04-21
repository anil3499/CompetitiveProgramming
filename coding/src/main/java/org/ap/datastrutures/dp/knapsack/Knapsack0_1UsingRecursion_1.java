package org.ap.datastrutures.dp.knapsack;

/*
Input:
Let value elements = {60, 100, 120}
Let weight values = {10, 20, 30}
Capacity=50

 */
public class Knapsack0_1UsingRecursion_1 {

    int knapsack(int weight[], int value[], int capacity, int indexOfElement) {
        if(indexOfElement==0 || capacity==0){
            return 0;
        }
        //if item weight is lower than capacity
        if(weight[indexOfElement-1] <= capacity){
            //first section shows item selected and second shows not selected
            return Integer.max(value[indexOfElement-1]  +  knapsack(weight,value,capacity-weight[indexOfElement-1],indexOfElement-1),
                    knapsack(weight,value,capacity,indexOfElement-1));
        }else{
            //if item weight is greater than capacity
            return knapsack(weight,value,capacity,indexOfElement-1);
        }
    }

    public static void main(String[] args) {
        Knapsack0_1UsingRecursionWithMemoization_1 knapsack0_1UsingRecursion=new Knapsack0_1UsingRecursionWithMemoization_1();
        int []value=new int[]{60, 100, 120};
        int []weight= new int[]{10,20,30};
        int capacity=50;
        System.out.println("max value within capacity is "+ knapsack0_1UsingRecursion.knapsack(weight,value,capacity,value.length));
    }

}
