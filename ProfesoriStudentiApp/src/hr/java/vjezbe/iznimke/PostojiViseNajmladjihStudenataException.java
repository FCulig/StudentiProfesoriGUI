package hr.java.vjezbe.iznimke;

/**
 * Klasa iznimke kada postoji više mlaðih studenata kod dodjeljivanja rektorove nagrade.
 * @author Filip Èulig
 *
 */
public class PostojiViseNajmladjihStudenataException extends RuntimeException{
	/**
	 * ID iznimke PostojiViseNajmladihStudenataException
	 */
	private static final long serialVersionUID = -296381963817458610L;

	/**
	 * Defaultni konstruktor kada ne znamo što je prouzroèilo iznimku.
	 */
	public PostojiViseNajmladjihStudenataException() { 
		super("Dogodila se pogreška u radu programa!"); 
	}
	
	/**
	 * Prima poruku o pogrešci i prosljeðuje ju nadklasi
	 * @param message Poruka o pogrešci
	 * 
	 */
	public PostojiViseNajmladjihStudenataException(String message) { 
		super(message); 
	}
	
	/**
	 * Prima poruku o pogrešci i njezin uzrok i prosljeðuje ih nadklasi
	 * @param message Poruka o pogrešci
	 * @param cause Uzrok pogreske
	 * 
	 */
	public PostojiViseNajmladjihStudenataException(String message, Throwable cause) { 
		super(message, cause); 
	}
	
	/**
	 * Prima uzrok pogreške i prosljeðuje ga nadklasi
	 * @param cause uzrok pogreske
	 * 
	 */
	public PostojiViseNajmladjihStudenataException(Throwable cause) { 
		super(cause);
	}
}
