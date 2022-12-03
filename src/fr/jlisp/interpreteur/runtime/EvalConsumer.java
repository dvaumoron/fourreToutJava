package fr.jlisp.interpreteur.runtime;

import java.util.function.Consumer;

import fr.jlisp.interpreteur.Interpreteur;

public class EvalConsumer implements Consumer<String> {

	private final Interpreteur interpreteur;
	private final Environement environement;
	private StringBuilder buffer = new StringBuilder();
	private int indentation = 0;

	public EvalConsumer(final Interpreteur interpreteur,
			final Environement environement) {
		this.interpreteur = interpreteur;
		this.environement = environement;
	}

	@Override
	public void accept(final String s) {
		this.buffer.append(s);
		this.buffer.append('\n');
		this.indentation += Util.countIndentation(s);
		if (this.indentation == 0) {
			try {
				this.interpreteur.eval(this.buffer.toString(),
						this.environement);
			} catch (final Exception ex) {
				throw new RuntimeException(ex);
			}
			this.buffer = new StringBuilder();
		} else if (this.indentation < 0) {
			throw new IllegalStateException("erreur de parenthèse");
		}
	}

	public boolean isEmpty() {
		return this.buffer.length() == 0;
	}
}