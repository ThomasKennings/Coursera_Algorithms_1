/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int numberFullCells;
    private int arrayLength;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        numberFullCells = 0;
        arrayLength = 1;
        queue = (Item[]) new Object[arrayLength];
    }

    // Copy constructor
    private RandomizedQueue(RandomizedQueue<Item> queueToCopy) {
        this.numberFullCells = queueToCopy.numberFullCells;
        this.arrayLength = queueToCopy.arrayLength;
        this.queue = queueToCopy.queue;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (numberFullCells == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return numberFullCells;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("enqueue(): Cannot add null item.");
        }

        if (arrayLength <= numberFullCells) {
            lengthenArray();
        }

        queue[numberFullCells] = item;
        ++numberFullCells;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("dequeue(): Queue is already empty.");
        }

        int i;
        Item item;

        i = getRandomFullIndex();

        item = queue[i];
        queue[i] = queue[numberFullCells - 1];
        queue[numberFullCells - 1] = null;
        --numberFullCells;

        if ((arrayLength > 4 * numberFullCells) && (numberFullCells != 0)) {
            shortenArray();
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("sample(): Queue is empty.");
        }

        return queue[getRandomFullIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            // Iterators interfering with each other
            private RandomizedQueue<Item> iterQueue = new RandomizedQueue<Item>(
                    RandomizedQueue.this);

            @Override
            public boolean hasNext() {
                return !(iterQueue.isEmpty());
            }

            @Override
            public Item next() {
                if (iterQueue.isEmpty()) {
                    throw new NoSuchElementException("Iterator.next(): No elements remaining.");
                }
                return iterQueue.dequeue();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Iterator.remove(): Remove not allowed.");
            }
        };
    }

    private void lengthenArray() {
        int oldArrayLength = arrayLength;
        arrayLength *= 2;
        Item[] newQueue = (Item[]) new Object[arrayLength];
        for (int i = 0; i < oldArrayLength; ++i) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    private void shortenArray() {
        arrayLength /= 2;
        Item[] newQueue = (Item[]) new Object[arrayLength];
        for (int i = 0; i < arrayLength; ++i) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    private int getRandomFullIndex() {
        return StdRandom.uniformInt(numberFullCells);
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Write unit testing here
    }

}
