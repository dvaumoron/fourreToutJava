package fr.jlisp.interpreteur.runtime;

import java.util.List;

import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeList;

public class UserMacro extends AbstractUserMacro<List<String>> {

	public UserMacro(final String name, final Environement environement,
			final NodeList args, final Node body) {
		super(name, environement, Util.convertNameList(args), body);
	}

	@Override
	protected void updateLocal(final Environement local, final List<Node> args) {
		Util.updateCallLocal(this.args, local, args);
	}
}