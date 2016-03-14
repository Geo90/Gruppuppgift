package collections;
/**
 * This class can be used to store different elements in a LinkedList which is similar to a stack but
 * each elements has a priority (first in first out - FIFO).
 * 
 * @author Benjamin Sejdic
 *
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {
	LinkedList<E> list = new LinkedList<E>();

	public void enqueue(E data) {
		list.addLast(data);
	}

	public E dequeue() {
		E data = list.getData();
		list.removeFirst();
		return data;
	}

	public E peek() {
		return list.getData();
	}

	public boolean isEmpty() {
		return (list.size() == 0);
	}

	public int size() {
		return list.size();
	}

}
