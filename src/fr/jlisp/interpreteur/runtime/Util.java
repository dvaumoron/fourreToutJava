package fr.jlisp.interpreteur.runtime;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import fr.jlisp.interpreteur.Interpreteur;
import fr.jlisp.syntaxique.Node;
import fr.jlisp.syntaxique.NodeIdentifier;
import fr.jlisp.syntaxique.NodeList;

public final class Util {

	private Util() {
	}

	public static final String CLASS = "class";
	public static final String LAMBDA = "lambda";
	public static final String OBJECT = "object";

	public static final String APPLY = "apply";
	public static final String GET_ATTRIBUTE = "getAttribute";
	public static final String INIT = "init";
	public static final String UNQUOTE = "unquote";

	public static final String BUILTINS_NAME = "_builtins_";
	public static final String INTERPRETEUR_NAME = "_interpreteur_";
	public static final String MODULE_NAME = "_module_";

	public static int countIndentation(final String line) {
		int res = 0;
		final int length = line.length();
		boolean disable = false;
		for (int index = 0; index < length; index++) {
			final char c = line.charAt(index);
			if (disable) {
				if (c == '\\') {
					index++;
				} else if (c == '"') {
					disable = false;
				}
			} else {
				if (c == '(') {
					res++;
				} else if (c == ')') {
					res--;
				} else if (c == '"') {
					disable = true;
				} else if (c == '\'') {
					final char c2 = line.charAt(++index);
					if (c2 == '\\') {
						index += 2;
					} else {
						index++;
					}
				} else if (c == '#') {
					break;
				}
			}
		}
		return res;
	}

	public static Interpreteur getInterpreteur(final Environement environement) {
		return (Interpreteur) environement.get(INTERPRETEUR_NAME);
	}

	public static List<String> convertNameList(final NodeList nodeList) {
		final List<String> res = new ArrayList<>();
		for (final Node node : nodeList) {
			final NodeIdentifier nodeIdentifier = (NodeIdentifier) node;
			res.add(nodeIdentifier.getIdentifier());
		}
		return res;
	}

	public static void updateCallLocal(final List<String> declaredArgs,
			final Environement local, final List<?> args) {
		final int size = declaredArgs.size();
		for (int i = 0; i < size; i++) {
			local.set(declaredArgs.get(i), args.get(i));
		}
	}

	public static String getName(final Environement environement,
			final Node node) throws Exception {
		final String name;
		if (node instanceof NodeIdentifier) {
			final NodeIdentifier nodeName = (NodeIdentifier) node;
			name = nodeName.getIdentifier();
		} else {
			name = (String) node.eval(environement);
			if (name == null) {
				throw new NullPointerException();
			}
		}
		return name;
	}

	public static String getCategory(final String type, final String name) {
		final StringBuilder buffer = new StringBuilder();
		buffer.append(type);
		buffer.append(" '");
		buffer.append(name);
		buffer.append("'[");
		return buffer.toString();
	}

	public static Object decorate(final Environement environement,
			final List<Node> args, final int start, Object value)
			throws Exception {
		for (int i = start; i >= 0; i--) {
			final Fonction decorator = (Fonction) args.get(i)
					.eval(environement);
			value = decorator.apply(Collections.singletonList(value));
		}
		return value;
	}

	public static void convert(final Object[] objects,
			final List<Integer> biConversions,
			final List<Integer> intConversions) {
		for (final int i : biConversions) {
			objects[i] = new BiFonction((Fonction) objects[i]);
		}
		for (final int i : intConversions) {
			objects[i] = ((Number) objects[i]).intValue();
		}
	}

	public static boolean needBiConversion(final Class<?> c,
			final Class<?> parameterType) {
		return (parameterType.isAssignableFrom(BinaryOperator.class) || parameterType
				.isAssignableFrom(BiFunction.class))
				&& Fonction.class.isAssignableFrom(c)
				&& !(parameterType.isAssignableFrom(Fonction.class));
	}

	public static boolean needIntConversion(final Class<?> c,
			final Class<?> parameterType) {
		return ((parameterType == Integer.class || parameterType == Integer.TYPE) && (c == Long.class || c == Long.TYPE));
	}

	public static boolean isNotAssignableFrom(final Class<?> class1,
			final Class<?> class2) {
		if (class1.isAssignableFrom(class2)) {
			return false;
		} else if (class1 == Character.TYPE && class2 == Character.class) {
			return false;
		} else if (class1 == Short.TYPE && class2 == Short.class) {
			return false;
		} else if (class1 == Byte.TYPE && class2 == Byte.class) {
			return false;
		} else if (class1 == Integer.TYPE && class2 == Integer.class) {
			return false;
		} else if (class1 == Long.TYPE && class2 == Long.class) {
			return false;
		} else if (class1 == Float.TYPE && class2 == Float.class) {
			return false;
		} else if (class1 == Double.TYPE && class2 == Double.class) {
			return false;
		} else if (class1 == Boolean.TYPE && class2 == Boolean.class) {
			return false;
		}
		return true;
	}

	public static Object callMethod(final Environement environement,
			final Object object, final Class<?> clazz, final String methodName,
			final List<Object> args) throws Exception {
		final Interpreteur interpreteur = Util.getInterpreteur(environement);
		final Fonction enhancerMethod = interpreteur.getEnhancers().get(clazz,
				methodName);
		if (enhancerMethod == null) {
			final int size = args.size();
			final Object[] objects = new Object[size];
			final Class<?>[] classes = new Class[size];
			for (int i = 0; i < size; i++) {
				objects[i] = args.get(i);
				classes[i] = objects[i].getClass();
			}

			final MethodAndConversion methodAndConversion = findMethod(clazz,
					methodName, classes);

			if (methodAndConversion == null) {
				final StringBuilder buffer = new StringBuilder();
				buffer.append(clazz.getName());
				buffer.append('.');
				buffer.append(methodName);
				throw new NoSuchMethodException(buffer.toString());
			} else {
				convert(objects, methodAndConversion.biConversions,
						methodAndConversion.intConversions);
				final Method method = methodAndConversion.method;
				method.setAccessible(true);
				return method.invoke(object, objects);
			}
		} else {
			final List<Object> newArgs = new ArrayList<>(args.size() + 1);
			if (object == null) {
				newArgs.add(clazz);
			} else {
				newArgs.add(object);
			}
			newArgs.addAll(args);
			return enhancerMethod.apply(newArgs);
		}
	}

	public static List<Object> buildNewArgs(final Environement environement,
			final List<Node> args, final Object object) throws Exception {
		final List<Object> newArgs = new ArrayList<>(args.size() + 1);
		newArgs.add(object);
		for (final Node node : args) {
			newArgs.add(node.eval(environement));
		}
		return newArgs;
	}

	private static MethodAndConversion findMethod(final Class<?> clazz,
			final String methodName, Class<?>[] classes) {
		MethodAndConversion res = new MethodAndConversion();
		for (final Method method : clazz.getMethods()) {
			if (method.getName().equals(methodName)) {
				final Class<?>[] parameterTypes = method.getParameterTypes();
				if (parameterTypes.length == classes.length) {
					res.method = method;
					res.biConversions.clear();
					res.intConversions.clear();
					boolean match = true;
					int i = 0;
					for (final Class<?> c : classes) {
						final Class<?> parameterType = parameterTypes[i];
						if (Util.needBiConversion(c, parameterType)) {
							res.biConversions.add(i);
						} else if (Util.needIntConversion(c, parameterType)) {
							res.intConversions.add(i);
						} else if (Util.isNotAssignableFrom(parameterType, c)) {
							match = false;
							break;
						}
						i++;
					}
					if (match) {
						return res;
					}
				}
			}
		}
		return null;
	}

	private static class MethodAndConversion {
		private Method method;
		private final List<Integer> biConversions = new ArrayList<>();
		private final List<Integer> intConversions = new ArrayList<>();
	}

	public static Object callConstructor(final Class<?> clazz,
			final Object[] objects, final Class<?>[] classes)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException {
		final Object res;
		final ConstructorAndConversion constructorAndConversion = findConstructor(
				clazz, classes);
		if (constructorAndConversion == null) {
			res = null;
		} else {
			convert(objects, constructorAndConversion.biConversions,
					constructorAndConversion.intConversions);
			final Constructor<?> constructor = constructorAndConversion.constructor;
			constructor.setAccessible(true);
			res = constructor.newInstance(objects);
		}
		return res;
	}

	private static ConstructorAndConversion findConstructor(
			final Class<?> clazz, final Class<?>[] classes) {
		final ConstructorAndConversion res = new ConstructorAndConversion();
		for (final Constructor<?> constructor : clazz.getConstructors()) {
			final Class<?>[] parameterTypes = constructor.getParameterTypes();
			if (parameterTypes.length == classes.length) {
				res.constructor = constructor;
				res.biConversions.clear();
				res.intConversions.clear();
				boolean match = true;
				int i = 0;
				for (final Class<?> c : classes) {
					final Class<?> parameterType = parameterTypes[i];
					if (needBiConversion(c, parameterType)) {
						res.biConversions.add(i);
					} else if (needIntConversion(c, parameterType)) {
						res.intConversions.add(i);
					} else if (isNotAssignableFrom(parameterType, c)) {
						match = false;
						break;
					}
					i++;
				}
				if (match) {
					return res;
				}
			}
		}
		return null;
	}

	private static class ConstructorAndConversion {
		private Constructor<?> constructor;
		private final List<Integer> biConversions = new ArrayList<>();
		private final List<Integer> intConversions = new ArrayList<>();
	}
}