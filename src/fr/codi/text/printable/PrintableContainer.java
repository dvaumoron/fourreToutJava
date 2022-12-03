package fr.codi.text.printable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import fr.codi.text.Indenter;
import fr.codi.text.Rope;
import fr.codi.text.StringPool;

public class PrintableContainer extends Printable {

	protected Set<String> imports = null;
	protected final String name;
	protected List<PrintableMethod> methods = null;

	public PrintableContainer(final String name) {
		this.name = name;
	}

	public void addImport(final String className) {
		if (!(StringPool.VOID.equals(className)
				|| StringPool.CHAR.equals(className)
				|| StringPool.INT.equals(className)
				|| StringPool.LONG.equals(className)
				|| StringPool.FLOAT.equals(className)
				|| StringPool.DOUBLE.equals(className)
				|| StringPool.BOOLEAN.equals(className)
				|| className.contains("[]") || (className
				.startsWith("java.lang.") && !className.substring(10).contains(
				".")))) {
			if (this.imports == null) {
				this.imports = new TreeSet<>();
			}
			this.imports.add(className);
		}
	}

	public void addMethod(final PrintableMethod method) {
		if (PrintableMethod.isNotObjectFinalMethod(method)) {
			if (this.methods == null) {
				this.methods = new ArrayList<>();
			}
			this.methods.add(method);
		}
	}

	protected void printMethodsTo(final Rope buffer, final Indenter indenter) {
		if (this.methods != null) {
			for (final PrintableMethod method : this.methods) {
				buffer.append(method.toRope(indenter));
			}
		}
	}

	@Override
	public Rope toRope(final Indenter indenter) {
		final Rope buffer = new Rope();
		if (this.imports != null) {
			for (final String className : this.imports) {
				buffer.append("import ");
				buffer.append(className);
				buffer.append(";\n");
			}
		}
		buffer.append(super.toRope(indenter));
		return buffer;
	}

	@Override
	public String toString() {
		return this.toRope(new Indenter()).toString();
	}

}
