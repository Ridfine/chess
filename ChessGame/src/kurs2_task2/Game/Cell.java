package kurs2_task2.Game;

import java.util.ArrayList;
import java.util.EnumMap;


public class Cell {
	private String coordinates;
	private boolean color; // true = white false = black

	private EnumMap<nameLinks, Cell> links = new EnumMap<nameLinks, Cell>(nameLinks.class);
	public enum nameLinks {
		toUPLeft, toUP, toUPRight, toRight, toDownRight, toDown, toDownLeft, toLeft
	};

	public Cell getLink(nameLinks val){
		return links.get(val);
	}
	public String getCoordinates(){
		Cell a = links.get(nameLinks.toUP);
		return coordinates;
	}
	Cell(Boolean color, String coordinates){
		this.coordinates = coordinates;
		this.color = color;
		for (nameLinks link : nameLinks.values()){
			links.put(link, null);
		}
	}

	void setLink(ArrayList<Cell> tempArrayLinks){
		int i = 0;
		for (nameLinks link : nameLinks.values()){
			links.put(link, tempArrayLinks.get(i));
			i++;
		}

	}
}
