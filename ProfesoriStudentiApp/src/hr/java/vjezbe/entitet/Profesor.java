package hr.java.vjezbe.entitet;

/**
 * Sadrži podatke o profesorima i nasljeðuje abstraktnu klasu Osoba
 * @author Filip Èulig
 *
 */
public class Profesor extends Osoba {
	/**
	 * Ime profesora
	 */
	private String ime;
	/**
	 * Prezime profesroa
	 */
	private String prezime;
	/**
	 * Sifra profesora
	 */
	private String sifra;
	/**
	 * Titula profesora
	 */
	private String titula;

	/**
	 * Konstruktor klase Profesor
	 * @param ime Ime profesora
	 * @param prezime Prezime profesora
	 * @param sifra Sifra profesora
	 * @param titula Titula profesora
	 * @param id ID profesora
	 */
	public Profesor(String ime, String prezime, String sifra, String titula,Long id) {
		super(ime, prezime, id);
		this.ime = ime;
		this.prezime = prezime;
		this.sifra = sifra;
		this.titula = titula;
	}

	public Profesor(String imeProfesora, String prezimeProfesora, String sifraProfesora, String titulaProfesora) {
		super(imeProfesora, prezimeProfesora);
		this.ime = imeProfesora;
		this.prezime = prezimeProfesora;
		this.sifra = sifraProfesora;
		this.titula = titulaProfesora;
	}
	
	public Profesor() {	}

	/* (non-Javadoc)
	 * @see hr.java.vjezbe.entitet.Osoba#getIme()
	 */
	public String getIme() {
		return ime;
	}

	/* (non-Javadoc)
	 * @see hr.java.vjezbe.entitet.Osoba#setIme(java.lang.String)
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/* (non-Javadoc)
	 * @see hr.java.vjezbe.entitet.Osoba#getPrezime()
	 */
	public String getPrezime() {
		return prezime;
	}

	/* (non-Javadoc)
	 * @see hr.java.vjezbe.entitet.Osoba#setPrezime(java.lang.String)
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**
	 * Vraæa šifru profesora
	 * @return sifra Šifra profesora
	 */
	public String getSifra() {
		return sifra;
	}

	/**
	 * Mijenja šifru profesora
	 * @param sifra Šifra profesora
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	/**
	 * Vraæa titulu profesora
	 * @return titula Titula profesora
	 */
	public String getTitula() {
		return titula;
	}

	/**
	 * Mijenja titulu profesora
	 * @param titula Titula profesora
	 */
	public void setTitula(String titula) {
		this.titula = titula;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  ime +" "+prezime;
	}
	
	public String getImePrezime() {
		return ime+" "+prezime;
	}
	
	

}
