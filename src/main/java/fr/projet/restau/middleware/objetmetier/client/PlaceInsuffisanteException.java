package fr.projet.restau.middleware.objetmetier.client;

/**
 * Exception levée en cas de place insuffisante dans une cave.
 * 
 * @author JoPFullJS JFF
 */
public class PlaceInsuffisanteException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlaceInsuffisanteException(String message) {
		super(message);
	}

	public PlaceInsuffisanteException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
