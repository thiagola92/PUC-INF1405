package client.window.playerpanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import client.Translator;

public class NextTurnListener implements MouseListener {
	
	private Translator translator;
	
	public NextTurnListener(Translator translator) {
		this.translator = translator;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		translator.nextTurn();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
