package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Klassen kan användas till att skapa en array som är mer flexibel än en vanlig array då denna klass
 * har metoder som kan användas för att förändra arrayen på olika sätt.
 * @author Benjamin Sejdic
 *
 * @param <E> olika typer av objekt
 */
public class ArrayList<E> implements List<E> {
	private E[] elements;
	private int size;
	private int length;
	
	private void grow() {
		E[] temp = (E[]) new Object[2*elements.length];
		for(int i = 0; i<elements.length; i++) {
			temp[i] = elements[i];
		}
		length = 2*elements.length;
		elements = temp;
	}
	
	/**
	 * Konstruerar en arraylist med en kapaciteten 20
	 */
	public ArrayList() {
		this(20);
	}
	
	/**
	 * Konstruerar en arraylist med angiven kapacitet
	 * @param initialCapacity kapaciteten
	 */
	public ArrayList(int initialCapacity) {
		initialCapacity = Math.max(1, initialCapacity);
		elements = (E[])new Object[initialCapacity];
		length = initialCapacity;
	}

	/**
	 * Lägger till ett element i en angiven position
	 * @param index position
	 * @param element objektet man vill lägga till
	 */
	public void add(int index, E element) {
		if(index<0 || index>length)
			throw new IndexOutOfBoundsException();
		if(size==elements.length)
			grow();
		for(int i=size-1; i>index; i--) {
			elements[i]=elements[i-1];
		}
		elements[index] = element;
		size++;
		
	}

	/**
	 * Lägger till ett element i första lediga positionen
	 * @element elementet som ska sättas in i den första lediga positionen
	 */
	public void add(E element) {
		add(size,element);
	}

	/**
	 * Lägger till ett element i första positionen
	 * @element elementet som ska sättas in i första positionen
	 */
	public void addFirst(E element) {
		add(0, element);
	}

	/**
	 * Lägger till ett element i första lediga positionen
	 * @element elementet som ska sättas in sist i arraylist
	 */
	public void addLast(E element) {
		add(size, element);
	}

	/**
	 * Tar bort ett element från en angiven position och flyttar alla element som är till höger om det borttagna elementet ett steg 
	 * åt vänster
	 * @param index elementets position 
	 * @return det borttagna elementet 
	 */
	public E remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E element = elements[index];
		for(int i = index; i<elements.length-1; i++) {
			elements[i] = elements[i+1];
		}
		
		elements[size-1] = null;
		size--;

		return element;
	}
	
	/**
	 * Tar bor det första elementet i listan och returnerar det 
	 * @return elementet i första positionen
	 */
	public E removeFirst() {
		return remove(0);
	}

	/**
	 * Tar bort det sista elementet i listan och returnerar det 
	 * @return elementet i sista positionen
	 */
	public E removeLast() {
		return remove(size-1);
	}

	/**
	 * Tar bort alla element från listan. Efter att denna metod har anropas kommer listan bli tom.
	 */
	public void clear() {
		for(int i = 0; i < size; i++) {
			elements[i] = null;;
		}
		size = 0;
	}

	/**
	 * Returnerar elementet i den angivna positionen
	 * @param index elementets position
	 * @return elementet i den angivna positionen
	 */
	public E get(int index) {
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException();
		}
		return elements[index];
	}

	/**
	 * Sätter ett nytt element i angiven position
	 * @param index position
	 * @param element objektet man vill lägga sätta i listan
	 * @return det ersatta elementet 
	 */
	public E set(int index, E element) {
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException();
		}
		E prevElement = elements[index];
		elements[index] = element;
		return prevElement;
	}

	/**
	 * Returnerar positionen för det angivna elementet
	 * @param elementet att söka efter
	 * @return positionen för elementet eller -1 om elementet inte finns 
	 */
	public int indexOf(E element) {
		for(int i = 0; i < size; i++) {
			if(element.equals(elements[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returnerar positionen för det angivna elementet, sökningen börjar i startIndex
	 * @param startindex i vilken position sökningen ska börja leta efter elementet
	 * @param elementet att söka efter
	 */
	public int indexOf(int startIndex, E element) {
		if (startIndex < 0 || startIndex > size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = startIndex; i < size; i++) {
			if(element.equals(elements[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returnerar antalet element i listan
	 * @return antalet element i listan
	 */
	public int size() {
		return length;
	}
	
	/**
	 * 
	 */
	public String toString() {
		StringBuilder res = new StringBuilder("[ ");
		for(int i=0; i<size; i++) {
			res.append(elements[i]);
			if(i<size-1)
				res.append("; ");
		}
		res.append(" ]");
		return res.toString();
	}

	/**
	 * Returnerar en iterator över elementen i listan
	 * @return iterator över elementen i listan 
	 */
	public Iterator<E> iterator() {
		return new Iter();
		}
	
	private class Iter implements Iterator<E> {
		private int index=0;
		
		/**
		 * Kollar om det finns fler element
		 * @return true om det finns fler element annars false
		 */
		public boolean hasNext() {
			return index<size;
		}
		
		/**
		 * Hämtar nästa element i listan
		 * @return nästa element i listan annars slänger den en NoSuchElementException
		 */
		public E next() {
			if(index==size)
				throw new NoSuchElementException();
			return elements[index++];
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}	
}
