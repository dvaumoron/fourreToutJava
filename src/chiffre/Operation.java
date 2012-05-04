package chiffre;

public enum Operation {

	SOMME{
		@Override
		public int appliquer(int a, int b) {
			if (a == 0 || b == 0) {
				throw new IllegalArgumentException("somme inutile");
			} else {
				return a + b;
			}
		}

		@Override
		public String toString() {
			return " + ";
		}
	},

	SOUSTRACTION{
		@Override
		public int appliquer(int a, int b) {
			if (b == 0) {
				throw new IllegalArgumentException("soustraction inutile");
			} else {
				return a - b;
			}
		}

		@Override
		public String toString() {
			return " - ";
		}
	},

	MULTIPLICATION{
		@Override
		public int appliquer(int a, int b) {
			if (a == 1 || b == 1) {
				throw new IllegalArgumentException("multiplication inutile");
			} else {
				return a * b;
			}
		}

		@Override
		public String toString() {
			return " * ";
		}
	},

	DIVISION{
		@Override
		public int appliquer(int a, int b) {
			if (b == 0) {
				throw new IllegalArgumentException("division par zéro");
			} else if (b == 1) {
				throw new IllegalArgumentException("division inutile");
			} else {
				int aSurB = a / b;
				if (aSurB * b == a) {
					return aSurB;
				} else {
					throw new IllegalArgumentException("division inexacte");
				}
			}
		}

		@Override
		public String toString() {
			return " / ";
		}
	};

	public abstract int appliquer(int a, int b);

}
