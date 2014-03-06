package it.polimi.traveldream.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the VoliPers database table.
 * 
 */
@Embeddable
public class VoliPersPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int pacchetto;

	@Column(insertable=false, updatable=false)
	private int idVolo;

	public VoliPersPK() {
	}
	public int getPacchetto() {
		return this.pacchetto;
	}
	public void setPacchetto(int pacchetto) {
		this.pacchetto = pacchetto;
	}
	public int getIdVolo() {
		return this.idVolo;
	}
	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof VoliPersPK)) {
			return false;
		}
		VoliPersPK castOther = (VoliPersPK)other;
		return 
			(this.pacchetto == castOther.pacchetto)
			&& (this.idVolo == castOther.idVolo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pacchetto;
		hash = hash * prime + this.idVolo;
		
		return hash;
	}
}