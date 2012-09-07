package org.codi.interpreter.objet;


public class Reel implements Nombre, Objet {

	private final double valeur;

	public Reel(double valeur) {
		this.valeur = valeur;
	}

	public double getValeur() {
		return valeur;
	}

	@Override
	public Classe getClasse() {
		return Classe.REEL;
	}

	@Override
	public Nombre plus(Nombre n) {
		return n.plusReel(valeur);
	}

	@Override
	public Nombre moins(Nombre n) {
		return n.moinsReel(valeur);
	}

	@Override
	public Nombre fois(Nombre n) {
		return n.foisReel(valeur);
	}

	@Override
	public Booleen egal(Nombre n) {
		return n.egalReel(valeur);
	}

	@Override
	public Nombre sur(Nombre n) {
		return n.surReel(valeur);
	}

	@Override
	public Nombre plusEntier(long e) {
		return new Reel(e + valeur);
	}

	@Override
	public Nombre moinsEntier(long e) {
		return new Reel(e - valeur);
	}

	@Override
	public Nombre foisEntier(long e) {
		return new Reel(e * valeur);
	}

	@Override
	public Nombre surEntier(long e) {
		return new Reel(e / valeur);
	}

	@Override
	public Booleen egalEntier(long e) {
		return new Booleen(e == valeur);
	}

	@Override
	public Nombre plusReel(double r) {
		return new Reel(r + valeur);
	}

	@Override
	public Nombre moinsReel(double r) {
		return new Reel(r - valeur);
	}

	@Override
	public Nombre foisReel(double r) {
		return new Reel(r * valeur);
	}

	@Override
	public Nombre surReel(double r) {
		return new Reel(r / valeur);
	}

	@Override
	public Booleen egalReel(double r) {
		return new Booleen(r == valeur);
	}

}
