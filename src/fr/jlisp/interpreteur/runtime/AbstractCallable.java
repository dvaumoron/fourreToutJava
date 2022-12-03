package fr.jlisp.interpreteur.runtime;

import java.util.List;

import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeList;

public abstract class AbstractCallable<T1, T2> {

	protected String repr;
	protected final Environement environement;
	protected final T1 args;
	protected final Node body;

	public AbstractCallable(final String name, final Environement environement,
			final T1 args, final Node body) {
		this.setName(name);
		this.environement = environement;
		this.args = args;
		this.body = execMacro(new EnvironementLocal(environement), body);
	}

	public String getName() {
		return this.repr.substring(this.repr.indexOf("'") + 1,
				this.repr.lastIndexOf("'"));
	}

	public void setName(final String name) {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(this.getCategory());
		buffer.append(name);
		buffer.append("'");
		this.repr = buffer.toString();
	}

	protected abstract String getCategory();

	protected abstract void updateLocal(final Environement local,
			final List<T2> args);

	@Override
	public String toString() {
		return this.repr;
	}

	private static Node execMacro(final Environement environement,
			final Node node) {
		if (node instanceof NodeList) {
			final NodeList nodeList = (NodeList) node;
			final int size = nodeList.size();
			final NodeList res = new NodeList();

			if (size > 0) {
				final Node node0 = nodeList.get(0);
				try {
					final Object object = node0.eval(environement);
					if (object instanceof AbstractUserMacro) {
						final AbstractUserMacro macro = (AbstractUserMacro) object;
						return execMacro(environement, macro.evalMacro(nodeList.subList()));
					}
				} catch (final Exception ex) {
					// nothing to do
				}

				res.add(execMacro(environement, node0));
				for (int i = 1; i < size; i++) {
					res.add(execMacro(environement, nodeList.get(i)));
				}
			}
			return res;
		}
		return node;
	}
}