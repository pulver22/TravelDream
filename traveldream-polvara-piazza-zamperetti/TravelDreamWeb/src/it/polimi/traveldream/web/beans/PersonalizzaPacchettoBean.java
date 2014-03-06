package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.usermanagement.ClienteMgr;
import it.polimi.traveldream.ejb.usermanagement.ComponentiEAO;
import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursionePersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelPersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPersonalizzatoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloPersDTO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="personalizzaPacchettoBean")
@SessionScoped
public class PersonalizzaPacchettoBean {

	@EJB
	private ClienteMgr clienteMgr;
	@EJB
	private UserMgr userMgr;
	@EJB
	private ComponentiEAO componentiEAO;
	
	private PacchettoPersonalizzatoDTO currentPacchettoPers;
	
	private List<HotelDTO>		hotels;
	private HotelDTO			hotel;
	
	private List<VoloDTO>		voliAndata;
	private VoloDTO				voloAndata;
	
	private List<VoloDTO>		voliRitorno;
	private VoloDTO				voloRitorno;

	private List<EscursioneDTO>	escursioni;
	private List<EscursioneDTO>	escursioniSelezionate;
	
	private List<EscursionePersDTO> escursioniPers;
	private VoloPersDTO voloAndataPers, voloRitornoPers;
	private HotelPersDTO hotelPers;
	
	public PersonalizzaPacchettoBean(){
		this.hotels = new ArrayList<HotelDTO>();
		this.voliAndata = new ArrayList<VoloDTO>();
		this.voliRitorno = new ArrayList<VoloDTO>();
		this.escursioni = new ArrayList<EscursioneDTO>();
		this.escursioniSelezionate = new ArrayList<EscursioneDTO>();
		
		this.escursioniPers = new ArrayList<EscursionePersDTO>();
		
		this.currentPacchettoPers = new PacchettoPersonalizzatoDTO();
	}
	
	
	public String modificaPacchetto(){
		
		if (voloAndata.getGiorno().after(voloRitorno.getGiorno()) ||
				voloRitorno.getGiorno().before(voloAndata.getGiorno()))
			return "/pacchettoNonConsistente";
		
		for (EscursioneDTO e: escursioniSelezionate){
			if (e.getData().before(voloAndata.getGiorno()) || e.getData().after(voloRitorno.getGiorno()))
				return "/pacchettoNonConsistente";
		}
		
		hotelPers = new HotelPersDTO(currentPacchettoPers.getId(),hotel,false,false,false);
		voloAndataPers = new VoloPersDTO(currentPacchettoPers.getId(),voloAndata,false,false,false);
		voloRitornoPers = new VoloPersDTO(currentPacchettoPers.getId(),voloRitorno,false,false,false);
		for(EscursioneDTO e: escursioniSelezionate){
			escursioniPers.add(new EscursionePersDTO(currentPacchettoPers.getId(),e,false,false,false));
		}
		
		currentPacchettoPers.setHotelPers(hotelPers);
		currentPacchettoPers.setVoloAndataPers(voloAndataPers);
		currentPacchettoPers.setVoloRitornoPers(voloRitornoPers);
		currentPacchettoPers.setEscursioniPers(escursioniPers);
		
		clienteMgr.aggiornaPacchettoPersonalizzato(currentPacchettoPers, userMgr.getUserDTO());
		
		return "/home?faces-redirect=true";
	}
	/*
	public String paginaPersonalizzazione(PacchettoPersonalizzatoDTO ppDTO){		
		hotel = null;
		voloAndata = null;
		voloRitorno = null;
		escursioniSelezionate.clear();
		currentPacchettoPers = ppDTO;
		
		String luogo = ppDTO.getLuogo();
		
		hotels		= componentiEAO.allHotel(luogo);
		voliAndata	= componentiEAO.allVoliAndata(luogo);
		voliRitorno	= componentiEAO.allVoliRitorno(luogo);
		escursioni	= componentiEAO.allEscursioni(luogo);
		
		return "/cliente/paginaPersonalizzazione";
	}
	*/
	public String indietro(){
		return "/cliente/personalizzaPacchetto?faces-redirect=true";
	}
	
	public String initModificaPacchetto(PacchettoPersonalizzatoDTO ppDTO){
		currentPacchettoPers = ppDTO;
		
		String luogo = currentPacchettoPers.getLuogo();
		
		hotels		= componentiEAO.allHotel(luogo);
		voliAndata	= componentiEAO.allVoliAndata(luogo);
		voliRitorno	= componentiEAO.allVoliRitorno(luogo);
		escursioni	= componentiEAO.allEscursioni(luogo);
		
		hotel = currentPacchettoPers.getHotelPers().getHotel();
		voloAndata = currentPacchettoPers.getVoloAndataPers().getVolo();
		voloRitorno = currentPacchettoPers.getVoloRitornoPers().getVolo();
		
		escursioniSelezionate=new ArrayList<EscursioneDTO>();
		for (EscursionePersDTO ep: currentPacchettoPers.getEscursioniPers())
			escursioniSelezionate.add(ep.getEscursione());
		
		return "/cliente/personalizzaPacchetto?faces-redirect=true";
	}


	public PacchettoPersonalizzatoDTO getCurrentPacchettoPers() {
		return currentPacchettoPers;
	}


	public void setCurrentPacchettoPers(
			PacchettoPersonalizzatoDTO currentPacchettoPers) {
		this.currentPacchettoPers = currentPacchettoPers;
	}


	public List<HotelDTO> getHotels() {
		return hotels;
	}


	public void setHotels(List<HotelDTO> hotels) {
		this.hotels = hotels;
	}


	public HotelDTO getHotel() {
		return hotel;
	}


	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}


	public List<VoloDTO> getVoliAndata() {
		return voliAndata;
	}


	public void setVoliAndata(List<VoloDTO> voliAndata) {
		this.voliAndata = voliAndata;
	}


	public VoloDTO getVoloAndata() {
		return voloAndata;
	}


	public void setVoloAndata(VoloDTO voloAndata) {
		this.voloAndata = voloAndata;
	}


	public List<VoloDTO> getVoliRitorno() {
		return voliRitorno;
	}


	public void setVoliRitorno(List<VoloDTO> voliRitorno) {
		this.voliRitorno = voliRitorno;
	}


	public VoloDTO getVoloRitorno() {
		return voloRitorno;
	}


	public void setVoloRitorno(VoloDTO voloRitorno) {
		this.voloRitorno = voloRitorno;
	}


	public List<EscursioneDTO> getEscursioni() {
		return escursioni;
	}


	public void setEscursioni(List<EscursioneDTO> escursioni) {
		this.escursioni = escursioni;
	}


	public List<EscursioneDTO> getEscursioniSelezionate() {
		return escursioniSelezionate;
	}


	public void setEscursioniSelezionate(List<EscursioneDTO> escursioniSelezionate) {
		this.escursioniSelezionate = escursioniSelezionate;
	}


	public List<EscursionePersDTO> getEscursioniPers() {
		return escursioniPers;
	}


	public void setEscursioniPers(List<EscursionePersDTO> escursioniPers) {
		this.escursioniPers = escursioniPers;
	}


	public VoloPersDTO getVoloAndataPers() {
		return voloAndataPers;
	}


	public void setVoloAndataPers(VoloPersDTO voloAndataPers) {
		this.voloAndataPers = voloAndataPers;
	}


	public VoloPersDTO getVoloRitornoPers() {
		return voloRitornoPers;
	}


	public void setVoloRitornoPers(VoloPersDTO voloRitornoPers) {
		this.voloRitornoPers = voloRitornoPers;
	}


	public HotelPersDTO getHotelPers() {
		return hotelPers;
	}


	public void setHotelPers(HotelPersDTO hotelPers) {
		this.hotelPers = hotelPers;
	}
	
	
}
