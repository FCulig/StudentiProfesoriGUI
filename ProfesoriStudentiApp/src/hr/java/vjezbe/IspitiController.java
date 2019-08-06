package hr.java.vjezbe;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.iznimke.BazaPodatakException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class IspitiController {
	@FXML
	private TableView<Ispit> ispitTablica;

	@FXML
	private TableColumn<Ispit, String> predmetIspita;
	@FXML
	private TableColumn<Ispit, String> imePrezimeStudenta;
	@FXML
	private TableColumn<Ispit, String> ocjenaIspita;
	@FXML
	private TableColumn<Ispit, String> datumVrijemeIspita;

	@FXML
	private TextField predmet;
	@FXML
	private TextField imePrezime;
	@FXML
	private DatePicker datumIspita;
	@FXML
	private TextField ocjena;

	@FXML
	private Button pretraziIspite;
	
	private List<Ispit> ispiti;
	
	public void pretraziIspite() {
		List<Ispit> filtrirani = new ArrayList<>();
		filtrirani.addAll(ispiti);
		
		if (!predmet.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(is -> is.getPredmet().getNaziv().toLowerCase().startsWith(predmet.getText()))
					.collect(Collectors.toList());
		}
		if (!imePrezime.getText().isEmpty()) {
			if (filtrirani.size() == 0) {
				filtrirani = filtrirani.stream().filter(is -> is.getStudent().getIme().toLowerCase()
						.startsWith(imePrezime.getText().toLowerCase())
						|| is.getStudent().getIme().toLowerCase().startsWith(imePrezime.getText().toLowerCase()))
						.collect(Collectors.toList());
			} else {
				filtrirani = filtrirani.stream().filter(is -> is.getStudent().getIme().toLowerCase()
						.startsWith(imePrezime.getText().toLowerCase())
						|| is.getStudent().getIme().toLowerCase().startsWith(imePrezime.getText().toLowerCase()))
						.collect(Collectors.toList());
			}
		}
		if (datumIspita.getValue() != null) {
			if (filtrirani.size() == 0) {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				String datumString = datumIspita.getValue().format(format);
				LocalDate datum = LocalDate.parse(datumString, format);
				filtrirani = filtrirani.stream()
						.filter(is -> is.getDatumIVrijeme().getDayOfMonth() == datum.getDayOfMonth()
								&& is.getDatumIVrijeme().getMonth() == datum.getMonth()
								&& is.getDatumIVrijeme().getYear() == datum.getYear())
						.collect(Collectors.toList());
			} else {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				String datumString = datumIspita.getValue().format(format);
				LocalDate datum = LocalDate.parse(datumString, format);
				filtrirani = filtrirani.stream()
						.filter(is -> is.getDatumIVrijeme().getDayOfMonth() == datum.getDayOfMonth()
								&& is.getDatumIVrijeme().getMonth() == datum.getMonth()
								&& is.getDatumIVrijeme().getYear() == datum.getYear())
						.collect(Collectors.toList());
			}
		}
		if (!ocjena.getText().isEmpty()) {
			if (filtrirani.size() == 0) {
				filtrirani = filtrirani.stream().filter(is -> is.getOcjena().equals(Integer.parseInt(ocjena.getText())))
						.collect(Collectors.toList());
			} else {
				filtrirani = filtrirani.stream().filter(is -> is.getOcjena().equals(Integer.parseInt(ocjena.getText())))
						.collect(Collectors.toList());
			}
		}

		ObservableList<Ispit> listaIspita = FXCollections.observableArrayList(filtrirani);
		ispitTablica.setItems(listaIspita);
		ocjenaIspita.setCellValueFactory(new PropertyValueFactory<>("ocjena"));
		predmetIspita.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> is) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(is.getValue().getPredmet().getNaziv());
						return property;
					}
				});
		datumVrijemeIspita.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> is) {
						SimpleStringProperty property = new SimpleStringProperty();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
						property.setValue(is.getValue().getDatumIVrijeme().format(formatter));
						return property;
					}
				});
		imePrezimeStudenta.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> is) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(is.getValue().getStudent().getImePrezime());
						return property;
					}
				});
		
		datumIspita.setValue(null);

		
	}

	public void initialize() {
		Ispit ispit = new Ispit();
		
		try {
			ispiti = BazaPodataka.dohvatiIspitePremaKriterijima(ispit);
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}

		ObservableList<Ispit> listaIspita = FXCollections.observableArrayList(ispiti);
		ispitTablica.setItems(listaIspita);
		ocjenaIspita.setCellValueFactory(new PropertyValueFactory<>("ocjena"));
		predmetIspita.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> is) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(is.getValue().getPredmet().getNaziv());
						return property;
					}
				});
		datumVrijemeIspita.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> is) {
						SimpleStringProperty property = new SimpleStringProperty();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
						property.setValue(is.getValue().getDatumIVrijeme().format(formatter));
						return property;
					}
				});
		imePrezimeStudenta.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Ispit, String> is) {
						SimpleStringProperty property = new SimpleStringProperty();
						property.setValue(is.getValue().getStudent().getImePrezime());
						return property;
					}
				});
		
		datumIspita.setValue(null);

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
