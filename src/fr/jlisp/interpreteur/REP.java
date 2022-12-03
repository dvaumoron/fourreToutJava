package fr.jlisp.interpreteur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.jlisp.interpreteur.runtime.Environement;
import fr.jlisp.interpreteur.runtime.EnvironementLocal;
import fr.jlisp.interpreteur.runtime.Util;

public class REP {

	public static void main(final String[] args) {

		try (final BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in))) {
			final Interpreteur interpreteur = new Interpreteur();
			final Environement env = new EnvironementLocal(
					interpreteur.getBuiltins());
			final String moduleName = "_input_";
			final String continueREP = "continueREP";
			env.set(Util.MODULE_NAME, moduleName);
			interpreteur.eval(
					"(defMacro quit () (quote (set continueREP false)))", env);
			env.set(continueREP, Boolean.TRUE);

			// (quit) permet de quitter
			while ((Boolean) env.get(continueREP)) {
				final String phrase = readInput(reader);
				try {
					final Object res = interpreteur.eval(phrase, env);
					env.set("_", res);
					if (res != null) {
						System.out.println(res);
					}
				} catch (final Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

	private static String readInput(final BufferedReader reader)
			throws IOException {
		System.out.print(">>> ");
		String line = reader.readLine();
		final StringBuilder buffer = new StringBuilder();
		int indentation = Util.countIndentation(line);
		boolean multiple = false;
		while (indentation > 0) {
			multiple = true;
			buffer.append(line);
			buffer.append('\n');
			System.out.print("... ");
			line = reader.readLine();
			indentation += Util.countIndentation(line);
		}
		if (multiple) {
			buffer.append(line);
			return buffer.toString();
		} else {
			return line;
		}
	}
}