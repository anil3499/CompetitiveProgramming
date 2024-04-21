package org.ap.datastrutures.linkedlist.linkedlist;
/*
https://www.geeksforgeeks.org/delete-a-node-from-linked-list-without-head-pointer/
-> this soln will not work for last element deletion
You will be given with only node which needs to be deleted not head

Logic :
1. Copy the data of next node to current node
2. Link current node after next node to current node
Ex :
a -> b -> c -> d ->e
We need to delete b and same reference is given to us
copy data of next node
a -> c -> c -> d -> e
link next to next point (deleting next second C)
a -> c -> d ->e
 */
public class DeleteNodeWithoutHeadPointer {
    public static void main(String[] args) {

    }
}
