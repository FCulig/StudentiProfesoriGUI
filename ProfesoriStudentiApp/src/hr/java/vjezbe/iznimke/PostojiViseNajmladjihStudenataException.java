package hr.java.vjezbe.iznimke;

/**
 * Klasa iznimke kada postoji vi�e mla�ih studenata kod dodjeljivanja rektorove nagrade.
 * @author Filip �ulig
 *
 */
public class PostojiViseNajmladjihStudenataException extends RuntimeException{
	/**
	 * ID iznimke PostojiViseNajmladihStudenataException
	 */
	private static final long serialVersionUID = -296381963817458610L;

	/**
	 * Defaultni konstruktor kada ne znamo �to je prouzro�ilo iznimku.
	 */
	public PostojiViseNajmladjihStudenataException() { 
		super("Dogodila se pogre�ka u radu programa!"); 
	}
	
	/**
	 * Prima poruku o pogre�ci i proslje�uje ju nadklasi
	 * @param message Poruka o pogre�ci
	 * 
	 */
	public PostojiViseNajmladjihStudenataException(String message) { 
		super(message); 
	}
	
	/**
	 * Prima poruku o pogre�ci i njezin uzrok i proslje�uje ih nadklasi
	 * @param message Poruka o pogre�ci
	 * @param cause Uzrok pogreske
	 * 
	 */
	public PostojiViseNajmladjihStudenataException(String message, Throwable cause) { 
		super(message, cause); 
	}
	
	/**
	 * Prima uzrok pogre�ke i proslje�uje ga nadklasi
	 * @param cause uzrok pogreske
	 * 
	 */
	public PostojiViseNajmladjihStudenataException(Throwable cause) { 
		super(cause);
	}
}
