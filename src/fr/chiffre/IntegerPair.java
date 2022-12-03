package fr.chiffre;

public class IntegerPair {

	private static final int prime = 31;

	private final int a;
	private final int b;

	public IntegerPair(final int a, final int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int hashCode() {
		return prime * this.a + this.b;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		}
		final IntegerPair other = (IntegerPair) obj;
		return this.a == other.a && this.b == other.b;
	}

	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder();
		buffer.append('(');
		buffer.append(this.a);
		buffer.append(", ");
		buffer.append(this.b);
		buffer.append(')');
		return buffer.toString();
	}
}