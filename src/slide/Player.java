package slide;

/**
 * Spieler Klasse
 * @author Team
 * @version 0.1
 */
public class Player {
	
	private int id = 0;
	private String name = "";
	private boolean bombAvailable = true;
	private boolean isCOM = false;
	/**
	 * Spieler initialisierung
	 * @param id
	 * @param name
	 * @param spielerSteine
	 */
	public Player(int id, String name) {
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
	
	public void setCOM() {
		isCOM = true;
	}
	public boolean getPlayerBombStatus() {
		return this.bombAvailable;
	}
	public void setPllayerBombStatusFalse() {
		this.bombAvailable = false;
	}
}
