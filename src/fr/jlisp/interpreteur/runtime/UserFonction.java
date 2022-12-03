package fr.jlisp.interpreteur.runtime;

import java.util.List;

import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeList;

public class UserFonction extends AbstractUserFonction<List<String>> {

	public UserFonction(final String name, final Environement environement,
			final NodeList args, final Node body) {
		super(name, environement, Util.convertNameList(args), body);
	}

	public UserFonction(Environement environement, NodeList args, Node body) {
		super(Util.LAMBDA, environement, Util.convertNameList(args), body);
	}

	@Override
	protected void updateLocal(final Environement local, final List<Object> args) {
		Util.updateCallLocal(this.args, local, args);
	}
}