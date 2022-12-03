package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Iterator;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionPrint implements Fonction {

	PRINT;

	@Override
	public String getName() {
		return "print";
	}

	@Override
	public Object apply(final List<Object> args) {
		Iterator<Object> it = args.iterator();
		if (it.hasNext()) {
			System.out.print(it.next());
			while (it.hasNext()) {
				System.out.print(" ");
				System.out.print(it.next());
			}
		}
		System.out.println();
		return null;
	}
}