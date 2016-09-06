package fr.projet.restau.middleware.services;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;

import fr.projet.restau.middleware.dao.ReservationDao;
import fr.projet.restau.middleware.objetmetier.contexte.ContexteConsommation;
import fr.projet.restau.middleware.objetmetier.contexte.ContexteConsommationInvalideException;
import fr.projet.restau.middleware.objetmetier.fournisseur.Fournisseur;
import fr.projet.restau.middleware.objetmetier.reservation.Reservation;
import fr.projet.restau.middleware.objetmetier.reservation.ReservationDejaExistanteException;
import fr.projet.restau.middleware.objetmetier.reservation.ReservationInexistanteException;
import fr.projet.restau.middleware.objetmetier.reservation.ReservationInvalideException;

@Named
@Transactional
public class ReservationService implements IReservationService {

	@Inject
	private ReservationDao reservationDao;

	// private ReservationService reservationService;

	@Override
	public Reservation obtenirReservation(final String id) throws ReservationInexistanteException {

		/*
		 * Nous pouvons implémenter des règles métier ici.
		 */
		// clientService.estPresente(reference);

		/*
		 * Nous déléguons la responsabilité de récupération des informations
		 * d'une bouteille en base au DAO.
		 */
		return reservationDao.obtenir(id);
	}

	@Override
	public List<Reservation> obtenirReservations() {

		return reservationDao.obtenir();
	}

	@Override
	public void supprimerReservation(final String id) throws ReservationInexistanteException {

		// Nous retirons la Reservation à supprimer de l'entrepot.
		reservationDao.retirer(id);
	}

	@Override
	public Reservation modifierReservation(final Reservation reservation)
			throws ReservationInexistanteException, ReservationInvalideException {

		return reservationDao.modifier(reservation);
	}

	@Override
	public boolean exister(final Predicate<Reservation> condition) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reservation> selectionnerReservations(final ContexteConsommation contexte)
			throws IllegalArgumentException, ContexteConsommationInvalideException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> obtenirReservationsSemblables(final String nom)
			throws ReservationInvalideException, ReservationInexistanteException {
		// TODO Auto-generated method stub
		return reservationDao.obtenirParNom(nom);
	}
	/**
	 * contrat qui permet d'obtenir les founissur de chaque entrepôts
	@Override
	public List<Fournisseur> obtenirVendeurs(final Reservation reservation)
			throws ReservationInvalideException, ReservationInexistanteException {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@Override
	public List<Reservation> obtenirReservations(Predicate<Reservation> condition) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> modifierReservations(Collection<Reservation> reservations)
			throws ReservationInexistanteException {

		return reservationDao.modifier(reservations);
	}

	/*
	 * public List<Reservation> ajouterReservations (List<Reservation>
	 * reservations, Reservation nomresa, Reservation prix, Reservation
	 * dateresa, Reservation nbperson, Reservation id ) throws
	 * ReservationInexistanteException{ //List<Bouteille> bouteilles = new
	 * ArrayList<Bouteille>();
	 * 
	 * return reservationDao.ajouter(reservations, nomresa, prix, dateresa,
	 * nbperson, id); }
	 */

	public Reservation creerReservation(Reservation reservation) throws ReservationDejaExistanteException {
		
		// Règle métier, nous générons une référence unique à partir d'un algo stupide.
		reservation.setId(RandomStringUtils.randomAlphabetic(10));
		
		return reservationDao.creer(reservation);
	}

	
	
}
