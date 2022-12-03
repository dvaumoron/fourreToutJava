package fr.jlisp.interpreteur.runtime.builtins;

import java.io.IOException;
import java.util.List;

import fr.jlisp.interpreteur.Interpreteur;
import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroImport implements Macro {

	IMPORT_JAVA {
		@Override
		public Object getModule(final Interpreteur interpreteur,
				final String moduleName) throws ClassNotFoundException {
			return Class.forName(moduleName);
		}
	},
	IMPORT_JLISP {
		@Override
		public Object getModule(final Interpreteur interpreteur,
				final String moduleName) throws IOException {
			return interpreteur.evalModule(moduleName);
		}
	};

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final String moduleName = Util.getName(environement, args.get(0));
		final Interpreteur interpreteur = Util.getInterpreteur(environement);
		final Object module = this.getModule(interpreteur, moduleName);
		final String name;
		if (args.size() > 1) {
			name = Util.getName(environement, args.get(1));
		} else {
			name = moduleName.substring(moduleName.lastIndexOf(".") + 1);
		}
		environement.set(name, module);
		return module;
	}

	public abstract Object getModule(final Interpreteur interpreteur,
			final String moduleName) throws Exception;
}