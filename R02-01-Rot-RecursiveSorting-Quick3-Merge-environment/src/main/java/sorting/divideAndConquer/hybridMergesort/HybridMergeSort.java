package sorting.divideAndConquer.hybridMergesort;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			int middleIndex = (leftIndex + rightIndex) / 2;

			sort(array, leftIndex, middleIndex);
			MERGESORT_APPLICATIONS = 0; 
			INSERTIONSORT_APPLICATIONS = 0;
			sort(array, middleIndex + 1, rightIndex);
			MERGESORT_APPLICATIONS = 0; 
			INSERTIONSORT_APPLICATIONS = 0;
			merge(array, leftIndex, middleIndex, rightIndex);
			MERGESORT_APPLICATIONS++;
		}
	}
	
	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
			if (array.length <= SIZE_LIMIT) {
				insertionSort(array, leftIndex, rightIndex);
				INSERTIONSORT_APPLICATIONS++;
			} else {
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

	private void insertionSort(T[] array, int leftIndex, int rightIndex) {
		for(int i = leftIndex+1; i <= rightIndex; i++){
			T key = array[i];
			int j = i-1;		
			int m = i;
			while(j >= 0 && array[j].compareTo(key) > 0){
				array[m] = array[j];
				j--;
				m--;
			}
			array[j+1] = key;
		}
	}
}
