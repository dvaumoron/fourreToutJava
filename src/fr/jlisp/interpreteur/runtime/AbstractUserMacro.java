package fr.jlisp.interpreteur.runtime;

import java.util.List;

import fr.jlisp.syntaxique.Node;

public abstract class AbstractUserMacro<T> extends AbstractCallable<T, Node>
		implements Macro {

	public AbstractUserMacro(final String name,
			final Environement environement, final T args, final Node body) {
		super(name, environement, args, body);
	}

	protected String getCategory() {
		return "macro '";
	}

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		return this.evalMacro(args).eval(environement);
	}

	public Node evalMacro(final List<Node> args)
			throws Exception {
		final Environement local = new EnvironementLocal(this.environement);
		this.updateLocal(local, args);
		return (Node) this.body.eval(local);
	}
}