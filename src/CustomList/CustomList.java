package CustomList;

import java.util.ArrayList;
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

	public static void main(String[] args) {
		CustomList<String> stringList = new CustomList<>(1);
		System.out.println(stringList.size());
		stringList.add("a1");
		stringList.add("a2");
		stringList.add("a3");
		stringList.add("a4");

		System.out.println(stringList.get(0));
		System.out.println(stringList.get(3));
		System.out.println(stringList.toString());
		System.out.println(Arrays.deepToString(stringList.toArray()));
		System.out.println(stringList.size());
		stringList.add("a1");
		System.out.println(stringList.toString());
		stringList.remove(2);

		System.out.println(stringList.toString());
		
		System.out.println(stringList.indexOf("a3"));

//		ArrayList<String> stringArrayList = new ArrayList<>();
//		System.out.println(stringArrayList.size());
//		stringArrayList.add("1");
//		stringArrayList.add("2");
//		stringArrayList.add("1");
//		stringArrayList.add("3");
//		System.out.println(stringArrayList.size());
//		String s = stringArrayList.toString();
//		System.out.println(s);
//		System.out.println(stringArrayList.toArray());
//		System.out.println(stringArrayList.indexOf("1"));
	}
}
