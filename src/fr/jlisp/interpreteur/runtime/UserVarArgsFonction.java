package fr.jlisp.interpreteur.runtime;

import java.util.List;

import fr.jlisp.syntaxique.Node;

public class UserVarArgsFonction extends AbstractUserFonction<String> {

	public UserVarArgsFonction(final String name,
			final Environement environement, final String argsName,
			final Node body) {
		super(name, environement, argsName, body);
	}

	public UserVarArgsFonction(final Environement environement,
			final String argsName, final Node body) {
		super(Util.LAMBDA, environement, argsName, body);
	}

	@Override
	protected void updateLocal(final Environement local, final List<Object> args) {
		local.set(this.args, args);
	}
}