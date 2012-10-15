package jdr.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;

import jdr.Language;
import jdr.Personnage;

public class CalculerListener implements ActionListener {

	private CharacterFrame frame;
	private boolean enable;
	
	public CalculerListener(CharacterFrame frame) {
		this.frame = frame;
		this.enable = true;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (enable) {
			String nom = frame.getTexteNom().getText();
			int niveau = Integer.parseInt(frame.getTexteNiveau().getText());
			int force = Integer.parseInt(frame.getTexteForce().getText());
			int constitution = Integer.parseInt(frame.getTexteConstitution().getText());
			int dexterite = Integer.parseInt(frame.getTexteDexterite().getText());
			int intelligence = Integer.parseInt(frame.getTexteIntelligence().getText());
			int sagesse = Integer.parseInt(frame.getTexteSagesse().getText());
			int charisme = Integer.parseInt(frame.getTexteCharisme().getText());
			String nomClasse = (String) frame.getChoixClasse().getSelectedItem();
			String nomOption = (String) frame.getChoixClasseOption().getSelectedItem();
			String nomRace = (String) frame.getChoixRace().getSelectedItem();
			String nomArmure = (String) frame.getChoixArmure().getSelectedItem();
			String nomArme = (String) frame.getChoixArme().getSelectedItem();
			String nomMainGauche = (String) frame.getChoixMainGauche().getSelectedItem();
			String nomCarac = (String) frame.getChoixCarac().getSelectedItem();
			String nomArmeMagique = (String) frame.getChoixArmeMagique().getSelectedItem();
			boolean acrobatics = frame.getBoxAcrobatics().isSelected();
			boolean arcana = frame.getBoxArcana().isSelected();
			boolean athletics = frame.getBoxAthletics().isSelected();
			boolean bluff = frame.getBoxBluff().isSelected();
			boolean diplomacy = frame.getBoxDiplomacy().isSelected();
			boolean dungeoneering = frame.getBoxDungeoneering().isSelected();
			boolean endurance = frame.getBoxEndurance().isSelected();
			boolean heal = frame.getBoxHeal().isSelected();
			boolean history = frame.getBoxHistory().isSelected();
			boolean insight = frame.getBoxInsight().isSelected();
			boolean intimidate = frame.getBoxIntimidate().isSelected();
			boolean nature = frame.getBoxNature().isSelected();
			boolean perception = frame.getBoxPerception().isSelected();
			boolean religion = frame.getBoxReligion().isSelected();
			boolean stealth = frame.getBoxStealth().isSelected();
			boolean streetwise = frame.getBoxStreetwise().isSelected();
			boolean thievery = frame.getBoxThievery().isSelected();

			Personnage personnage = new Personnage(
					nom, niveau, force, constitution, dexterite, intelligence, sagesse,
					charisme, nomClasse, nomOption, nomRace, nomArmure, nomArme,
					nomMainGauche, nomCarac, nomArmeMagique, acrobatics, arcana,
					athletics, bluff, diplomacy, dungeoneering, endurance, heal, history,
					insight, intimidate, nature, perception, religion, stealth, streetwise,
					thievery);

			frame.getValeurForce().setText(Integer.toString(personnage.getForce()));
			frame.getValeurConstitution().setText(Integer.toString(personnage.getConstitution()));
			frame.getValeurDexterite().setText(Integer.toString(personnage.getDexterite()));
			frame.getValeurIntelligence().setText(Integer.toString(personnage.getIntelligence()));
			frame.getValeurSagesse().setText(Integer.toString(personnage.getSagesse()));
			frame.getValeurCharisme().setText(Integer.toString(personnage.getCharisme()));

			frame.getValeurForceMod().setText(Integer.toString(personnage.getForceMod()));
			frame.getValeurConstitutionMod().setText(Integer.toString(personnage.getConstitutionMod()));
			frame.getValeurDexteriteMod().setText(Integer.toString(personnage.getDexteriteMod()));
			frame.getValeurIntelligenceMod().setText(Integer.toString(personnage.getIntelligenceMod()));
			frame.getValeurSagesseMod().setText(Integer.toString(personnage.getSagesseMod()));
			frame.getValeurCharismeMod().setText(Integer.toString(personnage.getCharismeMod()));

			frame.getValeurForceModDemi().setText(Integer.toString(personnage.getForceModDemi()));
			frame.getValeurConstitutionModDemi().setText(Integer.toString(personnage.getConstitutionModDemi()));
			frame.getValeurDexteriteModDemi().setText(Integer.toString(personnage.getDexteriteModDemi()));
			frame.getValeurIntelligenceModDemi().setText(Integer.toString(personnage.getIntelligenceModDemi()));
			frame.getValeurSagesseModDemi().setText(Integer.toString(personnage.getSagesseModDemi()));
			frame.getValeurCharismeModDemi().setText(Integer.toString(personnage.getCharismeModDemi()));

			frame.getValeurAC().setText(Integer.toString(personnage.getCA()));
			frame.getValeurVigueur().setText(Integer.toString(personnage.getVigueur()));
			frame.getValeurReflexe().setText(Integer.toString(personnage.getReflexe()));
			frame.getValeurVolonte().setText(Integer.toString(personnage.getVolonte()));

			frame.getValeurInit().setText(Integer.toString(personnage.getInitiative()));

			frame.getValeurMaxHP().setText(Integer.toString(personnage.getMaxHP()));
			frame.getValeurBloodied().setText(Integer.toString(personnage.getBloodiedHP()));
			frame.getValeurSurgeValue().setText(Integer.toString(personnage.getSurgeValue()));
			frame.getValeurSurgesDay().setText(Integer.toString(personnage.getSurgesByDay()));

			frame.getValeurAcrobatics().setText(Integer.toString(personnage.getAcrobatics()));
			frame.getValeurArcana().setText(Integer.toString(personnage.getArcana()));
			frame.getValeurAthletics().setText(Integer.toString(personnage.getAthletics()));
			frame.getValeurBluff().setText(Integer.toString(personnage.getBluff()));
			frame.getValeurDiplomacy().setText(Integer.toString(personnage.getDiplomacy()));
			frame.getValeurDungeoneering().setText(Integer.toString(personnage.getDungeoneering()));
			frame.getValeurEndurance().setText(Integer.toString(personnage.getEndurance()));
			frame.getValeurHeal().setText(Integer.toString(personnage.getHeal()));
			frame.getValeurHistory().setText(Integer.toString(personnage.getHistory()));
			frame.getValeurInsight().setText(Integer.toString(personnage.getInsight()));
			frame.getValeurIntimidate().setText(Integer.toString(personnage.getIntimidate()));
			frame.getValeurNature().setText(Integer.toString(personnage.getNature()));
			frame.getValeurPerception().setText(Integer.toString(personnage.getPerception()));
			frame.getValeurReligion().setText(Integer.toString(personnage.getReligion()));
			frame.getValeurStealth().setText(Integer.toString(personnage.getStealth()));
			frame.getValeurStreetwise().setText(Integer.toString(personnage.getStreetwise()));
			frame.getValeurThievery().setText(Integer.toString(personnage.getThievery()));

			frame.getValeurSkillNumber().setText(Integer.toString(personnage.getSkillNumber()));

			frame.getValeurSpeed().setText(Integer.toString(personnage.getSpeed()));

			frame.getValeurPassiveInsight().setText(Integer.toString(personnage.getPassiveInsight()));
			frame.getValeurPassivePerception().setText(Integer.toString(personnage.getPassivePerception()));

			JLabel[] valeursLanguage = frame.getValeursLanguage();
			for(JLabel label : valeursLanguage) {
				label.setText("");
			}
			int index = 0;
			List<Language> languages = personnage.getLanguages();
			for(Language language : languages) {
				valeursLanguage[index].setText(language.getNom());
				index++;
			}

			frame.getValeurAttaque().setText(Integer.toString(personnage.getAttaque()));
			frame.getValeurDegat().setText(personnage.getDegats());
			frame.getValeurAttaqueGauche().setText(Integer.toString(personnage.getAttaqueGauche()));
			frame.getValeurDegatGauche().setText(personnage.getDegatsGauche());

			frame.getValeurSize().setText(personnage.getSize());
			frame.getValeurPoids().setText(personnage.getPoids());
			frame.getValeurTaille().setText(personnage.getTaille());
		}
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
