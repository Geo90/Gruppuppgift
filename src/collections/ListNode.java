package collections;

public class ListNode<E> {
    private E data;
    private ListNode<E> next;
    
    public ListNode( E data, ListNode<E> next ) {
        this.data = data;
        this.next = next;
    }
    
    public E getData() {
        return this.data;
    }
    
    public void setData( E data ) {
        this.data = data;
    }
    
    public ListNode<E> getNext() {
        return this.next;
    }
    
    public void setNext( ListNode<E> next ) {
        this.next = next;
    }
    
    public String toString() {
    	StringBuilder str = new StringBuilder("[ ");
    	str.append(data.toString());
    	ListNode<E> node = next;
        while( node!=null ) {
        	str.append( "; ");
            str.append( node.getData().toString() );
            node = node.getNext();
        }
        str.append( " ]");
        return str.toString();
    }
}