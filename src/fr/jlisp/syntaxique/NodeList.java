package fr.jlisp.syntaxique;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.EnvironementLocal;
import fr.jlisp.interpreteur.runtime.Fonction;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;

public class NodeList implements Node, Iterable<Node> {

	private final List<Node> list = new ArrayList<>();

	public boolean add(final Node node) {
		return this.list.add(node);
	}

	public Node get(final int index) {
		return this.list.get(index);
	}

	public int size() {
		return this.list.size();
	}

	public List<Node> subList() {
		return this.list.subList(1, this.list.size());
	}

	public Iterator<Node> iterator() {
		return this.list.iterator();
	}

	@Override
	public Object eval(final Environement environement) throws Exception {
		final Object object = this.list.get(0).eval(environement);
		if (object == null) {
			return null;
		} else {
			final int size = this.list.size() - 1;

			final Object res;
			if (object instanceof Class) {
				final Class<?> clazz = (Class<?>) object;
				if (size == 0) {
					res = clazz.newInstance();
				} else {
					final Object[] objects = new Object[size];
					final Class<?>[] classes = new Class[size];
					for (int i = 1, j = 0; j < size; i++, j++) {
						objects[j] = this.list.get(i).eval(environement);
						classes[j] = objects[j].getClass();
					}

					res = Util.callConstructor(clazz, objects, classes);
				}
			} else if (object instanceof Environement) {
				final Environement objectEnv = (Environement) object;
				if (objectEnv.isClass()) {
					final String category = Util.getCategory(Util.OBJECT,
							objectEnv.getName());
					res = new EnvironementLocal(false, category, objectEnv);
					final Fonction initFonction = objectEnv
							.getFonction(Util.INIT);
					if (initFonction != null) {
						final List<Object> newArgs = new ArrayList<>(
								this.list.size());
						newArgs.add(res);
						this.addArgs(environement, newArgs);
						initFonction.apply(newArgs);
					}
				} else {
					res = this.applyFonction(environement, objectEnv, size);
				}
			} else if (object instanceof Fonction) {
				res = this.applyFonction(environement, (Fonction) object, size);
			} else if (object instanceof Macro) {
				final Macro macro = (Macro) object;
				res = macro.apply(environement, this.subList());
			} else {
				final List<Object> newArgs = new ArrayList<>(size);
				this.addArgs(environement, newArgs);
				res = Util.callMethod(environement, object, object.getClass(),
						Util.APPLY, newArgs);
			}
			return res;
		}
	}

	private Object applyFonction(final Environement environement,
			final Fonction fonction, final int size) throws Exception {
		final List<Object> args = new ArrayList<>(size);
		this.addArgs(environement, args);
		return fonction.apply(args);
	}

	private void addArgs(final Environement environement,
			final List<Object> args) throws Exception {
		for (final Node node : this.subList()) {
			args.add(node.eval(environement));
		}
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("NodeList(");
		buffer.append(this.list);
		buffer.append(")");
		return buffer.toString();
	}
}