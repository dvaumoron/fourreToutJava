package org.codi.core;

import gnu.mapping.Environment;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import kawa.standard.Scheme;

import org.codi.interpreter.semantique.Semantique;

import template.Rope;

public class Test {

	public static void main(String[] args) throws Throwable {
		Semantique inner = null;
		Semantique instance = (Semantique) Proxy.newProxyInstance(
				Semantique.class.getClassLoader(),
				new Class[] {Semantique.class},
				new DecorateurInvocationHandler<Semantique>(inner));
		
		System.out.println(buildDecorator(List.class));
		System.out.println("\n");
		System.out.println(buildDecorator(ArrayList.class));

		Scheme.registerEnvironment();
		Environment current = new Scheme().getEnvironment();
		Scheme.eval("(define (carre x) (* x x))", current);
		Scheme.eval("(define (fromTo a b) (if (>= a b) '() (cons a (fromTo (+ 1 a) b))))", current);
		System.out.println(Scheme.eval("(apply + (map carre (fromTo 1 11)))", current));
	}

	private static String buildDecorator(Class<?> aClass) {
		Rope rope = new Rope();
		
		String[] classNameSplit = aClass.getName().split("\\.");
		String className = classNameSplit[classNameSplit.length - 1];
		Rope decoratorName = new Rope().append(className).append("Decorator");
		rope.append("public class ").append(decoratorName).append(" ");
		if (aClass.isInterface()) {
			rope.append("implements");
		} else {
			rope.append("extends");
		}
		rope.append(' ').append(className).append(" {\n\n\tprivate ");
		rope.append(className).append(" inner;\n\n\tpublic ").append(decoratorName);
		rope.append(" (").append(className).append(" inner) {\n\t\tthis.inner = inner;\n\t}\n\n");

		for(Method m : aClass.getMethods()) {
			int mod = m.getModifiers();
			if (!Modifier.isPrivate(mod) && !Modifier.isFinal(mod) && !Modifier.isStatic(mod)) {
				rope.append('\t');
				if (Modifier.isProtected(mod)) {
					rope.append("protected ");
				} else if (Modifier.isPublic(mod)) {
					rope.append("public ");
				}
				if (Modifier.isSynchronized(mod)) {
					rope.append("synchronized ");
				}
				String returnTypeName = getTypeName(m.getReturnType());
				rope.append(returnTypeName).append(' ').append(m.getName()).append(" (");
				int i = 0;
				boolean first = true;
				for(Class<?> argType : m.getParameterTypes()) {
					if (first) {
						first = false;
					} else {
						rope.append(", ");
					}
					rope.append(getTypeName(argType)).append(" arg").append(++i);
				}
				rope.append(") {\n\t\t");
				if (!"void".equals(returnTypeName)) {
					rope.append("return ");
				}
				rope.append("inner.").append(m.getName()).append("(");
				first = true;
				for(int j = 1; j <= i; ++j) {
					if (first) {
						first = false;
					} else {
						rope.append(", ");
					}
					rope.append("arg").append(j);
				}
				rope.append(");\n\t}\n\n");
			}
		}
		
		rope.append("}");
		return rope.toString(); 
	}

	private static String getTypeName(Class<?> type) {
		return type.isArray() ? new Rope().append(type.getComponentType().getName()).append("[]").toString() : type.getName();
	}
	
}
