package chiffre;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SolveurRec extends Solveur {

	public List<Solution> recherche(List<Integer> chiffres) {
		List<Solution> solutions = new ArrayList<Solution>();
		int size = chiffres.size();
		for(int chiffre : chiffres) {
			solutions.add(new Solution(chiffre, new LinkedList<Calcul>()));
		}
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1 ; j < size; j++) {
				int a  = chiffres.get(i);
				int b = chiffres.get(j);
				if (a < b) {
					int t = a;
					a = b;
					b = t;
				}
				ArrayList<Integer> newChiffres = SolveurIt.createListWithoutIndex(
						chiffres, size, i, j);
				
				for(Operation operation : Operation.values()) {
					try {
						int appliquer = operation.appliquer(a, b);
						List<Solution> solutionsRec = recherche(
								SolveurIt.createListeIntermediaire(newChiffres, appliquer));
						for(Solution solutionRec : solutionsRec) {
							solutionRec.getCalculs().add(0, new Calcul(operation, a, b));
							solutions.add(solutionRec);
						}
					} catch (IllegalArgumentException iae) {
						// do nothing
					}
				}
			}
		}
		return solutions;
	}

}
