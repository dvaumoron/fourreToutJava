package chiffre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolveurIt extends Solveur {

	public List<Solution> recherche(List<Integer> chiffres) {
		List<Solution> solutions = new ArrayList<Solution>();
		solutions.addAll(recherche0(chiffres));

		List<List<Integer>> chiffresIntermediaire = new ArrayList<List<Integer>>();
		chiffresIntermediaire.add(chiffres);
		List<Solution> solutionsPrecedente = new ArrayList<Solution>();
		List<Calcul> emptyList = Collections.emptyList();
		solutionsPrecedente.add(new Solution(0, emptyList));
		
		int size = chiffres.size();
		for (int n = 1; n < size ; n++) {
			List<Solution> solutionsN = new ArrayList<Solution>();
			List<List<Integer>> chiffresIntermediaireN = recherche(
					chiffresIntermediaire, solutionsPrecedente, solutionsN);
			solutions.addAll(solutionsN);
			chiffresIntermediaire = chiffresIntermediaireN;
			solutionsPrecedente = solutionsN;
		}
		return solutions;
	}

	public static List<Solution> recherche0(List<Integer> chiffres) {
		List<Calcul> emptyList = Collections.emptyList();
		List<Solution> solutions = new ArrayList<Solution>();
		for (int i : chiffres) {
			solutions.add(new Solution(i, emptyList));
		}
		return solutions;
	}

	public static List<List<Integer>> recherche(
			List<List<Integer>> chiffresIntermediaire, List<Solution> solutionsPrecedente,
			List<Solution> solutions) {
		int i = 0;
		List<List<Integer>> chiffresIntermediaire2 = new ArrayList<List<Integer>>();
		for(List<Integer> chiffres : chiffresIntermediaire) {
			chiffresIntermediaire2.addAll(
					rechercheInterne(chiffres, solutionsPrecedente.get(i), solutions));
			i++;
		}
		return chiffresIntermediaire2;
	}

	private static List<List<Integer>> rechercheInterne(
			List<Integer> chiffres, Solution solution, List<Solution> solutions) {
		List<List<Integer>> chiffresIntermediaire = new ArrayList<List<Integer>>();
		int size = chiffres.size();
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1 ; j < size; j++) {
				int a  = chiffres.get(i);
				int b = chiffres.get(j);
				if (a < b) {
					int t = a;
					a = b;
					b = t;
				}
				ArrayList<Integer> newChiffres = createListWithoutIndex(
						chiffres, size, i, j);
				
				for(Operation operation : Operation.values()) {
					try {
						int appliquer = operation.appliquer(a, b);
						chiffresIntermediaire.add(
								createListeIntermediaire(newChiffres, appliquer));
						List<Calcul> listCalcul = new ArrayList<Calcul>(solution.getCalculs());
						listCalcul.add(new Calcul(operation, a, b));
						solutions.add(new Solution(appliquer, listCalcul));
					} catch (IllegalArgumentException iae) {
						// do nothing
					}
				}
			}
		}
		return chiffresIntermediaire;
	}

	public static ArrayList<Integer> createListWithoutIndex(
			List<Integer> chiffres, int size, int i, int j) {
		ArrayList<Integer> newChiffres = new ArrayList<Integer>();
		newChiffres.addAll(chiffres.subList(0, i));
		newChiffres.addAll(chiffres.subList(i + 1, j));
		newChiffres.addAll(chiffres.subList(j + 1, size));
		return newChiffres;
	}

	public static List<Integer> createListeIntermediaire(
			ArrayList<Integer> newChiffres, int appliquer) {
		ArrayList<Integer> chiffresOperation = new ArrayList<Integer>(newChiffres);
		chiffresOperation.add(appliquer);
		return chiffresOperation;
	}
}
