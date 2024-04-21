package org.ms.sd.lld;/*
package ms.sd.lld;

*/
/*
https://www.youtube.com/watch?v=B7iCXl_KSoM&list=PL564gOx0bCLqTolRIHIsR2JPv11w8LESW&index=3
https://github.com/anomaly2104/cache-low-level-system-design/tree/master/src/main/java/com/uditagarwal/algoritms

Problem Statement
We have to do low level design for a Cache system. Cache that we will design will have to support following operations:

Put: This will allow user to put a value against a key in the cache.
Get: This will allow user to get the previously saved value using key.
Eviction: Cache should also support removal of some key in case cache is full, and we try to add new key value.
Expectations
Code should be functionally correct.
Code should be modular and readable. Clean and professional level code.
Code should be extensible and scalable. Means it should be able to accommodate new requirements with minimal changes.
Code should have good OOPs design.

 *//*



import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

//cache---------------------------------------------------
class Cache<Key, Value> {
    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key, Value> storage;

    public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void put(Key key, Value value) {
        try {
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException exception) {
            System.out.println("Got storage full. Will try to evict.");
            Key keyToRemove = evictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new RuntimeException("Unexpected State. Storage full and no key to evict.");
            }
            this.storage.remove(keyToRemove);
            System.out.println("Creating space by evicting item..." + keyToRemove);
            put(key, value);
        }
    }

    public Value get(Key key) {
        try {
            Value value = this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        } catch (NotFoundException notFoundException) {
            System.out.println("Tried to access non-existing key.");
            return null;
        }
    }


}

//cache/storage---------------------------------------------------
interface Storage<Key, Value> {
    public void add(Key key, Value value) throws StorageFullException;

    void remove(Key key) throws NotFoundException;

    Value get(Key key) throws NotFoundException;
}
class HashMapBasedStorage<Key, Value> implements Storage<Key, Value> {

    Map<Key, Value> storage;
    private final Integer capacity;

    public HashMapBasedStorage(Integer capacity) {
        this.capacity = capacity;
        storage = new HashMap<>();
    }

    @Override
    public void add(Key key, Value value) throws StorageFullException {
        if (isStorageFull()) throw new StorageFullException("Capacity Full.....");
        storage.put(key, value);
    }

    @Override
    public void remove(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException1(key + "doesn't exist in cache.");
        storage.remove(key);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException1(key + "doesn't exist in cache.");
        return storage.get(key);
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }
}


//cache/policies---------------------------------------------------
interface EvictionPolicy<Key> {

    void keyAccessed(Key key);

    */
/**
     * Evict key from eviction policy and return it.
     *//*

    Key evictKey();
}
class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private DoublyLinkedList<Key> dll;
    private Map<Key, DoublyLinkedListNode<Key>> mapper;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<>();
        this.mapper = new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            DoublyLinkedListNode<Key> newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public Key evictKey() {
        DoublyLinkedListNode<Key> first = dll.getFirstNode();
        if(first == null) {
            return null;
        }
        dll.detachNode(first);
        return first.getElement();
    }
}

//cache/factories---------------------------------------------------
class CacheFactory<Key, Value> {

    public Cache<Key, Value> defaultCache(final int capacity) {
        return new Cache<Key, Value>(new LRUEvictionPolicy<Key>(),
                new HashMapBasedStorage<Key, Value>(capacity));
    }
}

//cache/exception---------------------------------------------------
class NotFoundException1 extends RuntimeException {

    public NotFoundException1(final String message) {
        super(message);
    }
}
class StorageFullException extends RuntimeException {

    public StorageFullException(String message) {
        super(message);
    }
}

//algorithm---------------------------------------------------
@Getter
class DoublyLinkedListNode<E> {
    DoublyLinkedListNode<E> next;
    DoublyLinkedListNode<E> prev;
    E element;

    public DoublyLinkedListNode(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }
}
class DoublyLinkedList<E> {

    DoublyLinkedListNode<E> dummyHead;
    DoublyLinkedListNode<E> dummyTail;

    public DoublyLinkedList() {
        // We can instantiate these by null, since we are never gonna use val for these dummyNodes
        dummyHead = new DoublyLinkedListNode<>(null);
        dummyTail = new DoublyLinkedListNode<>(null);

        // Also Initially there are no items
        // so just join dummyHead and Tail, we can add items in between them easily.
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public void detachNode(DoublyLinkedListNode<E> node) {
        // Just Simply modifying the pointers.
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void addNodeAtLast(DoublyLinkedListNode<E> node) {
        DoublyLinkedListNode tailPrev = dummyTail.prev;
        tailPrev.next = node;
        node.next = dummyTail;
        dummyTail.prev = node;
        node.prev = tailPrev;
    }

    public DoublyLinkedListNode<E> addElementAtLast(E element) {
        if (element == null) {
            throw new InvalidElementException();
        }
        DoublyLinkedListNode<E> newNode = new DoublyLinkedListNode<>(element);
        addNodeAtLast(newNode);
        return newNode;
    }

    public boolean isItemPresent() {
        return dummyHead.next != dummyTail;
    }

    public DoublyLinkedListNode getFirstNode() throws NoSuchElementException {
        DoublyLinkedListNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return dummyHead.next;
    }

    public DoublyLinkedListNode getLastNode() throws NoSuchElementException {
        DoublyLinkedListNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return dummyTail.prev;
    }
}


//algorithm/exception---------------------------------------------------
class InvalidElementException extends RuntimeException {
}
class InvalidNodeException extends RuntimeException {
}

//client====================================================================
class CacheLLD_Udit {


}
*/
