import java.util.Iterator;

/**
 * Hashtabellen använder sluten hashing
 * @author Rolf Axelsson
 **/
public class HashtableCH<K,V> implements Map<K,V> {
    private Bucket<K,V>[] table;
    private int size;
    private int threshold;
    private double loadfactor = 0.7;
    
    public HashtableCH( ) { 
    	this(11);
    }
    
    public HashtableCH( int size ) { 
        table = (Bucket<K,V>[])new Bucket[ size ];
        threshold = (int)(loadfactor*table.length);
        for( int i = 0; i < table.length; i++ ) {
            table[ i ] = new Bucket<K,V>();
        }
    }
    
    private void grow() {
    	Bucket<K,V>[] oldTable = table;
        table = (Bucket<K,V>[])new Bucket[ table.length*2 ];
    	size = 0;
    	threshold = (int)(loadfactor*table.length);
        for( int i = 0; i < table.length; i++ ) {
            table[ i ] = new Bucket<K,V>();
        }
    	for(int index=0; index<oldTable.length; index++) {
    		if( oldTable[index].state == Bucket.OCCUPIED )
    			put( oldTable[index].key, oldTable[ index ].value);
    	}
    }
    
    private int hashIndex( K key ) {
        int hashCode = key.hashCode();
        hashCode = hashCode % table.length;
        return ( hashCode < 0 ) ? -hashCode : hashCode;
    }
    
    public V put( K key, V value ) {
    	V res = null;
    	if( size >= threshold ) {
    		grow();
    	}
    	int hashIndex = hashIndex( key ), removed = -1;
    	while( table[ hashIndex ].state != Bucket.EMPTY && !key.equals(table[ hashIndex ].key) ) {
    		if( removed == -1 && table[ hashIndex ].state == Bucket.REMOVED )
    			removed = hashIndex;
    		hashIndex++;
    		if(hashIndex==table.length)
    			hashIndex=0;
    	}
    	if( table[ hashIndex ].state == Bucket.OCCUPIED ) {
    		res = table[ hashIndex ].value;
    		table[ hashIndex ].value = value;
    	} else {
    		if( removed != -1 ) 
    			hashIndex = removed;
            table[ hashIndex ].key = key;
            table[ hashIndex ].value = value;
            table[ hashIndex ].state = Bucket.OCCUPIED;
            size++;
    	}
    	return res;
    }

	public Iterator<K> keys() {
		ArrayList<K> keys = new ArrayList<K>();
		for(int i=0; i<table.length; i++)
		    if( table[ i ].state == Bucket.OCCUPIED )
			    keys.add(table[ i ].key);
		return keys.iterator();
	}
	
    public void list() {
        System.out.println( "Tabellen innehåller " + size() + " element:" );
        for(int i=0; i<table.length; i++)
            System.out.println(i+": key=" + table[ i ].key +" value=" + table[ i ].value + " state=" + table[ i ].state );
    }    
    
    public V get( K key ) {
    	int hashIndex = hashIndex(key);
    	while(table[hashIndex].state != Bucket.EMPTY && !key.equals(table[hashIndex].key)){
    		hashIndex++;
    		if(hashIndex == table.length){
    			hashIndex = 0;
    		}
    	}
    	if(table[hashIndex].state == Bucket.OCCUPIED){
    		return table[hashIndex].value;
    	}else{
    		return null;
    	}
//    	for(int i=0; i<table.length; i++){
//    		if(table[i].key == key){
//    			return table[i].value;
//    		}
//    	}
//        return null;
    }
    
    public V remove( K key ) {
    	V res = null;
    	int hashIndex = hashIndex(key); // fixa automatisk position för nyckeln i tabellen
		while (table[hashIndex].state != Bucket.EMPTY
				&& !key.equals(table[hashIndex].key)) { // kör till vi kommer
														// till en position med
														// korrekt nyckel.
			hashIndex++;
    		if(hashIndex == table.length){
    			hashIndex = 0;
    		}
    	}
		if(table[hashIndex].state == Bucket.OCCUPIED && table[hashIndex].key == key){
			res = table[hashIndex].value;
			table[hashIndex].key = null;
			table[hashIndex].value = null;
			table[hashIndex].state = Bucket.REMOVED;
			size--;
		}
		return res;
    }
    
    public int size() {
        return size;
    }
    
	public boolean isEmpty() {
		int i = 0;
		while(i < table.length && (table[i].state == Bucket.EMPTY || table[i].state == Bucket.REMOVED)){
			i++;
		}
		if(i >= table.length){
			return true;
		}else{
			return false;
		}
	}

	public boolean containsKey(K key) {
		return get(key) != null;
//		int hashIndex = hashIndex(key);
//		while (table[hashIndex].state != Bucket.EMPTY && !key.equals(table[hashIndex].key)) {
//			hashIndex++;
//			if(table[hashIndex].key == key){
//				return true;
//			}
//		}
//		return false;
	}

	public void clear() {
		int i = 0;
		while(i < table.length){
			if(table[i].state == Bucket.OCCUPIED){
				table[i].key = null;
				table[i].value = null;
				table[i].state = Bucket.REMOVED;
			}
			i++;
		}
		size = 0;
	}

	public Iterator<V> values() {
		ArrayList<V> list = new ArrayList<V>();
		for(int i = 0;i < table.length; i++){
			if(table[i].state == Bucket.OCCUPIED){
				list.add((V) table[i].key);
			}
		}
		return list.iterator();
	}
	
    public static void main(String[] args) {
        HashtableCH<String,String> table = new HashtableCH<String,String>(1);
        table.put("hej", "hello");      
        table.put("röd", "red");        
        table.put("vit", "white");      
        table.put("säng", "bed");       
        table.put("svart", "black");    
        table.put("gul", "yellow");     
        table.put("dator", "computer"); 
        table.put("snö", "snow");       
        table.put("blå", "blue");       
        table.put("grön", "green");     
        table.put("hus", "house");      
        table.list();
        System.out.println("-----KEYS-------------------------");
	    Iterator<String> keys = table.keys();
	    while(keys.hasNext())
		    System.out.println(keys.next());
	    // Test av get-metoden
	    System.out.println();
	    System.out.println("Test av get-metoden: " + table.get("blå") + ", " + table.get("åsna"));
	    // Test av remove-metoden
	    System.out.println();
//	    table.remove("hej");      
//        table.remove("röd");        
//        table.remove("vit");      
//        table.remove("säng");       
//        table.remove("svart");    
//        table.remove("gul");     
//        table.remove("dator"); 
//        table.remove("snö");       
//        table.remove("blå");       
//        table.remove("grön");     
//        table.remove("hus"); 
//        table.list();
//        System.out.println(table.isEmpty());
          //Test av containsKey-metoden
	    System.out.println("Test av containsKey-metoden: " + table.containsKey("blå"));
        //Test av clear-metoden
	    System.out.println();
	    table.clear();
	    table.list();

	    
    }
}

