package org.codi.interpreter.objet;


public class Entier implements Nombre, Objet {

	private final long valeur;

	public Entier(long valeur) {
		this.valeur = valeur;
	}

	public long getValeur() {
		return valeur;
	}

	@Override
	public Classe getClasse() {
		return Classe.ENTIER;
	}

	@Override
	public Nombre plus(Nombre n) {
		return n.plusEntier(valeur);
	}

	@Override
	public Nombre moins(Nombre n) {
		return n.moinsEntier(valeur);
	}

	@Override
	public Nombre fois(Nombre n) {
		return n.foisEntier(valeur);
	}
	
	@Override
	public Nombre sur(Nombre n) {
		return n.surEntier(valeur);
	}

	@Override
	public Booleen egal(Nombre n) {
		return n.egalEntier(valeur);
	}

	@Override
	public Nombre plusEntier(long e) {
		return new Entier(e + valeur);
	}

	@Override
	public Nombre moinsEntier(long e) {
		return new Entier(e - valeur);
	}

	@Override
	public Nombre foisEntier(long e) {
		return new Entier(e * valeur);
	}

	@Override
	public Nombre surEntier(long e) {
		return new Entier(e / valeur);
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
