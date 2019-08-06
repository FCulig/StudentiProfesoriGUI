package hr.java.vjezbe.iznimke;

/**
 * Klasa iznimke kada student ima barem jednu negativnu ocjenu na ispitima
 * @author Filip �ulig
 *
 */
public class NemoguceOdreditiProsjekStudenataException extends Exception{
	/**
	 * ID iznimke NemoguceOdreditiProsjekStudenataException.
	 */
	private static final long serialVersionUID = 1115957693559641626L;

	/**
	 * Defaultni konstruktor kada ne znamo za�to se pojavila iznimka
	 */
	public NemoguceOdreditiProsjekStudenataException() { 
		super("Dogodila se pogre�ka u radu programa!"); 
	}
	
	/**
	 *  Prima poruku o pogre�ci i proslje�uje ju nadklasi
	 * @param message Ba�ena poruka
	 */
	public NemoguceOdreditiProsjekStudenataException(String message) { 
		super(message); 
	}
	
	/**
	 * Prima poruku o pogre�ci i njezin uzrok i proslje�uje ih nadklasi
	 * @param message Ba�ena poruka
	 * @param cause	Uzrok iznimke
	 * 
	 */
	public NemoguceOdreditiProsjekStudenataException(String message, Throwable cause) { 
		super(message, cause); 
	}
	
	/**
	 * Prima uzrok pogre�ke i proslje�uje ga nadklasi
	 * @param cause Uzrok iznimke
	 * 
	 */
	public NemoguceOdreditiProsjekStudenataException(Throwable cause) { 
		super(cause);
	}
}
