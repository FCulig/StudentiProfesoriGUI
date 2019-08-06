package hr.java.vjezbe;
	
import hr.java.vjezbe.niti.DatumRodenjaNit;
import hr.java.vjezbe.niti.NajboljiStudentNit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
import javafx.util.Duration;

public class Main extends Application {
	public static Stage stage= new Stage();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,400,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage = primaryStage;
			Image icon = new Image("file:artwork/CF-Basic-ikona.jpg");
			stage.setTitle("Najbolji student jos nije određen!");
			stage.getIcons().add(icon);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Timeline prikazSlavljenika = new Timeline( 
				new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() { 
					@Override public void handle(ActionEvent event) {   
						Platform.runLater(new DatumRodenjaNit());  } })); 
		prikazSlavljenika.setCycleCount(Timeline.INDEFINITE); 
		prikazSlavljenika.play(); 
		
		Timeline prikazNajboljegStudenta = new Timeline( 
				new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() { 
					@Override public void handle(ActionEvent event) {   
						Platform.runLater(new NajboljiStudentNit());  } })); 
		prikazNajboljegStudenta.setCycleCount(Timeline.INDEFINITE); 
		prikazNajboljegStudenta.play();
	}
	
	public static void setMainPage(BorderPane root) {
		Scene scene = new Scene(root, 400, 500);
		stage.setScene(scene);
		stage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
