package hr.java.vjezbe;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
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

public class StudentController {
	@FXML
	private TableView<Student> studentTablica;

	@FXML
	private TableColumn<Student, String> jmbagStudenta;
	@FXML
	private TableColumn<Student, String> prezimeStudenta;
	@FXML
	private TableColumn<Student, String> imeStudenta;
	@FXML
	private TableColumn<Student, String> datumRodenjaStudenta;

	@FXML
	private TextField JMBAG;
	@FXML
	private TextField prezime;
	@FXML
	private TextField ime;
	@FXML
	private DatePicker datumRodenja;

	@FXML
	private Button pretraziStudente;

	private List<Student> studenti;

	public void pretraziStudente() {
		List<Student> filtrirani = new ArrayList<>();
		filtrirani.addAll(studenti);

		if (!JMBAG.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(st -> st.getJmbag().toLowerCase().startsWith(JMBAG.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		if (!prezime.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(st -> st.getPrezime().toLowerCase().startsWith(prezime.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		if (!ime.getText().isEmpty()) {
			filtrirani = filtrirani.stream()
					.filter(st -> st.getIme().toLowerCase().startsWith(ime.getText().toLowerCase()))
					.collect(Collectors.toList());
		}
		if (datumRodenja.getValue() != null) {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
			String datumString = datumRodenja.getValue().format(format);
			LocalDate datum = LocalDate.parse(datumString, format);
			filtrirani = filtrirani.stream().filter(st -> st.getDatumRodenja().equals(datum))
					.collect(Collectors.toList());
		}

		ObservableList<Student> listaStudenata = FXCollections.observableArrayList(filtrirani);
		studentTablica.setItems(listaStudenata);
		jmbagStudenta.setCellValueFactory(new PropertyValueFactory<>("jmbag"));
		imeStudenta.setCellValueFactory(new PropertyValueFactory<>("ime"));
		prezimeStudenta.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		datumRodenjaStudenta.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
						SimpleStringProperty property = new SimpleStringProperty();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
						property.setValue(student.getValue().getDatumRodenja().format(formatter));
						return property;
					}
				});

		datumRodenja.setValue(null);

	}

	@FXML
	public void initialize() {
		Student student = new Student();
		try {
			studenti = BazaPodataka.dohvatiStudentePremaKriterijima(student);
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}

		ObservableList<Student> listaStudenata = FXCollections.observableArrayList(studenti);
		studentTablica.setItems(listaStudenata);
		jmbagStudenta.setCellValueFactory(new PropertyValueFactory<>("jmbag"));
		imeStudenta.setCellValueFactory(new PropertyValueFactory<>("ime"));
		prezimeStudenta.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		datumRodenjaStudenta.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
						SimpleStringProperty property = new SimpleStringProperty();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
						property.setValue(student.getValue().getDatumRodenja().format(formatter));
						return property;
					}
				});

		datumRodenja.setValue(null);
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
