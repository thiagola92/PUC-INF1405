package frames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Toolkit;

public class FrameStatus extends JFrame {
	
	private int resets = 4;
	private int life = 4;
	
	private int damage = 0;
	private int attacks = 1;
	private int distance = 0;
	
	private JLabel label_resets = new JLabel("Resets: " + resets);
	private JLabel label_life = new JLabel("Life: " + life);
	
	private JLabel label_damage = new JLabel("Damage: +" + damage);
	private JLabel label_attacks = new JLabel("Attacks: " + attacks);
	private JLabel label_distance = new JLabel("Distance: +" + distance);
	
	public FrameStatus() {
		setVisible(true);
		setResizable(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		setSize(7*(dim.width/100), 50*(dim.height/100));
		setLocation(70*(dim.width/100), 30*(dim.height/100));
		setTitle("Status");
		
		JPanel panel = new JPanel();
		
		panel.add(label_resets);
		panel.add(label_life);
		panel.add(label_damage);
		panel.add(label_attacks);
		panel.add(label_distance);
		
		add(panel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setResets(int resets) {
		this.resets = resets;
		label_resets.setText("Resets: " + resets);
	}

	public void setLife(int life) {
		this.life = life;
		label_life.setText("Life: " + life);
	}

	public void setDamage(int damage) {
		this.damage = damage;
		label_damage.setText("Damage: +" + damage);
	}

	public void setAttacks(int attacks) {
		this.attacks = attacks;
		label_attacks.setText("Attacks: " + attacks);
	}

	public void setDistance(int distance) {
		this.distance = distance;
		label_distance.setText("Distance: +" + distance);
	}

	
	
}
