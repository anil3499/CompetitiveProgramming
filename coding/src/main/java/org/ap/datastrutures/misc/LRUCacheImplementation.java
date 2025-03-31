package org.ap.datastrutures.misc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//https://leetcode.com/problems/lru-cache/
// LeetCode -146
public class LRUCacheImplementation {
    private Map<Integer, Integer> storage;
    private LinkedList<Integer> linkedList;
    private Integer capacity;

    public LRUCacheImplementation(int capacity) {
        this.capacity = capacity;
        this.storage = new HashMap<>(capacity);
        this.linkedList = new LinkedList<Integer>();
    }

    public int get(int key) {
        //System.out.println("GET...."+key);
        // System.out.println(storage.toString());
        // System.out.println(linkedList.toString());
        if (storage.containsKey(key)) {
            linkedList.remove((Object) key);
            linkedList.add(key);
            return storage.get(key);
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        //System.out.println("PUT...."+key);
        if (storage.containsKey(key)) {
            linkedList.remove((Object) key);
            linkedList.add(key);
            storage.put(key, value);
            return;
        }
        if (linkedList.size() == capacity) {
            int oldKey = linkedList.removeFirst();
            storage.remove(oldKey);
        }
        linkedList.add(key);
        storage.put(key, value);

        //System.out.println(storage.toString());
        // System.out.println(linkedList.toString());
    }

}



