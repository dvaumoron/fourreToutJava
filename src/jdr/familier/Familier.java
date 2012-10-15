package jdr.familier;

import java.util.ArrayList;
import java.util.List;

public class Familier {

	private FamilierTemplate template;
	private int niveau;
	private int vie;
	private List<Effet> effets;

	public Familier(FamilierTemplate template, int niveau) {
		this.template = template;
		this.niveau = niveau;
		init();
		
	}

	private void init() {
		vie = template.getVieNiveauUn() + (niveau - 1) * template.getVieParNiveau();
		effets = new ArrayList<Effet>();
	}
	
}
