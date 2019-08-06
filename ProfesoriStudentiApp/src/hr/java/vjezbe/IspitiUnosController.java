package hr.java.vjezbe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;

public class IspitiUnosController {
	@FXML
	private ChoiceBox<String> predmet;
	@FXML
	private ChoiceBox<String> imePrezime;
	@FXML
	private DatePicker datumIspita;
	@FXML
	private ChoiceBox<String> ocjena;
	@FXML
	private TextField vrijemeIspita;

	
	private List<Ispit> ispiti;
	private List<String> predmeti = new ArrayList<>();
	private List<String> ocjene = new ArrayList<>();
	private List<String> studenti = new ArrayList<>();
	private static final String FILE_ISPIT = "dat/ispiti.txt";
	private List<Student> studentiList;
	private List<Predmet> predmetiList;
	
	public void unesi() {
		String prazniFieldovi = "";

		if (ocjena.getValue() == null|| imePrezime.getValue() == null || predmet.getValue() == null
				|| datumIspita.getValue() == null || vrijemeIspita.getText().isEmpty()) {
			if (ocjena.getValue() == null) {
				prazniFieldovi = "Polje ocjene ne smije biti prazno!\n";
			}
			if (imePrezime.getValue() == null) {
				prazniFieldovi = prazniFieldovi + "Polje studenta ne smije biti prazno!\n";
			}
			if (predmet.getValue() == null) {
				prazniFieldovi = prazniFieldovi + "Polje predmeta ne smije biti prazno!\n";
			}
			if (datumIspita.getValue() == null) {
				prazniFieldovi = prazniFieldovi + "Polje datuma ispita ne smije biti prazno!\n";
			}
			if (vrijemeIspita.getText().isEmpty()) {
				prazniFieldovi = prazniFieldovi + "Polje vrijeme pisanja ispita ispita ne smije biti prazno!\n";
			}

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogreška");
			alert.setHeaderText("Otklonite sljedeæe pogreške:");
			alert.setContentText(prazniFieldovi);

			alert.showAndWait();
		} else {
			OptionalLong maksimalniId = ispiti.stream().mapToLong(is -> is.getId()).max();
			
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
			DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
			String datumString = datumIspita.getValue().format(format1);
			datumString = datumString +"T"+vrijemeIspita.getText();
			LocalDateTime datumPisanjaIspita = LocalDateTime.parse(datumString, format);
			
			String[] array = imePrezime.getValue().split(" ", -1);
			String imePrezimeStudenta = array[0] +" "+array[1];
			String jmbagStudenta = array[2];
			Student student = null;
			for(Student st : studentiList) {
				if(imePrezimeStudenta.equals(st.getImePrezime())) {
					if(st.getJmbag().equals(jmbagStudenta)) {
						student = st;
					}
				}
			}
			
			Predmet predmetIspita = null;
			for(Predmet pr : predmetiList) {
				if(pr.getNaziv().equals(predmet.getValue())) {
					predmetIspita = pr;
				}
			}
			
			Ispit tmp = new Ispit(predmetIspita, student, Integer.parseInt(ocjena.getValue()),
					datumPisanjaIspita, maksimalniId.getAsLong() + 1);
			
			try {
				BazaPodataka.spremiNoviIspit(tmp);
			} catch (BazaPodatakException e1) {
				e1.printStackTrace();
			}

			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_ISPIT, true)));
				out.println("\n" + tmp.getId());
				out.println(tmp.getPredmet().getId());
				out.println(tmp.getStudent().getId());
				out.println(tmp.getOcjena());
				out.print(datumString);
				out.close();
			} catch (IOException e) {
				System.err.println(e);
			}

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješno dodavanje!");
			alert.setHeaderText("Ispit uspješno dodat!");
			alert.setContentText("Ispit iz " + tmp.getPredmet().getNaziv()+" za studenta "+ tmp.getStudent().getImePrezime()  +" je uspješno dodan u datoteku!");

			alert.showAndWait();

			imePrezime.setValue(null);
			vrijemeIspita.clear();
			ocjena.setValue(null);
			datumIspita.setValue(null);
			predmet.setValue(null);
		}
	}

	public void initialize() {
		Ispit ispit = new Ispit();
		try {
			ispiti = BazaPodataka.dohvatiIspitePremaKriterijima(ispit);
		} catch (BazaPodatakException e2) {
			e2.printStackTrace();
		}

		Predmet pred = new Predmet();
		try {
			predmetiList = BazaPodataka.dohvatiPredmetePremaKriterijima(pred);
		} catch (BazaPodatakException e1) {
			e1.printStackTrace();
		}
		predmetiList.stream().forEach(predm -> predmeti.add(predm.getNaziv()));
		
		Student student = new Student();
		try {
			studentiList = BazaPodataka.dohvatiStudentePremaKriterijima(student);
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}
		for(Student stud:studentiList) {
			studenti.add(stud.getImePrezime()+" "+stud.getJmbag());
		}
		
		for(int i = 1; i<6;i++) {
			ocjene.add(String.valueOf(i));
		}
		
		imePrezime.setItems(FXCollections.observableArrayList(studenti));
		ocjena.setItems(FXCollections.observableArrayList(ocjene));
		predmet.setItems(FXCollections.observableArrayList(predmeti));
	}

	@FXML
	public void prikaziUnosStudenata(ActionEvent ev) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("studentiUnos.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziUnosPredmeta(ActionEvent ev) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("predmetiUnos.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziUnosIspita(ActionEvent ev) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("ispitiUnos.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziUnosProfesora(ActionEvent ev) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("profesoriUnos.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziPretraguProfesora(ActionEvent ev) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("profesori.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziPretraguStudenata(ActionEvent ev) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("studenti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziPretraguPredmeta(ActionEvent ev) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("predmeti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziPretraguIspita(ActionEvent ev) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("ispiti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
