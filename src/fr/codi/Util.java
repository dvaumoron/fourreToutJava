package fr.codi;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.codi.text.Indenter;
import fr.codi.text.Rope;
import fr.codi.text.StringPool;
import fr.codi.text.printable.PrintableClass;
import fr.codi.text.printable.PrintableConstructor;
import fr.codi.text.printable.PrintableContainer;
import fr.codi.text.printable.PrintableField;
import fr.codi.text.printable.PrintableInterface;
import fr.codi.text.printable.PrintableMethod;
import fr.codi.text.printable.PrintableParameter;

public enum Util {

	INSTANCE;

	public String zeroValue(final String type) {
		final String res;
		if (StringPool.BOOLEAN.equals(type)) {
			res = "false";
		} else if (StringPool.CHAR.equals(type) || StringPool.BYTE.equals(type)
				|| StringPool.SHORT.equals(type) || StringPool.INT.equals(type)
				|| StringPool.LONG.equals(type)
				|| StringPool.FLOAT.equals(type)
				|| StringPool.DOUBLE.equals(type)) {
			res = "0";
		} else {
			res = "null";
		}
		return res;
	}

	public boolean isBasicType(final String type) {
		return StringPool.BOOLEAN.equals(type) || StringPool.CHAR.equals(type)
				|| StringPool.BYTE.equals(type)
				|| StringPool.SHORT.equals(type) || StringPool.INT.equals(type)
				|| StringPool.LONG.equals(type)
				|| StringPool.FLOAT.equals(type)
				|| StringPool.DOUBLE.equals(type);
	}

	public String extractInterface(final String name, final String fullClassName)
			throws ClassNotFoundException {
		final Class<?> c = Class.forName(fullClassName);
		final PrintableInterface pi = new PrintableInterface(name);
		pi.addModifier(Modifier.PUBLIC);
		for (final Method method : c.getMethods()) {
			final int modifiers = method.getModifiers();
			if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
				pi.addMethod(new PrintableMethod(method, pi));
			}
		}
		return pi.toString();
	}

	public String decorator(final String name, final String fullClassName)
			throws ClassNotFoundException {
		final Class<?> i = Class.forName(fullClassName);
		if (!i.isInterface()) {
			throw new IllegalArgumentException();
		}
		final String className = i.getSimpleName();
		final PrintableClass pc = new PrintableClass(name);
		pc.addImport(fullClassName);
		pc.addModifier(Modifier.PUBLIC);
		pc.addImplementedInterface(className);
		final PrintableField field = new PrintableField(className, "inner");
		field.addModifier(Modifier.PRIVATE);
		field.addModifier(Modifier.FINAL);
		pc.addField(field);
		final PrintableConstructor constructor = new PrintableConstructor(pc);
		constructor.addModifier(Modifier.PUBLIC);
		final PrintableParameter parameter = new PrintableParameter(className,
				"inner");
		parameter.addModifier(Modifier.FINAL);
		constructor.addParameter(parameter);
		constructor.addInstruction("this.inner = inner;");
		pc.addConstructor(constructor);
		for (final Method method : i.getMethods()) {
			pc.addMethod(createDelegateMethod(pc, method, "inner", true));
		}
		return pc.toString();
	}

	public String revealAccessDecorator(final String name,
			final String fullClassName) throws ClassNotFoundException {
		final Class<?> i = Class.forName(fullClassName);
		if (!i.isInterface()) {
			throw new IllegalArgumentException();
		}
		final String className = i.getSimpleName();
		final PrintableClass pc = new PrintableClass(name);
		pc.addImport(fullClassName);
		pc.addImport("java.io.PrintStream");
		pc.addModifier(Modifier.PUBLIC);
		pc.addImplementedInterface(className);
		final String printClassName = "PrintStream";
		PrintableField field = new PrintableField(printClassName, "out");
		field.addModifier(Modifier.PRIVATE);
		field.addModifier(Modifier.FINAL);
		pc.addField(field);
		field = new PrintableField(className, "inner");
		field.addModifier(Modifier.PRIVATE);
		field.addModifier(Modifier.FINAL);
		pc.addField(field);
		final PrintableConstructor constructor = new PrintableConstructor(pc);
		constructor.addModifier(Modifier.PUBLIC);
		PrintableParameter parameter = new PrintableParameter(printClassName,
				"out");
		parameter.addModifier(Modifier.FINAL);
		constructor.addParameter(parameter);
		parameter = new PrintableParameter(className, "inner");
		parameter.addModifier(Modifier.FINAL);
		constructor.addParameter(parameter);
		constructor.addInstruction("this.out = out;");
		constructor.addInstruction("this.inner = inner;");
		pc.addConstructor(constructor);
		for (final Method method : i.getMethods()) {
			final PrintableMethod pm = new PrintableMethod(method, pc);
			pm.addAnnotation(StringPool.OVERRIDE);
			pm.removeModifier(Modifier.ABSTRACT);
			Rope instructionBuffer = new Rope(3);
			instructionBuffer.append("this.out.println(\"enter : ");
			instructionBuffer.append(pm.getName());
			instructionBuffer.append("\");");
			pm.addInstruction(instructionBuffer);
			instructionBuffer = new Rope(5);
			final String returnType = pm.getReturnType();
			final boolean hasReturn = !StringPool.VOID.equals(returnType);
			if (hasReturn) {
				instructionBuffer.append("final ");
				instructionBuffer.append(returnType);
				instructionBuffer.append(" res = this.inner.");
			} else {
				instructionBuffer.append("this.inner.");
			}
			instructionBuffer.append(pm.toCallRope());
			instructionBuffer.append(";");
			pm.addInstruction(instructionBuffer);
			instructionBuffer = new Rope(3);
			instructionBuffer.append("this.out.println(\"exit : ");
			instructionBuffer.append(pm.getName());
			instructionBuffer.append("\");");
			pm.addInstruction(instructionBuffer);
			if (hasReturn) {
				pm.addInstruction("return res;");
			}
			pc.addMethod(pm);
		}
		return pc.toString();
	}

	public String composite(final String name, final String fullClassName)
			throws ClassNotFoundException {
		final Class<?> i = Class.forName(fullClassName);
		if (!i.isInterface()) {
			throw new IllegalArgumentException();
		}
		final String className = i.getSimpleName();
		final PrintableClass pc = new PrintableClass(name);
		pc.addImport(fullClassName);
		pc.addImport("java.util.ArrayList");
		pc.addImport("java.util.List");
		pc.addModifier(Modifier.PUBLIC);
		pc.addImplementedInterface(className);
		final String listType = new Rope(3).append("List<").append(className)
				.append(">").toString();
		final PrintableField field = new PrintableField(listType, "items");
		field.addModifier(Modifier.PRIVATE);
		field.setInitializer("new ArrayList<>();");
		pc.addField(field);
		PrintableMethod pm = new PrintableMethod(StringPool.BOOLEAN, "addItem");
		pm.addModifier(Modifier.PUBLIC);
		final PrintableParameter parameter = new PrintableParameter(className,
				"item");
		parameter.addModifier(Modifier.FINAL);
		pm.addParameter(parameter);
		pm.addInstruction("return this.items.add(item);");
		pc.addMethod(pm);
		for (final Method method : i.getMethods()) {
			pm = new PrintableMethod(method, pc);
			pm.addAnnotation(StringPool.OVERRIDE);
			pm.removeModifier(Modifier.ABSTRACT);
			Rope instructionBuffer = new Rope(4);
			final String returnType = pm.getReturnType();
			final boolean hasReturn = !StringPool.VOID.equals(returnType);
			if (hasReturn) {
				instructionBuffer.append(returnType);
				instructionBuffer.append(" res = ");
				instructionBuffer.append(zeroValue(returnType));
				instructionBuffer.append(";");
				pm.addInstruction(instructionBuffer);
				instructionBuffer = new Rope(3);
			}
			instructionBuffer.append("for (");
			instructionBuffer.append(className);
			instructionBuffer.append(" item : this.items) {");
			pm.addInstruction(instructionBuffer);
			instructionBuffer = new Rope(3);
			if (hasReturn) {
				instructionBuffer.append("\tres = item.");
			} else {
				instructionBuffer.append("\titem.");
			}
			instructionBuffer.append(pm.toCallRope());
			instructionBuffer.append(";");
			pm.addInstruction(instructionBuffer);
			pm.addInstruction("}");
			if (hasReturn) {
				pm.addInstruction("return res;");
			}
			pc.addMethod(pm);
		}
		return pc.toString();
	}

	public String adapter(final String name, final String fullClassName,
			final String fullInterfaceClassName) throws ClassNotFoundException {
		final Class<?> c = Class.forName(fullClassName);
		final Class<?> i = Class.forName(fullInterfaceClassName);
		if (!i.isInterface()) {
			throw new IllegalArgumentException();
		}
		final String className = c.getSimpleName();
		final String interfaceClassName = i.getSimpleName();
		final PrintableClass pc = new PrintableClass(name);
		pc.addImport(fullClassName);
		pc.addImport(fullInterfaceClassName);
		pc.addModifier(Modifier.PUBLIC);
		pc.addImplementedInterface(interfaceClassName);
		final PrintableField field = new PrintableField(className, "inner");
		field.addModifier(Modifier.PRIVATE);
		pc.addField(field);
		final PrintableConstructor constructor = new PrintableConstructor(pc);
		constructor.addModifier(Modifier.PUBLIC);
		final PrintableParameter parameter = new PrintableParameter(className,
				"inner");
		parameter.addModifier(Modifier.FINAL);
		constructor.addParameter(parameter);
		constructor.addInstruction("this.inner = inner;");
		pc.addConstructor(constructor);
		for (final Method method : i.getMethods()) {
			pc.addMethod(createDelegateMethod(pc, method, "inner", true));
		}
		return pc.toString();
	}

	public String synchronizedAdapter(final String name,
			final String fullClassName) throws ClassNotFoundException {
		final Class<?> c = Class.forName(fullClassName);
		final String className = c.getSimpleName();
		final PrintableClass pc = new PrintableClass(name);
		pc.addImport(fullClassName);
		pc.addModifier(Modifier.PUBLIC);
		final PrintableField field = new PrintableField(className, "inner");
		field.addModifier(Modifier.PRIVATE);
		pc.addField(field);
		final PrintableConstructor constructor = new PrintableConstructor(pc);
		constructor.addModifier(Modifier.PUBLIC);
		final PrintableParameter parameter = new PrintableParameter(className,
				"inner");
		parameter.addModifier(Modifier.FINAL);
		constructor.addParameter(parameter);
		constructor.addInstruction("this.inner = inner;");
		pc.addConstructor(constructor);
		for (final Method method : c.getMethods()) {
			final int modifiers = method.getModifiers();
			if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
				final PrintableMethod pm = createDelegateMethod(pc, method,
						"inner", false);
				pm.addModifier(Modifier.SYNCHRONIZED);
				pc.addMethod(pm);
			}
		}
		return pc.toString();
	}

	public String staticCaller(final String name, final String fullClassName)
			throws ClassNotFoundException {
		final Class<?> c = Class.forName(fullClassName);
		final String className = c.getSimpleName();
		final PrintableClass pc = new PrintableClass(name);
		pc.addImport(fullClassName);
		pc.addModifier(Modifier.PUBLIC);
		pc.addModifier(Modifier.ABSTRACT);
		final PrintableField field = new PrintableField(className, "_instance");
		field.addModifier(Modifier.PRIVATE);
		field.addModifier(Modifier.STATIC);
		pc.addField(field);
		PrintableMethod pm = new PrintableMethod(className, "getInstance");
		pm.addModifier(Modifier.PUBLIC);
		pm.addModifier(Modifier.STATIC);
		pm.addInstruction("return _instance;");
		pc.addMethod(pm);
		pm = new PrintableMethod(StringPool.VOID, "setInstance");
		pm.addModifier(Modifier.PUBLIC);
		pm.addModifier(Modifier.STATIC);
		final PrintableParameter parameter = new PrintableParameter(className,
				"instance");
		parameter.addModifier(Modifier.FINAL);
		pm.addParameter(parameter);
		pm.addInstruction("_instance = instance;");
		pc.addMethod(pm);
		for (final Method method : c.getMethods()) {
			final int modifiers = method.getModifiers();
			if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
				pm = new PrintableMethod(method, pc);
				if (PrintableMethod.isNotObjectNotStaticMethod(pm)) {
					pm.removeModifier(Modifier.ABSTRACT);
					pm.addModifier(Modifier.STATIC);
					final Rope instructionBuffer = new Rope(3);
					if (StringPool.VOID.equals(pm.getReturnType())) {
						instructionBuffer.append("_instance.");
					} else {
						instructionBuffer.append("return _instance.");
					}
					instructionBuffer.append(pm.toCallRope());
					instructionBuffer.append(";");
					pm.addInstruction(instructionBuffer);
					pc.addMethod(pm);
				}
			}
		}
		return pc.toString();
	}

	public String mock(final String name, final String fullClassName)
			throws ClassNotFoundException {
		return mock(name, fullClassName, null);
	}

	public String mock(final String name, final String fullClassName,
			Map<String, Object> mockInit) throws ClassNotFoundException {
		final Class<?> i = Class.forName(fullClassName);
		if (!i.isInterface()) {
			throw new IllegalArgumentException();
		}
		final String className = i.getSimpleName();
		final PrintableClass pc = new PrintableClass(name);
		pc.addImport(fullClassName);
		pc.addImport("java.util.LinkedHashMap");
		pc.addImport("java.util.Map");
		pc.addModifier(Modifier.PUBLIC);
		pc.addImplementedInterface(className);
		final String callsType = "Map<String, Integer>";
		PrintableField field = new PrintableField(callsType, "calls");
		field.addModifier(Modifier.PRIVATE);
		field.addModifier(Modifier.FINAL);
		field.setInitializer("new LinkedHashMap<>()");
		pc.addField(field);
		if (mockInit != null) {
			for (Entry<String, Object> entry : mockInit.entrySet()) {
				final Object value = entry.getValue();
				if (value instanceof List) {
					List<String> list = (List<String>) value;
					pc.addImport("java.util.Iterator");
					pc.addImport("java.util.Arrays");
					field = new PrintableField("Iterator", entry.getKey()
							.concat("Values"));
					field.addModifier(Modifier.PRIVATE);
					field.addModifier(Modifier.FINAL);
					final Rope initializerBuffer = new Rope(list.size() * 2 + 1);
					initializerBuffer.append("Arrays.asList(");
					final Iterator<String> iterator = list.iterator();
					initializerBuffer.append(iterator.next());
					while (iterator.hasNext()) {
						initializerBuffer.append(", ");
						initializerBuffer.append(iterator.next());
					}
					initializerBuffer.append(").iterator()");
					field.setInitializer(initializerBuffer.toString());
					pc.addField(field);

				}
			}
		}
		PrintableMethod pm = new PrintableMethod(StringPool.VOID, "call");
		pm.addModifier(Modifier.PRIVATE);
		final PrintableParameter parameter = new PrintableParameter(
				StringPool.STRING, "name");
		parameter.addModifier(Modifier.FINAL);
		pm.addParameter(parameter);
		pm.addInstruction("if (this.calls.containsKey(name)) {");
		pm.addInstruction("\tthis.calls.put(name, this.calls.get(name) + 1);");
		pm.addInstruction("} else {");
		pm.addInstruction("\tthis.calls.put(name, 1);");
		pm.addInstruction("}");
		pc.addMethod(pm);
		pm = new PrintableMethod(callsType, "getCalls");
		pm.addModifier(Modifier.PUBLIC);
		pm.addInstruction("return this.calls;");
		pc.addMethod(pm);
		for (final Method method : i.getMethods()) {
			pm = new PrintableMethod(method, pc);
			pm.addAnnotation(StringPool.OVERRIDE);
			pm.removeModifier(Modifier.ABSTRACT);
			Rope instructionBuffer = new Rope(3);
			instructionBuffer.append("this.call(\"");
			final String methodName = pm.getName();
			instructionBuffer.append(methodName);
			instructionBuffer.append("\");");
			pm.addInstruction(instructionBuffer);
			final String returnType = pm.getReturnType();
			final boolean hasReturn = !StringPool.VOID.equals(returnType);
			if (mockInit != null && mockInit.containsKey(methodName)) {
				final Object object = mockInit.get(methodName);
				if (object instanceof Throw) {
					final Throw t = (Throw) object;
					instructionBuffer = new Rope(3);
					instructionBuffer.append("throw new ");
					final String exceptionName = extractClassName(
							t.getExceptionName(), pc);
					instructionBuffer.append(exceptionName);
					instructionBuffer.append("();");
					pm.addInstruction(instructionBuffer);
				} else if (hasReturn) {
					instructionBuffer = new Rope(6);
					instructionBuffer.append("return ");
					if (object instanceof String) {
						instructionBuffer.append((String) object);
						instructionBuffer.append(";");
					} else {
						instructionBuffer.append("(");
						instructionBuffer.append(returnType);
						instructionBuffer.append(") this.");
						instructionBuffer.append(methodName);
						instructionBuffer.append("Values.next();");
					}
					pm.addInstruction(instructionBuffer);
				}
			} else if (hasReturn) {
				instructionBuffer = new Rope(3);
				instructionBuffer.append("return ");
				instructionBuffer.append(zeroValue(returnType));
				instructionBuffer.append(";");
				pm.addInstruction(instructionBuffer);
			}
			pc.addMethod(pm);
		}
		return pc.toString();
	}

	public Map<String, Object> map() {
		return new LinkedHashMap<>();
	}

	public List<String> list(String... strings) {
		return Arrays.asList(strings);
	}

	public Throw throwException(String exceptionName) {
		return new Throw(exceptionName);
	}

	public String pojo(final String name, final String... typeAndNames) {
		final PrintableClass pc = new PrintableClass(name);
		pc.addModifier(Modifier.PUBLIC);
		PrintableConstructor constructor = new PrintableConstructor(pc);
		constructor.addModifier(Modifier.PUBLIC);
		pc.addConstructor(constructor);
		constructor = new PrintableConstructor(pc);
		constructor.addModifier(Modifier.PUBLIC);
		final PrintableMethod toStringMethod = new PrintableMethod(
				StringPool.STRING, StringPool.TO_STRING);
		toStringMethod.addAnnotation(StringPool.OVERRIDE);
		toStringMethod.addModifier(Modifier.PUBLIC);
		toStringMethod
				.addInstruction("final StringBuilder buffer = new StringBuilder();");
		final PrintableMethod equalsMethod = new PrintableMethod(
				StringPool.BOOLEAN, StringPool.EQUALS);
		equalsMethod.addAnnotation(StringPool.OVERRIDE);
		equalsMethod.addModifier(Modifier.PUBLIC);
		final PrintableParameter objectParameter = new PrintableParameter(
				"Object", "object");
		equalsMethod.addParameter(objectParameter);
		equalsMethod.addInstruction("if (this == object) {");
		equalsMethod.addInstruction("\treturn true;");
		Rope equalsInstructionBuffer = new Rope(3);
		equalsInstructionBuffer
				.append("} else if (object == null || !(object instanceof ");
		equalsInstructionBuffer.append(name);
		equalsInstructionBuffer.append(")) {");
		equalsMethod.addInstruction(equalsInstructionBuffer);
		equalsMethod.addInstruction("\treturn false;");
		equalsMethod.addInstruction("} else {");
		equalsInstructionBuffer = new Rope(5);
		equalsInstructionBuffer.append("\t");
		equalsInstructionBuffer.append(name);
		equalsInstructionBuffer.append(" other = (");
		equalsInstructionBuffer.append(name);
		equalsInstructionBuffer.append(") object;");
		equalsMethod.addInstruction(equalsInstructionBuffer);
		equalsInstructionBuffer = new Rope(6 * typeAndNames.length + 1);
		equalsInstructionBuffer.append("\treturn");
		final PrintableMethod hashMethod = new PrintableMethod(StringPool.INT,
				StringPool.HASH_CODE);
		hashMethod.addAnnotation(StringPool.OVERRIDE);
		hashMethod.addModifier(Modifier.PUBLIC);
		hashMethod.addInstruction("int res = 0;");
		for (int i = 0; i < typeAndNames.length; i++) {
			final String fieldClassName = extractClassName(typeAndNames[i], pc);
			final String fieldName = typeAndNames[++i];
			final PrintableParameter parameter = new PrintableParameter(
					fieldClassName, fieldName);
			parameter.addModifier(Modifier.FINAL);
			constructor.addParameter(parameter);
			final Rope instructionBuffer = new Rope(5);
			instructionBuffer.append("this.");
			instructionBuffer.append(fieldName);
			instructionBuffer.append(" = ");
			instructionBuffer.append(fieldName);
			instructionBuffer.append(";");
			constructor.addInstruction(instructionBuffer);
			final PrintableField pf = new PrintableField(fieldClassName,
					fieldName);
			pf.addModifier(Modifier.PRIVATE);
			pc.addField(pf);
			final String camelFieldName = fieldName.substring(0, 1)
					.toUpperCase().concat(fieldName.substring(1));
			PrintableMethod pm = new PrintableMethod(fieldClassName,
					"get".concat(camelFieldName));
			pm.addModifier(Modifier.PUBLIC);
			final Rope instructionBuffer2 = new Rope(3);
			instructionBuffer2.append("return this.");
			instructionBuffer2.append(fieldName);
			instructionBuffer2.append(";");
			pm.addInstruction(instructionBuffer2);
			pc.addMethod(pm);
			pm = new PrintableMethod(StringPool.VOID,
					"set".concat(camelFieldName));
			pm.addModifier(Modifier.PUBLIC);
			pm.addParameter(parameter);
			pm.addInstruction(instructionBuffer);
			pc.addMethod(pm);
			final Rope hashInstructionBuffer = new Rope(5);
			if (isBasicType(fieldClassName)) {
				if (i == 1) {
					equalsInstructionBuffer.append(" this.");
				} else {
					equalsInstructionBuffer.append(" && this.");
				}
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" == other.");
				equalsInstructionBuffer.append(fieldName);

				hashInstructionBuffer.append("res += (int) this.");
				hashInstructionBuffer.append(fieldName);
				hashInstructionBuffer.append(";");
			} else {
				if (i == 1) {
					equalsInstructionBuffer.append(" (this.");
				} else {
					equalsInstructionBuffer.append(" && (this.");
				}
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" == null && other.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" == null || this.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" != null && this.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(".equals(other.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append("))");

				hashInstructionBuffer.append("res += (this.");
				hashInstructionBuffer.append(fieldName);
				hashInstructionBuffer.append(" == null) ? 0 : this.");
				hashInstructionBuffer.append(fieldName);
				hashInstructionBuffer.append(".hashCode();");
			}
			hashMethod.addInstruction(hashInstructionBuffer);
			Rope toStringInstructionBuffer = new Rope(5);
			if (i == 1) {
				toStringInstructionBuffer.append("buffer.append(\"");
				toStringInstructionBuffer.append(name);
				toStringInstructionBuffer.append(" [");
			} else {
				toStringInstructionBuffer.append("buffer.append(\", ");
			}
			toStringInstructionBuffer.append(fieldName);
			toStringInstructionBuffer.append("=\");");
			toStringMethod.addInstruction(toStringInstructionBuffer);
			toStringInstructionBuffer = new Rope(3);
			toStringInstructionBuffer.append("buffer.append(this.");
			toStringInstructionBuffer.append(fieldName);
			toStringInstructionBuffer.append(");");
			toStringMethod.addInstruction(toStringInstructionBuffer);
		}
		pc.addConstructor(constructor);
		equalsInstructionBuffer.append(";");
		equalsMethod.addInstruction(equalsInstructionBuffer);
		equalsMethod.addInstruction("}");
		pc.addMethod(equalsMethod);
		hashMethod.addInstruction("return res;");
		pc.addMethod(hashMethod);
		toStringMethod.addInstruction("buffer.append(\"]\");");
		toStringMethod.addInstruction("return buffer.toString();");
		pc.addMethod(toStringMethod);
		return pc.toString();
	}

	public String finalPojo(final String name, final String... typeAndNames) {
		final PrintableClass pc = new PrintableClass(name);
		pc.addModifier(Modifier.PUBLIC);
		final PrintableConstructor constructor = new PrintableConstructor(pc);
		constructor.addModifier(Modifier.PUBLIC);
		final PrintableMethod toStringMethod = new PrintableMethod(
				StringPool.STRING, StringPool.TO_STRING);
		toStringMethod.addAnnotation(StringPool.OVERRIDE);
		toStringMethod.addModifier(Modifier.PUBLIC);
		toStringMethod
				.addInstruction("final StringBuilder buffer = new StringBuilder();");
		final PrintableMethod equalsMethod = new PrintableMethod(
				StringPool.BOOLEAN, StringPool.EQUALS);
		equalsMethod.addAnnotation(StringPool.OVERRIDE);
		equalsMethod.addModifier(Modifier.PUBLIC);
		final PrintableParameter objectParameter = new PrintableParameter(
				"Object", "object");
		equalsMethod.addParameter(objectParameter);
		equalsMethod.addInstruction("if (this == object) {");
		equalsMethod.addInstruction("\treturn true;");
		Rope equalsInstructionBuffer = new Rope(3);
		equalsInstructionBuffer
				.append("} else if (object == null || !(object instanceof ");
		equalsInstructionBuffer.append(name);
		equalsInstructionBuffer.append(")) {");
		equalsMethod.addInstruction(equalsInstructionBuffer);
		equalsMethod.addInstruction("\treturn false;");
		equalsMethod.addInstruction("} else {");
		equalsInstructionBuffer = new Rope(5);
		equalsInstructionBuffer.append("\t");
		equalsInstructionBuffer.append(name);
		equalsInstructionBuffer.append(" other = (");
		equalsInstructionBuffer.append(name);
		equalsInstructionBuffer.append(") object;");
		equalsMethod.addInstruction(equalsInstructionBuffer);
		equalsInstructionBuffer = new Rope(6 * typeAndNames.length + 1);
		equalsInstructionBuffer.append("\treturn");
		final PrintableMethod hashMethod = new PrintableMethod(StringPool.INT,
				StringPool.HASH_CODE);
		hashMethod.addAnnotation(StringPool.OVERRIDE);
		hashMethod.addModifier(Modifier.PUBLIC);
		hashMethod.addInstruction("int res = 0;");
		for (int i = 0; i < typeAndNames.length; i++) {
			final String fieldClassName = extractClassName(typeAndNames[i], pc);
			final String fieldName = typeAndNames[++i];
			final PrintableParameter parameter = new PrintableParameter(
					fieldClassName, fieldName);
			parameter.addModifier(Modifier.FINAL);
			constructor.addParameter(parameter);
			Rope instructionBuffer = new Rope(5);
			instructionBuffer.append("this.");
			instructionBuffer.append(fieldName);
			instructionBuffer.append(" = ");
			instructionBuffer.append(fieldName);
			instructionBuffer.append(";");
			constructor.addInstruction(instructionBuffer);
			final PrintableField pf = new PrintableField(fieldClassName,
					fieldName);
			pf.addModifier(Modifier.PRIVATE);
			pf.addModifier(Modifier.FINAL);
			pc.addField(pf);
			final Rope nameBuffer = new Rope(3);
			nameBuffer.append("get");
			nameBuffer.append(fieldName.substring(0, 1).toUpperCase());
			nameBuffer.append(fieldName.substring(1));
			PrintableMethod pm = new PrintableMethod(fieldClassName,
					nameBuffer.toString());
			pm.addModifier(Modifier.PUBLIC);
			instructionBuffer = new Rope(3);
			instructionBuffer.append("return this.");
			instructionBuffer.append(fieldName);
			instructionBuffer.append(";");
			pm.addInstruction(instructionBuffer);
			pc.addMethod(pm);
			final Rope hashInstructionBuffer = new Rope(5);
			if (isBasicType(fieldClassName)) {
				if (i != 1) {
					equalsInstructionBuffer.append(" && this.");
				} else {
					equalsInstructionBuffer.append(" this.");
				}
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" == other.");
				equalsInstructionBuffer.append(fieldName);

				hashInstructionBuffer.append("res += (int) this.");
				hashInstructionBuffer.append(fieldName);
				hashInstructionBuffer.append(";");
			} else {
				if (i != 1) {
					equalsInstructionBuffer.append(" && (this.");
				} else {
					equalsInstructionBuffer.append(" (this.");
				}
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" == null && other.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" == null || this.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" != null && this.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(".equals(other.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append("))");

				hashInstructionBuffer.append("res += (this.");
				hashInstructionBuffer.append(fieldName);
				hashInstructionBuffer.append(" == null) ? 0 : this.");
				hashInstructionBuffer.append(fieldName);
				hashInstructionBuffer.append(".hashCode();");
			}
			hashMethod.addInstruction(hashInstructionBuffer);
			instructionBuffer = new Rope(5);
			if (i != 1) {
				instructionBuffer.append("buffer.append(\", ");
			} else {
				instructionBuffer.append("buffer.append(\"");
				instructionBuffer.append(name);
				instructionBuffer.append(" [");
			}
			instructionBuffer.append(fieldName);
			instructionBuffer.append("=\");");
			toStringMethod.addInstruction(instructionBuffer);
			instructionBuffer = new Rope(3);
			instructionBuffer.append("buffer.append(this.");
			instructionBuffer.append(fieldName);
			instructionBuffer.append(");");
			toStringMethod.addInstruction(instructionBuffer);
		}
		pc.addConstructor(constructor);
		equalsInstructionBuffer.append(";");
		equalsMethod.addInstruction(equalsInstructionBuffer);
		equalsMethod.addInstruction("}");
		pc.addMethod(equalsMethod);
		hashMethod.addInstruction("return res;");
		pc.addMethod(hashMethod);
		toStringMethod.addInstruction("buffer.append(\"]\");");
		toStringMethod.addInstruction("return buffer.toString();");
		pc.addMethod(toStringMethod);
		return pc.toString();
	}

	public String function(final String name, final int nbParams) {
		final PrintableInterface pi = new PrintableInterface(name);
		pi.addAnnotation(StringPool.FUNCTIONAL_INTERFACE);
		pi.addModifier(Modifier.PUBLIC);
		pi.addGenericType("R");
		final PrintableMethod pm = new PrintableMethod("R", "apply");
		for (int i = 0; i < nbParams; i++) {
			final String iStr = Integer.toString(i);
			final String type = "T".concat(iStr);
			pi.addGenericType(type);
			final PrintableParameter parameter = new PrintableParameter(type,
					"arg".concat(iStr));
			parameter.addModifier(Modifier.FINAL);
			pm.addParameter(parameter);
		}
		pi.addMethod(pm);
		return pi.toString();
	}

	public String consumer(final String name, final int nbParams) {
		final PrintableInterface pi = new PrintableInterface(name);
		pi.addAnnotation(StringPool.FUNCTIONAL_INTERFACE);
		pi.addModifier(Modifier.PUBLIC);
		final PrintableMethod pm = new PrintableMethod(StringPool.VOID,
				"accept");
		for (int i = 0; i < nbParams; i++) {
			final String iStr = Integer.toString(i);
			final String type = "T".concat(iStr);
			pi.addGenericType(type);
			final PrintableParameter parameter = new PrintableParameter(type,
					"arg".concat(iStr));
			parameter.addModifier(Modifier.FINAL);
			pm.addParameter(parameter);
		}
		pi.addMethod(pm);
		return pi.toString();
	}

	public String visitor(final String name, final String returnFullType,
			final String... types) {
		final PrintableInterface pi = new PrintableInterface(name);
		pi.addModifier(Modifier.PUBLIC);
		final String returnType = extractClassName(returnFullType, pi);
		for (final String fullType : types) {
			final String type = extractClassName(fullType, pi);
			final PrintableMethod pm = new PrintableMethod(returnType,
					"visit".concat(type));
			final String lowerType = type.substring(0, 1).toLowerCase()
					.concat(type.substring(1));
			final PrintableParameter parameter = new PrintableParameter(type,
					lowerType);
			parameter.addModifier(Modifier.FINAL);
			pm.addParameter(parameter);
			pi.addMethod(pm);
		}
		return pi.toString();
	}

	public String visitable(final String name,
			final String visitorfullClassName, final String returnFullType) {
		final PrintableInterface pi = new PrintableInterface(name);
		pi.addAnnotation(StringPool.FUNCTIONAL_INTERFACE);
		pi.addModifier(Modifier.PUBLIC);
		final String returnType = extractClassName(returnFullType, pi);
		final String visitorClassName = extractClassName(visitorfullClassName,
				pi);
		final PrintableMethod pm = new PrintableMethod(returnType, "accept");
		final PrintableParameter parameter = new PrintableParameter(
				visitorClassName, "visitor");
		parameter.addModifier(Modifier.FINAL);
		pm.addParameter(parameter);
		pi.addMethod(pm);
		return pi.toString();
	}

	public String acceptVisitor(final int indentationLevel, final String name,
			final String visitorFullClassName, final String returnFullType) {
		PrintableContainer container = new PrintableContainer(null);
		final String visitorClassName = extractClassName(visitorFullClassName,
				container);
		final String returnType = extractClassName(returnFullType, container);
		final PrintableMethod acceptMethod = new PrintableMethod(returnType,
				"accept");
		acceptMethod.addAnnotation(StringPool.OVERRIDE);
		acceptMethod.addModifier(Modifier.PUBLIC);
		final PrintableParameter parameter = new PrintableParameter(
				visitorClassName, "visitor");
		parameter.addModifier(Modifier.FINAL);
		acceptMethod.addParameter(parameter);
		Rope instructionBuffer = new Rope(3);
		if (StringPool.VOID.equals(returnType)) {
			instructionBuffer.append("visitor.visit");
		} else {
			instructionBuffer.append("return visitor.visit");
		}
		instructionBuffer.append(name);
		instructionBuffer.append("(this);");
		acceptMethod.addInstruction(instructionBuffer);

		final Indenter indenter = new Indenter(indentationLevel);
		return acceptMethod.toRope(indenter).toString();
	}

	public String delegate(final int indentationLevel,
			final String fieldClassName, final String fieldName,
			final String... methodNames) throws ClassNotFoundException {
		final Class<?> c = Class.forName(fieldClassName);
		final List<PrintableMethod> pms = new ArrayList<>();
		final PrintableContainer pc = new PrintableContainer(null);
		if (methodNames.length == 0) {
			for (final Method method : c.getMethods()) {
				if (!Modifier.isPrivate(method.getModifiers())) {
					pms.add(createDelegateMethod(pc, method, fieldName, false));
				}
			}
		} else {
			for (final Method method : c.getMethods()) {
				if (arrayContains(methodNames, method.getName())
						&& !Modifier.isPrivate(method.getModifiers())) {
					pms.add(createDelegateMethod(pc, method, fieldName, false));
				}
			}
		}
		final Rope buffer = new Rope(pms.size());
		final Indenter indenter = new Indenter(indentationLevel);
		for (final PrintableMethod pm : pms) {
			buffer.append(pm.toRope(indenter));
		}
		return buffer.toString();
	}

	public String equals(final int indentationLevel, final String name,
			final String... typeAndNames) {
		final PrintableMethod equalsMethod = new PrintableMethod(
				StringPool.BOOLEAN, StringPool.EQUALS);
		equalsMethod.addAnnotation(StringPool.OVERRIDE);
		equalsMethod.addModifier(Modifier.PUBLIC);
		final PrintableParameter objectParameter = new PrintableParameter(
				StringPool.OBJECT, "object");
		equalsMethod.addParameter(objectParameter);
		equalsMethod.addInstruction("if (this == object) {");
		equalsMethod.addInstruction("\treturn true;");
		Rope equalsInstructionBuffer = new Rope(3);
		equalsInstructionBuffer
				.append("} else if (object == null || !(object instanceof ");
		equalsInstructionBuffer.append(name);
		equalsInstructionBuffer.append(")) {");
		equalsMethod.addInstruction(equalsInstructionBuffer);
		equalsMethod.addInstruction("\treturn false;");
		equalsMethod.addInstruction("} else {");
		equalsInstructionBuffer = new Rope(5);
		equalsInstructionBuffer.append("\t");
		equalsInstructionBuffer.append(name);
		equalsInstructionBuffer.append(" other = (");
		equalsInstructionBuffer.append(name);
		equalsInstructionBuffer.append(") object;");
		equalsMethod.addInstruction(equalsInstructionBuffer);
		equalsInstructionBuffer = new Rope(6 * typeAndNames.length + 1);
		equalsInstructionBuffer.append("\treturn");
		for (int i = 0; i < typeAndNames.length; i++) {
			final String fullFieldClassName = typeAndNames[i];
			final String fieldName = typeAndNames[++i];
			if (isBasicType(fullFieldClassName)) {
				if (i == 1) {
					equalsInstructionBuffer.append(" this.");
				} else {
					equalsInstructionBuffer.append(" && this.");
				}
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" == other.");
				equalsInstructionBuffer.append(fieldName);
			} else {
				if (i == 1) {
					equalsInstructionBuffer.append(" (this.");
				} else {
					equalsInstructionBuffer.append(" && (this.");
				}
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" == null && other.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" == null || this.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(" != null && this.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append(".equals(other.");
				equalsInstructionBuffer.append(fieldName);
				equalsInstructionBuffer.append("))");
			}
		}
		equalsInstructionBuffer.append(";");
		equalsMethod.addInstruction(equalsInstructionBuffer);
		equalsMethod.addInstruction("}");

		final Indenter indenter = new Indenter(indentationLevel);
		return equalsMethod.toRope(indenter).toString();
	}

	public String hashCode(final int indentationLevel,
			final String... typeAndNames) {
		final PrintableMethod hashMethod = new PrintableMethod(StringPool.INT,
				StringPool.HASH_CODE);
		hashMethod.addAnnotation(StringPool.OVERRIDE);
		hashMethod.addModifier(Modifier.PUBLIC);
		hashMethod.addInstruction("int res = 0;");
		for (int i = 0; i < typeAndNames.length; i++) {
			final String fullFieldClassName = typeAndNames[i];
			final String fieldName = typeAndNames[++i];
			final Rope hashInstructionBuffer = new Rope(5);
			if (isBasicType(fullFieldClassName)) {
				hashInstructionBuffer.append("res += (int) this.");
				hashInstructionBuffer.append(fieldName);
				hashInstructionBuffer.append(";");
			} else {
				hashInstructionBuffer.append("res += (this.");
				hashInstructionBuffer.append(fieldName);
				hashInstructionBuffer.append(" == null) ? 0 : this.");
				hashInstructionBuffer.append(fieldName);
				hashInstructionBuffer.append(".hashCode();");
			}
			hashMethod.addInstruction(hashInstructionBuffer);
		}
		hashMethod.addInstruction("return res;");

		final Indenter indenter = new Indenter(indentationLevel);
		return hashMethod.toRope(indenter).toString();
	}

	public String toString(final int indentationLevel, final String name,
			final String firstFieldName, final String... otherFieldNames) {
		final PrintableMethod toStringMethod = new PrintableMethod(
				StringPool.STRING, StringPool.TO_STRING);
		toStringMethod.addAnnotation(StringPool.OVERRIDE);
		toStringMethod.addModifier(Modifier.PUBLIC);
		toStringMethod
				.addInstruction("final StringBuilder buffer = new StringBuilder();");
		Rope instructionBuffer = new Rope(5);
		instructionBuffer.append("buffer.append(\"");
		instructionBuffer.append(name);
		instructionBuffer.append(" [");
		instructionBuffer.append(firstFieldName);
		instructionBuffer.append("=\");");
		toStringMethod.addInstruction(instructionBuffer);
		instructionBuffer = new Rope(3);
		instructionBuffer.append("buffer.append(this.");
		instructionBuffer.append(firstFieldName);
		instructionBuffer.append(");");
		toStringMethod.addInstruction(instructionBuffer);
		for (final String fieldName : otherFieldNames) {
			instructionBuffer = new Rope(3);
			instructionBuffer.append("buffer.append(\", ");
			instructionBuffer.append(fieldName);
			instructionBuffer.append("=\");");
			toStringMethod.addInstruction(instructionBuffer);
			instructionBuffer = new Rope(3);
			instructionBuffer.append("buffer.append(this.");
			instructionBuffer.append(fieldName);
			instructionBuffer.append(");");
			toStringMethod.addInstruction(instructionBuffer);
		}
		toStringMethod.addInstruction("buffer.append(\"]\");");
		toStringMethod.addInstruction("return buffer.toString();");
		final Indenter indenter = new Indenter(indentationLevel);
		return toStringMethod.toRope(indenter).toString();
	}

	public String squareMatrix(final String name, final String columnName,
			final int n) {
		final int innerArraySize = n * n;
		final String innerArraySizeStr = Integer.toString(innerArraySize);
		final String doubleArrayType = "double[]";
		final String nStr = Integer.toString(n);
		final PrintableClass pc = new PrintableClass(name);
		pc.addImport("fr.codi.text.Rope");
		pc.addModifier(Modifier.PUBLIC);
		final PrintableField field = new PrintableField(doubleArrayType,
				"innerArray");
		field.addModifier(Modifier.PRIVATE);
		pc.addField(field);
		PrintableConstructor constructor = new PrintableConstructor(pc);
		constructor.addModifier(Modifier.PUBLIC);
		Rope instructionBuffer = new Rope(3);
		instructionBuffer.append("this.innerArray = new double[");
		instructionBuffer.append(innerArraySizeStr);
		instructionBuffer.append("];");
		constructor.addInstruction(instructionBuffer);
		pc.addConstructor(constructor);
		constructor = new PrintableConstructor(pc);
		constructor.addModifier(Modifier.PUBLIC);
		PrintableParameter parameter = new PrintableParameter(doubleArrayType,
				"array");
		parameter.addModifier(Modifier.FINAL);
		constructor.addParameter(parameter);
		constructor.addException("IllegalArgumentException");
		instructionBuffer = new Rope(3);
		instructionBuffer.append("if (array.length < ");
		instructionBuffer.append(innerArraySizeStr);
		instructionBuffer.append(") {");
		constructor.addInstruction(instructionBuffer);
		constructor.addInstruction("\tthrow new IllegalArgumentException();");
		constructor.addInstruction("}");
		constructor.addInstruction("this.innerArray = array;");
		pc.addConstructor(constructor);
		PrintableMethod method = new PrintableMethod(doubleArrayType,
				"getInnerArray");
		method.addModifier(Modifier.PUBLIC);
		method.addInstruction("return this.innerArray;");
		pc.addMethod(method);
		method = new PrintableMethod(StringPool.DOUBLE, "get");
		method.addModifier(Modifier.PUBLIC);
		final PrintableParameter parameterLine = new PrintableParameter(
				StringPool.INT, "line");
		parameterLine.addModifier(Modifier.FINAL);
		method.addParameter(parameterLine);
		final PrintableParameter parameterColumn = new PrintableParameter(
				StringPool.INT, "column");
		parameterColumn.addModifier(Modifier.FINAL);
		method.addParameter(parameterColumn);
		method.addException("IndexOutOfBoundsException");
		final String nMoins1Str = Integer.toString(n - 1);
		final Rope indexTestBuffer = new Rope(5);
		indexTestBuffer.append("if (line < 0 || line > ");
		indexTestBuffer.append(nMoins1Str);
		indexTestBuffer.append(" || column < 0 || column > ");
		indexTestBuffer.append(nMoins1Str);
		indexTestBuffer.append(") {");
		method.addInstruction(indexTestBuffer);
		method.addInstruction("\tthrow new IndexOutOfBoundsException();");
		method.addInstruction("}");
		instructionBuffer = new Rope(3);
		instructionBuffer.append("return this.innerArray[line * ");
		instructionBuffer.append(nStr);
		instructionBuffer.append(" + column];");
		method.addInstruction(instructionBuffer);
		pc.addMethod(method);
		method = new PrintableMethod(StringPool.VOID, "set");
		method.addModifier(Modifier.PUBLIC);
		method.addParameter(parameterLine);
		method.addParameter(parameterColumn);
		final PrintableParameter parameterValue = new PrintableParameter(
				StringPool.DOUBLE, "value");
		parameterValue.addModifier(Modifier.FINAL);
		method.addParameter(parameterValue);
		method.addException("IndexOutOfBoundsException");
		method.addInstruction(indexTestBuffer);
		method.addInstruction("\tthrow new IndexOutOfBoundsException();");
		method.addInstruction("}");
		instructionBuffer = new Rope(3);
		instructionBuffer.append("this.innerArray[line * ");
		instructionBuffer.append(nStr);
		instructionBuffer.append(" + column] = value;");
		method.addInstruction(instructionBuffer);
		pc.addMethod(method);
		method = new PrintableMethod(name, "transpose");
		method.addModifier(Modifier.PUBLIC);
		instructionBuffer = new Rope(2 * innerArraySize + n + 3);
		instructionBuffer.append("return new ");
		instructionBuffer.append(name);
		instructionBuffer.append("(new double[] {");
		for (int i = 0; i < n; i++) {
			if (i != 0) {
				instructionBuffer.append("], ");
			}
			for (int j = 0; j < n; j++) {
				if (j == 0) {
					instructionBuffer.append("this.innerArray[");
				} else {
					instructionBuffer.append("], this.innerArray[");
				}
				instructionBuffer.append(Integer.toString(j * n + i));
			}
		}
		instructionBuffer.append("]});");
		method.addInstruction(instructionBuffer);
		pc.addMethod(method);
		method = new PrintableMethod(name, "add");
		method.addModifier(Modifier.PUBLIC);
		final PrintableParameter parameterMatrix = new PrintableParameter(name,
				"matrix");
		parameterMatrix.addModifier(Modifier.FINAL);
		method.addParameter(parameterMatrix);
		final int ropeAddSize = 4 * innerArraySize + 4;
		instructionBuffer = new Rope(ropeAddSize);
		instructionBuffer.append("return new ");
		instructionBuffer.append(name);
		instructionBuffer.append("(new double[] {");
		for (int i = 0; i < innerArraySize; i++) {
			if (i == 0) {
				instructionBuffer.append("this.innerArray[");
			} else {
				instructionBuffer.append("], this.innerArray[");
			}
			final String iStr = Integer.toString(i);
			instructionBuffer.append(iStr);
			instructionBuffer.append("] + matrix.innerArray[");
			instructionBuffer.append(iStr);
		}
		instructionBuffer.append("]});");
		method.addInstruction(instructionBuffer);
		pc.addMethod(method);
		method = new PrintableMethod(name, "substract");
		method.addModifier(Modifier.PUBLIC);
		method.addParameter(parameterMatrix);
		instructionBuffer = new Rope(ropeAddSize);
		instructionBuffer.append("return new ");
		instructionBuffer.append(name);
		instructionBuffer.append("(new double[] {");
		for (int i = 0; i < innerArraySize; i++) {
			if (i == 0) {
				instructionBuffer.append("this.innerArray[");
			} else {
				instructionBuffer.append("], this.innerArray[");
			}
			final String iStr = Integer.toString(i);
			instructionBuffer.append(iStr);
			instructionBuffer.append("] - matrix.innerArray[");
			instructionBuffer.append(iStr);
		}
		instructionBuffer.append("]});");
		method.addInstruction(instructionBuffer);
		pc.addMethod(method);
		final String multiplyStr = "multiply";
		method = new PrintableMethod(name, multiplyStr);
		method.addModifier(Modifier.PUBLIC);
		method.addParameter(parameterValue);
		instructionBuffer = new Rope(2 * innerArraySize + 4);
		instructionBuffer.append("return new ");
		instructionBuffer.append(name);
		instructionBuffer.append("(new double[] {");
		for (int i = 0; i < innerArraySize; i++) {
			if (i == 0) {
				instructionBuffer.append("value * this.innerArray[");
			} else {
				instructionBuffer.append("], value * this.innerArray[");
			}
			instructionBuffer.append(Integer.toString(i));
		}
		instructionBuffer.append("]});");
		method.addInstruction(instructionBuffer);
		pc.addMethod(method);
		method = new PrintableMethod(name, multiplyStr);
		method.addModifier(Modifier.PUBLIC);
		method.addParameter(parameterMatrix);
		instructionBuffer = new Rope(4 * n * innerArraySize + innerArraySize
				+ n + 4);
		instructionBuffer.append("return new ");
		instructionBuffer.append(name);
		instructionBuffer.append("(new double[] {");
		for (int i = 0; i < n; i++) {
			if (i != 0) {
				instructionBuffer.append("], ");
			}
			for (int j = 0; j < n; j++) {
				if (j != 0) {
					instructionBuffer.append("], ");
				}
				for (int k = 0; k < n; k++) {
					if (k == 0) {
						instructionBuffer.append("this.innerArray[");
					} else {
						instructionBuffer.append("] + this.innerArray[");
					}
					instructionBuffer.append(Integer.toString(i * n + k));
					instructionBuffer.append("] * matrix.innerArray[");
					instructionBuffer.append(Integer.toString(k * n + j));
				}
			}
		}
		instructionBuffer.append("]});");
		method.addInstruction(instructionBuffer);
		pc.addMethod(method);
		method = new PrintableMethod(columnName, multiplyStr);
		method.addModifier(Modifier.PUBLIC);
		parameter = new PrintableParameter(columnName, "matrix");
		parameter.addModifier(Modifier.FINAL);
		method.addParameter(parameter);
		method.addInstruction("double[] matrixInnerArray = matrix.getInnerArray();");
		instructionBuffer = new Rope(4 * innerArraySize + n + 3);
		instructionBuffer.append("return new ");
		instructionBuffer.append(columnName);
		instructionBuffer.append("(new double[] {");
		for (int i = 0; i < n; i++) {
			if (i != 0) {
				instructionBuffer.append("], ");
			}
			for (int j = 0; j < n; j++) {
				if (j == 0) {
					instructionBuffer.append("this.innerArray[");
				} else {
					instructionBuffer.append("] + this.innerArray[");
				}
				instructionBuffer.append(Integer.toString(i * n + j));
				instructionBuffer.append("] * matrixInnerArray[");
				instructionBuffer.append(Integer.toString(j));
			}
		}
		instructionBuffer.append("]});");
		method.addInstruction(instructionBuffer);
		pc.addMethod(method);
		method = new PrintableMethod(StringPool.BOOLEAN, StringPool.EQUALS);
		method.addAnnotation(StringPool.OVERRIDE);
		method.addModifier(Modifier.PUBLIC);
		method.addParameter(new PrintableParameter(StringPool.OBJECT, "object"));
		method.addInstruction("if (this == object) {");
		method.addInstruction("\treturn true;");
		instructionBuffer = new Rope(3);
		instructionBuffer
				.append("} else if (object == null || !(object instanceof ");
		instructionBuffer.append(name);
		instructionBuffer.append(")) {");
		method.addInstruction(instructionBuffer);
		method.addInstruction("\treturn false;");
		method.addInstruction("} else {");
		instructionBuffer = new Rope(5);
		instructionBuffer.append("\t");
		instructionBuffer.append(name);
		instructionBuffer.append(" other = (");
		instructionBuffer.append(name);
		instructionBuffer.append(") object;");
		method.addInstruction(instructionBuffer);
		instructionBuffer = new Rope(5 * innerArraySize + 1);
		for (int i = 0; i < innerArraySize; i++) {
			final String iStr = Integer.toString(i);
			if (i == 0) {
				instructionBuffer.append("\treturn this.innerArray[");
			} else {
				instructionBuffer.append(" && this.innerArray[");
			}
			instructionBuffer.append(iStr);
			instructionBuffer.append("] == other.innerArray[");
			instructionBuffer.append(iStr);
			instructionBuffer.append("]");

		}
		instructionBuffer.append(";");
		method.addInstruction(instructionBuffer);
		method.addInstruction("}");
		pc.addMethod(method);
		method = new PrintableMethod(StringPool.STRING, StringPool.TO_STRING);
		method.addAnnotation(StringPool.OVERRIDE);
		method.addModifier(Modifier.PUBLIC);
		instructionBuffer = new Rope(3);
		instructionBuffer.append("final Rope buffer = new Rope(");
		instructionBuffer.append(Integer.toString(2 * innerArraySize + 1));
		instructionBuffer.append(");");
		method.addInstruction(instructionBuffer);
		method.addInstruction("buffer.append(\"[[\");");
		for (int i = 0; i < n; i++) {
			if (i != 0) {
				method.addInstruction("buffer.append(\"]\\n [\");");
			}
			for (int j = 0; j < n; j++) {
				if (j != 0) {
					method.addInstruction("buffer.append(\", \");");
				}
				instructionBuffer = new Rope(3);
				instructionBuffer
						.append("buffer.append(Double.toString(this.innerArray[");
				instructionBuffer.append(Integer.toString(i * n + j));
				instructionBuffer.append("]));");
				method.addInstruction(instructionBuffer);
			}
		}
		method.addInstruction("buffer.append(\"]]\");");
		method.addInstruction("return buffer.toString();");
		pc.addMethod(method);
		return pc.toString();
	}

	public String columnMatrix(final String name, final int n) {
		final String doubleArrayType = "double[]";
		final String nStr = Integer.toString(n);
		final PrintableClass pc = new PrintableClass(name);
		pc.addImport("fr.codi.text.Rope");
		pc.addModifier(Modifier.PUBLIC);
		PrintableField field = new PrintableField(doubleArrayType, "innerArray");
		field.addModifier(Modifier.PRIVATE);
		pc.addField(field);
		PrintableConstructor constructor = new PrintableConstructor(pc);
		constructor.addModifier(Modifier.PUBLIC);
		Rope instructionBuffer = new Rope(3);
		instructionBuffer.append("this.innerArray = new double[");
		instructionBuffer.append(nStr);
		instructionBuffer.append("];");
		constructor.addInstruction(instructionBuffer);
		pc.addConstructor(constructor);
		constructor = new PrintableConstructor(pc);
		constructor.addModifier(Modifier.PUBLIC);
		PrintableParameter parameter = new PrintableParameter(doubleArrayType,
				"array");
		parameter.addModifier(Modifier.FINAL);
		constructor.addParameter(parameter);
		constructor.addException("IllegalArgumentException");
		instructionBuffer = new Rope(3);
		instructionBuffer.append("if (array.length < ");
		instructionBuffer.append(nStr);
		instructionBuffer.append(") {");
		constructor.addInstruction(instructionBuffer);
		constructor.addInstruction("\tthrow new IllegalArgumentException();");
		constructor.addInstruction("}");
		constructor.addInstruction("this.innerArray = array;");
		pc.addConstructor(constructor);
		PrintableMethod method = new PrintableMethod(doubleArrayType,
				"getInnerArray");
		method.addModifier(Modifier.PUBLIC);
		method.addInstruction("return this.innerArray;");
		pc.addMethod(method);
		method = new PrintableMethod(StringPool.DOUBLE, "get");
		method.addModifier(Modifier.PUBLIC);
		final PrintableParameter parameterLine = new PrintableParameter(
				StringPool.INT, "line");
		parameterLine.addModifier(Modifier.FINAL);
		method.addParameter(parameterLine);
		final PrintableParameter parameterColumn = new PrintableParameter(
				StringPool.INT, "column");
		parameterColumn.addModifier(Modifier.FINAL);
		method.addParameter(parameterColumn);
		method.addException("IndexOutOfBoundsException");
		method.addInstruction("if (column != 0) {");
		method.addInstruction("\tthrow new IndexOutOfBoundsException();");
		method.addInstruction("}");
		method.addInstruction("return this.innerArray[line];");
		pc.addMethod(method);
		method = new PrintableMethod(StringPool.VOID, "set");
		method.addModifier(Modifier.PUBLIC);
		method.addParameter(parameterLine);
		method.addParameter(parameterColumn);
		final PrintableParameter parameterValue = new PrintableParameter(
				StringPool.DOUBLE, "value");
		parameterValue.addModifier(Modifier.FINAL);
		method.addParameter(parameterValue);
		method.addException("IndexOutOfBoundsException");
		method.addInstruction("if (column != 0) {");
		method.addInstruction("\tthrow new IndexOutOfBoundsException();");
		method.addInstruction("}");
		method.addInstruction("this.innerArray[line] = value;");
		pc.addMethod(method);
		method = new PrintableMethod(StringPool.DOUBLE, "get");
		method.addModifier(Modifier.PUBLIC);
		method.addParameter(parameterLine);
		method.addInstruction("return this.innerArray[line];");
		pc.addMethod(method);
		method = new PrintableMethod(StringPool.VOID, "set");
		method.addModifier(Modifier.PUBLIC);
		method.addParameter(parameterLine);
		method.addParameter(parameterValue);
		method.addInstruction("this.innerArray[line] = value;");
		pc.addMethod(method);
		method = new PrintableMethod(name, "add");
		method.addModifier(Modifier.PUBLIC);
		final PrintableParameter parameterMatrix = new PrintableParameter(name,
				"matrix");
		parameterMatrix.addModifier(Modifier.FINAL);
		method.addParameter(parameterMatrix);
		final int ropeAddSize = 4 * n + 4;
		instructionBuffer = new Rope(ropeAddSize);
		instructionBuffer.append("return new ");
		instructionBuffer.append(name);
		instructionBuffer.append("(new double[] {");
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				instructionBuffer.append("this.innerArray[");
			} else {
				instructionBuffer.append("], this.innerArray[");
			}
			final String iStr = Integer.toString(i);
			instructionBuffer.append(iStr);
			instructionBuffer.append("] + matrix.innerArray[");
			instructionBuffer.append(iStr);
		}
		instructionBuffer.append("]});");
		method.addInstruction(instructionBuffer);
		pc.addMethod(method);
		method = new PrintableMethod(name, "substract");
		method.addModifier(Modifier.PUBLIC);
		method.addParameter(parameterMatrix);
		instructionBuffer = new Rope(ropeAddSize);
		instructionBuffer.append("return new ");
		instructionBuffer.append(name);
		instructionBuffer.append("(new double[] {");
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				instructionBuffer.append("this.innerArray[");
			} else {
				instructionBuffer.append("], this.innerArray[");
			}
			final String iStr = Integer.toString(i);
			instructionBuffer.append(iStr);
			instructionBuffer.append("] - matrix.innerArray[");
			instructionBuffer.append(iStr);
		}
		instructionBuffer.append("]});");
		method.addInstruction(instructionBuffer);
		pc.addMethod(method);
		method = new PrintableMethod(name, "multiply");
		method.addModifier(Modifier.PUBLIC);
		method.addParameter(parameterValue);
		instructionBuffer = new Rope(2 * n + 4);
		instructionBuffer.append("return new ");
		instructionBuffer.append(name);
		instructionBuffer.append("(new double[] {");
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				instructionBuffer.append("value * this.innerArray[");
			} else {
				instructionBuffer.append("], value * this.innerArray[");
			}
			instructionBuffer.append(Integer.toString(i));
		}
		instructionBuffer.append("]});");
		method.addInstruction(instructionBuffer);
		pc.addMethod(method);
		method = new PrintableMethod(name, "applySigmoid");
		method.addModifier(Modifier.PUBLIC);
		for (int i = 0; i < n; i++) {
			final String iStr = Integer.toString(i);
			instructionBuffer = new Rope(5);
			instructionBuffer.append("this.innerArray[");
			instructionBuffer.append(iStr);
			instructionBuffer.append("] = 1 / (1 + Math.exp(-this.innerArray[");
			instructionBuffer.append(iStr);
			instructionBuffer.append("]));");
			method.addInstruction(instructionBuffer);
		}
		method.addInstruction("return this;");
		pc.addMethod(method);
		method = new PrintableMethod(StringPool.BOOLEAN, StringPool.EQUALS);
		method.addAnnotation(StringPool.OVERRIDE);
		method.addModifier(Modifier.PUBLIC);
		method.addParameter(new PrintableParameter(StringPool.OBJECT, "object"));
		method.addInstruction("if (this == object) {");
		method.addInstruction("\treturn true;");
		instructionBuffer = new Rope(3);
		instructionBuffer
				.append("} else if (object == null || !(object instanceof ");
		instructionBuffer.append(name);
		instructionBuffer.append(")) {");
		method.addInstruction(instructionBuffer);
		method.addInstruction("\treturn false;");
		method.addInstruction("} else {");
		instructionBuffer = new Rope(5);
		instructionBuffer.append("\t");
		instructionBuffer.append(name);
		instructionBuffer.append(" other = (");
		instructionBuffer.append(name);
		instructionBuffer.append(") object;");
		method.addInstruction(instructionBuffer);
		instructionBuffer = new Rope(5 * n + 1);
		for (int i = 0; i < n; i++) {
			final String iStr = Integer.toString(i);
			if (i == 0) {
				instructionBuffer.append("\treturn this.innerArray[");
			} else {
				instructionBuffer.append(" && this.innerArray[");
			}
			instructionBuffer.append(iStr);
			instructionBuffer.append("] == other.innerArray[");
			instructionBuffer.append(iStr);
			instructionBuffer.append("]");

		}
		instructionBuffer.append(";");
		method.addInstruction(instructionBuffer);
		method.addInstruction("}");
		pc.addMethod(method);
		method = new PrintableMethod(StringPool.STRING, StringPool.TO_STRING);
		method.addAnnotation(StringPool.OVERRIDE);
		method.addModifier(Modifier.PUBLIC);
		instructionBuffer = new Rope(3);
		instructionBuffer.append("final Rope buffer = new Rope(");
		instructionBuffer.append(Integer.toString(2 * n + 1));
		instructionBuffer.append(");");
		method.addInstruction(instructionBuffer);
		method.addInstruction("buffer.append(\"[[\");");
		for (int i = 0; i < n; i++) {
			if (i != 0) {
				method.addInstruction("buffer.append(\"]\\n [\");");
			}
			instructionBuffer = new Rope(3);
			instructionBuffer
					.append("buffer.append(Double.toString(this.innerArray[");
			instructionBuffer.append(Integer.toString(i));
			instructionBuffer.append("]));");
			method.addInstruction(instructionBuffer);
		}
		method.addInstruction("buffer.append(\"]]\");");
		method.addInstruction("return buffer.toString();");
		pc.addMethod(method);
		return pc.toString();
	}

	private String extractClassName(final String fullClassName,
			final PrintableContainer pc) {
		final String className;
		if (fullClassName.contains(".")) {
			pc.addImport(fullClassName);
			className = fullClassName
					.substring(fullClassName.lastIndexOf(".") + 1);
		} else {
			// type de base ou doit appartenir à java.lang ou au même
			// package
			className = fullClassName;
		}
		return className;
	}

	private PrintableMethod createDelegateMethod(final PrintableContainer pc,
			final Method method, final String delegateName,
			final boolean override) {
		final PrintableMethod pm = new PrintableMethod(method, pc);
		if (override) {
			pm.addAnnotation(StringPool.OVERRIDE);
		}
		pm.removeModifier(Modifier.ABSTRACT);
		final Rope instructionBuffer = new Rope(5);
		if (StringPool.VOID.equals(pm.getReturnType())) {
			instructionBuffer.append("this.");
		} else {
			instructionBuffer.append("return this.");
		}
		instructionBuffer.append(delegateName);
		instructionBuffer.append(".");
		instructionBuffer.append(pm.toCallRope());
		instructionBuffer.append(";");
		pm.addInstruction(instructionBuffer);
		return pm;
	}

	private <T> boolean arrayContains(final T[] array, final T object) {
		for (final T elem : array) {
			if (object.equals(elem)) {
				return true;
			}
		}
		return false;
	}
}
