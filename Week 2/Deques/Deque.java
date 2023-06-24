/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node imaginaryFirstNode;
    private Node imaginaryLastNode;
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
        imaginaryFirstNode = new Node(null);
        imaginaryLastNode = new Node(null);

        imaginaryFirstNode.next = imaginaryLastNode;
        imaginaryLastNode.prev = imaginaryFirstNode;

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

        Node currentFirst = imaginaryFirstNode.next;
        Node newFirst = new Node(item);

        newFirst.next = currentFirst;
        currentFirst.prev = newFirst;

        newFirst.prev = imaginaryFirstNode;
        imaginaryFirstNode.next = newFirst;

        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("addLast(): Cannot add null item to deque.");
        }

        Node currentLast = imaginaryLastNode.prev;
        Node newLast = new Node(item);

        newLast.prev = currentLast;
        currentLast.next = newLast;

        newLast.next = imaginaryLastNode;
        imaginaryLastNode.prev = newLast;

        ++size;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("removeFirst(): Deque is already empty.");
        }

        Node oldFirst = imaginaryFirstNode.next;

        imaginaryFirstNode.next = oldFirst.next;
        oldFirst.next.prev = imaginaryFirstNode;

        --size;
        return oldFirst.data;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("removeLast(): Deque is already empty.");
        }

        Node oldLast = imaginaryLastNode.prev;

        imaginaryLastNode.prev = oldLast.prev;
        oldLast.prev.next = imaginaryLastNode;

        --size;
        return oldLast.data;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node currentNode = imaginaryFirstNode;

            @Override
            public boolean hasNext() {
                return !(currentNode.next == imaginaryLastNode);
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
        System.out.println("Deque is empty?: " + myDeque.isEmpty());
        System.out.println("Deque size: " + myDeque.size());
        myDeque.addFirst(6);
        System.out.println("Deque size: " + myDeque.size());
        System.out.println("Deque is empty?: " + myDeque.isEmpty());
        myDeque.addFirst(5);
        System.out.println("Deque size: " + myDeque.size());
        myDeque.addLast(7);
        System.out.println("Deque size: " + myDeque.size());

        for (Integer i : myDeque) {
            System.out.println(i);
        }

        System.out.println("First element: " + myDeque.removeFirst());
        System.out.println("Deque size: " + myDeque.size());

        for (Integer i : myDeque) {
            System.out.println(i);
        }

        System.out.println("Last element: " + myDeque.removeLast());
        System.out.println("Deque size: " + myDeque.size());

        for (Integer i : myDeque) {
            System.out.println(i);
        }
    }

}
