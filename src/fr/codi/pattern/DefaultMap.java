package fr.codi.pattern;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class DefaultMap<K, V> implements Map<K, V> {

	private final Map<K, V> inner;
	private final Function<K, V> factory;

	public DefaultMap(final Map<K, V> inner, final Function<K, V> factory) {
		this.inner = inner;
		this.factory = factory;
	}

	public DefaultMap(final Function<K, V> factory) {
		this(new LinkedHashMap<>(), factory);
	}

	@Override
	public void clear() {
		this.inner.clear();
	}

	@Override
	public boolean containsKey(final Object key) {
		return this.inner.containsKey(key);
	}

	@Override
	public boolean containsValue(final Object value) {
		return this.inner.containsValue(value);
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return this.inner.entrySet();
	}

	@Override
	public V get(final Object object) {
		V res = this.inner.get(object);
		if (res == null) {
			final K key = (K) object;
			res = this.factory.apply(key);
			this.inner.put(key, res);
		}
		return res;
	}

	@Override
	public boolean isEmpty() {
		return this.inner.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return this.inner.keySet();
	}

	@Override
	public V put(final K key, final V value) {
		return this.inner.put(key, value);
	}

	@Override
	public void putAll(final Map<? extends K, ? extends V> other) {
		this.inner.putAll(other);
	}

	@Override
	public V remove(final Object key) {
		return this.inner.remove(key);
	}

	@Override
	public int size() {
		return this.inner.size();
	}

	@Override
	public Collection<V> values() {
		return this.inner.values();
	}

	@Override
	public int hashCode() {
		return this.inner.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		}
		DefaultMap other = (DefaultMap) obj;
		return this.factory.equals(other.factory)
				&& this.inner.equals(other.inner);
	}

	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder();
		buffer.append("DefaultMap[");
		buffer.append(this.factory);
		buffer.append(", ");
		buffer.append(this.inner);
		buffer.append("]");
		return buffer.toString();
	}
}