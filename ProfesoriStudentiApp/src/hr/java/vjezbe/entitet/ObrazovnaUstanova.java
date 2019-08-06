package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Abstraktna klasa koja sadr�i osnovne podatke o obrazovnoj ustanovi i koju naslje�uju Fakultet Ra�unarstva i Veleu�ili�te Jave
 * @author Filip �ulig
 *
 */
public abstract class ObrazovnaUstanova extends Entitet{
	/**
	 * Naziv obrazovne ustanove
	 */
	private String nazivUstanove;
	/**
	 * Predmeti koji se mogu poha�at u toj ustanovi
	 */
	private List<Predmet> predmeti;
	/**
	 * Profesori koji predaju u toj ustanovi
	 */
	private List<Profesor> profesori;
	/**
	 * Studenti koji studiraju u toj obrazovnoj ustanovi
	 */
	private List<Student> studenti;
	/**
	 * Ispiti koji se mogu polo�iti u toj obrazovnoj ustanovi
	 */
	private List<Ispit> ispiti;
	
	/**
	 * Vra�a najuspje�nijeg studenta na godini
	 * @param godinaStudija Godina za koju tra�imo najuspje�nijeg studenta
	 * @return Student Najuspje�niji student na godini
	 */
	public abstract Student odrediNajuspjesnijegStudentaNaGodini(int godinaStudija);

	/**
	 * Konstruktor abstraktne klase ObrazovnaUstanova
	 * @param nazivUstanove Ime ustanove
	 * @param predmeti2 Predmeti koji se poha�aju u toj ustanovi
	 * @param profesori2  Profesori koji su zaposleni u toj ustanovi
	 * @param studenti2 Studenti koji studiraju u toj ustanovi
	 * @param ispiti2 Ispiti koje mo�emo polo�iti u toj ustanovi
	 * @param id ID obrazovne ustanove
	 */
	public ObrazovnaUstanova(String nazivUstanove, List<Predmet> predmeti2, List<Profesor> profesori2, List<Student> studenti2,
			List<Ispit> ispiti2, Long id) {
		super(id);
		this.nazivUstanove = nazivUstanove;
		this.predmeti = predmeti2;
		this.profesori = profesori2;
		this.studenti = studenti2;
		this.ispiti = ispiti2;
	}

	public ObrazovnaUstanova() {
		// TODO Auto-generated constructor stub
	}

	public ObrazovnaUstanova(String nazivUstanove2, List<Predmet> predmeti2, List<Profesor> profesori2,
			List<Student> studenti2, List<Ispit> ispiti2) {
		this.nazivUstanove = nazivUstanove2;
		this.predmeti = predmeti2;
		this.profesori = profesori2;
		this.studenti = studenti2;
		this.ispiti = ispiti2;
	}

	/**
	 * Vra�a naziv ustanove
	 * @return String Naziv ustanove
	 */
	public String getNazivUstanove() {
		return nazivUstanove;
	}

	/**
	 * Mijenja naziv ustanove
	 * @param nazivUstanove Naziv ustanove
	 */
	public void setNazivUstanove(String nazivUstanove) {
		this.nazivUstanove = nazivUstanove;
	}

	/**
	 * Vra�a polje predmeta koji se mogu poha�ati u toj ustanovi
	 * @return predmeti Polje predmeta
	 */
	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	/**
	 * Mijenjamo predmete koji se mogu poha�ati u toj ustanovi
	 * @param predmeti Polje predmeta
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	/**
	 * Vra�a polje profesora koji su zaposleni u toj ustanovi
	 * @return profesori Polje profesora
	 */
	public List<Profesor> getProfesori() {
		return profesori;
	}

	/**
	 * Mijenjamo profesore koji su zaposleni u toj ustanovi
	 * @param profesori Polje profesora
	 */
	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}

	/**
	 * Vra�a studente koji studiraju u toj ustanovi
	 * @return studenti Polje studenata
	 */
	public List<Student> getStudenti() {
		return studenti;
	}

	public int getBrojStudenata() {
		return studenti.size();
	}
	
	/**
	 * Mijenjamo studente koji studiraju u toj ustanovi
	 * @param studenit Polje studenata
	 */
	public void setStudenti(List<Student> studenit) {
		this.studenti = studenit;
	}

	/**
	 * Vra�a ispite koji se mogu polagati u toj ustanovi
	 * @return ispiti Polje ispita
	 */
	public List<Ispit> getIspiti() {
		return ispiti;
	}

	/**
	 * Mijenjamo ispite koji se mogu polagati u toj ustanovi
	 * @param ispiti Polje ispita
	 */
	public void setIspiti(List<Ispit> ispiti) {
		this.ispiti = ispiti;
	}

}
