package hr.java.vjezbe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.OptionalLong;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class StudentiUnosController {
	@FXML
	private TextField JMBAG;
	@FXML
	private TextField prezime;
	@FXML
	private TextField ime;
	@FXML
	private DatePicker datumRodenja;

	@FXML
	private Button unesiStudente;

	private List<Student> studenti;
	private static final String FILE_STUDENTI = "dat/studenti.txt";

	public void unesi() {
		String prazniFieldovi = "";

		if (JMBAG.getText().isEmpty() || prezime.getText().isEmpty() || ime.getText().isEmpty()
				|| datumRodenja.getValue() == null) {
			if (JMBAG.getText().isEmpty()) {
				prazniFieldovi = "Polje JMBAG ne smije biti prazno!\n";
			}
			if (ime.getText().isEmpty()) {
				prazniFieldovi = prazniFieldovi + "Polje ime ne smije biti prazno!\n";
			}
			if (prezime.getText().isEmpty()) {
				prazniFieldovi = prazniFieldovi + "Polje prezime bodova ne smije biti prazno!\n";
			}
			if (datumRodenja.getValue() == null) {
				prazniFieldovi = prazniFieldovi + "Polje datum roðenja ne smije biti prazno!\n";
			}

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogreška");
			alert.setHeaderText("Otklonite sljedeæe pogreške:");
			alert.setContentText(prazniFieldovi);

			alert.showAndWait();
		} else {
			OptionalLong maksimalniId = studenti.stream().mapToLong(student -> student.getId()).max();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
			String datumString = datumRodenja.getValue().format(format);
			LocalDate datumRodenjaStudenta = LocalDate.parse(datumString, format);
			
			Student tmp = new Student(ime.getText(), prezime.getText(), JMBAG.getText(),
					datumRodenjaStudenta, maksimalniId.getAsLong() + 1);
			try {
				BazaPodataka.spremiNovogStudenta(tmp);
			} catch (BazaPodatakException e1) {
				e1.printStackTrace();
			}

			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_STUDENTI, true)));
				out.println("\n" + tmp.getId());
				out.println(tmp.getIme());
				out.println(tmp.getPrezime());
				out.println(datumString);
				out.print(tmp.getJmbag());
				out.close();
			} catch (IOException e) {
				System.err.println(e);
			}

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješno dodavanje!");
			alert.setHeaderText("Student uspješno dodat!");
			alert.setContentText("Student " + tmp.getImePrezime() + " je uspješno dodan u datoteku!");

			alert.showAndWait();

			ime.clear();
			prezime.clear();
			JMBAG.clear();
			datumRodenja.setValue(null);
		}

	}

	public void initialize() {
		Student student = new Student();
		try {
			studenti = BazaPodataka.dohvatiStudentePremaKriterijima(student);
		} catch (BazaPodatakException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
