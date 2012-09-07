package org.codi.interpreter.objet;

import java.util.List;

import org.codi.interpreter.Environnement;
import org.codi.interpreter.EnvironnementLocal;
import org.codi.interpreter.Expression;
import org.codi.interpreter.Variable;

public class Fonction implements Objet{

	public static final String RETURN = "return";

	private final List<Variable> arguments;
	private final List<Expression> corps;

	public Fonction(List<Variable> arguments, List<Expression> corps) {
		this.arguments = arguments;
		this.corps = corps;
	}

	public Object appliquer(Environnement environnement, List<Expression> valeurArguments) {
		Environnement environnementLocal = new EnvironnementLocal(environnement);

		int i = 0;
		for(Expression valeur : valeurArguments) {
			environnementLocal.declare(
					arguments.get(i).getNom(),
					valeur.evaluer(environnementLocal));
			i++;
		}

		for(Expression expression : corps) {
			expression.evaluer(environnementLocal);
			if (environnementLocal.declare(RETURN)) {
				break;
			}
		}

		return environnementLocal.get(RETURN);
	}

	@Override
	public Classe getClasse() {
		return Classe.FONCTION;
	}

}
