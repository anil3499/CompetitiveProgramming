package org.ap.generics;/*
package org.ap.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericSort {
    private static <E extends Comparable<E>> void swap(List<E> sequence,
                                                       int positionOne, int positionTwo) {
        E temp = sequence.get(positionOne);
        sequence.set(positionOne, sequence.get(positionTwo));
        sequence.set(positionTwo, temp);
    }
    public static <E extends Comparable<E>> List<E> selectionSort(List<E> sequence) {
        for (int i = 0; i < sequence.size(); i++) {
            int minPosition = -1;
            E minValue = sequence.get(i);
            for (int j = i + 1; j < sequence.size(); j++) {
                E secondPosition = sequence.get(j);
                if (minValue.compareTo(secondPosition) > 0) {
                    minPosition = j;
                    minValue = sequence.get(j);
                }
            }
            if (minPosition >= 0) {
                swap(sequence, i, minPosition);
            }
        }
        return sequence;
    }

    public static <E extends Comparable<E>> E[] selectionSort(E[] sequence) {
        return selectionSort(new ArrayList(Arrays.asList(sequence))).toArray(new E[sequence.length]);
    }

    public static void main(String[] args) {
        Integer[] integers = {1, 6, 88, 7, 44, 3, 11};
        System.out.println("Before: " + Arrays.toString(integers));
        GenericSort.selectionSort(integers);
        System.out.println("Later: " + Arrays.toString(integers));
    }
}*/
