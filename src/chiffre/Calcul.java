package chiffre;

public class Calcul {

	public static void main(String[] args) {
		System.out.print(new Calcul(Operation.MULTIPLICATION, 12, 12));
	}

	private final Operation operation;
	private final int a;
	private final int b;

	public Calcul(Operation operation, int a, int b) {
		this.operation = operation;
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(a).append(operation).append(b).append(" = ")
			.append(operation.appliquer(a, b)).toString();
	}

}
