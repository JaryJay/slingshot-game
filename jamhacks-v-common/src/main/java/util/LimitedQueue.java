package util;

import java.util.LinkedList;

public class LimitedQueue<E> extends LinkedList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7088977872856787061L;
	private int limit;

	public LimitedQueue(int limit) {
		this.limit = limit;
	}

	@Override
	public boolean add(E o) {
		super.add(o);
		while (size() > limit) {
			super.remove();
		}
		return true;
	}

}
