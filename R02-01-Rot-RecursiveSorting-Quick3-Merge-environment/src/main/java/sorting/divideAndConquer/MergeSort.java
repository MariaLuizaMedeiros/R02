package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;
/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			int middleIndex = (leftIndex + rightIndex) / 2;

			this.sort(array, leftIndex, middleIndex);
			this.sort(array, middleIndex + 1, rightIndex);

			merge(array, leftIndex, middleIndex, rightIndex);
		}
	}

	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
		T[] array2 = Arrays.copyOf(array, array.length);
		int i = leftIndex;
		int j = leftIndex;
		int k = middleIndex + 1;

		while ((i <= middleIndex) && (k <= rightIndex)) {
			if (array2[i].compareTo(array2[k]) < 0) {
				array[j] = array2[i];
				i++;
			} else {
				array[j] = array2[k];
				k++;
			}
			j++;
		}

		while (i <= middleIndex) {
			array[j] = array2[i];
			i++;
			j++;
		}

		while (k <= middleIndex) {
			array[j] = array2[k];
			j++;
			k++;
		}
	}
}
