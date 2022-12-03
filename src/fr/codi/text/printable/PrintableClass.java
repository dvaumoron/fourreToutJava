package fr.codi.text.printable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import fr.codi.text.Indenter;
import fr.codi.text.Rope;

public class PrintableClass extends PrintableContainer {

	protected String extendedClass = null;
	protected Set<String> implementedInterfaces = null;
	protected List<PrintableField> fields = null;
	protected List<PrintableConstructor> constructors = null;

	public PrintableClass(final String name) {
		super(name);
	}

	public void setExtendedClass(final String extendedClass) {
		this.extendedClass = extendedClass;
	}

	public void addImplementedInterface(final String implementedInterface) {
		if (this.implementedInterfaces == null) {
			this.implementedInterfaces = new LinkedHashSet<>();
		}
		this.implementedInterfaces.add(implementedInterface);
	}

	public void addField(final PrintableField field) {
		if (this.fields == null) {
			this.fields = new ArrayList<>();
		}
		this.fields.add(field);
	}

	public void addConstructor(final PrintableConstructor constructor) {
		if (this.constructors == null) {
			this.constructors = new ArrayList<>();
		}
		this.constructors.add(constructor);
	}

	@Override
	public Rope toRope(Indenter indenter) {
		final Rope buffer = super.toRope(indenter);
		buffer.append("class ");
		buffer.append(this.name);
		this.printGenericTypeTo(buffer);
		if (this.extendedClass != null && !this.extendedClass.isEmpty()) {
			buffer.append(" extends ");
			buffer.append(this.extendedClass);
		}
		if (!(this.implementedInterfaces == null || this.implementedInterfaces
				.isEmpty())) {
			buffer.append(" implements ");
			final Iterator<String> itInterface = this.implementedInterfaces
					.iterator();
			buffer.append(itInterface.next());
			while (itInterface.hasNext()) {
				buffer.append(", ");
				buffer.append(itInterface.next());
			}
		}
		buffer.append(" {\n");
		indenter.increment();
		if (this.fields != null) {
			for (final PrintableField field : this.fields) {
				buffer.append(field.toRope(indenter));
			}
		}
		if (this.constructors != null) {
			for (final PrintableConstructor constructor : this.constructors) {
				buffer.append(constructor.toRope(indenter));
			}
		}
		this.printMethodsTo(buffer, indenter);
		indenter.decrement();
		indenter.printIndentation(buffer);
		buffer.append("}\n");
		return buffer;
	}
}
