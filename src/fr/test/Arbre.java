package fr.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Arbre<T> implements Iterable<T> {

	private final T label;
	private Arbre<T> gauche;
	private Arbre<T> droit;

	public Arbre(final T label) {
		this.label = label;
	}

	public Arbre<T> getGauche() {
		return this.gauche;
	}

	public void setGauche(Arbre<T> gauche) {
		this.gauche = gauche;
	}

	public Arbre<T> getDroit() {
		return this.droit;
	}

	public void setDroit(Arbre<T> droit) {
		this.droit = droit;
	}

	public T getLabel() {
		return this.label;
	}

	public List<T> parcoursEnProfondeur() {
		final List<T> res = new ArrayList<>();
		if (this.gauche != null) {
			res.addAll(this.gauche.parcoursEnProfondeur());
		}
		res.add(this.label);
		if (this.droit != null) {
			res.addAll(this.droit.parcoursEnProfondeur());
		}
		return res;
	}

	public List<T> parcoursEnLargeur() {
		final List<T> res = new ArrayList<>();
		for (final T e : this) {
			res.add(e);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArbreLargeurIterator<>(this);
	}

	private static class ArbreLargeurIterator<T> implements Iterator<T> {

		private final List<Arbre<T>> working = new LinkedList<>();

		public ArbreLargeurIterator(final Arbre<T> a) {
			this.working.add(a);
		}

		@Override
		public boolean hasNext() {
			return !this.working.isEmpty();
		}

		@Override
		public T next() {
			final Arbre<T> current = this.working.remove(0);
			if (current.gauche != null) {
				this.working.add(current.gauche);
			}
			if (current.droit != null) {
				this.working.add(current.droit);
			}
			return current.label;
		}
	}
}