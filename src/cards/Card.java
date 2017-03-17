package cards;

import others.Controller;

public class Card {
	
	private String name = "NULL";
	
	private String description = "Explaining what this card can do";
	
	private String image = "images/cardexample.png";

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImage(String image) {
		this.image = "images/" + image;
	}
}
