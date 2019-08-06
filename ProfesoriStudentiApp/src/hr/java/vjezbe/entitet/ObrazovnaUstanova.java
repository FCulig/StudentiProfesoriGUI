package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Abstraktna klasa koja sadrži osnovne podatke o obrazovnoj ustanovi i koju nasljeðuju Fakultet Raèunarstva i Veleuèilište Jave
 * @author Filip Èulig
 *
 */
public abstract class ObrazovnaUstanova extends Entitet{
	/**
	 * Naziv obrazovne ustanove
	 */
	private String nazivUstanove;
	/**
	 * Predmeti koji se mogu pohaðat u toj ustanovi
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
	 * Ispiti koji se mogu položiti u toj obrazovnoj ustanovi
	 */
	private List<Ispit> ispiti;
	
	/**
	 * Vraæa najuspješnijeg studenta na godini
	 * @param godinaStudija Godina za koju tražimo najuspješnijeg studenta
	 * @return Student Najuspješniji student na godini
	 */
	public abstract Student odrediNajuspjesnijegStudentaNaGodini(int godinaStudija);

	/**
	 * Konstruktor abstraktne klase ObrazovnaUstanova
	 * @param nazivUstanove Ime ustanove
	 * @param predmeti2 Predmeti koji se pohaðaju u toj ustanovi
	 * @param profesori2  Profesori koji su zaposleni u toj ustanovi
	 * @param studenti2 Studenti koji studiraju u toj ustanovi
	 * @param ispiti2 Ispiti koje možemo položiti u toj ustanovi
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
	 * Vraæa naziv ustanove
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
	 * Vraæa polje predmeta koji se mogu pohaðati u toj ustanovi
	 * @return predmeti Polje predmeta
	 */
	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	/**
	 * Mijenjamo predmete koji se mogu pohaðati u toj ustanovi
	 * @param predmeti Polje predmeta
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	/**
	 * Vraæa polje profesora koji su zaposleni u toj ustanovi
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
	 * Vraæa studente koji studiraju u toj ustanovi
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
	 * Vraæa ispite koji se mogu polagati u toj ustanovi
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
