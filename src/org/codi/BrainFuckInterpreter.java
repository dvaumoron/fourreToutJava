package org.codi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BrainFuckInterpreter {

	public static int MEMO_SIZE = 10;

	public static void main(String[] args) throws IOException {
		BrainFuckInterpreter interpreter = new BrainFuckInterpreter();
		// Hello World !
		interpreter.eval("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>.+.>.");
		// addition (valeur à 1 chiffre)
		// ex: la saisie 3+4 renvoi 7
		interpreter.eval(",>++++++[<-------->-],,[<+>-],<.>.");
		// test avec une boucle imbriqué (affiche H)
		interpreter.eval("++++++++[>>+++[<+++>-]<<-]>.");
	}

	private List<Character> memo;
	private int memoIndex;
	private int instructionIndex;
	
	public void eval(String program) throws IOException {
		memoIndex = 0;
		memo = initMemo();
		instructionIndex = 0;
		while (instructionIndex < program.length()) {
			evalInstruction(program);
			instructionIndex++;
		}
	}

	private void evalInstruction(String program) throws IOException {
		switch (program.charAt(instructionIndex)) {
		case '>':
			memoIndex++;
			break;
		case '<':
			memoIndex--;
			break;
		case '+':
			memo.set(memoIndex, (char) (memo.get(memoIndex) + 1));
			break;
		case '-':
			memo.set(memoIndex, (char) (memo.get(memoIndex) - 1));
			break;
		case '.':
			System.out.print(memo.get(memoIndex));
			break;
		case ',':
			memo.set(memoIndex, (char) System.in.read());
			break;
		case '[':
			if (memo.get(memoIndex) == 0) {
				int nbCrochet = 0;
				instructionIndex++;
				while (program.charAt(instructionIndex) != ']' || nbCrochet != 0) {
					if (program.charAt(instructionIndex) == '[') {
						nbCrochet++;
					} else if (program.charAt(instructionIndex) == ']') {
						nbCrochet--;
					}
					instructionIndex++;
				}
			}
			break;
		case ']':
			if (memo.get(memoIndex) != 0) {
				int nbCrochet = 0;
				instructionIndex--;
				while (program.charAt(instructionIndex) != '[' || nbCrochet != 0) {
					if (program.charAt(instructionIndex) == ']') {
						nbCrochet++;
					} else if (program.charAt(instructionIndex) == '[') {
						nbCrochet--;
					}
					instructionIndex--;
				}
			}
			break;
		default:
			// nothing to do (comment)
			break;
		}
	}

	private static List<Character> initMemo() {
		List<Character> memo = new ArrayList<Character>();
		for (int i = 0; i < MEMO_SIZE; ++i) {
			memo.add((char) 0);
		}
		return memo;
	}

}
