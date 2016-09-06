package fr.projet.restau.middleware.objetmetier.client;

import java.io.Serializable;

//import java.io.Serializable;

import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
//import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
//import javax.ws.rs.FormParam;
//import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.rits.cloning.Cloner;

import fr.projet.restau.middleware.objetmetier.reservation.Reservation;

/**
 * Une client.
 * 
 * @author JoPFullJS JFF
 */

@Entity
@Table(name = "T_CLIENT")
public class Client implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
/*
 * private
 */
	

	/** Référence unique */
	
	private String reference;

	/** Nom  */
	
	private String nom;
	
	/** prenom */
	
	private String prenom;
	
	/** adresse */
	
	
	private String adresse;
	
	/** mail */
	
	private String mail;
	
	/** 
	 * 
	 * Dictionnaire des ReservationS 
	 * 
	 * private List<Reservation> reservations;
	 * 
	 */
	
	
	/*
	 * fin private
	 */
	
	/**
	 * 
	 * 
	 * 
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Client(String reference, List<Reservation> reservations) {
		super();
		this.reference = reference;
		this.reservations = reservations;
	}
	

	public Client(String reference, String nom, String prenom, String adresse, String mail,
			List<Reservation> reservations) {
		super();
		this.reference = reference;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.mail = mail;
		this.reservations = reservations;
	}
	*/
	

	@Id
	@Column(name = "CLI_REFERENCE")
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Column(name = "CLI_NOM")
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "CLI_PRENOM")
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	@Column(name = "CLI_MAIL")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	@Column(name = "CLI_adresse")
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * 
	 *  list des reservations : Probleme jointure commit Hibernate
	 * 
	@OneToMany(fetch = FetchType.EAGER)
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	*/

	

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				//.append(reservations)
				.append(nom)
				.append(prenom)
				.append(adresse)
				.append(mail)
				.append(reference)
				.build();
	}

	

	
	@Override
	public boolean equals(Object candidat) {
		
		if (candidat == null)
			return false;
		if (candidat == this)
			return true;
		if (!(candidat instanceof Client))
			return false;
		
		Client autre = (Client) candidat;
		
		return new EqualsBuilder()
				//.append(this.reservations, autre.reservations)
				.append(this.nom, autre.nom)
				.append(this.prenom, autre.prenom)
				.append(this.adresse, autre.adresse)
				.append(this.mail, autre.mail)
				.append(this.reference, autre.reference)
				.build();
	}

	@Override
	public Client clone() {
		return Cloner.shared().deepClone(this);
	}
 
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("Référence", this.reference)
				//.append("reservations", this.reservations)
				.append("nom", nom)
				.append("prenom", prenom)
				.append("adresse", adresse)
				.append("mail", mail)
				.build();
	}

	public void setCapaciteMaximale(int size) {
		// TODO Auto-generated method stub
		
	}
	
}
