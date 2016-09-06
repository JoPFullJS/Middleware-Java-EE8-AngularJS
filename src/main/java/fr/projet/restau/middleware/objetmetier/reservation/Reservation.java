package fr.projet.restau.middleware.objetmetier.reservation;

//import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
//import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
// javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.rits.cloning.Cloner;

import fr.projet.restau.middleware.objetmetier.client.Client;




/**
 * Une reservation
 * 
 * @author jopFred
 */
@Entity
@Table(name = "T_Reservation")
//@XmlRootElement
public class Reservation implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	/** Référence unique */
	private String id;

	/** nom */
	private String nomresa;


	/** Date */
	private Date dateresa;

	/** NB DE PERSONNES */
	private Integer nbperson;
	

	/** prix */
	private Integer prix;
	
	/** la reference de client qui à reservé */
	
	private String cliReservation;
	
	/**
	 * private Client client;
	 * 
	 */
	
	


	/**
	 * 
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Reservation(Date dateresa, Client client) {
		super();
		this.dateresa = dateresa;
		this.client = client;
	}



	public Reservation(String id, String nomresa, Date dateresa, Integer nbperson, Integer prix, Client client) {
		super();
		this.id = id;
		this.nomresa = nomresa;
		this.dateresa = dateresa;
		this.nbperson = nbperson;
		this.prix = prix;
		this.client = client;
	}
	*/

	@Id
	@Column(name = "COM_ID")
	@XmlElement(name = "idUnique")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "COM_NOMRESA")
	public String getNomresa() {
		return nomresa;
	}

	public void setNomresa(String nomresa) {
		this.nomresa = nomresa;
	}
	
	
	@Column(name = "COM_DATERESA")
	@Temporal(TemporalType.DATE)
	public Date getDateresa() {
		return dateresa;
	}

	public void setDateresa(Date dateresa) {
		this.dateresa = dateresa;
	}
	
	@Column(name = "COM_PRIX")
	public Integer getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	@Column(name = "COM_NBPERSON")
	public Integer getNbperson() {
		return nbperson;
	}

	public void setNbperson(Integer nbperson) {
		this.nbperson = nbperson;
	}
	
	@Column(name="CLIENT_CLI_RESERVATION")
	public String getCliReservation() {
		return cliReservation;
	}

	public void setCliReservation(String cliReservation) {
		this.cliReservation = cliReservation;
	}
	
	/*
	@JoinTable(name = "t_client_t_revervation", joinColumns = {
			@JoinColumn(name = "CLI_REFERENCE") },
			inverseJoinColumns = { @JoinColumn(name = "COM_ID") })
			*/
	/**
	 * 
	 * 
	 * 
	@ManyToOne(fetch = FetchType.EAGER)
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	*/

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.append(nomresa)
				.append(dateresa)
				.append(nbperson)
				.append(prix)
				.build();
	}

	@Override
	public boolean equals(Object candidat) {

		if (candidat == null)
			return false;

		if (this == candidat)
			return true;

		if (!(candidat instanceof Reservation))
			return false;

		Reservation autre = (Reservation) candidat;

		return new EqualsBuilder()
				.append(this.id, autre.id)
				.append(this.nomresa, autre.nomresa)

				.append(this.dateresa, autre.dateresa)
				
				.append(this.nbperson, autre.nbperson)
				.append(this.prix, autre.prix)
			
				.build();
	}

	@Override
	public Reservation clone() {
		return Cloner.shared().deepClone(this);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("Id", id)
				.append("nom", nomresa)
				.append("date", dateresa)
				.append("nbperson", nbperson)
				.append("prix", prix)
				.build();
	}

	

	
}
