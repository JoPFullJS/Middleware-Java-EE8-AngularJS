package fr.projet.restau.middleware.dao;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import fr.projet.restau.middleware.objetmetier.reservation.Reservation;
import fr.projet.restau.middleware.objetmetier.reservation.ReservationDejaExistanteException;
import fr.projet.restau.middleware.objetmetier.reservation.ReservationInexistanteException;

/**
 * Un DAO JPA de gestion des reservations.
 * 
 * @author JoPFullJS JFF
 */
@Named
@Transactional
public class ReservationDao implements IDao<Reservation> {

	// Injecte un manageur d'entité construit
	// depuis le paramétrage de l'unité de persistence par défaut (voir
	// persistence.xml).
	@PersistenceContext
	private EntityManager em;

	public boolean ping() {

		final String requeteSQL = "SELECT 1";

		final Query requete = em.createNamedQuery(requeteSQL);

		return requete.getSingleResult() != null;
	}

	public List<Reservation> obtenirParNom(final String nom) {
		// J'écris ma requête.
		final String requeteJPQL = "SELECT r FROM Reservation r WHERE r.nomresa = :nom";
		// Je crée la représentation java de ma requête
		final TypedQuery<Reservation> requeteType = em.createQuery(requeteJPQL, Reservation.class);
		requeteType.setParameter("nom", nom);
		/*
		 * J'exécute ma requête Elle est envoyée à la BdD La BdD répond Le
		 * résultat m'est fourni sous la forme d'une liste de "truc" Je cast la
		 * liste de "truc" en une liste de bouteilles.
		 */
		return requeteType.getResultList();
	}

	/*
	 * public List<Commande> obtenirParContenance(final Integer contenance) {
	 * 
	 * // J'obtiens un builder. final CriteriaBuilder cb =
	 * em.getCriteriaBuilder();
	 * 
	 * // Je crée une requête Criteria. final CriteriaQuery<Commande>
	 * requeteCriteria = cb.createQuery(Commande.class); // Je choisi la clause
	 * FROM de ma requête. final Root<Commande> root =
	 * requeteCriteria.from(Commande.class); // Je choisi la clause WHERE de ma
	 * requête. requeteCriteria.where(cb.equal(root.get("contenance"),
	 * contenance));
	 * 
	 * // Je crée la représentation de la requête Critéria final
	 * TypedQuery<Commande> requeteType = em.createQuery(requeteCriteria);
	 * 
	 * return requeteType.getResultList(); }
	 */
	public Reservation obtenir(final String id) throws ReservationInexistanteException {
		// Je récupère juste la référence de mon entité.
		// Reservation reservation = em.getReference(Reservation .class, id);
		// Je récupère les informations de mon entité.
		// Reservation reservation2 = em.find(Reservation.class, id);

		final Reservation reservationTrouvee = em.find(Reservation.class, id);

		if (reservationTrouvee == null)
			throw new ReservationInexistanteException();

		return reservationTrouvee;
	}

	public List<Reservation> obtenir() {

		final String requeteJpql = "SELECT r FROM Reservation r";
		TypedQuery<Reservation> requeteTypee = em.createQuery(requeteJpql, Reservation.class);

		return requeteTypee.getResultList();
	}

	public String persister(final Reservation reservation) throws ReservationDejaExistanteException {

		try {
			em.persist(reservation);
			return reservation.getId();
		} catch (final EntityExistsException e) {
			throw new ReservationDejaExistanteException();
		}

	}

	public Reservation modifier(final Reservation reservation) throws ReservationInexistanteException {
		try {
			return em.merge(reservation);
		} catch (final IllegalArgumentException e) {
			throw new ReservationInexistanteException();
		}
	}

	public void retirer(final String id) throws ReservationInexistanteException {

		try {

			em.remove(em.getReference(Reservation.class, id));
		} catch (final IllegalArgumentException e) {
			throw new ReservationInexistanteException();
		}

	}

	public boolean exister(final String id) {

		boolean resultat;

		try {
			em.getReference(Reservation.class, id);
			resultat = true;
		} catch (final EntityNotFoundException e) {
			resultat = false;
		}

		return resultat;
	}

	public List<Reservation> modifier(final Collection<Reservation> reservations)
			throws ReservationInexistanteException {
		checkArgument(reservations != null && !reservations.isEmpty());

		for (Reservation reservation : reservations)
			modifier(reservation);

		return reservations.parallelStream().collect(Collectors.toList());
	}

	@Override
	public Reservation creer(final Reservation reservation) throws ReservationDejaExistanteException {
		checkArgument(reservation != null);

		try {
			em.persist(reservation);
		} catch (final EntityExistsException e) {
			throw new ReservationDejaExistanteException();
		}

		return reservation;
	}

}
