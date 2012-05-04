package org.codi.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DecorateurInvocationHandler<T> implements InvocationHandler {

	private final T inner;

	public DecorateurInvocationHandler(T inner) {
		this.inner = inner;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Class<?>[] parameterTypes = new Class[args.length];
		int i = 0;
		for(Object o : args) {
			parameterTypes[i] = o.getClass();
			i++;
		}
		
		Method innerMethod = inner.getClass().getMethod(
				method.getName(), parameterTypes);
		return innerMethod.invoke(inner, args);
	}

}
