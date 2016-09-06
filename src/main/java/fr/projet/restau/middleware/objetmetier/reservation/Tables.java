package fr.projet.restau.middleware.objetmetier.reservation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.rits.cloning.Cloner;
//import javax.persistence.Table;
/**
 * Une table.
 * 
 * @author JoPFullJS JFF
 */
@Entity
@Table(name = "T_Tables")
public class Tables implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	/** Référence unique */
	private String id;
	
	
	/** type */
	private String type;
	
	
	
	@Id
	@Column(name = "T_ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	@Column(name = "T_TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.id)
				.append(this.type)
				.build();
	}

	@Override
	public boolean equals(Object candidat) {
		
		if (candidat == null)
			return false;
		if (candidat == this)
			return true;
		if (!(candidat instanceof Tables))
			return false;
		
		Tables autre = (Tables) candidat;
		
		return new EqualsBuilder()
				.append(this.id, autre.id)
				.append(this.type, autre.type)
				.build();
	}

	@Override
	public Tables clone() {
		
		/*Recompense clone = new Recompense();
		
		clone.annee = this.annee;
		clone.nom = this.nom;
		clone.poids = new BigDecimal(poids.toPlainString());
		
		return clone;*/
		
		return new Cloner().deepClone(this);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id)
				.append("type", this.type)
				.build();
	}
	
}
