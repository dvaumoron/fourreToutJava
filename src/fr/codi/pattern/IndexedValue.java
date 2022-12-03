package fr.codi.pattern;

public class IndexedValue<T> {

	private final int index;
	private final T value;

	public IndexedValue(final int index, final T value) {
		this.index = index;
		this.value = value;
	}

	public int getIndex() {
		return this.index;
	}

	public T getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder();
		buffer.append("IndexedValue[");
		buffer.append(this.index);
		buffer.append(", ");
		buffer.append(this.value);
		buffer.append("]");
		return buffer.toString();
	}
}