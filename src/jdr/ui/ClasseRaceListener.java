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
import jdr.Feature;
import jdr.Race;
import jdr.Shield;
import jdr.Skill;
import jdr.Weapon;
import jdr.WeaponType;

public class ClasseRaceListener implements ActionListener {

	private CharacterFrame frame;
	private boolean enable;
	private CalculerListener calculerListener;
	private ShieldListener shieldListener;
	private WeaponListener weaponListener;
	
	public ClasseRaceListener(
			CharacterFrame frame, CalculerListener calculerListener,
			ShieldListener shieldListener, WeaponListener weaponListener) {
		this.frame = frame;
		enable = true;
		this.calculerListener = calculerListener;
		this.shieldListener = shieldListener;
		this.weaponListener = weaponListener;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (enable) {
			enable = false;
			calculerListener.setEnable(false);
			shieldListener.setEnable(false);
			weaponListener.setEnable(false);

			String nomClasse = (String) frame.getChoixClasse().getSelectedItem();
			Classe classe = Classe.getClasseByName(nomClasse);

			String nomRace = (String) frame.getChoixRace().getSelectedItem();
			Race race = Race.getRaceByName(nomRace);

			JComboBox choixArmure = frame.getChoixArmure();
			int selectedIndex = choixArmure.getSelectedIndex();
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

			JComboBox choixBouclier = frame.getChoixBouclier();
			choixBouclier.removeAllItems();

			for(Shield shield : Shield.values()) {
				if (armorProficiencies.contains(shield.getArmorType())) {
					choixBouclier.addItem(shield.getShieldName());
				}
			}
			choixBouclier.setSelectedIndex(choixBouclier.getItemCount() - 1);

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

			JComboBox choixClasseOption = frame.getChoixClasseOption();
			selectedIndex = choixClasseOption.getSelectedIndex();
			choixClasseOption.removeAllItems();

			for(ClasseOption option : ClasseOption.getClasseOptions(classe)) {
				choixClasseOption.addItem(option.getNom());
			}
			choixClasseOption.setSelectedIndex(
					Math.min(selectedIndex, choixClasseOption.getItemCount() - 1 ));

			JComboBox choixArme = frame.getChoixArme();
			choixArme.removeAllItems();

			List<WeaponType> weaponProficiencies = classe.getWeaponProficiencies();

			String nomOption = (String) choixClasseOption.getSelectedItem();
			ClasseOption option = ClasseOption.getClasseOption(nomOption);

			List<Feature> features = new ArrayList<Feature>();
			features.addAll(race.getRaceFeatures());
			features.addAll(option.getFeatures());

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
				}
			}
			choixArme.setSelectedIndex(0);

			enable = true;
			calculerListener.setEnable(true);
			shieldListener.setEnable(true);
			weaponListener.setEnable(true);

			calculerListener.actionPerformed(event);
		}
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
