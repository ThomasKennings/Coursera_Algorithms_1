/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int numberFullCells;
    private int arrayLength;

    // construct an empty randomized queue
    public RandomizedQueue() {
        numberFullCells = 0;
        arrayLength = 0;    // what should initial array length be?
        // instantiate array of length ??? type Item, all cells null
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

        // if arrayLength >= numberFullCells
        //      double array size (private method)

        // add item to first available empty space
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("dequeue(): Queue is already empty.");
        }

        // i = getRandomFullIndex() // <-- private method
        // set data at i to null
        // return data that was at i

        // if arrayLength > 4*numberFullCells
        //      cut array length in half (private method)

        // there is a much simpler, elegant implementation where you don't end up with nulls in your array.
        // Rather, when you dequeue a random element from the array, you then take the element on the
        // tail of the array and insert it into that index.
        return null;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("sample(): Queue is empty.");
        }

        // return value at random non-empty cell in array
        // getRandomFullIndex() // <-- private method
        return null;
    }

    // private function that 'slides' all elements over such that there are no empty spaces between them.

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            @Override
            public boolean hasNext() {
                return !isEmpty();
            }

            @Override
            public Item next() {
                // should this dequeue the items? Or should it just sample?
                // if sample, will need to keep track of which cells have been visited on a per-iterator basis.
                // simply "return dequeue()" if this should dequeue.
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Iterator.remove(): Remove not allowed.");
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
