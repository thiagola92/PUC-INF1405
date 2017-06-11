package client.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import client.Translator;

public class useCardListener implements MouseListener {
	
	private Translator translator;
	
	public useCardListener(Translator translator) {
		this.translator = translator;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		translator.useCard();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
