package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filip Èulig
 * Generièka klasa u koju se pohranjuju sve obrazovne ustanove koje su dio Sveuèilišta.
 * @param <T>
 */
public class Sveuciliste<T> extends ObrazovnaUstanova{
	
	private List<T> lista;

	
	
	/**
	 * Konstruktor klase
	 * @param nazivUstanove Naziv ustanove.
	 * @param predmeti2 Lista predmeta koji se mogu upisati u toj ustanovi.
	 * @param profesori2 Lista profesora koji su zaposleni u toj ustanovi.
	 * @param studenti2 Lista studenata koji pohaðaju obrazovnu ustanovu.
	 * @param ispiti2 Lista ispita koji se mogu polagat u obrazovnoj ustanovi.
	 * @param id Id obrazovne ustanove
	 */
	public Sveuciliste(String nazivUstanove, List<Predmet> predmeti2, List<Profesor> profesori2,
			List<Student> studenti2, List<Ispit> ispiti2,Long id) {
		super(nazivUstanove, predmeti2, profesori2, studenti2, ispiti2, id);
		lista = new ArrayList<>();
	}
	
	
	/**
	 * Defaultni konstruktor
	 */
	public Sveuciliste() {
		super();
		lista = new ArrayList<>();
	}


	/**
	 * @param ustanova Ustanova koja æe biti dodana u listu ustanova.
	 */
	public void dodajObrazovnuUstanovu(T ustanova) {
		this.lista.add(ustanova);
	}
	
	/**
	 * @param index Index obrazovne ustanove
	 * @return T Obrazovna ustanova pod zadanim indexom
	 */
	public T dohvatiObrazovnuUstanovu(int index){
		return this.lista.get(index);
	}
	
	/**
	 * @return List<T> Vraæa listu obrazovnih ustanova
	 */
	public List<T> dohvatiListuUstanova(){
		return this.lista;
	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godinaStudija) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
