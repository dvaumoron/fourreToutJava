package fr.sudoku;

import java.time.Duration;
import java.time.LocalTime;

public class SudokuSolveur {

	public static void main(String[] args) {

		final LocalTime start = LocalTime.now();

		final Grille grille = new Grille("grilles\\generatedGrille1.txt");

		System.out.println(grille.toDisplayString());

		final int nbBoucle = grille.solve();

		System.out.print("Nombre de boucle pour resoudre : ");
		System.out.println(nbBoucle);
		System.out.println();

		System.out.println(grille.toDisplayString());

		final LocalTime end = LocalTime.now();

		System.out.print("temps de calcul : ");
		final Duration d = Duration.between(start, end);
		System.out.println(d.toString().substring(2));
	}
}