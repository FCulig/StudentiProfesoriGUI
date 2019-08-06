package hr.java.vjezbe.iznimke;

public class BazaPodatakException extends Exception{

	/**
	 * Generirani ID iznimke
	 */
	private static final long serialVersionUID = 6765386089692576620L;
	
	public BazaPodatakException() { 
		super("Dogodila se pogreška u radu programa!"); 
	}
	
	public BazaPodatakException(String message) { 
		super(message); 
	}
	public BazaPodatakException(String message, Throwable cause) { 
		super(message, cause); 
	}
	
	public BazaPodatakException(Throwable cause) { 
		super(cause);
	}
}
