package client.window.cards;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import client.Translator;

public class UseCardListener implements MouseListener {
	
	private Translator translator;
	
	public UseCardListener(Translator translator) {
		this.translator = translator;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		translator.useCard();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
