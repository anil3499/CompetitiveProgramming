package org.ap.utils;

public class Print {
    public static void print_array(int arr[]){
        System.out.println("Printing array below...");
        for ( int i =0; i < arr.length; i++){
            System.out.print(" "+ arr[i]);
        }
        System.out.println("\n");
    }
}
