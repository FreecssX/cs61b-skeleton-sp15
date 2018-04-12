/* Radix.java */

package radix;

/**
 * Sorts is a class that contains an implementation of radix sort.
 * @author
 */
public class Sorts {


    /**
     *  Sorts an array of int keys according to the values of <b>one</b>
     *  of the base-16 digits of each key. Returns a <b>NEW</b> array and
     *  does not modify the input array.
     *  
     *  @param key is an array of ints.  Assume no key is negative.
     *  @param whichDigit is a number in 0...7 specifying which base-16 digit
     *    is the sort key. 0 indicates the least significant digit which
     *    7 indicates the most significant digit
     *  @return an array of type int, having the same length as "keys"
     *    and containing the same keys sorted according to the chosen digit.
     **/
    public static int[] countingSort(int[] keys, int whichDigit) {
        int[] num = new int[16];
        int[] shiftedKeys = new int[keys.length];
        for(int i = 0; i < keys.length; i += 1) {
            int keyShifted = keys[i];
            keyShifted = keyShifted >> (4 * whichDigit);
            shiftedKeys[i] = (keyShifted & 15);
            num[keyShifted & 15] = num[keyShifted & 15] + 1;
        }
        int sum = 0;
        int[] position = new int[16];
        for(int i = 0; i < 16; i += 1) {
            position[i] = sum;
            sum += num[i];
        }
        int[] sortedArray = new int[keys.length];
        for(int i = 0; i < sortedArray.length; i += 1) {
            sortedArray[position[shiftedKeys[i]]] = keys[i];
            position[shiftedKeys[i]] = position[shiftedKeys[i]] + 1; 
        }
        return sortedArray;
    }

    /**
     *  radixSort() sorts an array of int keys (using all 32 bits
     *  of each key to determine the ordering). Returns a <b>NEW</b> array
     *  and does not modify the input array
     *  @param key is an array of ints.  Assume no key is negative.
     *  @return an array of type int, having the same length as "keys"
     *    and containing the same keys in sorted order.
     **/
    public static int[] radixSort(int[] keys) {
        for(int i = 0; i < 8; i += 1) {
            keys = countingSort(keys, i);
        }
        return keys;
    }

}
