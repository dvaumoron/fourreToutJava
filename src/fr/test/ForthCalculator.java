package fr.test;

import java.util.ArrayList;
import java.util.List;

public class ForthCalculator {

	public static void main(String[] args) {
		System.out.println(eval("1 2 3 4 5 * + * -"));
		
		System.out.println(eval("10 5 7 * 8 9 * * /"));
	}
	
	public static int eval(String code) {
		final List<Integer> pile = new ArrayList<>();

		for (String instruction : code.split(" ")) {
			final int value1;
			final int value2;
			switch (instruction) {
			case "+":
				value1 = pile.remove(pile.size() - 1);
				value2 = pile.remove(pile.size() - 1);
				pile.add(value1 + value2);
				break;
			case "*":
				value1 = pile.remove(pile.size() - 1);
				value2 = pile.remove(pile.size() - 1);
				pile.add(value1 * value2);
				break;
			case "-":
				value1 = pile.remove(pile.size() - 1);
				value2 = pile.remove(pile.size() - 1);
				pile.add(value1 - value2);
				break;
			case "/":
				value1 = pile.remove(pile.size() - 1);
				value2 = pile.remove(pile.size() - 1);
				pile.add(value1 / value2);
				break;
			default:
				pile.add(Integer.valueOf(instruction));
				break;
			}
		}
		
		return pile.get(0);
	}
}