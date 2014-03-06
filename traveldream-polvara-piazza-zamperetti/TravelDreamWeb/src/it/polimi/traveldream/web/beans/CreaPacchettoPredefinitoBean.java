package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.usermanagement.ComponentiEAO;
import it.polimi.traveldream.ejb.usermanagement.ImpiegatoMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="creaPacchettoPredefintoBean")
@SessionScoped
public class CreaPacchettoPredefinitoBean implements Serializable{
	
	private static final long serialVersionUID = -8029596538203551673L;
	
	@EJB
	private ImpiegatoMgr impiegatoMgr;
	@EJB
	private ComponentiEAO componentiEAO;
	
	private PacchettoPredefinitoDTO currentPacchettoPred;
	
	private List<HotelDTO>		hotels;
	private HotelDTO			hotel;
	
	private List<VoloDTO>		voliAndata;
	private VoloDTO				voloAndata;
	
	private List<VoloDTO>		voliRitorno;
	private VoloDTO				voloRitorno;

	private List<EscursioneDTO>	escursioni;
	private List<EscursioneDTO>	escursioniSelezionate;
	
	public CreaPacchettoPredefinitoBean(){
		this.hotels = new ArrayList<HotelDTO>();
		this.voliAndata = new ArrayList<VoloDTO>();
		this.voliRitorno = new ArrayList<VoloDTO>();
		this.escursioni = new ArrayList<EscursioneDTO>();
		this.escursioniSelezionate = new ArrayList<EscursioneDTO>();
		
		this.currentPacchettoPred = new PacchettoPredefinitoDTO();
	}
	
	public String creaPacchetto(){	
		
		if (voloAndata.getGiorno().after(voloRitorno.getGiorno()) ||
				voloRitorno.getGiorno().before(voloAndata.getGiorno()))
			return "/pacchettoNonConsistente";
		
		for (EscursioneDTO e: escursioniSelezionate){
			if (e.getData().before(voloAndata.getGiorno()) || e.getData().after(voloRitorno.getGiorno()))
				return "/pacchettoNonConsistente";
		}
		
		currentPacchettoPred.setHotel(hotel);
		currentPacchettoPred.setVoloAndata(voloAndata);
		currentPacchettoPred.setVoloRitorno(voloRitorno);
		currentPacchettoPred.setEscursioni(escursioniSelezionate);
		
		impiegatoMgr.creaPacchettoPredefinito(currentPacchettoPred);
		
		return "/home";
	}
	
	public String modificaPacchetto(){
		
		if (voloAndata.getGiorno().after(voloRitorno.getGiorno()) ||
				voloRitorno.getGiorno().before(voloAndata.getGiorno()))
			return "/pacchettoNonConsistente";
		
		for (EscursioneDTO e: escursioniSelezionate){
			if (e.getData().before(voloAndata.getGiorno()) || e.getData().after(voloRitorno.getGiorno()))
				return "/pacchettoNonConsistente";
		}
		
		currentPacchettoPred.setHotel(hotel);
		currentPacchettoPred.setVoloAndata(voloAndata);
		currentPacchettoPred.setVoloRitorno(voloRitorno);
		currentPacchettoPred.setEscursioni(escursioniSelezionate);
		
		impiegatoMgr.aggiornaPacchettoPredefinito(currentPacchettoPred);
		
		return "/home";
	}
	
	public String avanti(){		
		hotel = null;
		voloAndata = null;
		voloRitorno = null;
		escursioniSelezionate.clear();
		
		String luogo = currentPacchettoPred.getLuogo();
		
		hotels		= componentiEAO.allHotel(luogo);
		voliAndata	= componentiEAO.allVoliAndata(luogo);
		voliRitorno	= componentiEAO.allVoliRitorno(luogo);
		escursioni	= componentiEAO.allEscursioni(luogo);
		
		if(hotels.size() > 0 && voliAndata.size() > 0 && voliRitorno.size() > 0){
			hotel = hotels.get(0);
			voloAndata = voliAndata.get(0);
			voloRitorno = voliRitorno.get(0);
		} else return "/pacchettoIncompleto";
		
		return "/impiegato/creaPacchettoPredefinito?faces-redirect=true&luogo=true";
	}
	public String indietro(){
		return "/impiegato/creaPacchettoPredefinito?faces-redirect=true&luogo=false";
	}
	
	public String initModificaPacchetto(PacchettoPredefinitoDTO pacchettoPredefinitoDTO){
		currentPacchettoPred = pacchettoPredefinitoDTO;
		
		String luogo = currentPacchettoPred.getLuogo();
		
		hotels		= componentiEAO.allHotel(luogo);
		voliAndata	= componentiEAO.allVoliAndata(luogo);
		voliRitorno	= componentiEAO.allVoliRitorno(luogo);
		escursioni	= componentiEAO.allEscursioni(luogo);
		
		hotel = currentPacchettoPred.getHotel();
		voloAndata = currentPacchettoPred.getVoloAndata();
		voloRitorno = currentPacchettoPred.getVoloRitorno();
		escursioniSelezionate = currentPacchettoPred.getEscursioni();
		
		return "/impiegato/modificaPacchettoPredefinito";
	}
	
	//** getters and setters   **//	
	public PacchettoPredefinitoDTO getCurrentPacchettoPred() {
		return currentPacchettoPred;
	}

	public void setCurrentPacchettoPred(PacchettoPredefinitoDTO currentPacchettoPred) {
		this.currentPacchettoPred = currentPacchettoPred;
	}

	public List<HotelDTO> getHotels() {
		return hotels;
	}

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public VoloDTO getVoloAndata() {
		return voloAndata;
	}

	public void setVoloAndata(VoloDTO voloAndata) {
		this.voloAndata = voloAndata;
	}

	public VoloDTO getVoloRitorno() {
		return voloRitorno;
	}

	public void setVoloRitorno(VoloDTO voloRitorno) {
		this.voloRitorno = voloRitorno;
	}

	public List<VoloDTO> getVoliAndata() {
		return voliAndata;
	}

	public List<VoloDTO> getVoliRitorno() {
		return voliRitorno;
	}

	public List<EscursioneDTO> getEscursioni() {
		return escursioni;
	}

	public List<EscursioneDTO> getEscursioniSelezionate() {
		return escursioniSelezionate;
	}

	public void setEscursioniSelezionate(List<EscursioneDTO> escursioniSelezionate) {
		this.escursioniSelezionate = escursioniSelezionate;
	}
}
