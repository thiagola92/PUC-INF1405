package frames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cards.Color;
import others.Controller;

import java.awt.Dimension;
import java.awt.Toolkit;

public class FrameStatus extends JFrame {
	
	private Controller controller;
	
	private JPanel panel = new JPanel();
	
	private int resets = 4;
	private int life = 4;
	
	private int damage = 0;
	private int attacks = 1;
	private int distance = 0;
	
	private Color team = Color.NO_COLOR;
	
	private String name = "empty";
	private String ability = "nothing";
	
	private JLabel label_resets = new JLabel("Resets: " + resets);
	private JLabel label_life = new JLabel("Life: " + life);
	
	private JLabel label_damage = new JLabel("Damage: +" + damage);
	private JLabel label_attacks = new JLabel("Attacks: " + attacks);
	private JLabel label_distance = new JLabel("Distance: +" + distance);
	
	private JLabel label_team = new JLabel("Team: " + team);
	
	private JLabel label_name = new JLabel("Name: " + name);
	private JLabel label_ability = new JLabel("Ability: " + ability);
	
	public FrameStatus(Controller c) {
		controller = c;
		
		setVisible(true);
		setResizable(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		setSize(10*(dim.width/100), 50*(dim.height/100));
		setLocation(70*(dim.width/100), 30*(dim.height/100));
		setTitle("Status");
		
		panel.add(label_resets);
		panel.add(label_life);
		
		panel.add(label_damage);
		panel.add(label_attacks);
		panel.add(label_distance);
		
		panel.add(label_team);
		
		panel.add(label_name);
		panel.add(label_ability);
		
		add(panel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setResets(int resets) {
		this.resets = resets;
		label_resets.setText("Resets: " + this.resets);
	}

	public void setLife(int life) {
		this.life = life;
		label_life.setText("Life: " + this.life);
	}

	public void setDamage(int damage) {
		this.damage = damage;
		label_damage.setText("Damage: +" + this.damage);
	}

	public void setAttacks(int attacks) {
		this.attacks = attacks;
		label_attacks.setText("Attacks: " + this.attacks);
	}

	public void setDistance(int distance) {
		this.distance = distance;
		label_distance.setText("Distance: +" + this.distance);
	}

	public void setTeam(Color team) {
		this.team = team;
		label_team.setText("Team: " + this.team);
	}

	public void setName(String name) {
		this.name = name;
		label_name.setText("Name: " + this.name);
	}

	public void setAbility(String ability) {
		this.ability = ability;
		label_ability.setText("Ability: " + this.ability);
	}
	
}
