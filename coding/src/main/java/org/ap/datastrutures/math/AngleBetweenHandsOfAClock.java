package org.ap.datastrutures.math;
/*

https://practice.geeksforgeeks.org/problems/angle-between-hour-and-minute-hand0545/1/?company[]=Salesforce&company[]=Salesforce&page=1&query=company[]Salesforcepage1company[]Salesforce
angle in 1 hour : 360/12 -> 30
angle in 1 min : 360/60 -> 6
now min niddle will chnage the position of hour niddle
h = ( hour + minutes/60 ) * 30
m = minuts * 6
angle= |(h-m)|

 */
public class AngleBetweenHandsOfAClock {
    public static void main(String[] args) {
        System.out.println(angleClock(9 , 60));
    }
    public static double angleClock(int hour, int minutes) {
        float h = (hour%12 + (float)minutes/60)*30;
        float m = minutes*6;
        float angle = Math.abs(h-m);
        if(angle > 180) angle = 360-angle;
        return angle;
    }
}
