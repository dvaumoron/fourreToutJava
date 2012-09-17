package jdr.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jdr.Weapon;

public class WeaponListener implements ActionListener {

	private CharacterFrame frame;
	private boolean enable;
	private CalculerListener calculerListener;
	
	public WeaponListener(CharacterFrame frame, CalculerListener calculerListener) {
		this.frame = frame;
		this.enable = true;
		this.calculerListener = calculerListener;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (enable) {
			String nomArme = (String) frame.getChoixArme().getSelectedItem();
			Weapon weapon = Weapon.getWeaponByName(nomArme);

			if (weapon.isTwoHanded()) {
				frame.getChoixBouclier().setSelectedIndex(0);
			}
			calculerListener.actionPerformed(event);
		}
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
