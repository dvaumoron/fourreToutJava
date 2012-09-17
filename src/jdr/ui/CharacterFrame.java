package jdr.ui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jdr.Ability;
import jdr.Armor;
import jdr.Classe;
import jdr.ClasseOption;
import jdr.Race;
import jdr.Shield;
import jdr.Weapon;

public class CharacterFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String DIX = "10";

	private JTextField texteNom;
	private JTextField texteNiveau;
	private JLabel valeurInit;
	private JTextField texteForce;
	private JLabel valeurForce;
	private JLabel valeurForceMod;
	private JLabel valeurForceModDemi;
	private JTextField texteConstitution;
	private JLabel valeurConstitution;
	private JLabel valeurConstitutionMod;
	private JLabel valeurConstitutionModDemi;
	private JLabel valeurVigueur;
	private JTextField texteDexterite;
	private JLabel valeurDexterite;
	private JLabel valeurDexteriteMod;
	private JLabel valeurDexteriteModDemi;
	private JTextField texteIntelligence;
	private JLabel valeurIntelligence;
	private JLabel valeurIntelligenceMod;
	private JLabel valeurIntelligenceModDemi;
	private JLabel valeurReflexe;
	private JTextField texteSagesse;
	private JLabel valeurSagesse;
	private JLabel valeurSagesseMod;
	private JLabel valeurSagesseModDemi;
	private JTextField texteCharisme;
	private JLabel valeurCharisme;
	private JLabel valeurCharismeMod;
	private JLabel valeurCharismeModDemi;
	private JLabel valeurVolonte;
	private JComboBox choixClasse;
	private JComboBox choixClasseOption;
	private JComboBox choixRace;
	private JLabel valeurMaxHP;
	private JLabel valeurBloodied;
	private JLabel valeurSurgeValue;
	private JLabel valeurSurgesDay;
	private JLabel valeurAC;
	private JComboBox choixArmure;
	private JComboBox choixBouclier;
	private JComboBox choixArme;
	private JComboBox choixCarac;
	private JLabel starAcrobatics;
	private JCheckBox boxAcrobatics;
	private JLabel valeurAcrobatics;
	private JLabel starArcana;
	private JCheckBox boxArcana;
	private JLabel valeurArcana;
	private JLabel starAthletics;
	private JCheckBox boxAthletics;
	private JLabel valeurAthletics;
	private JLabel starBluff;
	private JCheckBox boxBluff;
	private JLabel valeurBluff ;
	private JLabel starDiplomacy;
	private JCheckBox boxDiplomacy;
	private JLabel valeurDiplomacy;
	private JLabel starDungeoneering;
	private JCheckBox boxDungeoneering;
	private JLabel valeurDungeoneering;
	private JLabel starEndurance;
	private JCheckBox boxEndurance;
	private JLabel valeurEndurance;
	private JLabel starHeal;
	private JCheckBox boxHeal;
	private JLabel valeurHeal;
	private JLabel starHistory;
	private JCheckBox boxHistory;
	private JLabel valeurHistory;
	private JLabel starInsight;
	private JCheckBox boxInsight;
	private JLabel valeurInsight;
	private JLabel starIntimidate;
	private JCheckBox boxIntimidate;
	private JLabel valeurIntimidate;
	private JLabel starNature;
	private JCheckBox boxNature;
	private JLabel valeurNature;
	private JLabel starPerception;
	private JCheckBox boxPerception;
	private JLabel valeurPerception;
	private JLabel starReligion;
	private JCheckBox boxReligion;
	private JLabel valeurReligion;
	private JLabel starStealth;
	private JCheckBox boxStealth;
	private JLabel valeurStealth;
	private JLabel starStreetwise;
	private JCheckBox boxStreetwise;
	private JLabel valeurStreetwise;
	private JLabel starThievery;
	private JCheckBox boxThievery;
	private JLabel valeurThievery;
	private JLabel valeurSkillNumber;
	private JLabel valeurSpeed;
	private JLabel valeurPassiveInsight;
	private JLabel valeurPassivePerception;
	private JLabel[] valeursLanguage;

	private JLabel valeurAttaque;
	private JLabel valeurDegat;

	public CharacterFrame() {
		build();
	}

	private void build() {
		setTitle("My Character Builder");
		setSize(900, 1100);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
	}

	private Container buildContentPane() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		CalculerListener calculerListener = new CalculerListener(this);
		ShieldListener shieldListener = new ShieldListener(this, calculerListener);
		WeaponListener weaponListener = new WeaponListener(this, calculerListener);
		ClasseRaceListener classeRaceListener = new ClasseRaceListener(
				this, calculerListener, shieldListener, weaponListener);

		texteNom = new JTextField();
		texteNom.setColumns(12);
		texteNom.setText("Nom du personnage");
		panel.add(texteNom, getGridBagConstraints(0, 0, 30, 10));

		JLabel labelNiveau = new JLabel("Niveau");
		panel.add(labelNiveau, getGridBagConstraints(1, 0, 2, 1, 20, 10));

		texteNiveau = new JTextField();
		texteNiveau.setColumns(2);
		texteNiveau.setText("1");
		panel.add(texteNiveau, getGridBagConstraints(3, 0, 10, 10));
		texteNiveau.addActionListener(calculerListener);

		choixClasse = new JComboBox(Classe.getClassNameList().toArray());
		panel.add(choixClasse, getGridBagConstraints(4, 0, 2, 1, 30, 10));
		choixClasse.addActionListener(classeRaceListener);

		choixClasseOption = new JComboBox(new String[] {ClasseOption.NONE.getNom()});
		panel.add(choixClasseOption, getGridBagConstraints(6, 0, 2, 1, 30, 10));
		choixClasseOption.setSelectedIndex(0);
		choixClasseOption.addActionListener(classeRaceListener);

		choixRace = new JComboBox(Race.getRaceNameList().toArray());
		panel.add(choixRace, getGridBagConstraints(0, 1, 3, 1, 50, 10));
		choixRace.addActionListener(classeRaceListener);

		JLabel labelInit = new JLabel("Initiative");
		panel.add(labelInit, getGridBagConstraints(0, 2, 30, 10));

		valeurInit = new JLabel("-");
		panel.add(valeurInit, getGridBagConstraints(1, 2, 10, 10));

		JLabel labelMod = new JLabel("Mod");
		panel.add(labelMod, getGridBagConstraints(3, 2, 10, 10));

		JLabel labelModNiv = new JLabel("+ 1/2 Niv");
		panel.add(labelModNiv, getGridBagConstraints(4, 2, 10, 10));

		JLabel labelAC = new JLabel("CA");
		panel.add(labelAC, getGridBagConstraints(5, 2, 20, 10));

		valeurAC = new JLabel("-");
		panel.add(valeurAC, getGridBagConstraints(6, 2, 10, 10));

		JLabel labelSpeed = new JLabel("Vitesse");
		panel.add(labelSpeed, getGridBagConstraints(7, 2, 20, 10));

		valeurSpeed = new JLabel("-");
		panel.add(valeurSpeed, getGridBagConstraints(8, 2, 10, 10));

		JLabel labelForce = new JLabel("Force");
		panel.add(labelForce, getGridBagConstraints(0, 3, 30, 10));

		texteForce = new JTextField();
		texteForce.setColumns(2);
		texteForce.setText(DIX);
		panel.add(texteForce, getGridBagConstraints(1, 3, 10, 10));
		texteForce.addActionListener(calculerListener);

		valeurForce = new JLabel("-");
		panel.add(valeurForce, getGridBagConstraints(2, 3, 10, 10));

		valeurForceMod = new JLabel("-");
		panel.add(valeurForceMod, getGridBagConstraints(3, 3, 10, 10));

		valeurForceModDemi = new JLabel("-");
		panel.add(valeurForceModDemi, getGridBagConstraints(4, 3, 10, 10));

		JLabel labelConstitution = new JLabel("Constitution");
		panel.add(labelConstitution, getGridBagConstraints(0, 4, 30, 10));

		texteConstitution = new JTextField();
		texteConstitution.setColumns(2);
		texteConstitution.setText(DIX);
		panel.add(texteConstitution, getGridBagConstraints(1, 4, 10, 10));
		texteConstitution.addActionListener(calculerListener);

		valeurConstitution = new JLabel("-");
		panel.add(valeurConstitution, getGridBagConstraints(2, 4, 10, 10));

		valeurConstitutionMod = new JLabel("-");
		panel.add(valeurConstitutionMod, getGridBagConstraints(3, 4, 10, 10));

		valeurConstitutionModDemi = new JLabel("-");
		panel.add(valeurConstitutionModDemi, getGridBagConstraints(4, 4, 10, 10));

		JLabel labelVigueur = new JLabel("Vigueur");
		panel.add(labelVigueur, getGridBagConstraints(5, 3, 1, 2, 20, 20));

		valeurVigueur = new JLabel("-");
		panel.add(valeurVigueur, getGridBagConstraints(6, 3, 1, 2, 10, 20));

		JLabel labelPassiveInsight = new JLabel("Intuition Passive");
		panel.add(labelPassiveInsight, getGridBagConstraints(7, 3, 20, 10));

		valeurPassiveInsight = new JLabel("-");
		panel.add(valeurPassiveInsight, getGridBagConstraints(8, 3, 10, 10));

		JLabel labelPassivePerception = new JLabel("Perception Passive");
		panel.add(labelPassivePerception, getGridBagConstraints(7, 4, 20, 10));

		valeurPassivePerception = new JLabel("-");
		panel.add(valeurPassivePerception, getGridBagConstraints(8, 4, 10, 10));

		JLabel labelDexterite = new JLabel("Dexterité");
		panel.add(labelDexterite, getGridBagConstraints(0, 5, 30, 10));

		texteDexterite = new JTextField();
		texteDexterite.setColumns(2);
		texteDexterite.setText(DIX);
		panel.add(texteDexterite, getGridBagConstraints(1, 5, 10, 10));
		texteDexterite.addActionListener(calculerListener);

		valeurDexterite = new JLabel("-");
		panel.add(valeurDexterite, getGridBagConstraints(2, 5, 10, 10));

		valeurDexteriteMod = new JLabel("-");
		panel.add(valeurDexteriteMod, getGridBagConstraints(3, 5, 10, 10));

		valeurDexteriteModDemi = new JLabel("-");
		panel.add(valeurDexteriteModDemi, getGridBagConstraints(4, 5, 10, 10));

		JLabel labelIntelligence = new JLabel("Intelligence");
		panel.add(labelIntelligence, getGridBagConstraints(0, 6, 30, 10));

		texteIntelligence = new JTextField();
		texteIntelligence.setColumns(2);
		texteIntelligence.setText(DIX);
		panel.add(texteIntelligence, getGridBagConstraints(1, 6, 10, 10));
		texteIntelligence.addActionListener(calculerListener);

		valeurIntelligence = new JLabel("-");
		panel.add(valeurIntelligence, getGridBagConstraints(2, 6, 10, 10));

		valeurIntelligenceMod = new JLabel("-");
		panel.add(valeurIntelligenceMod, getGridBagConstraints(3, 6, 10, 10));

		valeurIntelligenceModDemi = new JLabel("-");
		panel.add(valeurIntelligenceModDemi, getGridBagConstraints(4, 6, 10, 10));

		JLabel labelReflexe = new JLabel("Reflexe");
		panel.add(labelReflexe, getGridBagConstraints(5, 5, 1, 2, 20, 20));

		valeurReflexe = new JLabel("-");
		panel.add(valeurReflexe, getGridBagConstraints(6, 5, 1, 2, 10, 20));

		JLabel labelAttaque = new JLabel("Attaque");
		panel.add(labelAttaque, getGridBagConstraints(7, 5, 20, 10));

		valeurAttaque = new JLabel("-");
		panel.add(valeurAttaque, getGridBagConstraints(8, 5, 20, 10));

		JLabel labelDegat = new JLabel("Dégâts");
		panel.add(labelDegat, getGridBagConstraints(7, 6, 20, 10));

		valeurDegat = new JLabel("-");
		panel.add(valeurDegat, getGridBagConstraints(8, 6, 20, 10));

		JLabel labelSagesse = new JLabel("Sagesse");
		panel.add(labelSagesse, getGridBagConstraints(0, 7, 30, 10));

		texteSagesse = new JTextField();
		texteSagesse.setColumns(2);
		texteSagesse.setText(DIX);
		panel.add(texteSagesse, getGridBagConstraints(1, 7, 10, 10));
		texteSagesse.addActionListener(calculerListener);

		valeurSagesse = new JLabel("-");
		panel.add(valeurSagesse, getGridBagConstraints(2, 7, 10, 10));

		valeurSagesseMod = new JLabel("-");
		panel.add(valeurSagesseMod, getGridBagConstraints(3, 7, 10, 10));

		valeurSagesseModDemi = new JLabel("-");
		panel.add(valeurSagesseModDemi, getGridBagConstraints(4, 7, 10, 10));

		JLabel labelCharisme = new JLabel("Charisme");
		panel.add(labelCharisme, getGridBagConstraints(0, 8, 30, 10));

		texteCharisme = new JTextField();
		texteCharisme.setColumns(2);
		texteCharisme.setText(DIX);
		panel.add(texteCharisme, getGridBagConstraints(1, 8, 10, 10));
		texteCharisme.addActionListener(calculerListener);

		valeurCharisme = new JLabel("-");
		panel.add(valeurCharisme, getGridBagConstraints(2, 8, 10, 10));

		valeurCharismeMod = new JLabel("-");
		panel.add(valeurCharismeMod, getGridBagConstraints(3, 8, 10, 10));

		valeurCharismeModDemi = new JLabel("-");
		panel.add(valeurCharismeModDemi, getGridBagConstraints(4, 8, 10, 10));

		JLabel labelVolonte = new JLabel("Volonté");
		panel.add(labelVolonte, getGridBagConstraints(5, 7, 1, 2, 20, 20));

		valeurVolonte = new JLabel("-");
		panel.add(valeurVolonte, getGridBagConstraints(6, 7, 1, 2, 10, 20));

		JLabel labelMaxHP = new JLabel("PV Max");
		panel.add(labelMaxHP, getGridBagConstraints(0, 9, 30, 10));

		valeurMaxHP = new JLabel("-");
		panel.add(valeurMaxHP, getGridBagConstraints(0, 10, 30, 10));

		JLabel labelBloodied = new JLabel("Péril");
		panel.add(labelBloodied, getGridBagConstraints(1, 9, 2, 1, 20, 10));

		valeurBloodied = new JLabel("-");
		panel.add(valeurBloodied, getGridBagConstraints(1, 10, 2, 1, 20, 10));

		JLabel labelSurgeValue = new JLabel("Valeur de récup"); 
		panel.add(labelSurgeValue, getGridBagConstraints(3, 9, 2, 1, 20, 10));

		valeurSurgeValue = new JLabel("-");
		panel.add(valeurSurgeValue, getGridBagConstraints(3, 10, 2, 1, 20, 10));

		JLabel labelSurgesDay = new JLabel("Récup/jour");
		panel.add(labelSurgesDay, getGridBagConstraints(5, 9, 20, 10));

		valeurSurgesDay = new JLabel("-");
		panel.add(valeurSurgesDay, getGridBagConstraints(5, 10, 20, 10));

		choixArmure = new JComboBox(Armor.getArmorNameList().toArray());
		panel.add(choixArmure, getGridBagConstraints(0, 11, 2, 1, 40, 10));
		choixArmure.addActionListener(calculerListener);

		choixBouclier = new JComboBox(Shield.getShieldNameList().toArray());
		panel.add(choixBouclier, getGridBagConstraints(2, 11, 2, 1, 20, 10));
		choixBouclier.addActionListener(shieldListener);

		choixArme = new JComboBox(Weapon.getWeaponNameList().toArray());
		panel.add(choixArme, getGridBagConstraints(4, 11, 2, 1, 30, 10));
		choixArme.addActionListener(weaponListener);

		choixCarac = new JComboBox(Ability.getAbilityNameList().toArray());
		panel.add(choixCarac, getGridBagConstraints(6, 11, 2, 1, 30, 10));
		choixCarac.addActionListener(calculerListener);

		JLabel labelSkill = new JLabel("Compétences");
		panel.add(labelSkill, getGridBagConstraints(0, 12, 30, 10));

		valeurSkillNumber = new JLabel("-");
		panel.add(valeurSkillNumber, getGridBagConstraints(2, 12, 10, 10));

		JLabel labelAcrobatics = new JLabel("Acrobaties");
		panel.add(labelAcrobatics, getGridBagConstraints(0, 13, 30, 10));

		starAcrobatics = new JLabel("*");
		panel.add(starAcrobatics, getGridBagConstraints(1, 13, 10, 10));

		boxAcrobatics = new JCheckBox();
		panel.add(boxAcrobatics, getGridBagConstraints(2, 13, 10, 10));
		boxAcrobatics.addActionListener(calculerListener);

		valeurAcrobatics = new JLabel("-");
		panel.add(valeurAcrobatics, getGridBagConstraints(3, 13, 10, 10));

		JLabel labelArcana = new JLabel("Arcanes");
		panel.add(labelArcana, getGridBagConstraints(0, 14, 30, 10));

		starArcana = new JLabel("*");
		panel.add(starArcana, getGridBagConstraints(1, 14, 10, 10));

		boxArcana = new JCheckBox();
		panel.add(boxArcana, getGridBagConstraints(2, 14, 10, 10));
		boxArcana.addActionListener(calculerListener);

		valeurArcana = new JLabel("-");
		panel.add(valeurArcana, getGridBagConstraints(3, 14, 10, 10));
		
		JLabel labelAthletics = new JLabel("Athlétisme");
		panel.add(labelAthletics, getGridBagConstraints(0, 15, 30, 10));

		starAthletics = new JLabel("*");
		panel.add(starAthletics, getGridBagConstraints(1, 15, 10, 10));

		boxAthletics = new JCheckBox();
		panel.add(boxAthletics, getGridBagConstraints(2, 15, 10, 10));
		boxAthletics.addActionListener(calculerListener);

		valeurAthletics = new JLabel("-");
		panel.add(valeurAthletics, getGridBagConstraints(3, 15, 10, 10));

		JLabel labelBluff = new JLabel("Bluff");
		panel.add(labelBluff, getGridBagConstraints(0, 16, 30, 10));

		starBluff = new JLabel("*");
		panel.add(starBluff, getGridBagConstraints(1, 16, 10, 10));

		boxBluff = new JCheckBox();
		panel.add(boxBluff, getGridBagConstraints(2, 16, 10, 10));
		boxBluff.addActionListener(calculerListener);

		valeurBluff = new JLabel("-");
		panel.add(valeurBluff, getGridBagConstraints(3, 16, 10, 10));

		JLabel labelDiplomacy = new JLabel("Diplomatie");
		panel.add(labelDiplomacy, getGridBagConstraints(0, 17, 30, 10));

		starDiplomacy = new JLabel("*");
		panel.add(starDiplomacy, getGridBagConstraints(1, 17, 10, 10));

		boxDiplomacy = new JCheckBox();
		panel.add(boxDiplomacy, getGridBagConstraints(2, 17, 10, 10));
		boxDiplomacy.addActionListener(calculerListener);

		valeurDiplomacy = new JLabel("-");
		panel.add(valeurDiplomacy, getGridBagConstraints(3, 17, 10, 10));

		JLabel labelDungeoneering = new JLabel("Exploration");
		panel.add(labelDungeoneering, getGridBagConstraints(0, 18, 30, 10));

		starDungeoneering = new JLabel("*");
		panel.add(starDungeoneering, getGridBagConstraints(1, 18, 10, 10));

		boxDungeoneering = new JCheckBox();
		panel.add(boxDungeoneering, getGridBagConstraints(2, 18, 10, 10));
		boxDungeoneering.addActionListener(calculerListener);

		valeurDungeoneering = new JLabel("-");
		panel.add(valeurDungeoneering, getGridBagConstraints(3, 18, 10, 10));

		JLabel labelEndurance = new JLabel("Endurance");
		panel.add(labelEndurance, getGridBagConstraints(0, 19, 30, 10));

		starEndurance = new JLabel("*");
		panel.add(starEndurance, getGridBagConstraints(1, 19, 10, 10));

		boxEndurance = new JCheckBox();
		panel.add(boxEndurance, getGridBagConstraints(2, 19, 10, 10));
		boxEndurance.addActionListener(calculerListener);

		valeurEndurance = new JLabel("-");
		panel.add(valeurEndurance, getGridBagConstraints(3, 19, 10, 10));

		JLabel labelHeal = new JLabel("Soins");
		panel.add(labelHeal, getGridBagConstraints(0, 20, 30, 10));

		starHeal = new JLabel("*");
		panel.add(starHeal, getGridBagConstraints(1, 20, 10, 10));

		boxHeal = new JCheckBox();
		panel.add(boxHeal, getGridBagConstraints(2, 20, 10, 10));
		boxHeal.addActionListener(calculerListener);

		valeurHeal = new JLabel("-");
		panel.add(valeurHeal, getGridBagConstraints(3, 20, 10, 10));

		JLabel labelHistory = new JLabel("Histoire");
		panel.add(labelHistory, getGridBagConstraints(0, 21, 30, 10));

		starHistory = new JLabel("*");
		panel.add(starHistory, getGridBagConstraints(1, 21, 10, 10));

		boxHistory = new JCheckBox();
		panel.add(boxHistory, getGridBagConstraints(2, 21, 10, 10));
		boxHistory.addActionListener(calculerListener);

		valeurHistory = new JLabel("-");
		panel.add(valeurHistory, getGridBagConstraints(3, 21, 10, 10));

		JLabel labelInsight = new JLabel("Intuition");
		panel.add(labelInsight, getGridBagConstraints(0, 22, 30, 10));

		starInsight = new JLabel("*");
		panel.add(starInsight, getGridBagConstraints(1, 22, 10, 10));

		boxInsight = new JCheckBox();
		panel.add(boxInsight, getGridBagConstraints(2, 22, 10, 10));
		boxInsight.addActionListener(calculerListener);

		valeurInsight = new JLabel("-");
		panel.add(valeurInsight, getGridBagConstraints(3, 22, 10, 10));

		JLabel labelIntimidate = new JLabel("Intimidation");
		panel.add(labelIntimidate, getGridBagConstraints(0, 23, 30, 10));

		starIntimidate = new JLabel("*");
		panel.add(starIntimidate, getGridBagConstraints(1, 23, 10, 10));

		boxIntimidate = new JCheckBox();
		panel.add(boxIntimidate, getGridBagConstraints(2, 23, 10, 10));
		boxIntimidate.addActionListener(calculerListener);

		valeurIntimidate = new JLabel("-");
		panel.add(valeurIntimidate, getGridBagConstraints(3, 23, 10, 10));

		JLabel labelNature = new JLabel("Nature");
		panel.add(labelNature, getGridBagConstraints(0, 24, 30, 10));

		starNature = new JLabel("*");
		panel.add(starNature, getGridBagConstraints(1, 24, 10, 10));

		boxNature = new JCheckBox();
		panel.add(boxNature, getGridBagConstraints(2, 24, 10, 10));
		boxNature.addActionListener(calculerListener);

		valeurNature = new JLabel("-");
		panel.add(valeurNature, getGridBagConstraints(3, 24, 10, 10));

		JLabel labelPerception = new JLabel("Perception");
		panel.add(labelPerception, getGridBagConstraints(0, 25, 30, 10));

		starPerception = new JLabel("*");
		panel.add(starPerception, getGridBagConstraints(1, 25, 10, 10));

		boxPerception = new JCheckBox();
		panel.add(boxPerception, getGridBagConstraints(2, 25, 10, 10));
		boxPerception.addActionListener(calculerListener);

		valeurPerception = new JLabel("-");
		panel.add(valeurPerception, getGridBagConstraints(3, 25, 10, 10));

		JLabel labelReligion = new JLabel("Religion");
		panel.add(labelReligion, getGridBagConstraints(0, 26, 30, 10));

		starReligion = new JLabel("*");
		panel.add(starReligion, getGridBagConstraints(1, 26, 10, 10));

		boxReligion = new JCheckBox();
		panel.add(boxReligion, getGridBagConstraints(2, 26, 10, 10));
		boxReligion.addActionListener(calculerListener);

		valeurReligion = new JLabel("-");
		panel.add(valeurReligion, getGridBagConstraints(3, 26, 10, 10));

		JLabel labelStealth = new JLabel("Discrétion");
		panel.add(labelStealth, getGridBagConstraints(0, 27, 30, 10));

		starStealth = new JLabel("*");
		panel.add(starStealth, getGridBagConstraints(1, 27, 10, 10));

		boxStealth = new JCheckBox();
		panel.add(boxStealth, getGridBagConstraints(2, 27, 10, 10));
		boxStealth.addActionListener(calculerListener);

		valeurStealth = new JLabel("-");
		panel.add(valeurStealth, getGridBagConstraints(3, 27, 10, 10));

		JLabel labelStreetwise = new JLabel("Connais. de la rue");
		panel.add(labelStreetwise, getGridBagConstraints(0, 28, 30, 10));

		starStreetwise = new JLabel("*");
		panel.add(starStreetwise, getGridBagConstraints(1, 28, 10, 10));

		boxStreetwise = new JCheckBox();
		panel.add(boxStreetwise, getGridBagConstraints(2, 28, 10, 10));
		boxStreetwise.addActionListener(calculerListener);

		valeurStreetwise = new JLabel("-");
		panel.add(valeurStreetwise, getGridBagConstraints(3, 28, 10, 10));

		JLabel labelThievery = new JLabel("Larcin");
		panel.add(labelThievery, getGridBagConstraints(0, 29, 30, 10));

		starThievery = new JLabel("*");
		panel.add(starThievery, getGridBagConstraints(1, 29, 10, 10));

		boxThievery = new JCheckBox();
		panel.add(boxThievery, getGridBagConstraints(2, 29, 10, 10));
		boxThievery.addActionListener(calculerListener);

		valeurThievery = new JLabel("-");
		panel.add(valeurThievery, getGridBagConstraints(3, 29, 10, 10));

		JLabel labelLanguageKnown = new JLabel("Langues Connues");
		panel.add(labelLanguageKnown, getGridBagConstraints(4, 26, 2, 1, 30, 10));

		valeursLanguage = new JLabel[3];
		valeursLanguage[0] = new JLabel("");
		panel.add(valeursLanguage[0], getGridBagConstraints(4, 27, 2, 1, 30, 10));

		valeursLanguage[1] = new JLabel("");
		panel.add(valeursLanguage[1], getGridBagConstraints(4, 28, 2, 1, 30, 10));

		valeursLanguage[2] = new JLabel("");
		panel.add(valeursLanguage[2], getGridBagConstraints(4, 29, 2, 1, 30, 10));

		classeRaceListener.actionPerformed(null);

		return panel;
	}

	private static GridBagConstraints getGridBagConstraints(int x, int y, int weightx, int weighty) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.anchor = GridBagConstraints.CENTER;
		return constraints;
	}

	private static GridBagConstraints getGridBagConstraints(int x, int y, int width, int height, int weightx, int weighty) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.anchor = GridBagConstraints.CENTER;
		return constraints;
	}

	public JTextField getTexteNom() {
		return texteNom;
	}

	public JTextField getTexteNiveau() {
		return texteNiveau;
	}

	public JLabel getValeurInit() {
		return valeurInit;
	}

	public JTextField getTexteForce() {
		return texteForce;
	}

	public JLabel getValeurForceMod() {
		return valeurForceMod;
	}

	public JLabel getValeurForceModDemi() {
		return valeurForceModDemi;
	}

	public JTextField getTexteConstitution() {
		return texteConstitution;
	}

	public JLabel getValeurConstitutionMod() {
		return valeurConstitutionMod;
	}

	public JLabel getValeurConstitutionModDemi() {
		return valeurConstitutionModDemi;
	}

	public JLabel getValeurVigueur() {
		return valeurVigueur;
	}

	public JTextField getTexteDexterite() {
		return texteDexterite;
	}

	public JLabel getValeurDexteriteMod() {
		return valeurDexteriteMod;
	}

	public JLabel getValeurDexteriteModDemi() {
		return valeurDexteriteModDemi;
	}

	public JTextField getTexteIntelligence() {
		return texteIntelligence;
	}

	public JLabel getValeurIntelligenceMod() {
		return valeurIntelligenceMod;
	}

	public JLabel getValeurIntelligenceModDemi() {
		return valeurIntelligenceModDemi;
	}

	public JLabel getValeurReflexe() {
		return valeurReflexe;
	}

	public JTextField getTexteSagesse() {
		return texteSagesse;
	}

	public JLabel getValeurSagesseMod() {
		return valeurSagesseMod;
	}

	public JLabel getValeurSagesseModDemi() {
		return valeurSagesseModDemi;
	}

	public JTextField getTexteCharisme() {
		return texteCharisme;
	}

	public JLabel getValeurCharismeMod() {
		return valeurCharismeMod;
	}

	public JLabel getValeurCharismeModDemi() {
		return valeurCharismeModDemi;
	}

	public JLabel getValeurVolonte() {
		return valeurVolonte;
	}

	public JComboBox getChoixClasse() {
		return choixClasse;
	}

	public JComboBox getChoixClasseOption() {
		return choixClasseOption;
	}

	public JComboBox getChoixRace() {
		return choixRace;
	}

	public JLabel getValeurForce() {
		return valeurForce;
	}

	public JLabel getValeurConstitution() {
		return valeurConstitution;
	}

	public JLabel getValeurDexterite() {
		return valeurDexterite;
	}

	public JLabel getValeurIntelligence() {
		return valeurIntelligence;
	}

	public JLabel getValeurSagesse() {
		return valeurSagesse;
	}

	public JLabel getValeurCharisme() {
		return valeurCharisme;
	}

	public JLabel getValeurMaxHP() {
		return valeurMaxHP;
	}

	public JLabel getValeurBloodied() {
		return valeurBloodied;
	}

	public JLabel getValeurSurgeValue() {
		return valeurSurgeValue;
	}

	public JLabel getValeurSurgesDay() {
		return valeurSurgesDay;
	}

	public JLabel getValeurAC() {
		return valeurAC;
	}

	public JComboBox getChoixArmure() {
		return choixArmure;
	}

	public JComboBox getChoixBouclier() {
		return choixBouclier;
	}

	public JComboBox getChoixArme() {
		return choixArme;
	}

	public JComboBox getChoixCarac() {
		return choixCarac;
	}

	public JCheckBox getBoxAcrobatics() {
		return boxAcrobatics;
	}

	public JLabel getValeurAcrobatics() {
		return valeurAcrobatics;
	}

	public JCheckBox getBoxArcana() {
		return boxArcana;
	}

	public JLabel getValeurArcana() {
		return valeurArcana;
	}

	public JCheckBox getBoxAthletics() {
		return boxAthletics;
	}

	public JLabel getValeurAthletics() {
		return valeurAthletics;
	}

	public JCheckBox getBoxBluff() {
		return boxBluff;
	}

	public JLabel getValeurBluff() {
		return valeurBluff;
	}

	public JCheckBox getBoxDiplomacy() {
		return boxDiplomacy;
	}

	public JLabel getValeurDiplomacy() {
		return valeurDiplomacy;
	}

	public JCheckBox getBoxDungeoneering() {
		return boxDungeoneering;
	}

	public JLabel getValeurDungeoneering() {
		return valeurDungeoneering;
	}

	public JCheckBox getBoxEndurance() {
		return boxEndurance;
	}

	public JLabel getValeurEndurance() {
		return valeurEndurance;
	}

	public JCheckBox getBoxHeal() {
		return boxHeal;
	}

	public JLabel getValeurHeal() {
		return valeurHeal;
	}

	public JCheckBox getBoxHistory() {
		return boxHistory;
	}

	public JLabel getValeurHistory() {
		return valeurHistory;
	}

	public JCheckBox getBoxInsight() {
		return boxInsight;
	}

	public JLabel getValeurInsight() {
		return valeurInsight;
	}

	public JCheckBox getBoxIntimidate() {
		return boxIntimidate;
	}

	public JLabel getValeurIntimidate() {
		return valeurIntimidate;
	}

	public JCheckBox getBoxNature() {
		return boxNature;
	}

	public JLabel getValeurNature() {
		return valeurNature;
	}

	public JCheckBox getBoxPerception() {
		return boxPerception;
	}

	public JLabel getValeurPerception() {
		return valeurPerception;
	}

	public JCheckBox getBoxReligion() {
		return boxReligion;
	}

	public JLabel getValeurReligion() {
		return valeurReligion;
	}

	public JCheckBox getBoxStealth() {
		return boxStealth;
	}

	public JLabel getValeurStealth() {
		return valeurStealth;
	}

	public JCheckBox getBoxStreetwise() {
		return boxStreetwise;
	}

	public JLabel getValeurStreetwise() {
		return valeurStreetwise;
	}

	public JCheckBox getBoxThievery() {
		return boxThievery;
	}

	public JLabel getValeurThievery() {
		return valeurThievery;
	}

	public JLabel getStarAcrobatics() {
		return starAcrobatics;
	}

	public JLabel getStarArcana() {
		return starArcana;
	}

	public JLabel getStarAthletics() {
		return starAthletics;
	}

	public JLabel getStarBluff() {
		return starBluff;
	}

	public JLabel getStarDiplomacy() {
		return starDiplomacy;
	}

	public JLabel getStarDungeoneering() {
		return starDungeoneering;
	}

	public JLabel getStarEndurance() {
		return starEndurance;
	}

	public JLabel getStarHeal() {
		return starHeal;
	}

	public JLabel getStarHistory() {
		return starHistory;
	}

	public JLabel getStarInsight() {
		return starInsight;
	}

	public JLabel getStarIntimidate() {
		return starIntimidate;
	}

	public JLabel getStarNature() {
		return starNature;
	}

	public JLabel getStarPerception() {
		return starPerception;
	}

	public JLabel getStarReligion() {
		return starReligion;
	}

	public JLabel getStarStealth() {
		return starStealth;
	}

	public JLabel getStarStreetwise() {
		return starStreetwise;
	}

	public JLabel getStarThievery() {
		return starThievery;
	}

	public JLabel getValeurSkillNumber() {
		return valeurSkillNumber;
	}

	public JLabel getValeurSpeed() {
		return valeurSpeed;
	}

	public JLabel getValeurPassiveInsight() {
		return valeurPassiveInsight;
	}

	public JLabel getValeurPassivePerception() {
		return valeurPassivePerception;
	}

	public JLabel[] getValeursLanguage() {
		return valeursLanguage;
	}

	public JLabel getValeurAttaque() {
		return valeurAttaque;
	}

	public JLabel getValeurDegat() {
		return valeurDegat;
	}

}
