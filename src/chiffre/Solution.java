package chiffre;

import java.util.Arrays;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		Solution solution = new Solution(144, Arrays.asList(
				new Calcul(Operation.SOMME, 9, 3),
				new Calcul(Operation.SOMME, 10, 2),
				new Calcul(Operation.MULTIPLICATION, 12, 12)));
		System.out.print(solution);
	}

	private final int resultat;
	private final List<Calcul> calculs;

	public Solution(
			int resultat, List<Calcul> calculs) {
		this.resultat = resultat;
		this.calculs = calculs;

	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("resultat : ").append(resultat).append("\n");
		if (calculs != null) {
			for(Calcul calcul : calculs) {
				buffer.append(calcul).append("\n");
			}
		}
		return buffer.toString();
	}

	public List<Calcul> getCalculs() {
		return calculs;
	}

	public int getResultat() {
		return resultat;
	}

}
