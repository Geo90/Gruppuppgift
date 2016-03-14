package collections;

import java.util.Comparator;

/**
 * A heap
 * @author Benjamin Sejdic
 *
 * @param <E> any type of object
 */
public class ArrayHeap<E> {
	private E[] list;
	private int size;
	private Comparator<E> comp;

	/**
	 * Creates a new arrayheap 
	 * @param initialCapacity size of the heap
	 */
	public ArrayHeap(int initialCapacity) {
		initialCapacity = Math.max(initialCapacity, 20);
		list = (E[]) (new Object[initialCapacity]);
		comp = new Comp(); // Den naturliga sorteringsordningen
	}

	/**
	 * Adds new elements to the arrayheap and a new custom comparator
	 * @param elements the array to add to the arrayheap
	 * @param comparator the custom comparator
	 */
	public ArrayHeap(E[] elements, Comparator<E> comparator) {
		this.list = elements;
		size = list.length;
		comp = comparator;
		heapify();
	}
	
	/**
	 * Creates a bew arrayheap with a new custom comparator
	 * @param initialCapacity size of the heap
	 * @param comparator the custom comparator
	 */
	public ArrayHeap(int initialCapacity, Comparator<E> comparator) {
		initialCapacity = Math.max(initialCapacity, 20);
		list = (E[])(new Object[initialCapacity]);
		comp = comparator;
	}

	/**
	 * Adds new elements to the arrayheap
	 * @param elements the array to add to the arrayheap
	 */
	public ArrayHeap(E[] elements) {
		this.list = elements;
		size = list.length;
		comp = new Comp();
		heapify();
	}

	public void heapify() {
		int parent = (size - 2) / 2;
		while (parent >= 0) {
			siftDown(parent);
			parent--;
		}
	}

	/**
	 * Sorts the array in reverese order compared to the natural order
	 * @param arr the array to be sorted
	 */
	public static <E> void sort(E[] arr) {
		ArrayHeap<E> heap = new ArrayHeap<E>(arr, new ReverseComparable<E>());
		for (int i = arr.length - 1; i > 0; i--) {
			arr[i] = heap.delete();
		}
	}

	private void grow() {
		E[] newList = (E[]) (new Object[list.length * 2]);
		System.arraycopy(list, 0, newList, 0, list.length);
		list = newList;
	}

	private void siftUp(E value) {
		int position = size;
		int parent = (position - 1) / 2;
		while (position > 0 && comp.compare(value, list[parent]) < 0) {
			list[position] = list[parent];
			position = parent;
			parent = (position - 1) / 2;
		}
		list[position] = value;
	}

	private void siftDown(int parent) {
		E value = list[parent];
		int child = parent * 2 + 1; // first child
		while (child < size) { // at least one child
			if ((child + 1 < size) && (comp.compare(list[child], list[child + 1]) > 0)) {
				child++;
			}
			if (comp.compare(value, list[child]) > 0) {
				list[parent] = list[child];
				parent = child;
				child = parent * 2 + 1;
			} else
				break;
		}
		list[parent] = value;
	}

	/**
	 * Inserts a value into the hep
	 * @param value the value to insert
	 */
	public void insert(E value) {
		if (size == list.length) {
			grow();
		}
		siftUp(value);
		size++;
	}

	/**
	 * Deletes the first value
	 * @return the first value
	 */
	public E delete() {
		E value = null;
		if (size > 0) {
			value = list[0];
			list[0] = list[--size];
			siftDown(0);
		}
		return value;
	}

	public E peek() {
		return (size > 0) ? list[0] : null;
	}

	/**
	 * The size of the heap gets returned
	 * @return size of the heap
	 */
	public int size() {
		return size;
	}

	private class Comp implements Comparator<E> {
		public int compare(E elem1, E elem2) {
			return ((Comparable<E>) elem1).compareTo(elem2);
		}
	}
}
