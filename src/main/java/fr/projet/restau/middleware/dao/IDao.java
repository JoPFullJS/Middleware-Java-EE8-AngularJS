package fr.projet.restau.middleware.dao;

import fr.projet.restau.middleware.objetmetier.reservation.ReservationDejaExistanteException;

public interface IDao<E> {

	// FIXME
	public E creer(E element) throws ReservationDejaExistanteException;
	
}
