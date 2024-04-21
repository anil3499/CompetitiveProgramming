package org.ap.utils;

import java.util.LinkedList;
import java.util.List;

public class Utils {
    public static List<SinglyLinkedListNode> generateLinkedlistFromArray(int[] items){
        LinkedList root = new LinkedList<>();
        SinglyLinkedListNode prev = null;
        for(int it : items){
            SinglyLinkedListNode node = new SinglyLinkedListNode(it,null);
            prev = node;
            root.add(node);
        }
        return null;
    }
}
