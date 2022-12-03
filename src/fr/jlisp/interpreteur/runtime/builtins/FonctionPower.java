package fr.jlisp.interpreteur.runtime.builtins;

import java.util.List;

import fr.jlisp.interpreteur.runtime.Fonction;

public enum FonctionPower implements Fonction {

	POWER;

	@Override
	public String getName() {
		return "**";
	}

	@Override
	public Object apply(final List<Object> args) {
		final Number arg0 = (Number) args.get(0);
		final Number arg1 = (Number) args.get(1);
		final double res = Math.pow(arg0.doubleValue(), arg1.doubleValue());
		if (arg0 instanceof Float || arg0 instanceof Double
				|| arg1 instanceof Float || arg1 instanceof Double) {
			return res;
		} else {
			return Double.valueOf(res).longValue();
		}
	}
}