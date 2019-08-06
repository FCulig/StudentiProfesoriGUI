package hr.java.vjezbe;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class MainController {
	@FXML
	private MenuItem pretragaProfesora;
	@FXML
	private MenuItem pretragaPredmeta;
	@FXML
	private MenuItem pretragaStudenata;
	@FXML
	private MenuItem pretragaIspita;
	@FXML
	private MenuItem unosProfesora;
	@FXML
	private MenuItem unosPredmeta;
	@FXML
	private MenuItem unosStudenata;
	@FXML
	private MenuItem unosIspita;
	
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
