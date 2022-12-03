package fr.jlisp.interpreteur.runtime.builtins;

import java.util.Iterator;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionMinus implements Fonction {

	MINUS;

	@Override
	public String getName() {
		return "-";
	}

	@Override
	public Object apply(final List<Object> args) {
		long resLong = 0;
		double resDouble = 0;
		boolean hasDouble = false;
		final Iterator<Object> it = args.iterator();
		if (it.hasNext()) {
			Number next = (Number) it.next();
			if (next instanceof Integer || next instanceof Long) {
				resLong = next.longValue();
			} else {
				hasDouble = true;
				resDouble = next.doubleValue();
			}
			while (it.hasNext()) {
				next = (Number) it.next();
				if (hasDouble) {
					resDouble -= next.doubleValue();
				} else if (next instanceof Integer || next instanceof Long) {
					resLong -= next.longValue();
				} else {
					hasDouble = true;
					resDouble = resLong - next.doubleValue();
				}
			}
		}
		if (hasDouble) {
			return resDouble;
		} else {
			return resLong;
		}
	}
}