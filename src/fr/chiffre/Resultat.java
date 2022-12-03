package fr.chiffre;

import java.util.Iterator;
import java.util.List;

public class Resultat {

	private final List<String> expr;
	private final int cost;

	public Resultat(final List<String> expr, final int cost) {
		this.expr = expr;
		this.cost = cost;
	}

	public List<String> getExpr() {
		return this.expr;
	}

	public int getCost() {
		return this.cost;
	}

	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder();
		final Iterator<String> it = this.expr.iterator();
		if (it.hasNext()) {
			buffer.append(it.next());
			while (it.hasNext()) {
				buffer.append('\n');
				buffer.append(it.next());
			}
		}
		return buffer.toString();
	}
}