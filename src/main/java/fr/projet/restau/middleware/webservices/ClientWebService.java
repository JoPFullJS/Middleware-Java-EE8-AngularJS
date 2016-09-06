package fr.projet.restau.middleware.webservices;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.transaction.Transactional;
//import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

//import org.jboss.logging.FormatWith;

import fr.projet.restau.middleware.objetmetier.client.Client;
import fr.projet.restau.middleware.objetmetier.client.ClientInexistanteException;
import fr.projet.restau.middleware.objetmetier.client.ClientInvalideException;
import fr.projet.restau.middleware.objetmetier.reservation.Reservation;
import fr.projet.restau.middleware.services.IClientService;


@WebService
@Path(value = "/Client")
@Transactional
public class ClientWebService{

	
	@Inject
	private IClientService clientService;
	
	
	////////////////////////
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenirClients() {
		
		final List<Client> clients = clientService.obtenirClients();
		
		return Response
				.ok(clients)
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerClient(
			@QueryParam("nom") final String nom,
			@QueryParam("prenom") final String prenom,		
			@QueryParam("adresse") final String adresse,
			@QueryParam("mail") final String mail
			
	) {
	
		final Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setAdresse(adresse);
		client.setMail(mail);
		
		
		final String referenceCree = clientService.creerClient(client);
		
		return Response
				.ok(referenceCree)
				
				.build();
	}
	
	@DELETE
	@Path("/{reference}")
	public Response supprimerClient(
			@PathParam("reference") final String reference
	) {
		
		Response reponse = null;
		
		try {
			
			// Nous tentons de supprimer la Reservation depuis son id
			clientService.supprimerClient(reference);
			// Nous répondons un HTTP 200 à notre client.
			reponse = Response.ok().build();
			
		} catch(final ClientInexistanteException e) {
			
			// Nous répondons un HTTP 404 à notre client.
			reponse = Response.status(Status.NOT_FOUND).build();
			
		}
		
		// Nous retournons la réponse.
		return reponse;
	}
	
	
	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response modifierClient(
			@QueryParam("reference") final String reference,
			@QueryParam("nom") String nom,
			@QueryParam("prenom") String prenom,		
			@QueryParam("adresse") String adresse,
			@QueryParam("mail") String mail
			
		
	) throws ClientInexistanteException, ClientInvalideException {
		
		final Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setMail(mail);
		client.setAdresse(adresse);
		client.setReference(reference);

		Response reponse = null;
		
		try {
			
			// Nous tentons de modifier notre Reservation.
			final Client clientModifiee = clientService.modifierClient(client);
			
			reponse = ok(clientModifiee).build();
			
		} catch (final ClientInexistanteException e) {
			
			// Nous n'avons pu modifié une entitée inexistante : 404.
			reponse = status(NOT_FOUND).build();
			
		} catch (final ClientInvalideException e) {
			
			// Nous n'avons pu modifié une entitée invalide : 400.
			reponse = status(BAD_REQUEST).build();
			
		}
		
		return reponse;
	}
}
