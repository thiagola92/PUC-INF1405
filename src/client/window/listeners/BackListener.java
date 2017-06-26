package client.window.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import client.window.EnemyPanel;

public class BackListener implements MouseListener {
	
	private EnemyPanel panel;

	public BackListener(EnemyPanel panel) {
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
		panel.backPlayer();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
