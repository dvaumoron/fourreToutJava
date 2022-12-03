package fr.codi.text.printable;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import fr.codi.text.Indenter;
import fr.codi.text.Rope;

public class PrintableInterface extends PrintableContainer {

	protected Set<String> extendedInterfaces = null;

	public PrintableInterface(final String name) {
		super(name);
	}

	public void addExtendedInterface(final String extendedInterface) {
		if (this.extendedInterfaces == null) {
			this.extendedInterfaces = new LinkedHashSet<>();
		}
		this.extendedInterfaces.add(extendedInterface);
	}

	@Override
	public void addMethod(final PrintableMethod method) {
		method.convertToInterfaceMethod();
		super.addMethod(method);
	}

	@Override
	public Rope toRope(Indenter indenter) {
		final Rope buffer = super.toRope(indenter);
		buffer.append("interface ");
		buffer.append(this.name);
		this.printGenericTypeTo(buffer);
		if (!(this.extendedInterfaces == null || this.extendedInterfaces.isEmpty())) {
			buffer.append(" extends ");
			final Iterator<String> itInterface = this.extendedInterfaces.iterator();
			buffer.append(itInterface.next());
			while (itInterface.hasNext()) {
				buffer.append(", ");
				buffer.append(itInterface.next());
			}
		}
		buffer.append(" {\n");
		indenter.increment();
		this.printMethodsTo(buffer, indenter);
		indenter.decrement();
		indenter.printIndentation(buffer);
		buffer.append("}\n");
		return buffer;
	}
}
