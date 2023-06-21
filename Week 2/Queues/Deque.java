/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private DoublyLinkedList list;

    class DoublyLinkedList {
        Node first;
        Node last;

        class Node {
            Item data;
            Node next;
            Node prev;

            Node(Item i) {
                data = i;
                next = null;
                prev = null;
            }
        }

        DoublyLinkedList() {
            first = null;
            last = null;
        }
    }

    // construct an empty deque
    public Deque() {
        list = new DoublyLinkedList();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size() == 0);
    }

    // return the number of items on the deque
    public int size() {
        return -1;
    }

    // add the item to the front
    public void addFirst(Item item) {

    }

    // add the item to the back
    public void addLast(Item item) {

    }

    // remove and return the item from the front
    public Item removeFirst() {
        return new Item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        return new Item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        // https://stackoverflow.com/questions/20949978/return-an-iterator
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
