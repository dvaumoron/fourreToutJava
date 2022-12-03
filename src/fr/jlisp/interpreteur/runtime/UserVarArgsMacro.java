package fr.jlisp.interpreteur.runtime;

import java.util.List;

import fr.jlisp.syntaxique.Node;

public class UserVarArgsMacro extends AbstractUserMacro<String> {

	public UserVarArgsMacro(final String name, final Environement environement,
			final String argsName, final Node body) {
		super(name, environement, argsName, body);
	}

	@Override
	protected void updateLocal(final Environement local, final List<Node> args) {
		local.set(this.args, args);
	}
}