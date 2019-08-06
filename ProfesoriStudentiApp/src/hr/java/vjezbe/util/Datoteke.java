package hr.java.vjezbe.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

public class Datoteke {
	private static final String FILE_PROFESORI= "dat/profesori.txt";
	private static final String FILE_PREDMETI = "dat/predmeti.txt";
	private static final String FILE_STUDENTI = "dat/studenti.txt";
	private static final String FILE_ISPITI = "dat/ispiti.txt";
	private static final String DATE_TIME_FORMAT = "dd.MM.yyyy.'T'HH:mm";
	private static final String DATE_FORMAT = "dd.MM.yyyy.";
	
	public Datoteke() {};
	
	public List<Profesor> ucitajDatotekuProfesora() {
		List<Profesor> profesori = new ArrayList<>();

		try (BufferedReader in = new BufferedReader(new FileReader(FILE_PROFESORI))) {
			String line;
			while ((line = in.readLine()) != null) {
				Long id = Long.parseLong(line);
				String sifra = in.readLine();
				String ime = in.readLine();
				String prezime = in.readLine();
				String titula = in.readLine();
				profesori.add(new Profesor(ime,prezime,sifra,titula,id));
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		return profesori;
	}
	
	public List<Predmet> ucitajDatotekuPredmeta(){
		List<Predmet> predmeti = new ArrayList<>();
		List<Profesor> profesori = new ArrayList<>();
		profesori = ucitajDatotekuProfesora();
		try (BufferedReader in = new BufferedReader(new FileReader(FILE_PREDMETI))) {
			String line;
			while ((line = in.readLine()) != null) {
				Long id = Long.parseLong(line);
				String sifra = in.readLine();
				String naziv = in.readLine();
				String brojECTSaString = in.readLine();
				int ECTS = Integer.parseInt(brojECTSaString);
				String idProfesoraString = in.readLine();
				Long idProfesora = Long.parseLong(idProfesoraString);
				
				Profesor profesor = null;
				for(Profesor prof : profesori) {
					if(prof.getId().equals(idProfesora)) {
						profesor = prof;
					}
				}
				
				String setIDStudenataString = in.readLine();
				if(setIDStudenataString.equals("x")) {
					predmeti.add(new Predmet(naziv, sifra, ECTS, profesor,id));
				}else {
					Set<Long> set = new TreeSet<>();
					String[] array = setIDStudenataString.split(" ", -1);
					for (String znak : array) {
						set.add(Long.parseLong(znak));
					}
					
					predmeti.add(new Predmet(naziv, sifra, ECTS, profesor,id,set));
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		
		return predmeti;
	}
	
	public List<Student> dohvatiDatotekuStudenata(){
		List<Student> studenti = new ArrayList<>();
		
		try (BufferedReader in = new BufferedReader(new FileReader(FILE_STUDENTI))) {
			String line;
			while ((line = in.readLine()) != null) {
				Long id = Long.parseLong(line);
				String ime = in.readLine();
				String prezime = in.readLine();
				String datumRodenjaString = in.readLine();
				DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
				LocalDate datumRodenja = LocalDate.parse(datumRodenjaString, format);
				String JMBAG = in.readLine();
				studenti.add(new Student(ime,prezime,JMBAG,datumRodenja,id));
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		
		return studenti;
	}
	
	public List<Ispit> dohvatiDatotekuIspita(){
		List<Ispit> ispiti = new ArrayList<>();
		List<Predmet> predmeti = new ArrayList<>();
		List<Student> studenti = new ArrayList<>();
		predmeti = ucitajDatotekuPredmeta();
		studenti = dohvatiDatotekuStudenata();
		
		try (BufferedReader in = new BufferedReader(new FileReader(FILE_ISPITI))) {
			String line;
			while ((line = in.readLine()) != null) {
				Long id = Long.parseLong(line);
				String idPredmetaString = in.readLine();
				Long idPredmeta = Long.parseLong(idPredmetaString);
				Predmet predmet = null;
				for(Predmet pred : predmeti) {
					if(pred.getId().equals(idPredmeta)) {
						predmet = pred;
					}
				}
				
				String idStudentaString = in.readLine();
				Long idStudenta = Long.parseLong(idStudentaString);
				Student student= null;
				for(Student stud : studenti) {
					if(stud.getId().equals(idStudenta)) {
						student = stud;
					}
				}
				
				String ocjenaString = in.readLine();
				int ocjena = Integer.parseInt(ocjenaString);
				String datumString = in.readLine();
				DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
				LocalDateTime datumIspita = LocalDateTime.parse(datumString, format);

				ispiti.add(new Ispit(predmet,student,ocjena,datumIspita,id));
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		return ispiti;
	}
}
