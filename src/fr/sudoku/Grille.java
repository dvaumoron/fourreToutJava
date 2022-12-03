package fr.sudoku;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Grille {

	private final Case[][] innerGrille = new Case[9][9];

	public Grille() {
		for (int i = 0; i < 9; i++) {
			final Case[] innerLine = this.innerGrille[i];
			for (int j = 0; j < 9; j++) {
				innerLine[j] = new Case();
			}
		}
	}

	public Grille(final Grille grille) {
		final Case[][] innerGrille = grille.getInnerGrille();
		for (int i = 0; i < 9; i++) {
			final Case[] innerLine = innerGrille[i];
			final Case[] thisInnerLine = this.innerGrille[i];
			for (int j = 0; j < 9; j++) {
				thisInnerLine[j] = new Case(innerLine[j]);
			}
		}
	}

	public Grille(final String path) {
		try (final FileInputStream fis = new FileInputStream(path);
				final InputStreamReader isr = new InputStreamReader(fis);
				final BufferedReader reader = new BufferedReader(isr)) {

			for (int i = 0; i < 9; i++) {
				final String[] values = reader.readLine().split(",");
				final Case[] innerLine = this.innerGrille[i];
				for (int j = 0; j < 9; j++) {
					final String value = values[j];
					if (" ".equals(value)) {
						innerLine[j] = new Case();
					} else {
						innerLine[j] = new Case(Integer.parseInt(value));
					}
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public Case[][] getInnerGrille() {
		return this.innerGrille;
	}

	public boolean isNotComplete() {
		for (int i = 0; i < 9; i++) {
			final Case[] innerLine = this.innerGrille[i];
			for (int j = 0; j < 9; j++) {
				if (!innerLine[j].found()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean notEquals(final Grille grille) {
		final Case[][] innerGrille = grille.getInnerGrille();
		for (int i = 0; i < 9; i++) {
			Case[] innerLine1 = this.innerGrille[i];
			Case[] innerLine2 = innerGrille[i];
			for (int j = 0; j < 9; j++) {
				final Case caseValue1 = innerLine1[j];
				final Case caseValue2 = innerLine2[j];
				if (!(caseValue1.found() && caseValue2.found() && caseValue1
						.getValue() == caseValue2.getValue())) {
					return true;
				}
			}
		}
		return false;
	}

	public int solve() {
		int nbBoucle = -1;
		boolean change = true;
		while (change) {
			change = false;
			nbBoucle++;
			for (int i = 0; i < 9; i++) {
				final Case[] innerLine = this.innerGrille[i];
				for (int j = 0; j < 9; j++) {
					final Case caseValue = innerLine[j];
					if (caseValue.foundFirst()) {
						final int value = caseValue.getValue();
						for (int k = 0; k < 9; k++) {
							if (k != j && innerLine[k].impossible(value)) {
								change = true;
							}
							if (k != i
									&& this.innerGrille[k][j].impossible(value)) {
								change = true;
							}
						}

						final int[] i2Index = subSquareIndex(i);
						final int[] j2Index = subSquareIndex(j);
						final int i2End = i2Index[1];
						final int j2Start = j2Index[0];
						final int j2End = j2Index[1];
						for (int i2 = i2Index[0]; i2 < i2End; i2++) {
							for (int j2 = j2Start; j2 < j2End; j2++) {
								if (!(i2 == i && j2 == j)
										&& this.innerGrille[i2][j2]
												.impossible(value)) {
									change = true;
								}
							}
						}
					}
				}
			}

			for (int value = 1; value < 10; value++) {
				for (int i = 0; i < 9; i++) {
					final List<Integer> listColumn = new ArrayList<>(9);
					final List<Integer> listLine = new ArrayList<>(9);
					for (int j = 0; j < 9; j++) {
						if (this.innerGrille[i][j].isPossible(value)) {
							listColumn.add(j);
						}

						if (this.innerGrille[j][i].isPossible(value)) {
							listLine.add(j);
						}
					}

					if (listColumn.size() == 1
							&& this.innerGrille[i][listColumn.get(0)]
									.set(value)) {
						change = true;
					}

					if (listLine.size() == 1
							&& this.innerGrille[listLine.get(0)][i].set(value)) {
						change = true;
					}
				}

				change = this.computeSquare(change, value, 0, 3, 0, 3);
				change = this.computeSquare(change, value, 0, 3, 3, 6);
				change = this.computeSquare(change, value, 0, 3, 6, 9);
				change = this.computeSquare(change, value, 3, 6, 0, 3);
				change = this.computeSquare(change, value, 3, 6, 3, 6);
				change = this.computeSquare(change, value, 3, 6, 6, 9);
				change = this.computeSquare(change, value, 6, 9, 0, 3);
				change = this.computeSquare(change, value, 6, 9, 3, 6);
				change = this.computeSquare(change, value, 6, 9, 6, 9);
			}
		}

		return nbBoucle;
	}

	private static int[] subSquareIndex(final int n) {
		final int start;
		final int end;
		if (n < 3) {
			start = 0;
			end = 3;
		} else if (n < 6) {
			start = 3;
			end = 6;
		} else {
			start = 6;
			end = 9;
		}
		return new int[] { start, end };
	}

	private boolean computeSquare(boolean change, final int value,
			final int iStart, final int iEnd, final int jStart, final int jEnd) {

		final List<Position> listPosition = new ArrayList<>(9);
		for (int i = iStart; i < iEnd; i++) {
			for (int j = jStart; j < jEnd; j++) {
				if (this.innerGrille[i][j].isPossible(value)) {
					listPosition.add(new Position(i, j));
				}
			}
		}

		final Position position1;
		final Position position2;
		final int line;
		switch (listPosition.size()) {
		case 1:
			position1 = listPosition.get(0);
			if (this.innerGrille[position1.getLine()][position1.getColumn()]
					.set(value)) {
				change = true;
			}
			break;
		case 2:
			position1 = listPosition.get(0);
			position2 = listPosition.get(1);
			line = position1.getLine();
			if (line == position2.getLine()) {
				change = this.impossibleLine(change, value, line, jStart, jEnd);
			} else {
				final int column = position1.getColumn();
				if (column == position2.getColumn()) {
					change = this.impossibleColumn(change, value, column,
							iStart, iEnd);
				}
			}
			break;
		case 3:
			position1 = listPosition.get(0);
			position2 = listPosition.get(1);
			final Position position3 = listPosition.get(2);
			line = position1.getLine();
			if (line == position2.getLine() && line == position3.getLine()) {
				change = this.impossibleLine(change, value, line, jStart, jEnd);
			} else {
				final int column = position1.getColumn();
				if (column == position2.getColumn()
						&& column == position3.getColumn()) {
					change = this.impossibleColumn(change, value, column,
							iStart, iEnd);
				}
			}
			break;
		}
		return change;
	}

	private boolean impossibleLine(boolean change, final int value,
			final int line, final int jStart, final int jEnd) {
		for (int j = 0; j < 9; j++) {
			if ((j < jStart || j >= jEnd)
					&& this.innerGrille[line][j].impossible(value)) {
				change = true;
			}
		}
		return change;
	}

	private boolean impossibleColumn(boolean change, final int value,
			final int column, final int iStart, final int iEnd) {
		for (int i = 0; i < 9; i++) {
			if ((i < iStart || i >= iEnd)
					&& this.innerGrille[i][column].impossible(value)) {
				change = true;
			}
		}
		return change;
	}

	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder(2268);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				buffer.append(this.innerGrille[i][j]);
				if (j != 8) {
					buffer.append(',');
				}
			}
			buffer.append('\n');
		}
		return buffer.toString();
	}

	public String toDisplayString() {
		final StringBuilder buffer = new StringBuilder(260);
		final String lineSeparator = "+-----+-----+-----+\n";
		buffer.append(lineSeparator);
		for (int i = 0; i < 9; i++) {
			buffer.append('|');
			for (int j = 0; j < 9; j++) {
				buffer.append(this.innerGrille[i][j].toDisplayString());

				if (j == 2 || j == 5 || j == 8) {
					buffer.append('|');
				} else {
					buffer.append(',');
				}
			}
			buffer.append("\n");
			if (i == 2 || i == 5) {
				buffer.append(lineSeparator);
			}
		}
		buffer.append(lineSeparator);
		return buffer.toString();
	}

	public String toFileString() {
		final StringBuilder buffer = new StringBuilder(162);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				buffer.append(this.innerGrille[i][j].toDisplayString());
				if (j != 8) {
					buffer.append(',');
				}
			}
			buffer.append('\n');
		}
		return buffer.toString();
	}
}