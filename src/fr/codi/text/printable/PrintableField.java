package fr.codi.text.printable;

import java.lang.reflect.Field;

import fr.codi.text.Indenter;
import fr.codi.text.Rope;

public class PrintableField extends PrintableParameter {

	protected String initializer = null;

	public PrintableField(final String type, final String name) {
		super(type, name);
	}

	public PrintableField(final Field field, final PrintableContainer pc) {
		super(field.getType().getSimpleName(), field.getName());
		this.modifiers = field.getModifiers();
		pc.addImport(field.getType().getCanonicalName());
	}

	public void setInitializer(final String initializer) {
		this.initializer = initializer;
	}

	@Override
	public Rope toRope(Indenter indenter) {
		final Rope buffer = super.toRope(indenter);
		if (!(this.initializer == null || this.initializer.isEmpty())) {
			buffer.append(" = ");
			buffer.append(this.initializer);
		}
		buffer.append(";\n");
		return buffer;
	}
}
