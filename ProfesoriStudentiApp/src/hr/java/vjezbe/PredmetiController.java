package hr.java.vjezbe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.iznimke.BazaPodatakException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;

public class PredmetiController {
	@FXML
	private TableView<Predmet> predmetTablica;

	@FXML
	private TableColumn<Predmet, String> sifraPredmeta;
	@FXML
	private TableColumn<Predmet, String> nazivPredmeta;
	@FXML
	private TableColumn<Predmet, Integer> ECTSBodovi;
	@FXML
	private TableColumn<Predmet, String> nositeljPredmeta;

	@FXML
	private TextField sifra;
	@FXML
	private TextField naziv;
	@FXML
	private TextField ECTS;
	@FXML
	private TextField nositelj;

	@FXML
	private Button pretraziPredmete;

	private List<Predmet> predmeti;

	public void pretraziPredmete() {
		List<Predmet> filtrirani = new ArrayList<>();
		filtrirani.addAll(predmeti);

		if (!sifra.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(pr -> pr.getSifra().toLowerCase().startsWith(sifra.getText().toLowerCase()))
					.collect(Collectors.toList());
		}

		if (!naziv.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(pr -> pr.getNaziv().toLowerCase().startsWith(naziv.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		if (!ECTS.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(pr -> pr.getBrojEctsBodova().equals(Integer.parseInt(ECTS.getText())))
					.collect(Collectors.toList());
		}
		if (!nositelj.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(pr -> pr.getNositelj().getIme().toLowerCase().startsWith(nositelj.getText().toLowerCase())
							|| pr.getNositelj().getPrezime().toLowerCase().startsWith(nositelj.getText().toLowerCase()))
					.collect(Collectors.toList());
		}

		ObservableList<Predmet> listaPredmeta = FXCollections.observableArrayList(filtrirani);
		predmetTablica.setItems(listaPredmeta);
		sifraPredmeta.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		nazivPredmeta.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		ECTSBodovi.setCellValueFactory(new PropertyValueFactory<>("brojEctsBodova"));
		nositeljPredmeta.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> predmet) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(predmet.getValue().getNositelj().getImePrezime());
						return property;
					}
				});

	}

	@FXML
	public void initialize() {
		Predmet predmet = new Predmet();
		try {
			predmeti = BazaPodataka.dohvatiPredmetePremaKriterijima(predmet);
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}

		ObservableList<Predmet> listaPredmeta = FXCollections.observableArrayList(predmeti);
		predmetTablica.setItems(listaPredmeta);
		sifraPredmeta.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		nazivPredmeta.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		ECTSBodovi.setCellValueFactory(new PropertyValueFactory<>("brojEctsBodova"));
		nositeljPredmeta.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Predmet, String> predmet) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(predmet.getValue().getNositelj().getImePrezime());
						return property;
					}
				});
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
	public void prikaziPretraguIspita(ActionEvent ev) {
		BorderPane root;
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("ispiti.fxml"));
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
