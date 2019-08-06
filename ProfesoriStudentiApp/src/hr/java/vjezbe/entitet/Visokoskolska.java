package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenataException;

/**
 * Sadrži funkcije koje služe za obradu ispita
 * @author Filip Èulig
 *
 */
public interface Visokoskolska {
	/**
	 * Raèuna konaènu ocjenu studenta na studiju
	 * @param ispiti Polje ispita 
	 * @param ocjenaPismenogZavrsnogRada Ocjena pismenog dijela zavrsnog rada
	 * @param ocjenaObraneZavrsnogRada Ocjena obrane zavrsnog rada
	 * @return BigDecimal Konaèna ocjena
	 */
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaPismenogZavrsnogRada, int ocjenaObraneZavrsnogRada);

	/**
	 * Raèunanje prosjeka na ispitama, ako student ima barem jedan ispit ocjenjen nedovoljno onda baca iznimku i vraæa 1
	 * @param list Polje ispita
	 * @return BigDecimal Prosjek ocjena na ispitima
	 * @throws NemoguceOdreditiProsjekStudenataException Iznimka ako student ima barem jedan ispit nedovoljan
	 */
	public default BigDecimal odrediProsjekOcjenaNaIspitima(List<Ispit> list) throws NemoguceOdreditiProsjekStudenataException{
		int zbroj = 0;
		
		for(Ispit ispit : list) {
			if(ispit.getOcjena()==1) {
				throw new NemoguceOdreditiProsjekStudenataException("Student "+ispit.getStudent().getIme()+" "+ispit.getStudent().getPrezime()+" ima negativnu ocjenu na ispitu "+ ispit.getPredmet().getNaziv());
			}
		}
		
		for(Ispit ispit : list) {
			zbroj += ispit.getOcjena();
		}
		if(zbroj==0) {
			return new BigDecimal(1);
		}else {
			BigDecimal prosjek = new BigDecimal(zbroj);
			BigDecimal tmpLeng = new BigDecimal(list.size());
			prosjek.divide(tmpLeng);
		
			return prosjek;
		}
	}
	
	/**
	 * Filtrira polozene ispite
	 * @param list Polje ispita
	 * @return Ispit[] Polozeni ispiti
	 */
	private List<Ispit> filtrirajPolozeneIspite(List<Ispit> list) {
		//long start = System.currentTimeMillis();
		List<Ispit> polozeniIspiti = new ArrayList<>();
		polozeniIspiti = list.stream().filter(ispit -> ispit.getOcjena()>1).collect(Collectors.toList());
		//long end = System.currentTimeMillis();
		return polozeniIspiti;	
		//bez lamdi je trajalo 1ms
		//s lamdama je trajalo 5ms
	}
	
	/**
	 * Filtrira i vraæa ispite kojima je student pristupio
	 * @param list Polje ispita
	 * @param student Student
	 * @return Ispit[] Ispiti kojima je student pristupio
	 */
	public default List<Ispit> filtrirajIspitePoStudentu(List<Ispit> list, Student student) {
		List<Ispit> polozeniIspiti = filtrirajPolozeneIspite(list);
		
		List<Ispit> ispitiPoStudentu = new ArrayList<>();
		for(Ispit ispit : polozeniIspiti) {
			if (ispit.getStudent().equals(student)) {
				ispitiPoStudentu.add(ispit);
			}
		}

		return ispitiPoStudentu;
	}
}
