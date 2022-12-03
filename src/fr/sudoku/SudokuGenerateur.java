package fr.sudoku;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerateur {

	public static void main(String[] args) {

		final LocalTime start = LocalTime.now();

		final Random rand = new Random();

		Grille grille = generateGrille(rand);

		System.out.println(grille.toDisplayString());

		System.out.println(obfuscateGrille(grille, rand).toDisplayString());

		final LocalTime end = LocalTime.now();

		System.out.print("temps de calcul : ");
		final Duration d = Duration.between(start, end);
		System.out.println(d.toString().substring(2));
	}

	public static Grille generateGrille(final Random rand) {

		int nbBoucle = 0;
		boolean run = true;
		Grille grille = null;
		while (run) {
			grille = new Grille();

			final Case[][] innerGrille = grille.getInnerGrille();

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (innerGrille[i][j].randomValue(rand)) {
						nbBoucle += grille.solve();
					}
				}
			}
			run = grille.isNotComplete();
		}

		System.out.print("Nombre de boucle pour generer : ");
		System.out.println(nbBoucle);
		System.out.println();

		return grille;
	}

	public static Grille obfuscateGrille(final Grille grille, final Random rand) {
		final Grille grille2 = new Grille(grille);
		final Case[][] innerGrille2 = grille2.getInnerGrille();

		final List<Position> positions = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				positions.add(new Position(i, j));
			}
		}

		Collections.shuffle(positions);

		int nbBoucleObfuscate = 0;
		for (final Position position : positions) {
			final Case caseValue = innerGrille2[position.getLine()][position
					.getColumn()];
			if (!caseValue.found()) {
				throw new IllegalStateException();
			}
			final int value = caseValue.getValue();
			caseValue.reset();
			final Grille workingGrille = new Grille(grille2);
			nbBoucleObfuscate += workingGrille.solve();

			if (workingGrille.isNotComplete()) {
				caseValue.set(value);
			}
		}

		final Grille workingGrille = new Grille(grille2);
		final int nbBoucleSolve = workingGrille.solve();
		if (grille.notEquals(workingGrille)) {
			throw new IllegalStateException();
		}

		System.out.print("Nombre de boucle pour obfusquer : ");
		System.out.println(nbBoucleObfuscate);
		System.out.print("Nombre de boucle pour resoudre : ");
		System.out.println(nbBoucleSolve);
		System.out.println();

		return grille2;
	}
}
