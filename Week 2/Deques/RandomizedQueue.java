/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // there is a much simpler, elegant implementation where you don't end up with nulls in your array.
    // Rather, when you dequeue a random element from the array, you then take the element on the
    // tail of the array and insert it into that index.
    private int numberFullCells;
    private int arrayLength;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        numberFullCells = 0;
        arrayLength = 10;
        queue = (Item[]) new Object[arrayLength];
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

        // issue: is assignment operator copying value? if not, value of "item" will change
        item = queue[i];
        queue[i] = queue[numberFullCells - 1];
        queue[numberFullCells - 1] = null;
        --numberFullCells;

        if (arrayLength > 4 * numberFullCells) {
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

    private void shortenArray() {
        // cut array length in half
    }

    private int getRandomFullIndex() {
        return StdRandom.uniformInt(numberFullCells);
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> Q = new RandomizedQueue<Integer>();
        System.out.println("Array Length: " + Q.arrayLength);
        System.out.println("Number Full Cells: " + Q.numberFullCells);
        System.out.println(Arrays.toString(Q.queue));

        Q.enqueue(1);
        Q.enqueue(2);
        Q.enqueue(3);
        Q.enqueue(4);

        System.out.println("Array Length: " + Q.arrayLength);
        System.out.println("Number Full Cells: " + Q.numberFullCells);
        System.out.println(Arrays.toString(Q.queue));

        System.out.println("Sampled object: " + Q.sample());
        
    }

}
