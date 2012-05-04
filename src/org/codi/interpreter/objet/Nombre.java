package org.codi.interpreter.objet;

public interface Nombre {

	public abstract Nombre plus(Nombre n);
	public abstract Nombre moins(Nombre n);
	public abstract Nombre fois(Nombre n);
	public abstract Nombre sur(Nombre n);
	public abstract Booleen egal(Nombre n);

	public abstract Nombre plusEntier(long e);
	public abstract Nombre moinsEntier(long e);
	public abstract Nombre foisEntier(long e);
	public abstract Nombre surEntier(long e);
	public abstract Booleen egalEntier(long e);
	
	public abstract Nombre plusReel(double r);
	public abstract Nombre moinsReel(double r);
	public abstract Nombre foisReel(double r);
	public abstract Nombre surReel(double r);
	public abstract Booleen egalReel(double r);

}
