package client.window.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import client.window.EnemyPanel;

public class NextListener implements MouseListener {
	
	private EnemyPanel panel;

	public NextListener(EnemyPanel panel) {
		this.panel = panel;
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
		panel.nextPlayer();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
