package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

/**
 * Sadr�i sve podatke o Ispitu
 * @author Filip �ulig
 *
 */
public class Ispit extends Entitet{
	/**
	 * Predmet iz kojeg se pi�e ispit
	 */
	private Predmet predmet;
	/**
	 * Student koji pi�e ispit
	 */
	private Student student;
	/**
	 * Ocjena koju je student dobio na ispitu
	 */
	private Integer ocjena;
	/**
	 * Datum i vrijeme pisanja ispita
	 */
	private LocalDateTime datumIVrijeme;

	/**
	 * Kostruktor klase Ispit
	 * @param predmet Predmet iz kojeg je ispit
	 * @param student Student koji pi�e ispit
	 * @param ocjena Ocjena studenta na ispitu
	 * @param datumIVrijeme Datum i vrijeme pisanja ispita
	 */
	public Ispit(Predmet predmet, Student student, Integer ocjena, LocalDateTime datumIVrijeme, Long id) {
		super(id);
		this.predmet = predmet;
		this.student = student;
		this.ocjena = ocjena;
		this.datumIVrijeme = datumIVrijeme;
	}

	public Ispit(Predmet odabraniPredmet, Student odabraniStudent, Integer ocjena2, LocalDateTime datumIVrijemeIspita) {
		this.predmet = odabraniPredmet;
		this.student = odabraniStudent;
		this.ocjena = ocjena2;
		this.datumIVrijeme = datumIVrijemeIspita;
	}

	public Ispit() {
	}

	/**
	 * Vra�a predmet iz kojeg se pi�e ispit
	 * @return predmet Predmet
	 */
	public Predmet getPredmet() {
		return predmet;
	}

	/**
	 * Mijenja predmet iz kojeg se pi�e ispit
	 * @param predmet Predmet
	 */
	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	/**
	 * Vra�a studenta koji je pisao ispit
	 * @return student Student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * Mijenja studenta koji je pisao ispit
	 * @param student Student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * Vra�a ocjenu koju je student dobio na ispitu
	 * @return ocjena Ocjena
	 */
	public Integer getOcjena() {
		return ocjena;
	}

	/**
	 * Mijenja ocjenu koju je student dobio na ispitu
	 * @param ocjena Ocjena
	 */
	public void setOcjena(Integer ocjena) {
		this.ocjena = ocjena;
	}

	/**
	 * Vra�a datum i vrijeme pisanja ispita
	 * @return datumIVrijeme Datum i vrijeme
	 */
	public LocalDateTime getDatumIVrijeme() {
		return datumIVrijeme;
	}

	/**
	 * Mijenja datum i vrijeme pisanja ispita
	 * @param datumIVrijeme Datum i vrijeme
	 */
	public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
		this.datumIVrijeme = datumIVrijeme;
	}

	@Override
	public String toString() {
		return "Ispit [predmet=" + predmet + ", student=" + student + ", ocjena=" + ocjena + ", datumIVrijeme="
				+ datumIVrijeme + "]";
	}
	
	

}
