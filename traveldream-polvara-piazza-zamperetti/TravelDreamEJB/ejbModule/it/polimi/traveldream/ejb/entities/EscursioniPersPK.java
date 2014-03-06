package it.polimi.traveldream.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EscursioniPers database table.
 * 
 */
@Embeddable
public class EscursioniPersPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int pacchetto;

	@Column(insertable=false, updatable=false)
	private int idEscursione;

	public EscursioniPersPK() {
	}
	public int getPacchetto() {
		return this.pacchetto;
	}
	public void setPacchetto(int pacchetto) {
		this.pacchetto = pacchetto;
	}
	public int getIdEscursione() {
		return this.idEscursione;
	}
	public void setIdEscursione(int idEscursione) {
		this.idEscursione = idEscursione;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EscursioniPersPK)) {
			return false;
		}
		EscursioniPersPK castOther = (EscursioniPersPK)other;
		return 
			(this.pacchetto == castOther.pacchetto)
			&& (this.idEscursione == castOther.idEscursione);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pacchetto;
		hash = hash * prime + this.idEscursione;
		
		return hash;
	}
}