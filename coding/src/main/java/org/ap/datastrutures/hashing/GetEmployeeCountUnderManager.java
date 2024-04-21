package org.ap.datastrutures.hashing;

import java.io.*;
import java.util.*;
/*https://www.geeksforgeeks.org/find-number-of-employees-under-every-manager/
1. You are given number N and 2*N number of strings that contains mapping of the employee and his manager.
2. An employee directly reports to only one manager.
3. All managers are employees but the reverse is not true.
4. An employee reporting to himself is the CEO of the company.
5. You have to find the number of employees under each manager in the hierarchy not just their direct reports.
Sample Input
6
A C
B C
C F
D E
E F
F F
Sample Output
A 0
B 0
C 2
D 0
E 1
F 5
 */
public class GetEmployeeCountUnderManager {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = Integer.parseInt(scn.nextLine());
        String ceo = "";
        HashMap<String, HashSet<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] parts = scn.nextLine().split(" ");
            String employee = parts[0];
            String manager = parts[1];
            if (manager.equals(employee)) {
                ceo = manager;
                continue;
            }
            if (map.containsKey(manager)) {
                HashSet<String> emps = map.get(manager);
                emps.add(employee);
            } else {
                HashSet<String> emps = new HashSet<>();
                emps.add(employee);
                map.put(manager, emps);
            }
        }
        HashMap<String, Integer> ans = new HashMap<>();
        size(map, ceo, ans);
        for (String emp : ans.keySet()) {
            System.out.println(emp + " " + ans.get(emp));
        }
    }

    public static int size(HashMap<String, HashSet<String>> map,String man,HashMap<String, Integer> ans) {
        if(map.containsKey(man) == false){
            ans.put(man, 0);
            return 1;
        }
        int sz = 0;
        for (String emp : map.get(man)) {
            int cs = size(map, emp, ans);
            sz += cs;
        }
        ans.put(man, sz);
        return sz + 1;
    }
}



