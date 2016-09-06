package fr.projet.restau.middleware.services;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import fr.projet.restau.middleware.objetmetier.contexte.ContexteConsommation;
import fr.projet.restau.middleware.objetmetier.contexte.ContexteConsommationInvalideException;
import fr.projet.restau.middleware.objetmetier.fournisseur.Fournisseur;
import fr.projet.restau.middleware.objetmetier.reservation.Reservation;
import fr.projet.restau.middleware.objetmetier.reservation.ReservationDejaExistanteException;
import fr.projet.restau.middleware.objetmetier.reservation.ReservationInexistanteException;
import fr.projet.restau.middleware.objetmetier.reservation.ReservationInvalideException;

/**
 * Un contrat d'un service de gestion des Reservations.
 * 
 * @author JoPFullJS JFF
 */
public interface IReservationService {

	public Reservation obtenirReservation(final String id)
		throws ReservationInexistanteException;
	
	
	public List<Reservation> obtenirReservations();
	
	public Reservation creerReservation(Reservation reservation) throws ReservationDejaExistanteException;
	
	public void supprimerReservation(final String id)
		throws ReservationInexistanteException;
	
	public Reservation modifierReservation(final Reservation reservation)
		throws ReservationInexistanteException, ReservationInvalideException;
	
	public List<Reservation> modifierReservations(final Collection<Reservation> reservations)
			throws ReservationInexistanteException;
	
	/**
	 * Obtenir les reservations du catalogue selon une condition discriminante.
	 * 
	 * @param condition Une condition discriminante.
	 * @return Les reservations du catalogue répondant à la condition discriminante.
	 * 
	 * @throws IllegalArgumentException Si la condition fournie est invalide.
	 */
	
	public List<Reservation> obtenirReservations(final Predicate<Reservation> condition)
			throws IllegalArgumentException;
	
	
   
	/**
	 * Vérifier l'existence d'au moins une 	reservation répondant à la condition discriminante.
	 * 
	 * @param condition Une condition discriminante.
	 * @return Vrai si il existe au moins une 	reservation répondant à la condition discriminante.
	 * 
	 * @throws IllegalArgumentException Si la condition fournie est invalide.
	 */
	public boolean exister(final Predicate<Reservation> condition)
			throws IllegalArgumentException;
	 
	/**
	 * Selectionner de une à n reservations adaptées à un {@link ContexteConsommation contexte de consommation}.
	 * 
	 * @param contexte Un contexte de consommation.
	 * @return Les reservations adaptées au contexte de consommation.
	 * 
	 * @throws IllegalArgumentException Si l'un des paramètres fournis n'est pas valide.
	 * @throws ContexteConsommationInvalideException Si le contexte de consommation fourni est invalide.
	 */
	public List<Reservation> selectionnerReservations(final ContexteConsommation contexte)
			throws IllegalArgumentException, ContexteConsommationInvalideException;

	/**
	 * Obtenir les reservations considérées comme semblable à une reservation candidate.
	 *  
	 * @param reservation Une reservation candidate.
	 * @return Les reservations considérées comme semblable à la reservation candidate.
	 * 
	 * @throws ReservationInvalideException Si la reservation fournie est invalide.
	 * @throws ReservationInexistanteException Si la reservation fournie n'existe pas.
	 */
	public List<Reservation> obtenirReservationsSemblables(final String nom)
			throws ReservationInvalideException, ReservationInexistanteException;



	
	/**
	 * Obtenir les fournisseurs vendant une reservation candidate.
	 * 
	 * @param reservation  Une reservation  candidate.
	 * @return Les fournisseurs vendant la reservation  candidate.
	 * @throws ReservationInexistanteException Si la reservation  candidate n'existe pas.
	 * @throws ReservationInvalideException Si la reservation  candidate n'est pas valide.
	 */
	/*
	public List<Fournisseur> obtenirVendeurs(final Reservation reservation)
			throws ReservationInvalideException, ReservationInexistanteException;
	*/

	

}
