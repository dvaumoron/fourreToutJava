package fr.codi.text.printable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import fr.codi.text.Indenter;
import fr.codi.text.Rope;

public class PrintableCallable extends Printable {

	protected List<PrintableParameter> parameters = null;
	protected Set<String> exceptions = null;
	protected List<Object> instructions = null;

	public void addParameter(final PrintableParameter parameter) {
		if (this.parameters == null) {
			this.parameters = new ArrayList<>();
		}
		this.parameters.add(parameter);
	}

	public void addException(final String exception) {
		if (this.exceptions == null) {
			this.exceptions = new LinkedHashSet<>();
		}
		this.exceptions.add(exception);
	}

	private void initInstructions() {
		if (this.instructions == null) {
			this.instructions = new ArrayList<>();
		}
	}

	public void addInstruction(final String instruction) {
		this.initInstructions();
		this.instructions.add(instruction);
	}

	public void addInstruction(final Rope instruction) {
		this.initInstructions();
		this.instructions.add(instruction);
	}

	protected void printArgsAndExceptionsTo(final Rope buffer) {
		buffer.append("(");
		if (!(this.parameters == null || this.parameters.isEmpty())) {
			final Iterator<PrintableParameter> itParameter = this.parameters
					.iterator();
			buffer.append(itParameter.next().toRope());
			while (itParameter.hasNext()) {
				buffer.append(", ");
				buffer.append(itParameter.next().toRope());
			}
		}
		buffer.append(")");
		if (!(this.exceptions == null || this.exceptions.isEmpty())) {
			buffer.append(" throws ");
			final Iterator<String> itException = this.exceptions.iterator();
			buffer.append(itException.next());
			while (itException.hasNext()) {
				buffer.append(", ");
				buffer.append(itException.next());
			}
		}
	}

	protected void printInstructionsTo(final Rope buffer,
			final Indenter indenter) {
		buffer.append(" {\n");
		indenter.increment();
		if (this.instructions != null) {
			for (final Object instruction : this.instructions) {
				indenter.printIndentation(buffer);
				if (instruction instanceof Rope) {
					Rope instructionRope = (Rope) instruction;
					buffer.append(instructionRope);
				} else {
					String instructionString = (String) instruction;
					buffer.append(instructionString);
				}
				buffer.append("\n");
			}
		}
		indenter.decrement();
		indenter.printIndentation(buffer);
		buffer.append("}\n");
	}
}
