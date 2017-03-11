package frames;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import frames.hand.ListenerLeft;
import frames.hand.ListenerRight;
import frames.hand.ListenerUse;

import others.Controller;

public class FrameHand extends JFrame {
	
	private Controller controller;

	private JButton button_use = new JButton("Use");
	private JButton button_right = new JButton(">>");
	private JButton button_left = new JButton("<<");
	
	public FrameHand(Controller c) {
		controller = c;
		
		setVisible(true);
		setResizable(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		setSize(25*(dim.width/100), 50*(dim.height/100));
		setLocation(45*(dim.width/100), 30*(dim.height/100));
		setTitle("Hand");
		
		JPanel panel = new JPanel();
		
		button_use.addActionListener(new ListenerUse(controller));
		button_right.addActionListener(new ListenerRight(controller));
		button_left.addActionListener(new ListenerLeft(controller));
		
		panel.add(button_use);
		panel.add(button_right);
		panel.add(button_left);
		
		add(panel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
