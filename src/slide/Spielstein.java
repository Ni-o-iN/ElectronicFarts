package slide;

/**
 * Spielsteinklasse
 * @author Paul
 * @version 0.1
 * 
 */
public class Spielstein {
	private int id;
	private Spielsteinfarbe farbe;
	
	public Spielstein(int id, Spielsteinfarbe farbe) {
		this.id = id;
		this.farbe = farbe;
	}
	
	public void setSpielsteinId(int id) {
		this.id = id;
	}
	
	public int getSpielsteinId() {
		return this.id;
	}
	
	public void setSpielsteinFarbe(Spielsteinfarbe farbe) {
		this.farbe = farbe;
	}
	
	public Spielsteinfarbe getSpielsteinFarbe() {
		return this.farbe;
	}
}
