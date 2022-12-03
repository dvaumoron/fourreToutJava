package fr.chiffre;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ChiffreSolveur {

	public static void main(String[] args) {

		final LocalTime start = LocalTime.now();

		final String res = compute(new int[] { 4, 100, 6, 75, 8, 7 }, 573);

		final LocalTime end = LocalTime.now();

		System.out.println(res);
		System.out.println();
		System.out.print("temps de calcul : ");
		final Duration d = Duration.between(start, end);
		System.out.println(d.toString().substring(2));
	}

	public static List<Integer> allSubsets(final int max, final int card) {
		final List<Integer> resultats = new ArrayList<>();
		for (int i = 1; i < max; i++) {
			if (countBin1(i) == card) {
				resultats.add(i);
			}
		}
		return resultats;
	}

	private static int countBin1(final int i) {
		int count = 0;
		for (int j = 1; j <= i; j <<= 1) {
			if ((i & j) != 0) {
				count++;
			}
		}
		return count;
	}

	public static String compute(final int[] chiffres, final int target) {

		final int n = chiffres.length;
		final int m = 1 << n;
		final Map<Integer, Resultat>[] expr = new Map[m];

		int i = 1;
		for (final int e : chiffres) {
			final Map<Integer, Resultat> map = new LinkedHashMap<>();
			map.put(e, new Resultat(Collections.emptyList(), 0));
			expr[i] = map;
			i <<= 1;
		}

		final Set<IntegerPair> done = new LinkedHashSet<>();
		for (int card = 2; card <= n; card++) {
			for (final int S : allSubsets(m, card)) {
				final Map<Integer, Resultat> exprS = new LinkedHashMap<>();
				expr[S] = exprS;
				for (int L = 1; L < S; L++) {
					if ((L & S) == L) {
						final int R = S ^ L;
						if (!done.contains(new IntegerPair(R, L))) {
							done.add(new IntegerPair(L, R));
							final Set<Entry<Integer, Resultat>> exprREntrySet = expr[R]
									.entrySet();
							for (final Entry<Integer, Resultat> exprLEntry : expr[L]
									.entrySet()) {
								final int vL = exprLEntry.getKey();
								final Resultat rL = exprLEntry.getValue();
								final List<String> eL = rL.getExpr();
								final int cL = rL.getCost();
								for (final Entry<Integer, Resultat> exprREntry : exprREntrySet) {
									final int vR = exprREntry.getKey();
									final Resultat rR = exprREntry.getValue();
									final List<String> eR = rR.getExpr();
									final int opCost = cL + rR.getCost() + 1;
									if (!exprS.containsKey(vL)) {
										exprS.put(vL, rL);
									}
									if (!exprS.containsKey(vR)) {
										exprS.put(vR, rR);
									}
									final boolean vLnot0 = vL != 0;
									final boolean vRnot0 = vR != 0;
									if (vLnot0 && vRnot0) {
										addOrReplace(exprS, vL + vR, opCost,
												eL, '+', eR, vL, vR);
										if (vL > vR) {
											addOrReplace(exprS, vL - vR,
													opCost, eL, '-', eR, vL, vR);
										} else if (vR > vL) {
											addOrReplace(exprS, vR - vL,
													opCost, eR, '-', eL, vR, vL);
										}
									}
									final boolean vLnot1 = vL != 1;
									final boolean vRnot1 = vR != 1;
									if (vLnot1 && vRnot1) {
										addOrReplace(exprS, vL * vR, opCost,
												eL, '*', eR, vL, vR);
									}
									if (vRnot0 && vRnot1 && vL % vR == 0) {
										addOrReplace(exprS, vL / vR, opCost,
												eL, '/', eR, vL, vR);
									}
									if (vLnot0 && vLnot1 && vR % vL == 0) {
										addOrReplace(exprS, vR / vL, opCost,
												eR, '/', eL, vL, vR);
									}
								}
							}
						}
					}
				}
			}
		}

		final Map<Integer, Resultat> exprTout = expr[m - 1];
		final List<Integer> signs = Arrays.asList(-1, 1);
		for (int dist = 0; dist <= target; dist++) {
			for (final int sign : signs) {
				final int val = target + sign * dist;
				if (exprTout.containsKey(val)) {
					return exprTout.get(val).toString();
				}
			}
		}

		return "no solution found";
	}

	private static void addOrReplace(final Map<Integer, Resultat> exprS,
			final int newValue, final int opCost, final List<String> eL,
			final char op, final List<String> eR, final int vL, final int vR) {
		if (exprS.containsKey(newValue)) {
			final int opCost2 = exprS.get(newValue).getCost();
			if (opCost < opCost2) {
				exprS.put(newValue,
						createResultat(vL, vR, newValue, eL, op, eR, opCost));
			}
		} else {
			exprS.put(newValue,
					createResultat(vL, vR, newValue, eL, op, eR, opCost));
		}
	}

	private static Resultat createResultat(final int a, final int b,
			final int ab, final List<String> eL, final char op,
			final List<String> eR, final int cost) {
		final List<String> expr = new ArrayList<>(eL);
		expr.addAll(eR);
		expr.add(buildOpString(a, op, b, ab));
		return new Resultat(expr, cost);
	}

	private static String buildOpString(final int a, final char op,
			final int b, final int ab) {

		final String aStr = Integer.toString(a);
		final String bStr = Integer.toString(b);
		final String abStr = Integer.toString(ab);

		final StringBuilder buffer = new StringBuilder(6 + aStr.length()
				+ bStr.length() + abStr.length());
		buffer.append(aStr);
		buffer.append(' ');
		buffer.append(op);
		buffer.append(' ');
		buffer.append(bStr);
		buffer.append(" = ");
		buffer.append(abStr);
		return buffer.toString();
	}
}