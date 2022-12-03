package fr.jlisp.interpreteur.runtime;

import java.util.List;

import fr.jlisp.syntaxique.Node;

public abstract class AbstractUserFonction<T> extends AbstractCallable<T, Object>
		implements Fonction {

	public AbstractUserFonction(final String name,
			final Environement environement, final T args, final Node body) {
		super(name, environement, args, body);
	}

	protected String getCategory() {
		return "fonction '";
	}

	@Override
	public Object apply(final List<Object> args) throws Exception {
		final Environement local = new EnvironementLocal(this.environement);
		this.updateLocal(local, args);
		return this.body.eval(local);
	}
}