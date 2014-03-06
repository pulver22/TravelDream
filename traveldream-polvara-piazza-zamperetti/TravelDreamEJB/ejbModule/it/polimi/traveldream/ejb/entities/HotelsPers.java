package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.usermanagement.dto.HotelDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelPersDTO;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the HotelsPers database table.
 * 
 */
@Entity
@Table(name="HotelsPers")
@NamedQuery(name="HotelsPers.findAll", query="SELECT h FROM HotelsPers h")
public class HotelsPers implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HotelsPersPK id;

	private boolean acquistato;

	private boolean inGiftList;

	private boolean regalato;

	//bi-directional many-to-one association to PacchettoPersonalizzato
	@ManyToOne
	@JoinColumn(name="Pacchetto")
	private PacchettoPersonalizzato pacchettoPersonalizzato;

	//bi-directional many-to-one association to Hotel
	@ManyToOne
	@JoinColumn(name="IdHotel")
	private Hotel hotel;

	public HotelsPers() {
	}

	public HotelsPers(HotelPersDTO hpDTO){
		this.id = new HotelsPersPK();
		this.hotel=Hotel.fromDTO(hpDTO.getHotel());
		this.id.setIdHotel(hpDTO.getIdHotel());
		this.id.setPacchetto(hpDTO.getIdPacchetto());
		
		this.inGiftList=hpDTO.isInGiftList();
		this.acquistato=hpDTO.isAcquistato();
		this.regalato=hpDTO.isRegalato();
	}
	
	public HotelsPers(HotelDTO hDTO, PacchettoPersonalizzato pPersonalizzato, boolean inGiftList, boolean acquistato, boolean regalato){
		this.hotel=Hotel.fromDTO(hDTO);
		this.id = new HotelsPersPK();
		this.id.setIdHotel(hDTO.getIdHotel());
		this.pacchettoPersonalizzato = pPersonalizzato;
		this.inGiftList=inGiftList;
		this.acquistato=acquistato;
		this.regalato=regalato;
	}
	/**
	 * Ritorna la conversione in DTO di questa HotelPers, compresa l'istanza di Hotel
	 * in essa contenuta, gia' convertita in DTO anch'essa (anche l'id del Pacchetto in cui
	 * e' contenuto)s
	 * */
	public HotelPersDTO toDTO(){
		/*
		HotelPersDTO h = new HotelPersDTO(hotel.toDTO(), inGiftList, acquistato, regalato);
		h.setIdPacchetto(id.getPacchetto());
		*/
		return new HotelPersDTO(id.getPacchetto(),hotel.toDTO(), inGiftList, acquistato, regalato);
	}
	
	public HotelsPersPK getId() {
		return this.id;
	}

	public void setId(HotelsPersPK id) {
		this.id = id;
	}
	
	public boolean getAcquistato() {
		return this.acquistato;
	}

	public void setAcquistato(boolean acquistato) {
		this.acquistato = acquistato;
	}

	public boolean getInGiftList() {
		return this.inGiftList;
	}

	public void setInGiftList(boolean inGiftList) {
		this.inGiftList = inGiftList;
	}

	public boolean getRegalato() {
		return this.regalato;
	}

	public void setRegalato(boolean regalato) {
		this.regalato = regalato;
	}

	public PacchettoPersonalizzato getPacchettoPersonalizzato() {
		return this.pacchettoPersonalizzato;
	}

	public void setPacchettoPersonalizzato(PacchettoPersonalizzato pacchettoPersonalizzato) {
		this.pacchettoPersonalizzato = pacchettoPersonalizzato;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}