package fr.codi.text.printable;

import java.lang.reflect.Parameter;

import fr.codi.text.Indenter;
import fr.codi.text.Rope;

public class PrintableParameter extends Printable {

	protected final String type;
	protected final String name;
	
	public PrintableParameter(final String type, final String name) {
		this.type = type;
		this.name = name;
	}

	public PrintableParameter(final Parameter parameter, final PrintableContainer pc) {
		this.modifiers = parameter.getModifiers();
		Class<?> c = parameter.getType();
		this.type = c.getSimpleName();
		this.name = parameter.getName();
		pc.addImport(c.getCanonicalName());
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	@Override
	public Rope toRope(Indenter indenter) {
		final Rope buffer = super.toRope(indenter);
		buffer.append(this.type);
		buffer.append(" ");
		buffer.append(this.name);
		return buffer;
	}
	
	@Override
	public Rope toRope() {
		final Rope buffer = super.toRope();
		buffer.append(this.type);
		buffer.append(" ");
		buffer.append(this.name);
		return buffer;
	}
}
