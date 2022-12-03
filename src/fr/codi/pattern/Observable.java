package fr.codi.pattern;

public interface Observable<T> extends Observator<T> {

	public abstract void addObservator(Observator<T> observator);
}