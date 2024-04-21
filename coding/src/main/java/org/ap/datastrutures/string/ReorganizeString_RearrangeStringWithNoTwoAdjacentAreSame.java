package org.ap.datastrutures.string;

import java.util.*;

public class ReorganizeString_RearrangeStringWithNoTwoAdjacentAreSame {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("b");
        list.add("e");
        list.add("e");
        rearrange(list).forEach(System.out::println);
    }

    public static List<String> rearrange(List<String> list){
        List<String> ls=new ArrayList<>();
        Map<String, Integer> map=new HashMap<>();
        for (String s : list){
             map.put(s,map.getOrDefault(s,0)+1);
        }
        PriorityQueue<String> pq=new PriorityQueue<>((o1, o2) -> map.get(o2)-map.get(o1));
        pq.addAll(map.keySet());
        while(pq.size() > 1){
            String current=pq.remove();
            String next=pq.remove();
            ls.add(current);
            ls.add(next);
            map.put(current,map.get(current)-1);
            map.put(next,map.get(next)-1);
            if(map.get(current) > 0){
                pq.add(current);
            }
            if(map.get(next) > 0){
                pq.add(next);
            }
        }
        if(!pq.isEmpty()){
            String last=pq.remove();
            if(map.get(last) > 1){
                System.out.println("Not Possible");
                return new ArrayList<>();
            }else{
                ls.add(last);
            }
        }
        return ls;
    }
}
