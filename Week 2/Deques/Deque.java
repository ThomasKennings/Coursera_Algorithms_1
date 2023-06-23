/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item data;
        Node next;
        Node prev;

        Node(Item i) {
            data = i;
            next = null;
            prev = null;
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("addFirst(): Cannot add null item to deque.");
        }

        Node newFirst = new Node(item);
        newFirst.next = first;

        if (!isEmpty()) {
            first.prev = newFirst;
        }

        first = newFirst;
        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("addLast(): Cannot add null item to deque.");
        }

        Node newLast = new Node(item);
        newLast.prev = last;

        if (!isEmpty()) {
            last.next = newLast;
        }

        first = newLast;
        ++size;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("removeFirst(): Deque is already empty.");
        }

        Node oldFirst = first;

        if (size() > 1) {
            first = first.next;
        }

        first.prev = null;
        --size;
        return oldFirst.data;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("removeLast(): Deque is already empty.");
        }

        Node oldLast = last;

        if (size() > 1) {
            last = last.prev;
        }

        last.next = null;
        --size;
        return oldLast.data;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node currentNode = first;
            // needs to point at something before first node. Currently it skips the first node in the foreach loop

            @Override
            public boolean hasNext() {
                return !(currentNode.next == null);
            }

            @Override
            public Item next() {
                if (hasNext()) {
                    currentNode = currentNode.next;
                    return currentNode.data;
                }
                throw new NoSuchElementException("Iterator.next(): End of deque reached.");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Iterator.remove(): Remove not allowed.");
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> myDeque = new Deque<Integer>();
        myDeque.addFirst(6);
        myDeque.addFirst(5);
        myDeque.addFirst(6);
        myDeque.addFirst(5);
        myDeque.addFirst(6);
        myDeque.addFirst(5);
        for (Integer i : myDeque) {
            System.out.println(i);
        }
    }

}
