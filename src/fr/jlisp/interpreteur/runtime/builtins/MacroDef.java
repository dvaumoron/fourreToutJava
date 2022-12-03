package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.Macro;
import fr.jlisp.interpreteur.runtime.UserFonction;
import fr.jlisp.interpreteur.runtime.UserMacro;
import fr.jlisp.interpreteur.runtime.UserVarArgsFonction;
import fr.jlisp.interpreteur.runtime.UserVarArgsMacro;
import fr.jlisp.interpreteur.runtime.Util;
import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeIdentifier;
import fr.jlisp.syntaxique.NodeList;

public enum MacroDef implements Macro {

	DEF {
		@Override
		protected Object newStandard(final String name,
				final Environement environement, final NodeList args,
				final Node body) {
			return new UserFonction(name, environement, args, body);
		}

		@Override
		protected Object newVarArgs(final String name,
				final Environement environement, final String argsName,
				final Node body) {
			return new UserVarArgsFonction(name, environement, argsName, body);
		}
	},
	DEF_MACRO {
		@Override
		protected Object newStandard(final String name,
				final Environement environement, final NodeList args,
				final Node body) {
			return new UserMacro(name, environement, args, body);
		}

		@Override
		protected Object newVarArgs(final String name,
				final Environement environement, final String argsName,
				final Node body) {
			return new UserVarArgsMacro(name, environement, argsName, body);
		}
	};

	@Override
	public Object apply(final Environement environement, final List<Node> args)
			throws Exception {
		final String name = Util.getName(environement, args.get(0));
		final Node argsNode = args.get(1);
		final Node body = args.get(2);
		final Object value;
		if (argsNode instanceof NodeList) {
			value = this.newStandard(name, environement, (NodeList) argsNode,
					body);
		} else {
			value = this.newVarArgs(name, environement,
					((NodeIdentifier) argsNode).getIdentifier(), body);
		}
		environement.set(name, value);
		return value;
	}

	protected abstract Object newStandard(final String name,
			final Environement environement, final NodeList args,
			final Node body);

	protected abstract Object newVarArgs(final String name,
			final Environement environement, final String argsName,
			final Node body);
}