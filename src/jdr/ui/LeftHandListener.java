package jdr.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jdr.Shield;
import jdr.Weapon;

public class LeftHandListener implements ActionListener {

	private CharacterFrame frame;
	private boolean enable;
	private CalculerListener calculerListener;

	public LeftHandListener(CharacterFrame frame, CalculerListener calculerListener) {
		this.frame = frame;
		this.enable = true;
		this.calculerListener = calculerListener;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (enable) {
			String nomArme = (String) frame.getChoixArme().getSelectedItem();
			Weapon weapon = Weapon.getWeaponByName(nomArme);
			
			String nomMainGauche = (String) frame.getChoixMainGauche().getSelectedItem();
			Weapon leftWeapon = Weapon.getWeaponByName(nomMainGauche);
			Shield shield = Shield.getShieldByName(nomMainGauche);
			
			if ((leftWeapon == null && !Shield.NONE.equals(shield))
					|| (shield == null && !Weapon.UNARMED_ATTACK.equals(leftWeapon))) {
				if (weapon.isTwoHanded()) {
					frame.getChoixArme().setSelectedIndex(0);
				}
			}

			calculerListener.actionPerformed(event);
		}
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
