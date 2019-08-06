package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudenataException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;

/**
 * Klasa u kojoj overridamo metode iz ObrazovnaUstanova i Diplomski
 * @author Filip Èulig
 *
 */
public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1543084092981381082L;
	/**
	 * Konstanta koja nam omoguæuje lakše korištenje loggera
	 */
	private static final Logger logger = LoggerFactory.getLogger(FakultetRacunarstva.class);

	public FakultetRacunarstva(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti,
			List<Ispit> ispiti,Long id) {
		super(nazivUstanove, predmeti, profesori, studenti, ispiti, id);
	}

	public FakultetRacunarstva(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori,
			List<Student> studenti, List<Ispit> ispiti) {
		super(nazivUstanove, predmeti, profesori, studenti, ispiti);
	}

	/* (non-Javadoc)
	 * @see hr.java.vjezbe.entitet.Visokoskolska#izracunajKonacnuOcjenuStudijaZaStudenta(hr.java.vjezbe.entitet.Ispit[], int, int)
	 */
	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaPismenogDiplomskogRada,
			int ocjenaObraneDiplomskogRada) {
		try {
			BigDecimal djeljitelj = new BigDecimal(5);
			BigDecimal konacnaOcjena = (((odrediProsjekOcjenaNaIspitima(ispiti).multiply(new BigDecimal(3)).add(new BigDecimal(ocjenaPismenogDiplomskogRada))).add(new BigDecimal(ocjenaObraneDiplomskogRada))).divide(djeljitelj));
			return konacnaOcjena;
		}catch (NemoguceOdreditiProsjekStudenataException e){
			logger.info(e.getMessage());
		}
		return new BigDecimal(1);
	}

	/* (non-Javadoc)
	 * @see hr.java.vjezbe.entitet.Diplomski#odrediStudentaZaRektorovuNagradu()
	 */
	@Override
	public Student odrediStudentaZaRektorovuNagradu() throws PostojiViseNajmladjihStudenataException{
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
			} catch (NemoguceOdreditiProsjekStudenataException e) {
				logger.info(e.getMessage(),e);
				System.out.println(e.getMessage());
			}
			if (tmpProsjek.compareTo(najboljiProsjek)>0) {
				najuspjesnijiStudent = getStudenti().get(i);
				najboljiProsjek = tmpProsjek;
			}else if(tmpProsjek.compareTo(najboljiProsjek)==0) {
				if(najuspjesnijiStudent.getDatumRodenja().isAfter(getStudenti().get(i).getDatumRodenja())) {
					najuspjesnijiStudent=getStudenti().get(i);
				}
			}
		}
		
		for(int i=0; i<getStudenti().size(); i++) {
			if(najuspjesnijiStudent.getDatumRodenja().isEqual(getStudenti().get(i).getDatumRodenja())) {
				throw new PostojiViseNajmladjihStudenataException("Studenti "+ najuspjesnijiStudent.getIme()+" "+ najuspjesnijiStudent.getPrezime()+" i "+getStudenti().get(i).getIme()+" "+getStudenti().get(i).getPrezime()+" su rodeni na isti datum!");
			}
		}
		
		return najuspjesnijiStudent;
	}

	/* (non-Javadoc)
	 * @see hr.java.vjezbe.entitet.ObrazovnaUstanova#odrediNajuspjesnijegStudentaNaGodini(int)
	 */
	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godinaStudija) {
		Student najuspjesnijiStudent = getStudenti().get(0);
		int najviseIzvrsnih = 0;
		int izvrsni = 0;

		for(int i=0; i < getStudenti().size(); i++) {
			for(int j=0; j < filtrirajIspitePoStudentu(getIspiti(), getStudenti().get(i)).size() ; j++) {
				if(filtrirajIspitePoStudentu(getIspiti(), getStudenti().get(i)).get(j).getOcjena()==5) {
					izvrsni++;
				}
			}
			
			if(izvrsni>najviseIzvrsnih) {
				najuspjesnijiStudent = getStudenti().get(i);
				najviseIzvrsnih=izvrsni;
			}
			izvrsni=0;
		}
		
		return najuspjesnijiStudent;
	}

	@Override
	public String toString() {
		return  getNazivUstanove() + " " + getBrojStudenata() ;
	}
	
	
	
}
