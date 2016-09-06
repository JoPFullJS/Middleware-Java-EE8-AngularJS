package fr.projet.restau.middleware.objetmetier.client;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.rits.cloning.Cloner;

/**
 * Une salle
 * 
 * @author JoPFullJS JFF
 */
@Entity
@Table(name = "T_SALLE")
public class Salle implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	/** Numéro unique de l'entrepot */
	private int numero;

	/** Ensemble de clients uniques */
	private Set<Client> clients;
	
	/** Capacité maximale */
	private Integer capaciteMaximale;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SAL_NUMERO")
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@OneToMany
	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	@Column(name = "SAL_CAPACITE_MAX")
	public Integer getCapaciteMaximale() {
		return capaciteMaximale;
	}

	public void setCapaciteMaximale(Integer capaciteMaximale) {
		this.capaciteMaximale = capaciteMaximale;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.capaciteMaximale)
				.append(this.clients)
				.build();
	}

	@Override
	public boolean equals(Object candidat) {
		
		if (candidat == null)
			return false;
		if (candidat == this)
			return true;
		if (!(candidat instanceof Salle))
			return false;
		
		Salle autre = (Salle) candidat;
		
		return new EqualsBuilder()
				.append(this.capaciteMaximale, autre.capaciteMaximale)
				.append(this.clients, autre.clients)
				.build();
	}

	@Override
	public Salle clone() {
		return Cloner.shared().deepClone(this);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("Capacité maximale", this.capaciteMaximale)
				.append("Clients", this.clients)
				.build();
	}
	
}
