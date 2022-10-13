package CustomList;

import java.util.Arrays;

class CustomList<T> {
	private int size;

	private Object[] storage;

	public CustomList() {
		this.size = 0;
		this.storage = new Object[10];
	}

	public CustomList(int length) {
		if (length == 0 || length == 1) {
			this.size = 0;
			this.storage = new Object[2];
		} else if (length > 1) {
			this.size = 0;
			this.storage = new Object[length];
		} else {
			throw new IllegalArgumentException();
		}
	}

	public int size() {
		return this.size;
	}

	public void clear() {
		this.size = 0;
		this.storage = new Object[10];
	}

	public void add(T value) {
		size++;
		toCheckStorage(size);
		for (int i = 0; i < size; i++) {
			if (this.storage[i] == null) {
				this.storage[i] = value;
			}
			continue;
		}
	}

	public void remove(int index) {
		if (size != 0) {
			Object[] storage2 = new Object[storage.length - 1];
			for (int i = 0; i < index; i++) {
				storage2[i] = storage[i];
			}
			for (int i = index; i < storage2.length; i++) {
				storage2[i] = storage[i + 1];
			}
			this.storage = storage2;
			size--;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public int indexOf(T value) {
		for (int i = 0; i < storage.length; i++) {
			if (value.equals(storage[i])) {
				return i;
			}
		}
		return -1;
	}

	private void toCheckStorage(int size) {
		if (size == storage.length) {
			Object[] storage2 = new Object[(int) (storage.length * 1.6)];
			for (int i = 0; i < storage.length; i++) {
				storage2[i] = storage[i];
			}
			this.storage = new Object[storage2.length];
			this.storage = storage2;
		}
	}

	public T get(int i) {
		if (i <= this.size - 1) {
			return (T) storage[i];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public T[] toArray() {
		Object[] storage2 = new Object[size];
		for (int i = 0; i < storage2.length; i++) {
			storage2[i] = storage[i];
		}
		return (T[]) storage2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + size;
		result = prime * result + Arrays.deepHashCode(storage);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomList other = (CustomList) obj;
		if (size != other.size)
			return false;
		if (!Arrays.deepEquals(storage, other.storage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				s += storage[i];
				continue;
			}
			s += storage[i] + ", ";
		}
		return "[" + s + "]";
	}

//	public String toString() {
//		return Arrays.toString(this.toArray());
//	}

	public boolean isEmpty() {
		return this.size == 0;
	}
}
