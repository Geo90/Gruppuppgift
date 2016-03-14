package collections;

/**
 * Defines an entry object which is used in a HashMap to represent a pair of [key, value]
 * @author Benjamin Sejdic
 *
 * @param <K>
 * @param <V>
 */
class Entry<K, V> {
	K key;
	V value;

	/**
	 * Construct an entry with a key and value
	 * @param key 
	 * @param value 
	 */
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Compares two keys, return true if they are equal
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Entry))
			return false;
		Entry<K, V> entry = (Entry<K, V>) obj;
		return key.equals(entry.key);
	}
}
