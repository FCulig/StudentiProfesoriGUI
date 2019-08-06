package hr.java.vjezbe.entitet;

/**
 * @author Filip �ulig
 *	Enumeracija koja sadr�i ocjene koje studenti mogu dobiti na ispitima.
 */
public enum Ocjena {
	ODLICAN(5),
	VRLO_DOBAR(4),
	DOBAR(3),
	DOVOLJAN(2),
	NEDOVOLJAN(1);
	
	private int ocj;
	
	private Ocjena(int ocj) {
		this.ocj = ocj;
	}
	
	public int getOcjena() {
		return ocj;
	}
	
	public void setOcjena(int ocj) {
		this.ocj = ocj;
	}
}
