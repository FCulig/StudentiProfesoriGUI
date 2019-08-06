package hr.java.vjezbe.niti;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import hr.java.vjezbe.Main;
import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakException;

public class NajboljiStudentNit implements Runnable {


	public void run() {
		OptionalDouble najboljiProsjek = null;
		Student najboljiStudent = new Student();
		List<Student> studenti = new ArrayList<>();
		List<Ispit> ispiti = new ArrayList<>();
		List<Ispit> listaIspitaZaStudenta = new ArrayList<>();
		
		try {
			studenti = BazaPodataka.dohvatiStudentePremaKriterijima(new Student());
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}
		
		try {
			ispiti = BazaPodataka.dohvatiIspitePremaKriterijima(new Ispit());
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}
		
		
		najboljiStudent = studenti.get(0);
		for(Ispit is : ispiti) {
			if(is.getStudent().equals(studenti.get(0))) {
				listaIspitaZaStudenta.add(is);
			}
		}
		
		najboljiProsjek = listaIspitaZaStudenta.stream().mapToDouble(i -> i.getOcjena()).average();
	
		
		for(Student stud : studenti) {
			listaIspitaZaStudenta.clear();
			for(Ispit is : ispiti) {
				if(is.getStudent().equals(stud)) {
					listaIspitaZaStudenta.add(is);
				}
			}
			
			if(listaIspitaZaStudenta.isEmpty() == false) {
			
				OptionalDouble prosjek = listaIspitaZaStudenta.stream().mapToDouble(i -> i.getOcjena()).average();
		
				if(prosjek.getAsDouble() > najboljiProsjek.getAsDouble()) {
					najboljiProsjek = prosjek;
					najboljiStudent = stud;
				}
			}
		}
		
		Main.stage.setTitle("Najbolji student: "+ najboljiStudent.getImePrezime() +" ("+ najboljiProsjek.getAsDouble() +")");
	}
	
}
