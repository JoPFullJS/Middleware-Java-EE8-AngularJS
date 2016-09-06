package fr.projet.restau.middleware.webservices;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.serverError;
import static javax.ws.rs.core.Response.status;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
//import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import fr.projet.restau.middleware.objetmetier.client.Client;
import fr.projet.restau.middleware.objetmetier.reservation.Reservation;
import fr.projet.restau.middleware.objetmetier.reservation.ReservationDejaExistanteException;
import fr.projet.restau.middleware.objetmetier.reservation.ReservationInexistanteException;
import fr.projet.restau.middleware.objetmetier.reservation.ReservationInvalideException;
import fr.projet.restau.middleware.services.IReservationService;



@WebService
@Path("/reservation")
@Transactional
public class ReservationWebService {
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Inject
	private IReservationService reservationService;
	
	@GET
	@Path("/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response obtenirReservation(
			@PathParam("id") final String id
	) {
		Response reponse = null;
		
		try
		{
			/*
			 * Nous déléguons au service, la responsabilité
			 * de connaitre les règles métier d'obtention d'une Reservation.
			 */
			final Reservation reservation = reservationService.obtenirReservation(id);
			reponse = Response.ok(reservation).build();
		}
		catch (final ReservationInexistanteException e)
		{
			reponse = Response.status(NOT_FOUND).build();
		}
		
		return reponse; 
	}
	
	@GET
	@Path("/{nom}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response obtenirReservationsSemblables(
			@PathParam("nom") final String nom
	) throws ReservationInvalideException, ReservationInexistanteException {
		
		Response reponse = null;
		
		try
		{
			/*
			 * Nous déléguons au service, la responsabilité
			 * de connaitre les règles métier d'obtention d'une Reservation.
			 */
			final List<Reservation> reservation = reservationService.obtenirReservationsSemblables(nom);
			reponse = Response.ok(reservation).build();
		}
		catch (final ReservationInvalideException e)
		{
			reponse = Response.status(NOT_FOUND).build();
		}
		catch (final ReservationInexistanteException e)
		{
			reponse = Response.status(NOT_FOUND).build();
		}
		
		return reponse; 
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenirReservations() {
		
		List<Reservation> reservations = reservationService.obtenirReservations();
		
		return Response
				.ok(reservations)
				.build();
	}
	
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerReservation(
			@QueryParam("nomresa") final String nomresa,
			@QueryParam("nbperson") final Integer nbperson,
			@QueryParam("prix") final Integer prix,
			@QueryParam("cliReservation") final String cliReservation
			
			) throws ReservationDejaExistanteException {
		/*
		 * Nous transformons les paramètres d'entrée du WS en un objet métier. 
		 */
		Response reponse=null;
		
		try
		{
			final Reservation reservation = new Reservation();
			reservation.setNomresa(nomresa);
			reservation.setDateresa(new Date());
			reservation.setNbperson(nbperson);
			reservation.setPrix(prix);
			reservation.setCliReservation(cliReservation);
			
			
			final Reservation reservationCree = reservationService.creerReservation(reservation);
			//final String id = reservationCree.getId();
			reponse = Response.ok(reservationCree).build();
		}
		catch (ReservationDejaExistanteException e)
		{
			reponse = Response.status(BAD_REQUEST).build();
		}
		
		return reponse;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierReservations(final Collection<Reservation> reservations)
			throws ReservationInexistanteException, ReservationInvalideException {
		
		Response reponse;
		
		try {
			
			final List<Reservation> reservationsModifiees = reservationService.modifierReservations(reservations);
			
			final GenericEntity<List<Reservation>> entite = new GenericEntity<List<Reservation>>(reservationsModifiees) {};
			
			reponse = ok(entite).build();
			
		} catch (final ReservationInexistanteException e) {
			reponse = status(NOT_FOUND).build();
		} catch (final Exception e) {
			reponse = serverError().build();
		}
		
		return reponse;
	}

	@DELETE
	@Path("/{id}")
	public Response supprimerReservation(
			@PathParam("id") final String id
	) {
		
		Response reponse = null;
		
		try {
			
			// Nous tentons de supprimer la Reservation depuis son id
			reservationService.supprimerReservation(id);
			// Nous répondons un HTTP 200 à notre client.
			reponse = Response.ok().build();
			
		} catch(final ReservationInexistanteException e) {
			
			// Nous répondons un HTTP 404 à notre client.
			reponse = Response.status(Status.NOT_FOUND).build();
			
		}
		
		// Nous retournons la réponse.
		return reponse;
	}

}
