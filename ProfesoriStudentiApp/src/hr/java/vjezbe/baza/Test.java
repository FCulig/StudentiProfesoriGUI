package hr.java.vjezbe.baza;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakException;

public class Test {

	public static void main(String[] args) {
		List<Profesor> profesori = new ArrayList<>();
		Profesor profesor = new Profesor();
		//Profesor profesorZaUpis = new Profesor("Marko", "Markic","P1111","Profesor Visoke Skole");
		/*try {
			BazaPodataka.spremiNovogProfesora(profesorZaUpis);
		} catch (BazaPodatakException e1) {
			e1.printStackTrace();
		}*/
		try {
			profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(profesor);
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}
		
		for(Profesor prof : profesori) {
			System.out.println(prof);
		}
		
		List<Student> studenti = new ArrayList<>();
		Student student = new Student();
		//DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		//LocalDate datumRodenja = LocalDate.parse("12.01.1996.", format);
		//Student studentZaUpis = new Student("Jurica","Juric","024608574", datumRodenja);
		/*try {
			BazaPodataka.spremiNovogStudenta(studentZaUpis);
		} catch (BazaPodatakException e1) {
			e1.printStackTrace();
		}*/
		try {
			studenti = BazaPodataka.dohvatiStudentePremaKriterijima(student);
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}
		for(Student stud : studenti) {
			System.out.println(stud);
		}
		
		List<Predmet> predmeti = new ArrayList<>();
		Predmet predmet = new Predmet();
		//Predmet predmetZaUpis = new Predmet("Programiranje u jeziku C", "PR12455", 7, profesori.get(3));
		/*try {
			BazaPodataka.spremiNoviPredmet(predmetZaUpis);
		} catch (BazaPodatakException e1) {
			e1.printStackTrace();
		}*/
		try {
			predmeti = BazaPodataka.dohvatiPredmetePremaKriterijima(predmet);
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}
		for(Predmet pred : predmeti) {
			System.out.println(pred);
		}
		
		List<Ispit> ispiti = new ArrayList<>();
		Ispit ispit = new Ispit();
		DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
		LocalDateTime datumIspita = LocalDateTime.parse("12.12.2018.T15:33", formatDT);
		Ispit ispitZaUpis = new Ispit(predmeti.get(3), studenti.get(4), 5,datumIspita);
		try {
			BazaPodataka.spremiNoviIspit(ispitZaUpis);
		} catch (BazaPodatakException e1) {
			e1.printStackTrace();
		}
		try {
			ispiti = BazaPodataka.dohvatiIspitePremaKriterijima(ispit);
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}
		for(Ispit is : ispiti) {
			System.out.println(is);
		}

	}

}
