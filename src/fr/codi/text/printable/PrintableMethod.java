package fr.codi.text.printable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Iterator;

import fr.codi.text.Indenter;
import fr.codi.text.Rope;
import fr.codi.text.StringPool;

public class PrintableMethod extends PrintableCallable {

	private static final PrintableMethod[] objectFinalMethods;
	private static final PrintableMethod[] objectNotStaticMethods;

	static {
		objectFinalMethods = new PrintableMethod[6];
		PrintableMethod pm = new PrintableMethod(StringPool.VOID, "wait");
		objectFinalMethods[0] = pm;
		pm = new PrintableMethod(StringPool.VOID, "wait");
		PrintableParameter p = new PrintableParameter(StringPool.LONG, "arg0");
		pm.addParameter(p);
		pm.addParameter(new PrintableParameter(StringPool.INT, "arg1"));
		objectFinalMethods[1] = pm;
		pm = new PrintableMethod(StringPool.VOID, "wait");
		pm.addParameter(p);
		objectFinalMethods[2] = pm;
		pm = new PrintableMethod("Class", "getClass");
		objectFinalMethods[3] = pm;
		pm = new PrintableMethod(StringPool.VOID, "notify");
		objectFinalMethods[4] = pm;
		pm = new PrintableMethod(StringPool.VOID, "notifyAll");
		objectFinalMethods[5] = pm;

		objectNotStaticMethods = new PrintableMethod[2];
		pm = new PrintableMethod(StringPool.BOOLEAN, "equals");
		pm.addParameter(new PrintableParameter(StringPool.OBJECT, "arg0"));
		objectNotStaticMethods[0] = pm;
		pm = new PrintableMethod(StringPool.INT, "hashCode");
		objectNotStaticMethods[1] = pm;
	}

	public static boolean isNotObjectFinalMethod(final PrintableMethod pm) {
		for (final PrintableMethod objectFinalMethod : objectFinalMethods) {
			if (objectFinalMethod.sameSignature(pm)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotObjectNotStaticMethod(final PrintableMethod pm) {
		for (final PrintableMethod objectNotStaticMethod : objectNotStaticMethods) {
			if (objectNotStaticMethod.sameSignature(pm)) {
				return false;
			}
		}
		return true;
	}

	protected final String returnType;
	protected final String name;

	public PrintableMethod(final String returnType, final String name) {
		this.returnType = returnType;
		this.name = name;
	}

	public PrintableMethod(final Method method, final PrintableContainer pc) {
		this.modifiers = method.getModifiers();
		this.removeModifier(Modifier.NATIVE);
		final Class<?> returnType = method.getReturnType();
		this.returnType = returnType.getSimpleName();
		this.name = method.getName();
		pc.addImport(returnType.getCanonicalName());
		for (final Parameter p : method.getParameters()) {
			this.addParameter(new PrintableParameter(p, pc));
		}
		for (final Class<?> c : method.getExceptionTypes()) {
			this.addException(c.getSimpleName());
			pc.addImport(c.getCanonicalName());
		}
	}

	public String getReturnType() {
		return this.returnType;
	}

	public String getName() {
		return name;
	}

	public boolean sameSignature(final PrintableMethod pm) {
		if (!pm.name.equals(this.name)) {
			return false;
		}
		if (pm.parameters == null) {
			if (this.parameters == null) {
				return true;
			} else {
				return false;
			}
		}
		if (this.parameters == null) {
			return false;
		}
		if (pm.parameters.size() == this.parameters.size()) {
			for (int i = 0; i < pm.parameters.size(); i++) {
				if (!pm.parameters.get(i).getType()
						.equals(this.parameters.get(i).getType())) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public void convertToInterfaceMethod() {
		this.addModifier(Modifier.PUBLIC);
		this.addModifier(Modifier.ABSTRACT);
		if (this.instructions != null) {
			this.instructions.clear();
		}
	}

	public Rope toCallRope() {
		if (this.parameters == null) {
			final Rope buffer = new Rope(2);
			buffer.append(this.name);
			buffer.append("()");
			return buffer;
		} else {
			final Rope buffer = new Rope(2 * this.parameters.size() + 3);
			buffer.append(this.name);
			buffer.append("(");
			if (!this.parameters.isEmpty()) {
				final Iterator<PrintableParameter> itParameter = this.parameters
						.iterator();
				buffer.append(itParameter.next().getName());
				while (itParameter.hasNext()) {
					buffer.append(", ");
					buffer.append(itParameter.next().getName());
				}
			}
			buffer.append(")");
			return buffer;
		}
	}

	@Override
	public Rope toRope(final Indenter indenter) {
		final Rope buffer = super.toRope(indenter);
		this.printGenericTypeTo(buffer);
		buffer.append(this.returnType);
		buffer.append(" ");
		buffer.append(this.name);
		this.printArgsAndExceptionsTo(buffer);
		if (Modifier.isAbstract(this.modifiers)) {
			buffer.append(";\n");
		} else {
			this.printInstructionsTo(buffer, indenter);
		}
		return buffer;
	}
}
