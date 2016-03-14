package collections;

import java.util.Comparator;
/**
 * This class can be used to store different elements in a ArrayHeap, much similar to a stack but
 * the difference is that each elements has a priority (first in first out - FIFO) and a 
 * reference to another element.
 * @author Benjamin Sejdic
 * 
 * @param <E>
 */

public class PriorityQueue<E> implements Queue<E> {
	private ArrayHeap<PriorityQueueElement<E>> queue;

	/**
	 * Constructs the queue with a capacity of 20
	 */
	public PriorityQueue() {
		this(20);
	}

	/**
	 * Constructs the queue with a optional capacity
	 * @param initialCapacity the initial capacity of the list 
	 */
	public PriorityQueue(int initialCapacity) {
		queue = new ArrayHeap<PriorityQueueElement<E>>(initialCapacity);
	}

	/**
	 * 
	 * @param initialCapacity
	 * @param comparator
	 */
	public PriorityQueue(int initialCapacity, Comparator<E> comparator) {
		queue = new ArrayHeap<PriorityQueueElement<E>>(initialCapacity, new PriorityQueueComparator<E>(comparator));
	}

	/**
	 * Insert a PriorityQueueElement into the heap
	 * @param data the data
	 */
	public void enqueue(E data) {
		queue.insert(new PriorityQueueElement<E>(data));
	}

	/**
	 * Deletes the PriorityQueueElement with the highest priority
	 * @return the deleted element
	 */
	public E dequeue() {
		return queue.delete().getElement();
	}

	/**
	 * Returns the first stored element in the PriorityQueueElement
	 * @return first element in the ArrayHeap
	 */
	public E peek() {
		return queue.peek().getElement();
	}

	/**
	 * Returns true if the ArrayHeap is empty and false if it's not.
	 * @return true or false 
	 */
	public boolean isEmpty() {
		return queue.size() == 0;
	}

	/**
	 * Returns the size of the arrayheap
	 * @return size of arrayheap
	 */
	public int size() {
		return queue.size();
	}
}

class PriorityQueueElement<E> implements Comparable<PriorityQueueElement<E>> {
	private static int counter = 1;
	private E element;
	private int order;

	public PriorityQueueElement(E element) {
		this.element = element;
		this.order = counter++;
	}
	
	public E getElement() {
		return element;
	}

	public int getOrder() {
		return order;
	}

	public int compareTo(PriorityQueueElement<E> pqElement) {
		int res = ((Comparable<E>) element).compareTo(pqElement.element);
		if (res == 0)
			res = order - pqElement.order;
		return res;
	}
}

class PriorityQueueComparator<E> implements Comparator<PriorityQueueElement<E>> {
	private Comparator<E> comp;

	public PriorityQueueComparator(Comparator<E> comparator) {
		comp = comparator;
	}

	public int compare(PriorityQueueElement<E> pqElement1, PriorityQueueElement<E> pqElement2) {
		int res = comp.compare(pqElement1.getElement(), pqElement2.getElement());
		if (res == 0)
			res = pqElement1.getOrder() - pqElement2.getOrder();
		return res;
	}
}
