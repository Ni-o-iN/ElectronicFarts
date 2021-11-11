package slide;

import java.util.ArrayList;
/**
 * Spieler Klasse
 * @author Paul
 * @version 0.1
 */
import java.util.List;
public class Spieler {
	
	private int id;
	private String name;
	private ArrayList<Spielstein> spielerSteine = new ArrayList<Spielstein>();
	
	/**
	 * Spieler initialisierung
	 * @param id
	 * @param name
	 * @param spielerSteine
	 */
	public Spieler(int id, String name) {
		this.id = id;
		this.name = name;
	} 
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Spielstein> getSpielerSteine(){
		return this.spielerSteine;
	}
	
	public void setSpielerSteine() {
		
	}
}
