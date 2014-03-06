package it.polimi.traveldream.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HotelsPers database table.
 * 
 */
@Embeddable
public class HotelsPersPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int pacchetto;

	@Column(insertable=false, updatable=false)
	private int idHotel;

	public HotelsPersPK() {
	}
	public int getPacchetto() {
		return this.pacchetto;
	}
	public void setPacchetto(int pacchetto) {
		this.pacchetto = pacchetto;
	}
	public int getIdHotel() {
		return this.idHotel;
	}
	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HotelsPersPK)) {
			return false;
		}
		HotelsPersPK castOther = (HotelsPersPK)other;
		return 
			(this.pacchetto == castOther.pacchetto)
			&& (this.idHotel == castOther.idHotel);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.pacchetto;
		hash = hash * prime + this.idHotel;
		
		return hash;
	}
}