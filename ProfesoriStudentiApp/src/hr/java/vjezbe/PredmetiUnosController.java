package hr.java.vjezbe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class PredmetiUnosController {
	@FXML
	private TextField sifra;
	@FXML
	private TextField naziv;
	@FXML
	private TextField ECTS;
	@FXML
	private ChoiceBox<String> nositelj;

	private List<Predmet> predmeti;
	private List<Profesor> profesori;
	private static final String FILE_PREDMETI ="dat/predmeti.txt"; 
	
	public void unesi() {
		String prazniFieldovi = "";
		
		if (sifra.getText().isEmpty() || naziv.getText().isEmpty() || ECTS.getText().isEmpty()
				|| nositelj.getValue().toString().isEmpty()) {
			if (sifra.getText().isEmpty()) {
				prazniFieldovi = "Polje sifra ne smije biti prazno!\n";
			}
			if (naziv.getText().isEmpty()) {
				prazniFieldovi = prazniFieldovi + "Polje naziv ne smije biti prazno!\n";
			}
			if (ECTS.getText().isEmpty()) {
				prazniFieldovi = prazniFieldovi + "Polje ECTS bodova ne smije biti prazno!\n";
			}
			if (nositelj.getValue() == null) {
				prazniFieldovi = prazniFieldovi + "Polje nositelj predmeta ne smije biti prazno!\n";
			}

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogreška");
			alert.setHeaderText("Otklonite sljedeæe pogreške:");
			alert.setContentText(prazniFieldovi);

			alert.showAndWait();
		} else {
			OptionalLong maksimalniId = predmeti.stream().mapToLong(predmet -> predmet.getId()).max();

			Profesor nositeljPredmeta = new Profesor();
			for(Profesor prof : profesori) {
				if(prof.getImePrezime().equals(nositelj.getValue())) {
					nositeljPredmeta = prof;
				}
			}
			
			Predmet tmp = new Predmet(naziv.getText(), sifra.getText(), Integer.parseInt(ECTS.getText()), nositeljPredmeta,
					maksimalniId.getAsLong() + 1);
			
			try {
				BazaPodataka.spremiNoviPredmet(tmp);
			} catch (BazaPodatakException e1) {
				e1.printStackTrace();
			}

			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PREDMETI, true)));
			    out.println("\n"+tmp.getId());
				out.println(tmp.getSifra());
				out.println(tmp.getNaziv());
				out.println(tmp.getBrojEctsBodova());
				out.println(tmp.getNositelj().getId());
				out.print("x");
				out.close();
			} catch (IOException e) {
				System.err.println(e);
			}
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješno dodavanje!");
			alert.setHeaderText("Predmet uspješno dodat!");
			alert.setContentText("Predmet "+ tmp.getNaziv() +" je uspješno dodan u datoteku!");

			alert.showAndWait();
			
			sifra.clear();
			naziv.clear();
			ECTS.clear();
		}
	}

	public void initialize() {
		Predmet predmet = new Predmet();
		Profesor profesor = new Profesor();
		
		try {
			predmeti=BazaPodataka.dohvatiPredmetePremaKriterijima(predmet);
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}
		try {
			profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(profesor);
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}
		List<String> imenaProfesora = new ArrayList<>();
		profesori.stream().forEach(prof -> imenaProfesora.add(prof.getImePrezime()));
		
		nositelj.setItems(FXCollections.observableArrayList(imenaProfesora));
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
