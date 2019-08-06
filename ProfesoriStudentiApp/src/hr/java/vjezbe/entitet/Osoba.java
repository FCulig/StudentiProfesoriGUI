package hr.java.vjezbe.entitet;

/**
 * Abstraktna klasa koja sadr�i osnovne podatke o osobi i koju naslje�uju profesor i student
 * @author Filip �ulig
 *
 */
public abstract class Osoba extends Entitet{
	/**
	 * Ime osobe
	 */
	private String ime;
	/**
	 * Prezime osobe
	 */
	private String prezime;

	/**
	 * Konstruktor klase osoba
	 * @param ime Ime osobe
	 * @param prezime Prezime osobe
	 */
	public Osoba(String ime, String prezime,Long id) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
	}

	public Osoba(String ime2, String prezime2) {
		this.ime = ime2;
		this.prezime = prezime2;
	}
	
	public Osoba() {}

	/**
	 * Vra�a ime osobe
	 * @return ime Ime osobe
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Mijenjamo ime osobe
	 * @param ime Ime osobe
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/**
	 * Vra�a prezime osobe
	 * @return prezime Prezime osobe
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Mijenjamo prezime osobe
	 * @param prezime Prezime osobe
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

}
