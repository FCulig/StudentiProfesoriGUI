package hr.java.vjezbe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.OptionalLong;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class ProfesoriUnosController {
	@FXML
	private TextField sifra;
	@FXML
	private TextField prezime;
	@FXML
	private TextField ime;
	@FXML
	private TextField titula;

	private List<Profesor> profesori;
	public static final String FILE_PROFESORI = "dat/profesori.txt";

	public void unesi() {
		String prazniFieldovi = "";
		if (sifra.getText().isEmpty() || prezime.getText().isEmpty() || ime.getText().isEmpty()
				|| titula.getText().isEmpty()) {
			if (sifra.getText().isEmpty()) {
				prazniFieldovi = "Polje sifra ne smije biti prazno!\n";
			}
			if (ime.getText().isEmpty()) {
				prazniFieldovi = prazniFieldovi + "Polje ime ne smije biti prazno!\n";
			}
			if (prezime.getText().isEmpty()) {
				prazniFieldovi = prazniFieldovi + "Polje prezime ne smije biti prazno!\n";
			}
			if (titula.getText().isEmpty()) {
				prazniFieldovi = prazniFieldovi + "Polje titula ne smije biti prazno!\n";
			}

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogreška");
			alert.setHeaderText("Otklonite sljedeæe pogreške:");
			alert.setContentText(prazniFieldovi);

			alert.showAndWait();
		} else {
			OptionalLong maksimalniId = profesori.stream().mapToLong(profesor -> profesor.getId()).max();

			Profesor tmp = new Profesor(ime.getText(), prezime.getText(), sifra.getText(), titula.getText(),
					maksimalniId.getAsLong() + 1);
			try {
				BazaPodataka.spremiNovogProfesora(tmp);
			} catch (BazaPodatakException e1) {
				e1.printStackTrace();
			}

			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PROFESORI, true)));
			    out.println("\n"+tmp.getId());
				out.println(tmp.getSifra());
				out.println(tmp.getIme());
				out.println(tmp.getPrezime());
				out.print(tmp.getTitula());
				out.close();
			} catch (IOException e) {
				System.err.println(e);
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješno dodavanje!");
			alert.setHeaderText("Uspješno ste dodali profesora u datoteku!");
			alert.setContentText("Profesor "+tmp.getImePrezime()+" je uspješno dodan u datoteku.");

			alert.showAndWait();
			
			sifra.clear();
			ime.clear();
			prezime.clear();
			titula.clear();
		}
	}

	public void initialize() {
		Profesor profesor = new Profesor();
		try {
			profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(profesor);
		} catch (BazaPodatakException e) {
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
