package fr.projet.restau.middleware.services;

import java.util.List;

import fr.projet.restau.middleware.objetmetier.client.Client;
import fr.projet.restau.middleware.objetmetier.client.ClientInexistanteException;
import fr.projet.restau.middleware.objetmetier.client.ClientInvalideException;



public interface IClientService {

	public Client obtenirClient(final String reference);
	
	public List<Client> obtenirClients();
	
	public String creerClient(final Client client);
	
	public void supprimerClient(final String id)
			throws ClientInexistanteException;
	
	public Client modifierClient(final Client client)
			throws ClientInexistanteException, ClientInvalideException;
}