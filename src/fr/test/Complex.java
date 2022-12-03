package fr.test;

public class Complex {

	private final double real;
	private final double imag;

	public Complex(final double real, final double imag) {
		this.real = real;
		this.imag = imag;
	}

	public Complex(final double real) {
		this(real, 0);
	}

	public Complex() {
		this(0, 0);
	}

	public Complex(final Complex other) {
		this(other.real, other.imag);
	}
	
	public Complex(final String s) {
		if (s.contains("+")) {
			final String[] split = s.split("\\+");
			this.real = Double.parseDouble(split[0]);
			final String s1 = split[1];
			this.imag = Double.parseDouble(s1.substring(0, s1.indexOf("i")));
		} else if (s.contains("-")) {
			final String[] split = s.split("-");
			if (split.length == 2) {
				final String s0 = split[0].trim();
				if (s0.isEmpty()) {
					final String s1 = split[1];
					if (s1.contains("i")) {
						this.real = 0;
						this.imag = -Double.parseDouble(s1.substring(0,
								s1.indexOf("i")));
					} else {
						this.real = -Double.parseDouble(s1);
						this.imag = 0;
					}
				} else {
					this.real = Double.parseDouble(s0);
					final String s1 = split[1];
					this.imag = -Double.parseDouble(s1.substring(0,
							s1.indexOf("i")));
				}
			} else {
				this.real = -Double.parseDouble(split[1]);
				final String s1 = split[2];
				this.imag = -Double
						.parseDouble(s1.substring(0, s1.indexOf("i")));
			}
		} else if (s.contains("i")) {
			this.real = 0;
			this.imag = Double.parseDouble(s.substring(0, s.indexOf("i")));
		} else {
			this.real = Double.parseDouble(s);
			this.imag = 0;
		}
	}

	public double getReal() {
		return this.real;
	}

	public double getImag() {
		return this.imag;
	}

	public Complex add(final Complex other) {
		return new Complex(this.real + other.real, this.imag + other.imag);
	}

	public Complex sub(final Complex other) {
		return new Complex(this.real - other.real, this.imag - other.imag);
	}

	public Complex mul(final Complex other) {
		return new Complex(this.real * other.real - this.imag * other.imag,
				this.real * other.imag + this.imag * other.real);
	}

	public Complex div(final Complex other) {
		final double denom = other.squareNorme();
		return new Complex((this.real * other.real + this.imag * other.imag)
				/ denom, (-this.real * other.imag + this.imag * other.real)
				/ denom);
	}

	public Complex inverse() {
		final double denom = this.squareNorme();
		return new Complex(this.real / denom, -this.imag / denom);
	}

	private double squareNorme() {
		return this.real * this.real + this.imag * this.imag;
	}

	public Complex conjugate() {
		return new Complex(this.real, -this.imag);
	}

	public double norme() {
		return Math.sqrt(this.squareNorme());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.imag);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.real);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Complex other = (Complex) obj;
		if (Double.doubleToLongBits(this.imag) != Double
				.doubleToLongBits(other.imag))
			return false;
		if (Double.doubleToLongBits(this.real) != Double
				.doubleToLongBits(other.real))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder();
		if (this.real == 0) {
			if (this.imag == 0) {
				buffer.append(0.0);
			} else {
				buffer.append(this.imag);
				buffer.append('i');
			}
		} else if (this.imag == 0) {
			buffer.append(this.real);
		} else {
			buffer.append(this.real);
			if (this.imag > 0) {
				buffer.append("+");
			}
			buffer.append(this.imag);
			buffer.append('i');
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		final Complex c1 = new Complex(1, 2);
		System.out.println(c1);
		final Complex c2 = new Complex("3 + 4i");
		System.out.println(c2);
		final Complex c3 = new Complex("4 i");
		System.out.println(c3);
		final Complex c4 = new Complex(" 3");
		System.out.println(c4);
		final Complex c5 = new Complex("0");
		System.out.println(c5);
		final Complex c6 = new Complex("5-6i");
		System.out.println(c6);
		final Complex c7 = new Complex("-7-8i");
		System.out.println(c7);
		final Complex c8 = new Complex("-7+8i");
		System.out.println(c8);
		final Complex c9 = new Complex("-9i");
		System.out.println(c9);
		final Complex c10 = new Complex("-10");
		System.out.println(c10);
		System.out.println(c1.add(c2));
		System.out.println(c1.sub(c2));
		System.out.println(c1.mul(c2));
		System.out.println(c1.div(c2));
		System.out.println(c2.norme());
		System.out.println(c1.conjugate());
		System.out.println(c1.inverse());
	}
}