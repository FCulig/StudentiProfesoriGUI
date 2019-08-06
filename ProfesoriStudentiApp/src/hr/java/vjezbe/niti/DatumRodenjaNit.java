package hr.java.vjezbe.niti;

import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatumRodenjaNit implements Runnable {

	private List<Student> slavljenici;

	public void run() {
		try {
			slavljenici = BazaPodataka.dohvatiSlavljenike();
		} catch (BazaPodatakException e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Rođendani Studenata");
		alert.setHeaderText("ROĐENDAN!!!");
		
		StringBuilder poruka = new StringBuilder("Danas studenti ");
		if (!slavljenici.isEmpty()) {
			if(slavljenici.size()>1) {
				for(Student stud : slavljenici) {
					poruka.append(stud.getImePrezime()+", ");
				}
				poruka.append(" imaju rođendan."); 
				alert.setContentText(poruka.toString());
			}else {
				alert.setContentText("Danas student "+ slavljenici.get(0).getImePrezime() +" ima rođendan!");
			}
		}else {
			alert.setContentText("Danas niko nema rođendan.");
		}
		
		

		alert.showAndWait();
	}

}
