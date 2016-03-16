package collections;

import java.util.Comparator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import collections.ArrayList;
import collections.List;
/**
 * Representerar ett binärt sökträd som lagrar noder som är mindre än en annan node till vänster och de som är större till höger.  
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
	 * Konstruerar och initialiserar en ny comparator
	 */
	public BinarySearchTree() {
		comparator = new Comp();
	}

	/**
	 * Konstruerar och initialiserar en valfri comparator
	 * @param comp den comparator som man vill använda
	 */
	public BinarySearchTree(Comparator<K> comp) {
		comparator = comp;
	}

	/**
	 * Returnerar ett träd 
	 * @return ett träd 
	 */
	public BSTNode<K, V> root() {
		return tree;
	}
	
	/**
	 * Returnerar en nyckels värde 
	 * @param key den nyckel man vill hämta värdet ifrån 
	 * @return värdet (arraylist) av nyckeln eller null om key inte finns 
	 */
	public V get(K key) {
		BSTNode<K, V> node = find(key);
		if (node != null)
			return node.value;
		return null;
	}

	/**
	 * Sätter en ny nod i trädet 
	 * @param key nyckeln
	 * @param value värdet som ska förknippas med nyckeln
	 */
	public void put(K key, V value) {
		tree = put(tree, key, value);
	}

	/**
	 * Tar bort en nyckel i trädet 
	 * @param key nyckeln som man vill ta bort  
	 * @return null om nyckeln inte hittas annars returneras värdet som är förknippad med nyckeln
	 */
	public V remove(K key) {
		V value = get(key);
		if (value != null) {
			tree = remove(tree, key);
		}
		return value;
	}

	/**
	 * Kollar om nyckeln finns i trädet
	 * @param key nyckeln man vill söka efter
	 * @return true om nyckeln finns i trädet annars false
	 */
	public boolean contains(K key) {
		return find(key) != null;
	}

	/**
	 * Trädets höjd returneras, det vill säga antalet noder som måste passeras för att komma till den sista noden
	 * som är längst ner i trädet
	 */
	public int height() {
		return height(tree);
	}

	/**
	 * Returnerar en iterator över noderna i trädet
	 * @return en iterator över noderna i trädet
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
	 * Returnerar antalet element i trädet med size metoden i BSTNode klassen
	 * @return antalet element i trädet 
	 */
	public int size1() {
		if (tree == null) {
			return 0;
		}
		return tree.size();
	}

	/**
	 * Returnerar antalet element i trädet med hjälp av rekursion
	 * @return antalet element i trädet
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
	 * Returnerar antalet element i trädet
	 * @return antalet element i trädet
	 */
	public int size() {
		return size;
	}

	/**
	 * Returnerar en arraylist med alla nycklar från trädet i ordning
	 * @return en arraylist med nycklarna i trädet 
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
	 * Returnerar en länkad lista med alla värden från trädet i postorder
	 * @return en länkad lista med alla nycklars värde från trädet 
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
	 * Returnerar det minsta värdet i trädet
	 * @return minsta värdet i trädet 
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
	 * Returnerar det största värdet i trädet
	 * @return det största värdet i trädet
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
	 * Skriver ut samtliga nycklar och värden från trädet 
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
	 * Skriver ut samtliga nycklar och värden från trädet i preorder
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
	 * Skriver ut samtliga nycklar och värden från trädet i preorder i postorder
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
	 * En klass som implementerar en iterator över en arraylist, elementen i arraylisten är sorterade i oordning
	 * @author Benjamin Sejdic
	 *
	 */
	private class Iter implements Iterator<V> {
		ArrayList<V> list = new ArrayList<V>();
		int index = -1;

		/**
		 * Lagrar värdena i trädet i en arraylist i oordning
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
		 * Returnerar true om det finns fler elements annars false
		 */
		public boolean hasNext() {
			return index < list.size() - 1;
		}

		/**
		 * Hämtar nästa element i arraylisten
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
