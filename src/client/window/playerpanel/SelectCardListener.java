package client.window.playerpanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class SelectCardListener implements MouseListener {
	
	private HandPanel handPanel;
	
	/**
	 * The Panel where the JLabel is.
	 * <br>With the panel i can call the method that afect all JLabels.
	 * @param handPanel
	 */
	public SelectCardListener(HandPanel handPanel) {
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
		JLabel jLabel = (JLabel)arg0.getSource();
		handPanel.selectCard(jLabel);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
