package collections;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class MainP1 {
    public static boolean test(boolean condition, String... message ) {
    	if(condition) {
    		if(message.length>0)
    			System.out.println(message[0] + ": pass");
    		return true;
    	} else {
    		if (message.length > 1)
    			System.out.println(message[1] + ": fail");
    		return false;
    	}
    }
	
    public static <T> boolean method(boolean newTest, boolean prerequisite, Stack<T> stack, TestStack<T> test) {
    	if(newTest) {
    		System.out.println("----------" + test.getClass().getName() + "----------");
    	}
    	if(prerequisite) {
    		try {
    			boolean res = test.exec(stack);
    			System.out.println(test + ": pass");
    			return res;
    		}catch(Exception e) {
    			System.out.println(test + ": " + e.toString());
    			return false;
    		}
    	} else {
    		System.out.println(test + ": prerequisite=" + prerequisite);
    	}
    	return false;
    }
	
    public static <T> boolean method(boolean newTest, boolean prerequisite, Stack<T> stack, TestStack<T> test, Object... args) {
    	if(newTest) {
    		System.out.println("----------" + test.getClass().getName() + "----------");
    	}
    	if(prerequisite) {
    		try {
    			boolean res = test.exec(stack,args);
    			System.out.println(test + ": pass");
    			return res;
    		}catch(Exception e) {
    			System.out.println(test + ": " + e.toString());
    			return false;
    		}
    	} else {
    		System.out.println(test + ": prerequisite=" + prerequisite);
    	}
    	return false;
    }

    public static <E> boolean method(boolean newTest, boolean prerequisite, List<E> list, TestList<E> test) {
    	if(newTest) {
    		System.out.println("----------" + test.getClass().getName() + "----------");
    	}
    	if(prerequisite) {
    		try {
    			boolean res = test.exec(list);
    			System.out.println(test + ": pass");
    			return res;
    		}catch(Exception e) {
    			System.out.println(test + ": " + e.toString());
    			return false;
    		}
    	} else {
    		System.out.println(test + ": prerequisite=" + prerequisite);
    	}
    	return false;
    }
	
    public static <E> boolean method(boolean newTest, boolean prerequisite, List<E> list, TestList<E> test, Object... args) {
    	if(newTest) {
    		System.out.println("----------" + test.getClass().getName() + "----------");
    	}
    	if(prerequisite) {
    		try {
    			boolean res = test.exec(list, args);
    			System.out.println(test + ": pass");
    			return res;
    		}catch(Exception e) {
    			System.out.println(test + ": " + e.toString());
    			return false;
    		}
    	} else {
    		System.out.println(test + ": prerequisite=" + prerequisite);
    	}
    	return false;
    }
	
    public static boolean equals(List<Long> list, long[] arr) {
        if(list.size()!=arr.length)
            return false;
        boolean res = true;
        for(int i=0; i<arr.length && res; i++)
            res = arr[i]==list.get(i);
        return res;
    }
    
    private static void setList(List<Long> list, long min, long max) {
        list.clear();
        while(min<=max)
            list.add(min++);
    }
    
    private static void shuffle(List<Long> list) {
        Random rand = new Random();
        int pos;
        Long temp;
        for(int i=list.size()-1; i>0; i--) {
            pos = rand.nextInt(i+1);
            temp = list.get(pos);
            list.set(pos,list.get(i));
            list.set(i,temp);
        }
    }
    
    public static Object create(String className, Class[] paramType, Object[] params) {
    	Object res = null;
    	try {
    		Class cls = Class.forName(className);
    		Constructor constr = cls.getConstructor(paramType);
    		res = constr.newInstance(params);;
    	} catch (Exception e) {}
    	return res;
    }
    
    
    public static void testList(List<Long> list) {
    	if(list!=null) {
    		System.out.println("\n\nTEST: " + list.getClass().getName());
    		Object[] arr = {2L,3L,5L};
    		boolean testSize0, testAdd, testClear;
    		testSize0 = method(true,true,list,new TestListSize(),0);
    		testAdd = method(true,true,list,new TestAdd(),arr);
    		testClear = method(true,testSize0 && testAdd,list,new TestClear());
    		method(true,testClear,list,new TestAddLast(),arr);
    		method(true,testClear,list,new TestAddFirst(),arr);
    		method(true,testClear,list,new TestAddIndex());
    		method(true,testClear && testAdd,list,new TestSetIndexOf());
    		method(true,testClear && testAdd,list,new TestIterator());
    		method(true,testClear && testAdd,list,new TestRemoveIndex());
    		method(true,testClear && testAdd,list,new TestRemoveFirst());
    		method(true,testClear && testAdd,list,new TestRemoveLast());
    	} else {
    		System.out.println(list.getClass().getName() + "==null");
    	}
    }

    public static void testStack(Stack<Long> stack) {
    	if(stack!=null) {
    		System.out.println("\n\nTEST: " + stack.getClass().getName());
            boolean testSize = method(true,true,stack,new TestStackSize(),0);
            method(true,true,stack,new TestStackIsEmpty());
            method(true,testSize,stack,new TestPushPeekPop(), 10L,15L,5L,3L,8L);
    	} else {
    		System.out.println(stack.getClass().getName() + "==null");
    	}
    }
    
    public static void testSearchSort() {
        System.out.println("\n\n----------Searching, Sorting----------");
        ArrayList<Long> list = new ArrayList<Long>();
        setList(list, 50, 79);
        test(Searching.binarySearch(list, new Long(60))==10, "binarySearch(list,60)", "binarySearch(list,60)");
        test(Searching.binarySearch(list, new Long(100))<0, "binarySearch(list,100)", "binarySearch(list,100)");
        shuffle(list);
        double[] arr = new double[list.size()];
        for(int i=0; i<arr.length; i++)
            arr[i] = list.get(i);
        Sorting.sort(arr);
        test(Searching.linearSearch(list, new Long(60))>=0, "linearSearch(list,60)", "linearSearch(list,60)");
        test(Searching.linearSearch(list, new Long(100))<0, "linearSearch(list,100)", "linearSearch(list,100)");
        Sorting.sort(arr);
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    

    private static int getChoice(String[] menu) {
        String str = "";
        int choice=-1;
        for(int i=0; i<menu.length; i++) {
            str += menu[i] + "\n";
        }
        try {
            choice = Integer.parseInt(JOptionPane.showInputDialog(str));
        }catch(Exception e) {}
        return choice;
    }

	public static void main(String[] args) {
	    String[] menu = {"1. ArrayList<Long>()", "2. ArrayList<Long>(2)", "3. LinkedList<Long>()", "4. ArrayStack<Long>(5)","5. Searching, Sorting", "0. Exit"};
	    int choice = getChoice(menu);
	    while(choice != 0) {
	        switch(choice) {
	        case 1 : testList((List<Long>)create("collections.ArrayList",null,null)); break;
	        case 2 : testList((List<Long>)create("collections.ArrayList",new Class[]{int.class},new Object[]{2})); break;
	        case 3 : testList((List<Long>)create("collections.LinkedList",null,null)); break;
	        case 4 : testStack((Stack<Long>)create("collections.ArrayStack",new Class[]{int.class},new Object[]{5})); break;
	        case 5 : testSearchSort(); break;
	        }
	        choice = getChoice(menu);
	    }
	}
    
}




interface TestStack<T> {
	public boolean exec(Stack<T> list);
	public boolean exec(Stack<T> list, Object... args);
}

interface TestList<E> {
	public boolean exec(List<E> list);
	public boolean exec(List<E> list, Object... args);
}



// test classes Stack

class STest<E> implements TestStack<E> {
	public boolean exec(Stack<E> list) {throw new IllegalArgumentException();}
	public boolean exec(Stack<E> list, Object... args) {throw new IllegalArgumentException();}
}

class TestStackSize extends STest<Long> {
	public boolean exec(Stack<Long> stack, Object... args) {
		Integer i = (Integer)args[0];
		return MainP1.test(stack.size()==i, "stack.size()=="+i, "stack.size()!="+i);
	}
}

class TestStackIsEmpty extends STest<Long> {
	public boolean exec(Stack<Long> stack) {
		return MainP1.test(stack.isEmpty(), "stack.isEmpty()", "stack.isEmpty()");
	}
}

class TestPushPeekPop extends STest<Long> {
	public boolean exec(Stack<Long> stack, Object... args) {
		for (int i = 0; i < args.length; i++) {
			stack.push((Long) args[i]);
		}
		boolean res = MainP1.method(false, true, stack, new TestStackSize(),
				args.length);
		for (int i = 0; i < args.length; i++) {
			res = MainP1.test(stack.peek().equals(args[args.length - 1 - i]),
					"stack.peek()", "stack.peek()") && res;
			res = MainP1.test(stack.pop().equals(args[args.length - 1 - i]),
					"stack.pop()", "stack.pop()") && res;
		}
		res = MainP1.method(false, true, stack, new TestStackSize(), 0) && res;
		res = MainP1.method(false, true, stack, new TestStackIsEmpty()) && res;
		try {
			for (long i = 0; i < 20; i++) {
				stack.push(i);
			}
			res = false;
			System.out.println("stack.push(): missing exception");
		}catch(Exception e) {}
		int i = 0;
		try {
			while (i < 20) {
				stack.pop();
				i++;
			}
			res = false;
			System.out.println("stack.pop(): missing exception");
		} catch (Exception e) {
		}
		return res;
	}
}



// Test classes, List

class LTest<E> implements TestList<E> {
	public boolean exec(List<E> list) {throw new IllegalArgumentException();}
	public boolean exec(List<E> list, Object... args) {throw new IllegalArgumentException();}
}

class TestListSize extends LTest<Long> {
	public boolean exec(List<Long> list, Object... args) {
		Integer i = (Integer)args[0];
		return MainP1.test(list.size()==i, "list.size()=="+i, "list.size()!="+i);
	}
}

class TestGet extends LTest<Long> {
	public boolean exec(List<Long> list, Object... args) {
		boolean res = true;
		for(int i=0; i<list.size(); i++) {
			res = res && (list.get(i).equals(args[i]));
		}
		return MainP1.test(res, "list.get()","list.get()");
	}
}

class TestSetIndexOf extends LTest<Long> {
	public boolean exec(List<Long> list) {
		boolean res = true;
		long[] arr = {2,3,4,3,5};
		list.clear();
		for(int i=0; i<arr.length; i++) {
			list.add(arr[i]);
		}
		res = MainP1.test(list.indexOf(3L)==1, "list.indexOf(value)","list.indexOf(value)") && res;
		res = MainP1.test(list.indexOf(-1L)==-1, "list.indexOf(value)","list.indexOf(value)") && res;
		res = MainP1.test(list.indexOf(2,3L)==3, "list.indexOf(index,value)","list.indexOf(index,value)") && res;
		list.set(0, Long.MAX_VALUE);
		list.set(1, Long.MIN_VALUE);
		list.set(3, Long.MIN_VALUE);
		res = MainP1.test(list.get(0).equals(Long.MAX_VALUE) && list.get(1).equals(Long.MIN_VALUE), "list.set()","list.set()") && res;
		return res;
	}
}

class TestClear extends LTest<Long> {
	public boolean exec(List<Long> list) {
		list.clear();
		return MainP1.test(list.size()==0, "list.clear()", "list.clear()");
	}
}

class TestAdd extends LTest<Long> {
	public boolean exec(List<Long> list, Object... args) {
		Object[] arr1 = new Long[args.length];
		long[] arr2 = new long[args.length];
		for(int i=0; i<args.length; i++) {
			list.add((Long)args[i]);
			arr1[i] = args[i];
			arr2[i] = (Long)args[i];
		}
		MainP1.method(false,true,list,new TestListSize(), args.length);
		MainP1.method(false, true, list, new TestGet(), arr1);
		return MainP1.test(MainP1.equals(list,arr2), "list.add(elem)", "list.add(elem)");
	}
}

class TestAddLast extends LTest<Long> {
	public boolean exec(List<Long> list, Object... args) {
		list.clear();
		long[] arr = new long[args.length];
		for(int i=0; i<args.length; i++) {
			list.addLast((Long)args[i]);
			arr[i] = (Long)args[i];
		}
		MainP1.method(false,true,list,new TestListSize(), args.length);
		return MainP1.test(MainP1.equals(list,arr), "list.addLast(elem)", "list.addLast(elem)");
	}
}

class TestAddFirst extends LTest<Long> {
	public boolean exec(List<Long> list, Object... args) {
		list.clear();
		long[] arr = new long[args.length];
		for(int i=0; i<args.length; i++) {
			list.addFirst((Long)args[i]);
			arr[arr.length-1-i] = (Long)args[i];
		}
		MainP1.method(false,true,list,new TestListSize(), args.length);
		return MainP1.test(MainP1.equals(list,arr), "list.addFirst(elem)", "list.addFirst(elem)");
	}
}

class TestAddIndex extends LTest<Long> {
	public boolean exec(List<Long> list) {
		list.clear();
		long[] arr = {1,2,3,4,5};
		long[] arr2 = {2,4,5,3,1};
		for(int i=0; i<arr.length; i++) {
			list.add(i/2,arr[i]);
		}
		MainP1.method(false,true,list,new TestListSize(), 5);
		return MainP1.test(MainP1.equals(list,arr2), "list.add(index,elem)", "list.add(index,elem)");
	}
}

class TestIterator extends LTest<Long> {
	public boolean exec(List<Long> list) {
		list.clear();
		long[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		for(int i=0; i<arr.length; i++) {
			list.add(arr[i]);
		}
        Iterator<Long> iter = list.iterator();
        boolean res = true;
        int index=0;
        while(iter.hasNext()) {
            res = res & iter.next().equals(arr[index]);
            index++;
        }
        return MainP1.test(res, "list.iterator()", "list.iterator()");
	}
}

class TestRemoveLast extends LTest<Long> {
	public boolean exec(List<Long> list) {
		list.clear();
		long[] arr = {1,2,3,4,5};
		for(int i=0; i<arr.length; i++) {
			list.add(arr[i]);
		}
		list.add(100L);
		list.removeLast();
		MainP1.method(false,true,list,new TestListSize(), 5);
		return MainP1.test(MainP1.equals(list,arr), "list.removeLast()", "list.removeLast()");
	}
}

class TestRemoveFirst extends LTest<Long> {
	public boolean exec(List<Long> list) {
		list.clear();
		long[] arr = {1,2,3,4,5};
		list.add(100L);
		for(int i=0; i<arr.length; i++) {
			list.add(arr[i]);
		}
		list.removeFirst();
		MainP1.method(false,true,list,new TestListSize(), 5);
		return MainP1.test(MainP1.equals(list,arr), "list.removeFirst()", "list.removeFirst()");
	}
}

class TestRemoveIndex extends LTest<Long> {
	public boolean exec(List<Long> list) {
		list.clear();
		long[] arr = {1,2,3,4,5};
		for(int i=0; i<arr.length; i++) {
			if(i==2)
				list.add(100L);
			list.add(arr[i]);
		}
		list.remove(2);
		MainP1.method(false,true,list,new TestListSize(), 5);
		return MainP1.test(MainP1.equals(list,arr), "list.remove(index)", "list.remove(index)");
	}
}
