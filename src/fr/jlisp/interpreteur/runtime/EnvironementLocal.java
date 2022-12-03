package fr.jlisp.interpreteur.runtime;

import java.util.Set;

public class EnvironementLocal extends Environement {

	private final Environement parent;

	public EnvironementLocal(final Environement parent) {
		this.parent = parent;
	}

	public EnvironementLocal(final boolean isClass, final String category,
			final Environement parent) {
		super(isClass, category);
		this.parent = parent;
	}

	@Override
	public Object get(final String name) {
		Object res = super.get(name);
		if (res == null) {
			res = this.parent.get(name);
		}
		return res;
	}

	@Override
	public Object getInClass(final String name) {
		Object res = super.getInClass(name);
		if (res == null && this.parent.isClass()) {
			res = this.parent.getInClass(name);
		}
		return res;
	}

	@Override
	public Set<String> keySet() {
		final Set<String> res;
		if (this.parent.isClass()) {
			res = this.parent.keySet();
			res.addAll(super.keySet());
		} else {
			res = super.keySet();
		}
		return res;
	}

	public Environement getParent() {
		return this.parent;
	}
}