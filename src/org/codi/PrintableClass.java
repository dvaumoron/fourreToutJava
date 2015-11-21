package org.codi;

import java.util.ArrayList;
import java.util.List;

public class PrintableClass {

	private List<String> annotations= new ArrayList<>();
	private boolean isPublic;
	private boolean isAbstract;
	private String name;
	private String parentClass = null;
	private List<String> implementedInterfaces = new ArrayList<>();
	private List<PrintableField> fields = new ArrayList<>();
	private List<PrintableMethod> methods = new ArrayList<>();

	public PrintableClass(boolean isPublic, boolean isAbstract, String name) {
		this.isPublic = isPublic;
		this.isAbstract = isAbstract;
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		for (String annotation : annotations) {
			buffer.append("@").append(annotation).append("\n");
		}
		if (isPublic) {
			buffer.append("public ");
		}
		if (isAbstract) {
			buffer.append("abstract ");
		}
		buffer.append("class ").append(name);
		if (parentClass != null) {
			buffer.append(" extends ").append(parentClass);
		}
		if (!implementedInterfaces.isEmpty()) {
			buffer.append(" implements ");
			boolean first = true;
			for (String implementedInterface : implementedInterfaces) {
				if (first) {
					first = false;
				} else {
					buffer.append(", ");
				}
				buffer.append(implementedInterface);
			}
		}
		buffer.append(" {\n");

		buffer.append("}");
		return buffer.toString();
	}

	public boolean addAnnotation(String e) {
		return annotations.add(e);
	}

	public void setParentClass(String parentClass) {
		this.parentClass = parentClass;
	}

	public boolean addImplementedInterface(String e) {
		return implementedInterfaces.add(e);
	}

	public boolean addField(PrintableField e) {
		return fields.add(e);
	}

	public boolean addMethod(PrintableMethod e) {
		return methods.add(e);
	}
}