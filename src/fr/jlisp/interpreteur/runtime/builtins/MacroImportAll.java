package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;
import java.util.Set;

import fr.jlisp.interpreteur.Interpreteur;
import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;

public enum MacroImportAll implements Macro {

	IMPORT_ALL;

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final String moduleName = Util.getName(environement, args.get(0));
		final Interpreteur interpreteur = Util.getInterpreteur(environement);
		final Environement module = interpreteur.evalModule(moduleName);
		if (module != null) {
			final Set<String> keySet = module.keySet();
			keySet.remove(Util.MODULE_NAME);
			for (final String name : keySet) {
				environement.set(name, module.get(name));
			}
		}
		return module;
	}
}