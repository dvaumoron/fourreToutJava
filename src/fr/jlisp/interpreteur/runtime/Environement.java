package fr.jlisp.interpreteur.runtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Environement implements Fonction, Comparable<Object>,
		Iterable<Object>, Iterator<Object>, AutoCloseable {

	private final boolean isClass;
	private final String category;
	private final Map<String, Object> map = new LinkedHashMap<>();

	public Environement(final boolean isClass, final String category) {
		this.isClass = isClass;
		this.category = category;
	}

	public Environement() {
		this(false, "Environement[");
	}

	public boolean isClass() {
		return this.isClass;
	}

	public String getName() {
		return this.category.substring(this.category.indexOf("'") + 1,
				this.category.lastIndexOf("'"));
	}

	public void set(final String name, final Object value) {
		this.map.put(name, value);
	}

	public Object get(final String name) {
		return this.map.get(name);
	}

	public Object getInClass(final String name) {
		return this.map.get(name);
	}

	public void del(final String name) {
		this.map.remove(name);
	}

	public Set<String> keySet() {
		return new LinkedHashSet<>(this.map.keySet());
	}

	public Fonction getFonction(final String name) throws Exception {
		Fonction fonction = (Fonction) this.getInClass(name);
		if (fonction == null) {
			final Fonction getAttributeFonction = (Fonction) this
					.getInClass(Util.GET_ATTRIBUTE);
			if (getAttributeFonction != null) {
				fonction = (Fonction) getAttributeFonction.apply(Arrays.asList(
						this, name));
			}
		}
		return fonction;
	}

	@Override
	public String toString() {
		if (this.isClass) {
			return this.defaultToString();
		} else {
			try {
				final Fonction toStringFonction = this.getFonction("toString");
				if (toStringFonction == null) {
					return this.defaultToString();
				} else {
					return (String) toStringFonction.apply(Collections
							.singletonList(this));
				}
			} catch (final Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	private String defaultToString() {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(this.category);
		final Iterator<Entry<String, Object>> it = this.map.entrySet()
				.iterator();
		if (it.hasNext()) {
			this.printEntryTo(buffer, it);
			while (it.hasNext()) {
				buffer.append(", ");
				this.printEntryTo(buffer, it);
			}
		}
		buffer.append("]");
		return buffer.toString();
	}

	private void printEntryTo(final StringBuilder buffer,
			final Iterator<Entry<String, Object>> it) {
		final Entry<String, Object> entry = it.next();
		buffer.append(entry.getKey());
		buffer.append("=");
		final Object value = entry.getValue();
		if (value == this) {
			buffer.append("this");
		} else {
			buffer.append(value);
		}
	}

	@Override
	public boolean equals(final Object other) {
		try {
			final Fonction equalsFonction = this.getFonction("equals");
			if (equalsFonction != null) {
				return (Boolean) equalsFonction.apply(Arrays
						.asList(this, other));
			}
			return this == other;
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public int hashCode() {
		try {
			final Fonction hashCodeFonction = this.getFonction("hashCode");
			if (hashCodeFonction != null) {
				final Number res = (Number) hashCodeFonction.apply(Collections
						.singletonList(this));
				return res.intValue();

			}
			return super.hashCode();
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	@Override
	public Object apply(final List<Object> args) throws Exception {
		Fonction applyFonction = this.getFonction(Util.APPLY);
		final List<Object> newArgs = new ArrayList<>(args.size() + 1);
		newArgs.add(this);
		newArgs.addAll(args);
		return applyFonction.apply(newArgs);
	}

	@Override
	public int compareTo(final Object other) {
		try {
			final Fonction compareFonction = this.getFonction("compareTo");
			final Number res = (Number) compareFonction.apply(Arrays.asList(
					this, other));
			return res.intValue();
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public int compare(final Object arg0, final Object arg1) {
		try {
			final Fonction compareFonction = this.getFonction("compare");
			final Number res = (Number) compareFonction.apply(Arrays.asList(
					this, arg0, arg1));
			return res.intValue();
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Iterator<Object> iterator() {
		try {
			final Fonction itFonction = this.getFonction("iterator");
			if (itFonction == null) {
				return this.map.values().iterator();
			} else {
				return (Iterator<Object>) itFonction.apply(Collections
						.singletonList(this));
			}
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public boolean hasNext() {
		try {
			final Fonction hasNextFonction = this.getFonction("hasNext");
			return (Boolean) hasNextFonction.apply(Collections
					.singletonList(this));
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Object next() {
		try {
			final Fonction nextFonction = this.getFonction("next");
			return nextFonction.apply(Collections.singletonList(this));
		} catch (final Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void close() throws Exception {
		final Fonction closeFonction = this.getFonction("close");
		if (closeFonction != null) {
			closeFonction.apply(Collections.singletonList(this));
		}
	}
}