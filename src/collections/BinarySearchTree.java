package collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import collections.ArrayList;
import collections.LinkedList;
import collections.List;
/**
 * Represents a BinarySearchTree, the basics behind a tree like this is that if a children node
 * has a less value compared to is parent it will be placed to the left under the parent. Likewise,
 * if the children has a greater value than the parent it will be placed to the right under the parent node.
 * 
 * @author Benjamin Sejdic
 *
 * @param <K> key
 * @param <V> value
 */

public class BinarySearchTree<K, V> implements SearchTree<K, V> {
	private Comparator<K> comparator;
	private BSTNode<K, V> tree;
	private int size;

	/**
	 * Constructs and initializes a new comparator
	 */
	public BinarySearchTree() {
		comparator = new Comp();
	}

	/**
	 * Constructs and initalizes a optional comparator
	 * @param comp the comparator
	 */
	public BinarySearchTree(Comparator<K> comp) {
		comparator = comp;
	}

	/**
	 * Returns the tree that holds the nodes
	 * @return tree 
	 */
	public BSTNode<K, V> root() {
		return tree;
	}
	/**
	 * Returns the value of the key 
	 * @param key the key 
	 * @return the value of the key or null if it isnt found
	 */
	public V get(K key) {
		BSTNode<K, V> node = find(key);
		if (node != null)
			return node.value;
		return null;
	}

	/**
	 * Put a new key with a value into the tree
	 * @param key name of the key
	 * @param value the value associated with the key
	 */
	public void put(K key, V value) {
		tree = put(tree, key, value);
	}

	/**
	 * Removes the specified key
	 * @param key the key to be removed
	 * @return null if the key isn't found, if the key is found then the value of the key is 
	 * returned 
	 */
	public V remove(K key) {
		V value = get(key);
		if (value != null) {
			tree = remove(tree, key);
		}
		return value;
	}

	/**
	 * Checks whether the tree contains the key or not
	 * @param key the key
	 * @return true if the key is in the tree and false if not
	 */
	public boolean contains(K key) {
		return find(key) != null;
	}

	/**
	 * The height of the tree gets returned, that is how many nodes we need to pass in order to 
	 * get to the last node which is found on the bottom of the tree
	 */
	public int height() {
		return height(tree);
	}

	/**
	 * Returns an iterator over the nodes
	 * @return an iterator over the nodes
	 */
	public Iterator<V> iterator() {
		return new Iter();
	}

	private BSTNode<K, V> find(K key) {
		int res;
		BSTNode<K, V> node = tree;
		while ((node != null) && ((res = comparator.compare(key, node.key)) != 0)) {
			if (res < 0)
				node = node.left;
			else
				node = node.right;
		}
		return node;
	}

	private BSTNode<K, V> put(BSTNode<K, V> node, K key, V value) {
		if (node == null) {
			node = new BSTNode<K, V>(key, value, null, null);
			size++;
		} else {
			if (comparator.compare(key, node.key) < 0) {
				node.left = put(node.left, key, value);
			} else if (comparator.compare(key, node.key) > 0) {
				node.right = put(node.right, key, value);
			}
		}
		return node;
	}

	private BSTNode<K, V> remove(BSTNode<K, V> node, K key) {
		int compare = comparator.compare(key, node.key);
		if (compare == 0) {
			if (node.left == null && node.right == null)
				node = null;
			else if (node.left != null && node.right == null)
				node = node.left;
			else if (node.left == null && node.right != null)
				node = node.right;
			else {
				BSTNode<K, V> min = getMin(node.right);
				min.right = remove(node.right, min.key);
				min.left = node.left;
				node = min;
			}
		} else if (compare < 0) {
			node.left = remove(node.left, key);
		} else {
			node.right = remove(node.right, key);
		}
		return node;
	}

	private BSTNode<K, V> getMin(BSTNode<K, V> node) {
		while (node.left != null)
			node = node.left;
		return node;
	}

	private int height(BSTNode<K, V> node) {
		if (node == null)
			return -1;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	/**
	 * Returns the number of elements in the tree with the size method in the BSTNode class
	 * @return number of elements in thee tree
	 */
	public int size1() {
		if (tree == null) {
			return 0;
		}
		return tree.size();
	}

	/**
	 * Returns the number of elements in the tree
	 * @return number of elements in the tree
	 */
	public int size2() {
		return size2(tree);
	}

	private int size2(BSTNode<K, V> node) {
		if (node == null) {
			return 0;
		}
		return 1 + size2(node.left) + size2(node.right);
	}

	/**
	 * Returns the size of the tree
	 * @return size of the tree
	 */
	public int size() {
		return size;
	}

	/**
	 * All values from the tree gets copied and placed in an arrayList ordered
	 * @return an arrayList with the values from the tree
	 */
	public List<K> keys() {
		ArrayList<K> list = new ArrayList<K>();
		keys(tree, list);
		return list;
	}

	private void keys(BSTNode<K, V> node, ArrayList<K> list) {
		if (node != null) {
			keys(node.left, list);
			list.add(node.key);
			keys(node.right, list);
		}

	}

	/**
	 * All values from the tree gets copied and placed in a LinkedList in postorder
	 * @return a LinkedList with the values stored in postorder from the tree 
	 */
	public List<V> values() {
		ArrayList<V> list = new ArrayList<V>();
		values(tree, list);
		return list;
	}

	private void values(BSTNode<K, V> node, ArrayList<V> list) {
		if (node != null) {
			values(node.left, list);
			list.add(node.value);
			values(node.right, list);
		}
	}

	/**
	 * Returns the element with the least key
	 * @return the value of the least key
	 */
	public V first() {
		BSTNode<K, V> node = tree;
		if (node == null) {
			return null;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node.value;
	}

	/**
	 * Returns the element with the highest key value
	 * @return the value of the higest key value
	 */
	public V last() {
		BSTNode<K, V> node = tree;
		if (node == null) {
			return null;
		}
		while (node.right != null) {
			node = node.right;
		}
		return node.value;
	}

	/**
	 * Prints out the keys and corresponding values from the tree
	 */
	public void print() {
		print(tree);
	}

	private void print(BSTNode<K, V> node) {
		if (node != null) {
			print(node.left);
			System.out.println("Key: " + node.key + " | " + "Value: " + node.value);
			print(node.right);

		}
	}

	/**
	 * Prints out the keys and corresponding values from the tree in preorder 
	 */
	public void printPreorder() {
		printPreorder(tree);
	}

	private void printPreorder(BSTNode<K, V> node) {
		if (node != null) {
			System.out.println("Key: " + node.key + " | " + "Value " + node.value);
			printPreorder(node.left);
			printPreorder(node.right);
		}
	}

	/**
	 * Prints out the keys and corresponding values from the tree in postorder
	 */
	public void printPostorder() {
		printPostorder(tree);
	}

	private void printPostorder(BSTNode<K, V> node) {
		if (node != null) {
			printPostorder(node.left);
			printPostorder(node.right);
			System.out.println("Key: " + node.key + " | " + "Value " + node.value);
		}
	}

	private class Comp implements Comparator<K> {
		public int compare(K key1, K key2) {
			Comparable<K> k1 = (Comparable<K>) key1;
			return k1.compareTo(key2);
		}
	}

	/**
	 * Class that implements an iterator over an arraylist, the elements in the arraylist are stored in 
	 * inorder
	 * @author Benjamin Sejdic
	 *
	 */
	private class Iter implements Iterator<V> {
		ArrayList<V> list = new ArrayList<V>();
		int index = -1;

		/*
		 * Stors the values from the tree in an arrayList in inorder
		 */
		public Iter() {
			inOrder(tree);
		}

		private void inOrder(BSTNode<K, V> node) {
			if (node != null) {
				inOrder(node.left);
				list.add(node.value);
				inOrder(node.right);
			}
		}

		/**
		 * Return true if there is more elements otherwise false
		 */
		public boolean hasNext() {
			return index < list.size() - 1;
		}

		/**
		 * Get the next element in the arraylist
		 */
		public V next() {
			if (!hasNext())
				throw new NoSuchElementException();
			index++;
			return list.get(index);
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
