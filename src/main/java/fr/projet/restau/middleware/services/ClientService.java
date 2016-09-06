package fr.projet.restau.middleware.services;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
//import javax.ws.rs.FormParam;

import org.apache.commons.lang3.RandomStringUtils;

import fr.projet.restau.middleware.dao.ClientDao;
import fr.projet.restau.middleware.objetmetier.client.Client;
import fr.projet.restau.middleware.objetmetier.client.ClientInexistanteException;
import fr.projet.restau.middleware.objetmetier.client.ClientInvalideException;
import fr.projet.restau.middleware.objetmetier.reservation.Reservation;


/**
 * Un service de gestion des caves.
 * 
 * @author JoPFullJS JFF
 */
@Named
@Transactional
public class ClientService implements IClientService {
	
	@Inject
	private ClientDao clientDao;
	

	@Override
	public Client obtenirClient(String reference) {
		return clientDao.obtenir(reference);
	}

	@Override
	public List<Client> obtenirClients() {
		return clientDao.obtenir();
	}

	@Override
	public String creerClient(final Client client) {

		client.setReference(RandomStringUtils.randomAlphanumeric(5));
		
		
		clientDao.creer(client);
		
		return client.getReference();
		
		//return clientDao.creer(client);
	}

	
	

	@Override
	public void supprimerClient(final String id) throws ClientInexistanteException {
		
		// Nous retirons la Reservation à supprimer de l'entrepot.
		clientDao.retirer(id);
	}
	
	@Override
	public Client modifierClient(final Client client)
			throws ClientInexistanteException, ClientInvalideException {

		return clientDao.modifier(client);
	}
	
	
	
	public void ajouterReservation(Reservation reservationAjoutee, Client clientTestee) {
		// TODO Auto-generated method stub
		
	}

	public void retirerReservation(Reservation reservation1) {
		// TODO Auto-generated method stub
		
	}

	public void ajouterReservations(Collection<Reservation> reservations, Client clientTestee) {
		// TODO Auto-generated method stub
		
	}
	
	 
	
}
