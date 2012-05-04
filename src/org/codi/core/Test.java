package org.codi.core;

import java.lang.reflect.Proxy;

import org.codi.interpreter.semantique.Semantique;


public class Test {
	public static void main(String[] args) {
		Semantique inner = null;
		Semantique instance = (Semantique) Proxy.newProxyInstance(
				Semantique.class.getClassLoader(),
				new Class[] {Semantique.class},
				new DecorateurInvocationHandler<Semantique>(inner));
	}
}
