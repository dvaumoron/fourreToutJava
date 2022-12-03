package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.BreakException;
import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionBreakContinue implements Fonction {

	BREAK(true, "break"), CONTINUE(false, "continue");

	private final boolean isBreak;
	private final String name;

	private FonctionBreakContinue(final boolean isBreak, final String name) {
		this.isBreak = isBreak;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public Object apply(final List<Object> args) throws Exception {
		throw new BreakException(this.isBreak);
	}
}