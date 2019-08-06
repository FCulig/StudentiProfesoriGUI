package hr.java.vjezbe.entitet;

import java.util.Set;
import java.util.TreeSet;


/**
 * Klasa koja sadrži osnovne podatke o predmetu
 * @author Filip Èulig
 *
 */
public class Predmet extends Entitet{
	/**
	 * Naziv predmeta
	 */
	private String naziv;
	/**
	 * Sifra predmeta
	 */
	private String sifra;
	/**
	 * Broj ECTS bodova koje predmet nosi
	 */
	private Integer brojEctsBodova;
	/**
	 * Profesor koji je nositelj predemta
	 */
	private Profesor nositelj;
	/**
	 * Studenti koji su na tom predmetu
	 */
	private Set<Long> studentiID = new TreeSet<>();
	private Set<Student> studenti;

	/**
	 * Konstruktor klase predmet
	 * @param naziv Naziv predmeta
	 * @param sifra Sifra predmeta
	 * @param brojEctsBodova Broj ECTS bodova koje predmet nosi
	 * @param nositelj Profesor nositelj predmeta
	 * @param studId Set ID-a studenata koji su upisani na taj predmet
	 */
	public Predmet(String naziv, String sifra, Integer brojEctsBodova, Profesor nositelj,Long id, Set<Long> studId) {
		super(id);
		this.naziv = naziv;
		this.sifra = sifra;
		this.brojEctsBodova = brojEctsBodova;
		this.nositelj = nositelj;
		this.studentiID = studId;
		this.studenti = new TreeSet<>();
	}

	public Predmet(String imePredmeta, String sifraPredmeta, Integer brojEctsa, Profesor odabraniProfesor) {
		this.naziv = imePredmeta;
		this.sifra = sifraPredmeta;
		this.brojEctsBodova = brojEctsa;
		this.nositelj = odabraniProfesor;
		this.studenti = new TreeSet<>();
	}
	
	public Predmet(String imePredmeta, String sifraPredmeta, Integer brojEctsa, Profesor odabraniProfesor,Long id) {
		super(id);
		this.naziv = imePredmeta;
		this.sifra = sifraPredmeta;
		this.brojEctsBodova = brojEctsa;
		this.nositelj = odabraniProfesor;
		this.studenti = new TreeSet<>();
	}

	public Predmet() {
	}

	/**
	 * Vraæa naziv predmeta
	 * @return naziv Naziv predmeta
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Mijenja naziv predmeta
	 * @param naziv Naziv premdeta
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	/**
	 * Vraæa šifru predmeta
	 * @return sifra Šifra predmeta
	 */
	public String getSifra() {
		return sifra;
	}

	/**
	 * Mijenja šifru predmeta
	 * @param sifra Šifra predmeta
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	/**
	 * Vraæa broj ECTS bodova koje nosi predmet
	 * @return brojEctsBodova Broj ECTS bodova predmeta
	 */
	public Integer getBrojEctsBodova() {
		return brojEctsBodova;
	}

	/**
	 * Mijenja broj ECTS bodova koje predmet nosi
	 * @param brojEctsBodova Broj ECTS bodova predmeta
	 */
	public void setBrojEctsBodova(Integer brojEctsBodova) {
		this.brojEctsBodova = brojEctsBodova;
	}

	/**
	 * Vraæa nositelja predmeta
	 * @return nositelj Nositelj predmeta
	 */
	public Profesor getNositelj() {
		return nositelj;
	}

	/**
	 * Mijenja nositelja predmeta
	 * @param nositelj Nositelj predmeta
	 */
	public void setNositelj(Profesor nositelj) {
		this.nositelj = nositelj;
	}

	/**
	 * @return Set<Long> Set ID-eva studenata koji su upisani na taj predmet
	 */
	public Set<Long> getStudentiID() {
		return studentiID;
	}

	/**
	 * @param studentiID Set ID-eva studenata koji su upisani na taj predmet
	 */
	public void setStudentiID(Set<Long> studentiID) {
		this.studentiID = studentiID;
	}

	/**
	 * @return the studenti
	 */
	public Set<Student> getStudenti() {
		return studenti;
	}

	/**
	 * @param studenti the studenti to set
	 */
	public void setStudenti(Set<Student> studenti) {
		this.studenti = studenti;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Predmet [naziv=" + naziv + ", sifra=" + sifra + ", brojEctsBodova=" + brojEctsBodova + ", nositelj="
				+ nositelj + ", studentiID=" + studentiID + ", studenti=" + studenti + "]";
	}
	
	
}
