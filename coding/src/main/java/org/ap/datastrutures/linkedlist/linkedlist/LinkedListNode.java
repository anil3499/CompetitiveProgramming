package org.ap.datastrutures.linkedlist.linkedlist;



public class LinkedListNode<T> {

    public T data;
    public LinkedListNode<T> next;
    public LinkedListNode<T> random;
    public LinkedListNode<T> down;

    public LinkedListNode(T data) {
        this.data = data;
    }

    public LinkedListNode() {
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public LinkedListNode<T> getRandom() {
        return random;
    }

    public void setRandom(LinkedListNode<T> random) {
        this.random = random;
    }

    public LinkedListNode<T> getDown() {
        return down;
    }

    public void setDown(LinkedListNode<T> down) {
        this.down = down;
    }
}
