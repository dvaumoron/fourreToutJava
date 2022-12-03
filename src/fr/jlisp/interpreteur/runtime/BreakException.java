package fr.jlisp.interpreteur.runtime;

public class BreakException extends Exception {

	private static final long serialVersionUID = 1L;

	private final boolean isBreak;

	public BreakException(boolean isBreak) {
		this.isBreak = isBreak;
	}

	public boolean isBreak() {
		return isBreak;
	}
}