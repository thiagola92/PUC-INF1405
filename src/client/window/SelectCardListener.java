package client.window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class SelectCardListener implements MouseListener {
	
	private HandPanel handPanel;
	private JLabel image;
	
	public SelectCardListener(HandPanel handPanel, JLabel image) {
		this.image = image;
		this.handPanel = handPanel;
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
		handPanel.selectCard(image);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
