package hr.java.vjezbe.iznimke;

/**
 * Klasa iznimke kada student ima barem jednu negativnu ocjenu na ispitima
 * @author Filip Èulig
 *
 */
public class NemoguceOdreditiProsjekStudenataException extends Exception{
	/**
	 * ID iznimke NemoguceOdreditiProsjekStudenataException.
	 */
	private static final long serialVersionUID = 1115957693559641626L;

	/**
	 * Defaultni konstruktor kada ne znamo zašto se pojavila iznimka
	 */
	public NemoguceOdreditiProsjekStudenataException() { 
		super("Dogodila se pogreška u radu programa!"); 
	}
	
	/**
	 *  Prima poruku o pogrešci i prosljeðuje ju nadklasi
	 * @param message Baèena poruka
	 */
	public NemoguceOdreditiProsjekStudenataException(String message) { 
		super(message); 
	}
	
	/**
	 * Prima poruku o pogrešci i njezin uzrok i prosljeðuje ih nadklasi
	 * @param message Baèena poruka
	 * @param cause	Uzrok iznimke
	 * 
	 */
	public NemoguceOdreditiProsjekStudenataException(String message, Throwable cause) { 
		super(message, cause); 
	}
	
	/**
	 * Prima uzrok pogreške i prosljeðuje ga nadklasi
	 * @param cause Uzrok iznimke
	 * 
	 */
	public NemoguceOdreditiProsjekStudenataException(Throwable cause) { 
		super(cause);
	}
}
