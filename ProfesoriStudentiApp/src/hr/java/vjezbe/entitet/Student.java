package hr.java.vjezbe.entitet;

import java.time.LocalDate;

/**
 * Sadrži sve podatke o studentu. Nasljeðuje abstraktnu klasu Osoba.
 * 
 * @author Filip Èulig
 *
 */
public class Student extends Osoba {
	/**
	 * Ime studenta
	 */
	private String ime;
	/**
	 * Prezime studenta
	 */
	private String prezime;
	/**
	 * JMBAG studenta
	 */
	private String jmbag;
	/**
	 * Datum roðenja studenta
	 */
	private LocalDate datumRodenja;
	/**
	 * Ima li negativnih ocjena
	 */
	private boolean imaNegativnih = false;

	/**
	 * Konstruktor klase student
	 * 
	 * @param ime          Ime studenta
	 * @param prezime      Prezime studenta
	 * @param jmbag        JMBAG studenta
	 * @param datumRodenja Datum roðenja studenta
	 * @param id ID Studenta
	 */
	public Student(String ime, String prezime, String jmbag, LocalDate datumRodenja,Long id) {
		super(ime, prezime, id);
		this.ime = ime;
		this.prezime = prezime;
		this.jmbag = jmbag;
		this.datumRodenja = datumRodenja;
	}

	public Student(String imeStudenta, String prezimeStudenta, String jmbagStudenta, LocalDate datumRodenja2) {
		super(imeStudenta, prezimeStudenta);
		this.ime = imeStudenta;
		this.prezime = prezimeStudenta;
		this.jmbag = jmbagStudenta;
		this.datumRodenja = datumRodenja2;
	}

	public Student() {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.java.vjezbe.entitet.Osoba#getIme()
	 */
	public String getIme() {
		return ime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.java.vjezbe.entitet.Osoba#setIme(java.lang.String)
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.java.vjezbe.entitet.Osoba#getPrezime()
	 */
	public String getPrezime() {
		return prezime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.java.vjezbe.entitet.Osoba#setPrezime(java.lang.String)
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**
	 * Vraæa JMBAG studenta
	 * 
	 * @return jmbag JMBAG studenta
	 */
	public String getJmbag() {
		return jmbag;
	}

	@Override
	public String toString() {
		return "Student [ime=" + ime + ", prezime=" + prezime + ", jmbag=" + jmbag + ", datumRodenja=" + datumRodenja
				+ ", imaNegativnih=" + imaNegativnih + "]";
	}

	/**
	 * Mijenja JMBAG studenta
	 * 
	 * @param jmbag JMBAG studenta
	 */
	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}

	/**
	 * Vraæa datum roðenja studenta
	 * 
	 * @return datumRodenja Datum rodenja studenta
	 */
	public LocalDate getDatumRodenja() {
		return datumRodenja;
	}

	/**
	 * Mijenja datum roðenja studenta
	 * 
	 * @param datumRodenja Datum roðenja studenta
	 */
	public void setDatumRodenja(LocalDate datumRodenja) {
		this.datumRodenja = datumRodenja;
	}

	/**
	 * Vraæa ima li student negativnih ocjena na ispitima
	 * 
	 * @return imaNegativnih
	 */
	public boolean isImaNegativnih() {
		return imaNegativnih;
	}

	/**
	 * Mijenjamo ima li student negativnih ocjena na ispitima
	 * 
	 * @param imaNegativnih
	 */
	public void setImaNegativnih(boolean imaNegativnih) {
		this.imaNegativnih = imaNegativnih;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datumRodenja == null) ? 0 : datumRodenja.hashCode());
		result = prime * result + (imaNegativnih ? 1231 : 1237);
		result = prime * result + ((ime == null) ? 0 : ime.hashCode());
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		result = prime * result + ((prezime == null) ? 0 : prezime.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (datumRodenja == null) {
			if (other.datumRodenja != null)
				return false;
		} else if (!datumRodenja.equals(other.datumRodenja))
			return false;
		if (imaNegativnih != other.imaNegativnih)
			return false;
		if (ime == null) {
			if (other.ime != null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		if (prezime == null) {
			if (other.prezime != null)
				return false;
		} else if (!prezime.equals(other.prezime))
			return false;
		return true;
	}
	
	public String getImePrezime() {
		return ime+" "+prezime;
	}
}
