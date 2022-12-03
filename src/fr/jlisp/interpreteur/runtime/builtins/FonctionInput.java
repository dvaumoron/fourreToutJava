package fr.jlisp.interpreteur.runtime.builtins;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionInput implements Fonction {

	INPUT;

	private final BufferedReader reader;

	private FonctionInput() {
		this.reader = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public String getName() {
		return "input";
	}

	@Override
	public Object apply(final List<Object> args)
			throws Exception {
		if (args.size() > 0) {
			System.out.print(args.get(0));
		}
		return this.reader.readLine();
	}
}