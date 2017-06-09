package client.window;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CardsPanel extends JPanel {
	
	private String location = "./src/cards/";
	private String format = ".png";
	
	private ArrayList<JLabel> cardsImages = new ArrayList<JLabel>();

	public CardsPanel() {
		
		//	System.out.println(System.getProperty("user.dir"));			Get the directory of the application 
		
		for(int i=0; i < 7; ++i) {
			cardsImages.add(new JLabel("", null, JLabel.CENTER));
			
			this.add(cardsImages.get(i));
		}
		
	}
	
	public void updateCardsPanel(ArrayList<String> cards) {
		
		for(int i = 0; i < cardsImages.size(); ++i) {
			JLabel label = cardsImages.get(i);
			ImageIcon image = null;
			
			if(cards != null && i < cards.size())
				image = new ImageIcon(location + cards.get(i) + format);
			
			label.setIcon(image);
		}
	}
}