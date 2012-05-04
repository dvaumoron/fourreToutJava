package chiffre;

import java.util.Comparator;

public class SolutionComparator implements Comparator<Solution> {

	private final int objectif;
	
	public SolutionComparator(int objectif) {
		this.objectif = objectif;
	}

	@Override
	public int compare(Solution s1, Solution s2) {
		int distance = distance(s1) - distance(s2);
		if (distance == 0) {
			distance = s1.getCalculs().size() - s2.getCalculs().size();
		}
		return distance;
	}

	private int distance(Solution solution) {
		int resultat = solution.getResultat();
		if (resultat > objectif ) {
			return resultat - objectif;
		} else {
			return objectif - resultat;
		}
	}

}
