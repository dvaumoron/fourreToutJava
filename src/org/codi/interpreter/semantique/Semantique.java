package org.codi.interpreter.semantique;

import java.util.List;

import org.codi.interpreter.Environnement;
import org.codi.interpreter.Expression;

public interface Semantique {

	public abstract Object evaluer(Environnement environnement, List<Expression> expressions);

}
