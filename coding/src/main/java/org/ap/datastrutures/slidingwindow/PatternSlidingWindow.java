package org.ap.datastrutures.slidingwindow;
// ‘Maximum Sum Subarray of Size K’, ‘Fruits Into Baskets’, ‘Longest Substring with K Distinct Characters’.

public class PatternSlidingWindow {
}
//geeneral format for variable size
//while(j<size){
//         calculations
//          if(winsize < k)
//                  j++
//          elseif (winsize ==k)
//          {
//              ans  <<---- calculatios
//              calculation remove i
//              window size maintained and slide
//          }
// }


/*//geeneral format for fixed size

 *  Window size is fixed then called fixed sliding problem
* Window size is variable then called variable length sliding window problem
*
*
*
*Identifiation -
* 1. question is array ya substring
* 2. sub aray ya substring pucha hoga
* 3. window size diya hoga
*
* 85-95% sliding window lagega
*
*Basic for sliding window
*
* We need start and end
* What will be window size ==> j-1+1  //always remember
*
*
 * given is array 2,4,5,7,42,6,3
 * size is given as k
 *
 * maximum of all subarray whose size is 3
 * start i and j from 0
 *
 * run the loop till our j is not reached at end of array
 * While(j<a.length)
 * {
 *
 * sum = sum + a[j]
 *
 * //once we hit the window size check for condition
 *if(j-i+1 <k) j++
 * to move the window ==>
 * j++
 * sum = sum + a[j] //add element in sum
 * sum = sum - a[i] //exclude before moving element
 * i++
 *
 *
 *
 *  */