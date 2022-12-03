package fr.jlisp.interpreteur.runtime;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Enhancers {

	private final Map<Class<?>, Map<String, Fonction>> map = new LinkedHashMap<>();

	public Fonction get(final Class<?> clazz, final String methodName) {
		Fonction method = null;
		final Map<String, Fonction> enhancer = this.map.get(clazz);
		if (enhancer != null) {
			method = enhancer.get(methodName);
		}
		if (method == null) {
			for (final Entry<Class<?>, Map<String, Fonction>> entry : this.map
					.entrySet()) {
				if (entry.getKey().isAssignableFrom(clazz)) {
					method = entry.getValue().get(methodName);
					if (method != null) {
						break;
					}
				}
			}
		}
		return method;
	}

	public void put(final Class<?> clazz, final String methodName,
			final Fonction method) {
		Map<String, Fonction> enhancer = this.map.get(clazz);
		if (enhancer == null) {
			enhancer = new LinkedHashMap<>();
			this.map.put(clazz, enhancer);
		}
		enhancer.put(methodName, method);
	}

	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder();
		buffer.append("Enhancers");
		buffer.append(this.map);
		return buffer.toString();
	}
}