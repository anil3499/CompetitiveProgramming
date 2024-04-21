package org.ap.utils;


import java.util.LinkedList;

public class SinglyLinkedListNode {
   public Integer int_val;
   public String str_val;
   public SinglyLinkedListNode node;

    public SinglyLinkedListNode(Integer int_val, SinglyLinkedListNode node) {
        this.int_val = int_val;
        this.node = node;
    }

    public SinglyLinkedListNode(String str_val, SinglyLinkedListNode node) {
        this.str_val = str_val;
        this.node = node;
    }
}
