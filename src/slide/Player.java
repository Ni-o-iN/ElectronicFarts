package slide;

/**
 * Spielerklasse
 * @author Team
 * @version 0.1
 */
public class Player {
	
	private int id = 0;
	private int winCounter = 0;
	private String name = "";
	private boolean bombAvailable = true;
	/**
	 * Spielerinitialisierung
	 * @param id
	 * @param name
	 * @param spielerSteine
	 */
	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	} 

	public Player() {

	}
	
	public void setWinCounter() {
		winCounter++;
	}
	
	public int getWinCounter() {
		return winCounter;
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
	
	public boolean getPlayerBombStatus() {
		return this.bombAvailable;
	}
	public void setPlayerBombStatus(boolean bombStatus) {
		this.bombAvailable = bombStatus;
	}
}
