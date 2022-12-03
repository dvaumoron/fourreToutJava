package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionGenVar implements Fonction {

	GEN_VAR;

	private static final String VAR = "var";

	@Override
	public String getName() {
		return "genVar";
	}

	@Override
	public Object apply(final List<Object> args) {
		final Environement env = (Environement) args.get(0);
		String res = VAR;
		int i = 0;
		while (env.get(res) != null) {
			res = VAR.concat(Integer.toString(i));
			i++;
		}
		return res;
	}
}