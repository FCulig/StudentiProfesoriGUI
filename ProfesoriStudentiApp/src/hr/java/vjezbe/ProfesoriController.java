package hr.java.vjezbe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class ProfesoriController {
	@FXML
	private TableView<Profesor> profesorTablica;
	@FXML
	private TableColumn<Profesor, String> sifraProfesora;
	@FXML
	private TableColumn<Profesor, String> imeProfesora;
	@FXML
	private TableColumn<Profesor, String> prezimeProfesora;
	@FXML
	private TableColumn<Profesor, String> titulaProfesora;

	@FXML
	private TextField sifra;
	@FXML
	private TextField prezime;
	@FXML
	private TextField ime;
	@FXML
	private TextField titula;

	@FXML
	private Button pretraziProfesore;

	private List<Profesor> profesori;

	public void pretraziProfesore() {
		List<Profesor> filtrirani = new ArrayList<>();
		filtrirani.addAll(profesori);

		if (!sifra.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(pr -> pr.getSifra().toLowerCase().startsWith(sifra.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		if (!ime.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(pr -> pr.getIme().toLowerCase().startsWith(ime.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		if (!prezime.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(pr -> pr.getPrezime().toLowerCase().startsWith(prezime.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		if (!titula.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(pr -> pr.getTitula().toLowerCase().startsWith(titula.getText().toLowerCase()))
					.collect(Collectors.toList());
		}

		ObservableList<Profesor> listaProfesora = FXCollections.observableArrayList(filtrirani);
		profesorTablica.setItems(listaProfesora);
		titulaProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("titula"));
		imeProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("ime"));
		prezimeProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("prezime"));
		sifraProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("sifra"));

	}

	@FXML
	public void initialize() {
		Profesor profesor= new Profesor();
		try {
			profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(profesor);
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}

		ObservableList<Profesor> listaProfesora = FXCollections.observableArrayList(profesori);
		profesorTablica.setItems(listaProfesora);
		titulaProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("titula"));
		imeProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("ime"));
		prezimeProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("prezime"));
		sifraProfesora.setCellValueFactory(new PropertyValueFactory<Profesor, String>("sifra"));
	}

	@FXML
	public void prikaziPretraguIspita() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("ispiti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziPretraguStudenata() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("studenti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prikaziPretraguPredmeta() {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("predmeti.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
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
	public void prikaziUnosIspita(ActionEvent ev) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("ispitiUnos.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
