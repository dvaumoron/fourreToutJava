package fr.codi.text.printable;

import fr.codi.text.Indenter;
import fr.codi.text.Rope;

public class PrintableConstructor extends PrintableCallable {

	protected final PrintableClass pc;

	public PrintableConstructor(final PrintableClass pc) {
		this.pc = pc;
	}

	@Override
	public Rope toRope(final Indenter indenter) {
		final Rope buffer = super.toRope(indenter);
		buffer.append(this.pc.name);
		this.printArgsAndExceptionsTo(buffer);
		this.printInstructionsTo(buffer, indenter);
		return buffer;
	}
}
