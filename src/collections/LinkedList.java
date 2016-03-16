package collections;

import java.util.Iterator;

public class LinkedList<E> implements List<E>, Iterable<E> {
	private ListNode<E> list = null;

	private ListNode<E> locate(int index) {
		ListNode<E> node = list;
		for (int i = 0; i < index; i++)
			node = node.getNext();
		return node;
	}

	public int size() {
		int n = 0;
		ListNode<E> node = list;
		while (node != null) {
			node = node.getNext();
			n++;
		}
		return n;
	}

	public E get(int index) {
		if ((index < 0) || (index >= size())) {
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);
		}
		ListNode<E> node = locate(index);
		return node.getData();
	}

	public E set(int index, E data) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> node = locate(index);
		E res = node.getData();
		node.setData(data);
		return res;
	}

	public void add(E data) {
		add(size(), data);
	}

	public void addFirst(E data) {
		add(0, data);
	}

	public void addLast(E data) {
		add(size(), data);
	}

	public void add(int index, E data) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			list = new ListNode<E>(data, list);
		} else {
			ListNode<E> node = locate(index - 1);
			ListNode<E> newNode = new ListNode<E>(data, node.getNext());
			node.setNext(newNode);
		}

	}
	
	public E add2(int index, E data) {
		E res = null;
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			list = new ListNode<E>(data, list);
			res = list.getData();
		} else {
			ListNode<E> node = locate(index - 1);			
			ListNode<E> newNode = new ListNode<E>(data, node.getNext());
			node.setNext(newNode);
			res = node.getData();
			
		}
		return res; 

	}
	

	public E removeFirst() {
		return remove(0);
	}

	public E removeLast() {
		return remove(size() - 1);
	}

	public E remove(int index) {
		if ((index < 0) || (index >= size())) {
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);
		}
		E res;
		if (index == 0) {
			res = list.getData();
			list = setNull(list);
			// list = list.getNext();
		} else {
			ListNode<E> node = locate(index - 1);
			res = node.getNext().getData();
			node.setNext(setNull(node.getNext()));
			// node.setNext( node.getNext().getNext() );
		}
		return res;
	}

	public E getData() {
		E res;
		res = list.getData();
		return res;
	}

	private ListNode<E> setNull(ListNode<E> toNull) {
		ListNode<E> res = toNull.getNext();
		toNull.setData(null);
		toNull.setNext(null);
		return res;
	}

	public void clear() {
		int time = size();
		for (int i = 0; i < time; i++) {
			removeLast();
		}
	}

	public int indexOf(E data) {
		ListNode<E> theNode = list;
		int index = 0;
		while (theNode != null) {
			if (theNode.getData().equals(data)) {
				return index;
			}
			theNode = theNode.getNext();
			index++;
		}
		return -1;
	}

	public int indexOf(int startIndex, E data) {
		ListNode<E> theNode = locate(startIndex);
		int index = startIndex;
		while (theNode != null) {
			if (theNode.getData().equals(data)) {
				return index;
			}
			theNode = theNode.getNext();
			index++;
		}
		return -1;
	}

	public Iterator<E> iterator() {
		return new Iter();
	}

	public String toString() {
		if (list != null)
			return list.toString();
		else
			return "[]";
	}

	private class Iter implements Iterator<E> {
		ListNode<E> iterList = list;

		public boolean hasNext() {
			return (iterList != null);

		}

		public E next() {
			ListNode<E> temp = iterList;
			iterList = temp.getNext();
			return temp.getData();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
