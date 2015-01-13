package Utils;

import java.util.Iterator;
import java.util.LinkedList;

public class LexSortList<T extends Comparable<T>> {
	private LinkedList<T> list = new LinkedList<T>();
	
	public T addElement(T element) {
		if (this.list.contains(element)) {
			return element;
		} else {
			Integer index = -1;
			
			Iterator<T> it = list.iterator();
			
			Boolean added = false;
			
			if (!it.hasNext()) {
				this.list.add(element);
				added = true;
			} else {
				while (it.hasNext()) {
					T current = it.next();
					index++;
					
					if (current.compareTo(element) > 0) {
						this.list.add(index, element);
						added = true;
						break;
					}
				}
			}
			
			if (!added) {
				this.list.add(element);
			}
			
			return null;
		}
	}
	
	public T findElement(T element) {
		Iterator<T> it = this.list.iterator();
		
		while (it.hasNext()) {
			T current = it.next();
			
			if (current.equals(element)) {
				return current;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return this.list.toString();
	}
}