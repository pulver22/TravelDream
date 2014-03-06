package it.polimi.traveldream.web.beans;


import it.polimi.traveldream.ejb.usermanagement.ImpiegatoMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloDTO;

import java.sql.Time;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name="creaComponenteBean")
@RequestScoped
public class CreaComponenteBean {

	@EJB
	private ImpiegatoMgr impiegatoMgr;
	
	private HotelDTO		currentHotel;
	private EscursioneDTO	currentEscursione;
	private VoloDTO			currentVolo;
	
	public CreaComponenteBean(){
		this.currentHotel		= new HotelDTO();
		this.currentEscursione	= new EscursioneDTO();
		this.currentVolo		= new VoloDTO();
	}

	public String creaHotel(){
		impiegatoMgr.creaComponente(currentHotel);
		return "/home?faces-redirect=true";
	}

	public String creaEscursione(){
		currentEscursione.setDurata(new Time(0l));
		impiegatoMgr.creaComponente(currentEscursione);
		return "/home?faces-redirect=true";
	}

	public String creaVolo(){
		impiegatoMgr.creaComponente(currentVolo);
		return "/home?faces-redirect=true";
	}
	
	public String indietro(){
		return "/home?faces-redirect=true";
	}
	
	public HotelDTO getCurrentHotel() {
		return currentHotel;
	}

	public void setCurrentHotel(HotelDTO currentHotel) {
		this.currentHotel = currentHotel;
	}

	public EscursioneDTO getCurrentEscursione() {
		return currentEscursione;
	}

	public void setCurrentEscursione(EscursioneDTO currentEscursione) {
		this.currentEscursione = currentEscursione;
	}

	public VoloDTO getCurrentVolo() {
		return currentVolo;
	}

	public void setCurrentVolo(VoloDTO currentVolo) {
		this.currentVolo = currentVolo;
	}
}
