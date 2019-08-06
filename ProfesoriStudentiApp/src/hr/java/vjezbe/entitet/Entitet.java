package hr.java.vjezbe.entitet;

/**
 * @author Filip Èulig
 * Koristi se za dodavanje ID-eva objektima prilikom citanja iz datoteka.
 */
public class Entitet {
	private Long id;

	/**
	 * @param id ID objekta
	 */
	public Entitet(Long id) {
		super();
		this.id = id;
	}
	
	/**
	 * Defaultni konstruktor
	 */
	public Entitet() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
