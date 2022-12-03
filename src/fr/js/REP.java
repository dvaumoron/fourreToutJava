package fr.js;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class REP {

	public static void main(final String[] args) {

		final ScriptEngineManager sem = new ScriptEngineManager();
		final ScriptEngine scriptEngine = sem.getEngineByExtension("js");

		try (final InputStreamReader isr = new InputStreamReader(System.in);
				final BufferedReader reader = new BufferedReader(isr);) {

			while (true) { // quit() permet de quitter
				final String line = readInput(reader);
				try {
					final Object res = scriptEngine.eval(line);
					if (res != null) {
						System.out.println(res);
					}
				} catch (ScriptException se) {
					se.printStackTrace();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static String readInput(final BufferedReader reader)
			throws IOException {
		System.out.print(">>> ");
		String line = reader.readLine();
		final StringBuilder buffer = new StringBuilder();
		int indentation = countIndentation(line);
		boolean multiple = false;
		while (indentation > 0) {
			multiple = true;
			buffer.append(line);
			System.out.print("... ");
			line = reader.readLine();
			indentation += countIndentation(line);
		}
		if (multiple) {
			buffer.append(line);
			return buffer.toString();
		} else {
			return line;
		}
	}

	private static int countIndentation(final String line) {
		int res = 0;
		for (final char c : line.toCharArray()) {
			if (c == '{') {
				res++;
			} else if (c == '}') {
				res--;
			}
		}
		return res;
	}
}