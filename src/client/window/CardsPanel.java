package client.window;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CardsPanel extends JPanel {
	
	private String location;
	private String format;
	
	private ArrayList<JLabel> cardsImages;

	public CardsPanel() {
		this.location = "./src/cards/";
		this.format = ".png";
		
		this.cardsImages = new ArrayList<JLabel>();
		
		//	System.out.println(System.getProperty("user.dir"));			Get the directory of the application 
		for(int i=0; i < 7; ++i) {
			ImageIcon image = new ImageIcon(location + "TEMPLATE" + format);
			JLabel label = new JLabel("", image, JLabel.CENTER);
			cardsImages.add(label);
			
			this.add(cardsImages.get(i));
		}
		
	}
	
	public void updateCardsPanel(ArrayList<String> cards) {
		
		for(int i = 0; i < cardsImages.size(); ++i) {
			ImageIcon image = null;
			
			if(i < cards.size())
				image = new ImageIcon(location + cards.get(i) + format);
			
			cardsImages.get(i).setIcon(image);
		}
	}
}
