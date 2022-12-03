package fr.jlisp.interpreteur.runtime;

import java.util.List;

public class MethodWrapper implements Fonction {

	private final Environement environement;
	private final Class<?> clazz;
	private final String name;

	public MethodWrapper(final Environement environement, final Class<?> clazz,
			final String name) {
		this.environement = environement;
		this.clazz = clazz;
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object apply(final List<Object> args) throws Exception {
		return Util.callMethod(this.environement, args.get(0), this.clazz,
				this.name, args.subList(1, args.size()));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MethodWrapper[class=");
		builder.append(this.clazz);
		builder.append(", name=");
		builder.append(this.name);
		builder.append("]");
		return builder.toString();
	}
}