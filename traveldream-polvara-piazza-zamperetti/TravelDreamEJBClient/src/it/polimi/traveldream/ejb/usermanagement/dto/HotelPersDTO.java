package it.polimi.traveldream.ejb.usermanagement.dto;

public class HotelPersDTO {

	private int idPacchetto;
	
	private int idHotel;
	
	private HotelDTO hotel;
	
	private boolean inGiftList;
	
	private boolean acquistato;
	
	private boolean regalato;
	
	public HotelPersDTO(){}
	
	public HotelPersDTO(HotelDTO hotel,
			boolean inGiftList, boolean acquistato, boolean regalato) {
		this.idHotel = hotel.getIdHotel();
		this.hotel = hotel;
		this.inGiftList=inGiftList;
		this.acquistato=acquistato;
		this.regalato=regalato;
	}
	
	public HotelPersDTO(int idPacchetto, HotelDTO hotel,
			boolean inGiftList, boolean acquistato, boolean regalato) {
		this.idPacchetto=idPacchetto;
		this.idHotel = hotel.getIdHotel();
		this.hotel = hotel;
		this.inGiftList=inGiftList;
		this.acquistato=acquistato;
		this.regalato=regalato;
	}
	
	public int getIdPacchetto() {
		return idPacchetto;
	}

	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
		this.idHotel = hotel.getIdHotel();
	}

	public boolean isInGiftList() {
		return inGiftList;
	}

	public void setInGiftList(boolean inGiftList) {
		this.inGiftList = inGiftList;
	}

	public boolean isAcquistato() {
		return acquistato;
	}

	public void setAcquistato(boolean acquistato) {
		this.acquistato = acquistato;
	}

	public boolean isRegalato() {
		return regalato;
	}

	public void setRegalato(boolean regalato) {
		this.regalato = regalato;
	}
	
	
	
	
}
