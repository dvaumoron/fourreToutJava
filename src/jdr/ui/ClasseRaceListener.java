package jdr.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import jdr.Armor;
import jdr.ArmorType;
import jdr.Classe;
import jdr.ClasseOption;
import jdr.DemiClasse;
import jdr.Feature;
import jdr.HybridClasse;
import jdr.Race;
import jdr.Shield;
import jdr.Skill;
import jdr.Weapon;
import jdr.WeaponProperty;
import jdr.WeaponType;

public class ClasseRaceListener implements ActionListener {

	private CharacterFrame frame;
	private boolean enable;
	private CalculerListener calculerListener;
	private WeaponListener weaponListener;
	private LeftHandListener leftHandListener;
	
	public ClasseRaceListener(
			CharacterFrame frame, CalculerListener calculerListener,
			WeaponListener weaponListener, LeftHandListener leftHandListener) {
		this.frame = frame;
		enable = true;
		this.calculerListener = calculerListener;
		this.weaponListener = weaponListener;
		this.leftHandListener = leftHandListener;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (enable) {
			enable = false;
			calculerListener.setEnable(false);
			weaponListener.setEnable(false);
			leftHandListener.setEnable(false);

			String nomClasse = (String) frame.getChoixClasse().getSelectedItem();
			Classe classe = Classe.getClasseByName(nomClasse);

			String nomRace = (String) frame.getChoixRace().getSelectedItem();
			Race race = Race.getRaceByName(nomRace);

			List<Feature> features = new ArrayList<Feature>();
			features.addAll(race.getRaceFeatures());

			JComboBox choixClasseOption = frame.getChoixClasseOption();
			int selectedIndex = choixClasseOption.getSelectedIndex();
			choixClasseOption.removeAllItems();

			if (classe instanceof DemiClasse) {
				for(DemiClasse demiClasse : DemiClasse.values()) {
					if (demiClasse != classe) {
						choixClasseOption.addItem(demiClasse.getNom());
					}
				}
				choixClasseOption.setSelectedIndex(
						Math.min(selectedIndex, choixClasseOption.getItemCount() - 1 ));

				classe = new HybridClasse((DemiClasse) classe, (DemiClasse) Classe.getClasseByName((String) choixClasseOption.getSelectedItem()));
			} else {

				for(ClasseOption option : ClasseOption.getClasseOptions(classe)) {
					choixClasseOption.addItem(option.getNom());
				}
				choixClasseOption.setSelectedIndex(
						Math.min(selectedIndex, choixClasseOption.getItemCount() - 1 ));

				String nomOption = (String) choixClasseOption.getSelectedItem();
				ClasseOption option = ClasseOption.getClasseOption(nomOption);

				features.addAll(option.getFeatures());
			}

			JComboBox choixArmure = frame.getChoixArmure();
			selectedIndex = choixArmure.getSelectedIndex();
			choixArmure.removeAllItems();

			List<ArmorType> armorProficiencies = classe.getArmorProficiencies();

			for(Armor armor : Armor.values()) {
				if (armorProficiencies.contains(armor.getArmorType())) {
					choixArmure.addItem(armor.getArmorName());
				}
			}

			int itemCount = choixArmure.getItemCount();
			while (selectedIndex >= itemCount) {
				selectedIndex -= 7;
			}

			itemCount -= 7;
			while (selectedIndex < itemCount) {
				selectedIndex += 7;
			}

			choixArmure.setSelectedIndex(selectedIndex);

			JComboBox choixMainGauche = frame.getChoixMainGauche();
			choixMainGauche.removeAllItems();

			for(Shield shield : Shield.values()) {
				if (armorProficiencies.contains(shield.getArmorType())) {
					choixMainGauche.addItem(shield.getShieldName());
				}
			}
			choixMainGauche.setSelectedIndex(choixMainGauche.getItemCount() - 1);

			manageSkills(classe);

			JComboBox choixArme = frame.getChoixArme();
			choixArme.removeAllItems();

			List<WeaponType> weaponProficiencies = classe.getWeaponProficiencies();

			boolean oneHandedInOffhand = false;
			for (Feature feature : features) {
				if (feature.isOneHandedInOffhand()) {
					oneHandedInOffhand = true;
					break;
				}
			}

			choixArme.addItem(Weapon.UNARMED_ATTACK.getNom());
			for(Weapon weapon : Weapon.values()) {
				boolean proficiency = false;
				for(WeaponType type : weapon.getTypes()) {
					if (weaponProficiencies.contains(type)) {
						proficiency = true;
						break;
					}
				}	
				for (Feature feature : features) {
					if (feature.getWeaponProficiencies().contains(weapon)) {
						proficiency = true;
						break;
					}
				}
				if (proficiency) {
					choixArme.addItem(weapon.getNom());
					if (weapon.getProperties().contains(WeaponProperty.OFF_HAND)
							|| (oneHandedInOffhand && !weapon.isTwoHanded())) {
						choixMainGauche.addItem(weapon.getNom());
					}
				}
			}
			choixArme.setSelectedIndex(0);

			enable = true;
			calculerListener.setEnable(true);
			weaponListener.setEnable(true);
			leftHandListener.setEnable(true);

			calculerListener.actionPerformed(event);
		}
	}

	private void manageSkills(Classe classe) {
		List<Skill> classSkills = classe.getClassSkills();
		List<Skill> trainedSkills = classe.getTrainedSkills();

		manageSkill(
				classSkills, trainedSkills, frame.getStarAcrobatics(),
				frame.getBoxAcrobatics(), Skill.ACROBATICS);
		manageSkill(
				classSkills, trainedSkills, frame.getStarArcana(),
				frame.getBoxArcana(), Skill.ARCANA);
		manageSkill(
				classSkills, trainedSkills, frame.getStarAthletics(),
				frame.getBoxAthletics(), Skill.ATHLETICS);
		manageSkill(
				classSkills, trainedSkills, frame.getStarBluff(),
				frame.getBoxBluff(), Skill.BLUFF);
		manageSkill(
				classSkills, trainedSkills, frame.getStarDiplomacy(),
				frame.getBoxDiplomacy(), Skill.DIPLOMACY);
		manageSkill(
				classSkills, trainedSkills, frame.getStarDungeoneering(),
				frame.getBoxDungeoneering(), Skill.DUNGEONEERING);
		manageSkill(
				classSkills, trainedSkills, frame.getStarEndurance(),
				frame.getBoxEndurance(), Skill.ENDURANCE);
		manageSkill(
				classSkills, trainedSkills, frame.getStarHeal(),
				frame.getBoxHeal(), Skill.HEAL);
		manageSkill(
				classSkills, trainedSkills, frame.getStarHistory(),
				frame.getBoxHistory(), Skill.HISTORY);
		manageSkill(
				classSkills, trainedSkills, frame.getStarInsight(),
				frame.getBoxInsight(), Skill.INSIGHT);
		manageSkill(
				classSkills, trainedSkills, frame.getStarIntimidate(),
				frame.getBoxIntimidate(), Skill.INTIMIDATE);
		manageSkill(
				classSkills, trainedSkills, frame.getStarNature(),
				frame.getBoxNature(), Skill.NATURE);
		manageSkill(
				classSkills, trainedSkills, frame.getStarPerception(),
				frame.getBoxPerception(), Skill.PERCEPTION);
		manageSkill(
				classSkills, trainedSkills, frame.getStarReligion(),
				frame.getBoxReligion(), Skill.RELIGION);
		manageSkill(
			classSkills, trainedSkills, frame.getStarStealth(),
			frame.getBoxStealth(), Skill.STEALTH);
		manageSkill(
				classSkills, trainedSkills, frame.getStarStreetwise(),
				frame.getBoxStreetwise(), Skill.STREETWISE);
		manageSkill(
				classSkills, trainedSkills, frame.getStarThievery(),
				frame.getBoxThievery(), Skill.THIEVERY);
	}

	private static void manageSkill(List<Skill> classSkills, List<Skill> trainedSkills,
			JLabel star, JCheckBox box, Skill skill) {
		if (classSkills.contains(skill)) {
			star.setText("*");
		} else {
			star.setText("");
		}
		if (trainedSkills.contains(skill)) {
			box.setSelected(true);
			box.setEnabled(false);
		} else {
			box.setSelected(false);
			box.setEnabled(true);
		}
	}

}
