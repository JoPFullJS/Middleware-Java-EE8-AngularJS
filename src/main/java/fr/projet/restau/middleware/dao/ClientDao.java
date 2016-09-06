package fr.projet.restau.middleware.dao;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import fr.projet.restau.middleware.objetmetier.client.Client;
import fr.projet.restau.middleware.objetmetier.client.ClientInexistanteException;

/**
 * Un DAO JPA de gestion des clients.
 * 
 * @author JoPFullJS JFF
 */
@Named
@Transactional
public class ClientDao implements IDao<Client> {

	@PersistenceContext
	private EntityManager em;

	public Client modifier(final Client client) throws ClientInexistanteException {
		try {
			return em.merge(client);
		} catch (final IllegalArgumentException e) {
			throw new ClientInexistanteException();
		}
	}

	public void retirer(final String reference) throws ClientInexistanteException {

		try {

			em.remove(em.getReference(Client.class, reference));
		} catch (final IllegalArgumentException e) {
			throw new ClientInexistanteException();
		}
	}

	/*
	 * public void retirer(final String reference) {
	 * 
	 * em.remove(em.getReference(Client.class, reference)); }
	 */
	public Client obtenir(final String reference) {

		return em.find(Client.class, reference);
	}

	public List<Client> obtenir() {

		final String requeteJpql = "SELECT c FROM Client c";

		final TypedQuery<Client> requeteTypee = em.createQuery(requeteJpql, Client.class);

		return requeteTypee.getResultList();
	}

	public boolean exister(final String reference) {

		boolean resultat = false;

		try {
			em.getReference(Client.class, reference);
			resultat = true;
		} catch (EntityNotFoundException e) {
			resultat = false;
		}

		return resultat;
	}

	@Override
	public Client creer(final Client client) {
		checkArgument(client != null);

		em.persist(client);

		return client;
	}

}
