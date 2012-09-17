package jdr.ui;

import javax.swing.SwingUtilities;

public class CharacterBuilder {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				CharacterFrame mainframe = new CharacterFrame();
				mainframe.setVisible(true);
			}
		});
	}
}
