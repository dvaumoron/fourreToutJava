package fr.codi.pattern;

import java.util.ArrayList;
import java.util.List;

public class ObservableImpl<T> implements Observable<T> {
	
	private final List<Observator<T>> observators = new ArrayList<>();

	@Override
	public void addObservator(final Observator<T> observator) {
		this.observators.add(observator);
	}

	@Override
	public void notify(final T object) {
		for (final Observator<T> observator : this.observators) {
			observator.notify(object);
		}
	}
}