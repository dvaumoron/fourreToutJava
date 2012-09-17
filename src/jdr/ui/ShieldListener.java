package jdr.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jdr.Shield;
import jdr.Weapon;

public class ShieldListener implements ActionListener {

	private CharacterFrame frame;
	private boolean enable;
	private CalculerListener calculerListener;
	
	public ShieldListener(CharacterFrame frame, CalculerListener calculerListener) {
		this.frame = frame;
		this.enable = true;
		this.calculerListener = calculerListener;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (enable) {
			String nomBouclier = (String) frame.getChoixBouclier().getSelectedItem();
			Shield shield = Shield.getShieldByName(nomBouclier);

			String nomArme = (String) frame.getChoixArme().getSelectedItem();
			Weapon weapon = Weapon.getWeaponByName(nomArme);
			
			if (!shield.equals(Shield.NONE) && weapon.isTwoHanded()) {
				frame.getChoixArme().setSelectedIndex(0);
			}
			calculerListener.actionPerformed(event);
		}
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
