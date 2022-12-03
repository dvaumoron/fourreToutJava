package fr.codi.pattern;

public interface Observator<T> {

	public abstract void notify(T object);
}
