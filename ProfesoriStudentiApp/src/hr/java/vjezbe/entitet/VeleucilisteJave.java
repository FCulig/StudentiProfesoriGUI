package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenataException;

/**
 * Klasa u kojoj se overridaju metode nasljeðene od ObrazovnaUstanova i Visokoskolska
 * @author Filip Èulig
 *
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -507827903033781305L;
	/**
	 * Konstanta koja omoguæuje lakše korištenje loggera
	 */
	private static final Logger logger = LoggerFactory.getLogger(Visokoskolska.class);
	

	/**
	 * Konstruktor klase Veleuèilište jave
	 * @param nazivUstanove Ime ustanove
	 * @param predmeti Polje predmeta
	 * @param profesori	Polje profesora
	 * @param studenti Polje studenata
	 * @param ispiti Polje Ispita
	 */
	public VeleucilisteJave(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti,List<Ispit> ispiti,Long id) {
		super(nazivUstanove, predmeti, profesori, studenti, ispiti, id);
	}
	public VeleucilisteJave(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori,
			List<Student> studenti, List<Ispit> ispiti) {
		super(nazivUstanove, predmeti, profesori, studenti, ispiti);
	}
	/* (non-Javadoc)
	 * @see hr.java.vjezbe.entitet.Visokoskolska#izracunajKonacnuOcjenuStudijaZaStudenta(hr.java.vjezbe.entitet.Ispit[], int, int)
	 */
	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaPismenogZavrsnogRada,
			int ocjenaObraneZavrsnogRada) {
		try {
			BigDecimal konacnaOcjena = (((odrediProsjekOcjenaNaIspitima(ispiti).multiply(new BigDecimal(2)).add(new BigDecimal(ocjenaObraneZavrsnogRada))).add(new BigDecimal(ocjenaPismenogZavrsnogRada))).divide(new BigDecimal(4)));
			return konacnaOcjena;
		}catch(NemoguceOdreditiProsjekStudenataException e) {
			logger.info(e.getMessage());
		}
		return new BigDecimal(1);
	}

	/* (non-Javadoc)
	 * @see hr.java.vjezbe.entitet.ObrazovnaUstanova#odrediNajuspjesnijegStudentaNaGodini(int)
	 */
	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godinaStudija) {
		Student najuspjesnijiStudent = getStudenti().get(0);
		BigDecimal najboljiProsjek = new BigDecimal(1);
		
		try {
			najboljiProsjek = odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(getIspiti(), getStudenti().get(0)));
		} catch (NemoguceOdreditiProsjekStudenataException e) {
			logger.info(e.getMessage(),e);
			System.out.println(e.getMessage());
		}
		
		BigDecimal tmpProsjek = new BigDecimal(0);
		
		for(int i=0; i < getStudenti().size(); i++) {
				try {
					tmpProsjek = odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(getIspiti(), getStudenti().get(i)));
					if (tmpProsjek.compareTo(najboljiProsjek)>=0) {
						najuspjesnijiStudent = getStudenti().get(i);
						najboljiProsjek = tmpProsjek;
					}
				} catch (NemoguceOdreditiProsjekStudenataException e) {
					logger.info(e.getMessage(),e);
					System.out.println(e.getMessage());
				}
		}
		
		
		return najuspjesnijiStudent;
	}
	
	@Override
	public String toString() {
		return getNazivUstanove() +" "+ getBrojStudenata();
	}
	
}
