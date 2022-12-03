package fr.jlisp.syntaxique;

import java.util.ArrayList;
import java.util.List;

import fr.jlisp.interpreteur.runtime.builtins.MacroBloc;
import fr.jlisp.lexical.AnalyseurLexical;
import fr.jlisp.lexical.Lexeme;

public class AnalyseurSyntaxique {

	private static final NodeValue BLOC_VALUE = new NodeValue(MacroBloc.BLOC);
	private static final NodeValue FALSE_VALUE = new NodeValue(Boolean.FALSE);
	private static final NodeValue NULL_VALUE = new NodeValue(null);
	private static final NodeValue TRUE_VALUE = new NodeValue(Boolean.TRUE);

	public static void main(final String[] args) {
		AnalyseurLexical analyseur = AnalyseurLexical.createAnalyseur();

		System.out.println(parse(analyseur
				.parse("(def fact (n) (if (= n 0) 1 (* n (fact (- n 1)))))")));
	}

	public static Node parse(final Iterable<Lexeme> lexemes) {

		final List<NodeList> nodes = new ArrayList<>();
		final NodeList node0 = new NodeList();
		nodes.add(node0);
		node0.add(BLOC_VALUE);
		int index = 0;

		for (final Lexeme lexeme : lexemes) {
			final String value = lexeme.getValue();
			final NodeList nodeIndex = nodes.get(index);
			switch (lexeme.getType()) {
			case START_DELIMITER:
				final NodeList nodeList = new NodeList();
				nodeIndex.add(nodeList);
				nodes.add(nodeList);
				index++;
				break;
			case END_DELIMITER:
				nodes.remove(index);
				index--;
				break;
			case IDENTIFIER:
				nodeIndex.add(new NodeIdentifier(value));
				break;
			case NULL_KEYWORD:
				nodeIndex.add(NULL_VALUE);
				break;
			case TRUE_KEYWORD:
				nodeIndex.add(TRUE_VALUE);
				break;
			case FALSE_KEYWORD:
				nodeIndex.add(FALSE_VALUE);
				break;
			case INTEGER_LITERAL:
				nodeIndex.add(new NodeValue(Long.valueOf(value)));
				break;
			case FLOAT_LITERAL:
				nodeIndex.add(new NodeValue(Double.valueOf(value)));
				break;
			case STRING_LITERAL:
				nodeIndex.add(new NodeValue(extractString(value)));
				break;
			case CHAR_LITERAL:
				nodeIndex.add(new NodeValue(extractChar(value)));
				break;
			default:
				break;
			}
		}
		if (index != 0) {
			throw new IllegalStateException("erreur de parenthèse");
		}
		return node0;
	}

	private static String extractString(final String value) {
		final List<Character> newValue = new ArrayList<>();

		final int length = value.length() - 1;
		for (int index = 1; index < length; index++) {
			final char c = value.charAt(index);
			if (c == '\\') {
				final char c2 = value.charAt(++index);
				switch (c2) {
				case 'n':
					newValue.add('\n');
					break;
				case 't':
					newValue.add('\t');
					break;
				case 'b':
					newValue.add('\b');
					break;
				case 'f':
					newValue.add('\f');
					break;
				case 'r':
					newValue.add('\r');
					break;
				case '\\':
					newValue.add('\\');
					break;
				case '\'':
					newValue.add('\'');
					break;
				case '"':
					newValue.add('"');
					break;
				default:
					newValue.add(c);
					newValue.add(c2);
				}
			} else {
				newValue.add(c);
			}
		}

		final int size = newValue.size();
		final char[] newCharArray = new char[size];
		for (int index = 0; index < size; index++) {
			newCharArray[index] = newValue.get(index);
		}
		return new String(newCharArray);
	}

	private static char extractChar(String value) {
		final char c = value.charAt(1);
		if (c == '\\') {
			final char c2 = value.charAt(2);
			switch (c2) {
			case 'n':
				return '\n';
			case 't':
				return '\t';
			case 'b':
				return '\b';
			case 'f':
				return '\f';
			case 'r':
				return '\r';
			case '\\':
				return '\\';
			case '\'':
				return '\'';
			case '"':
				return '"';
			default:
				return c2;
			}
		} else {
			return c;
		}
	}
}